import { useEffect, useMemo, useState } from 'react'
import type { FormEvent } from 'react'
import './App.css'

type RecordValue = Record<string, unknown>
type ResourceKey = 'federations' | 'disciplines' | 'championships' | 'seasons' | 'competition-classes' | 'rounds' | 'sessions'

type Field = {
  key: string
  label: string
  type?: 'text' | 'number' | 'date' | 'datetime-local' | 'select'
  required?: boolean
  options?: () => { value: string; label: string }[]
}

type Resource = {
  key: ResourceKey
  label: string
  fields: Field[]
}

type ImportSummary = {
  importedRows: number
  createdDrivers: number
  createdClubs: number
  createdVehicles: number
  createdEntries: number
  updatedSessionResults: number
}

type ImportError = { rowNumber: number; subject: string; message: string }
type DriverImportSummary = { importedRows: number; createdDrivers: number; existingDrivers: number }

function App() {
  const [records, setRecords] = useState<Record<ResourceKey, RecordValue[]>>({
    federations: [], disciplines: [], championships: [], seasons: [], 'competition-classes': [], rounds: [], sessions: [],
  })
  const [activeResource, setActiveResource] = useState<ResourceKey>('federations')
  const [editing, setEditing] = useState<RecordValue | null>(null)
  const [formValues, setFormValues] = useState<Record<string, string>>({})
  const [loading, setLoading] = useState(true)
  const [message, setMessage] = useState<string | null>(null)
  const [error, setError] = useState<string | null>(null)
  const [importSessionId, setImportSessionId] = useState('')
  const [secondImportSessionId, setSecondImportSessionId] = useState('')
  const [resultImportMode, setResultImportMode] = useState<'single' | 'two-runs'>('single')
  const [importFile, setImportFile] = useState<File | null>(null)
  const [importSummary, setImportSummary] = useState<ImportSummary | null>(null)
  const [importErrors, setImportErrors] = useState<ImportError[]>([])
  const [driverImportFile, setDriverImportFile] = useState<File | null>(null)
  const [driverImportSummary, setDriverImportSummary] = useState<DriverImportSummary | null>(null)
  const [driverPdf, setDriverPdf] = useState<File | null>(null)
  const [resultsPdf, setResultsPdf] = useState<File | null>(null)
  const [converting, setConverting] = useState<'drivers' | 'results' | null>(null)

  const resources = useMemo<Resource[]>(() => [
    { key: 'federations', label: 'Federations', fields: [text('name', 'Name', true), text('countryCode', 'Country code')] },
    { key: 'disciplines', label: 'Disciplines', fields: [text('code', 'Code', true), text('name', 'Name', true)] },
    {
      key: 'championships', label: 'Championships', fields: [
        select('federationId', 'Federation', () => options(records.federations)),
        select('disciplineId', 'Discipline', () => options(records.disciplines)),
        text('name', 'Name', true), text('shortName', 'Short name'),
      ],
    },
    {
      key: 'seasons', label: 'Seasons', fields: [
        select('championshipId', 'Championship', () => options(records.championships)),
        number('year', 'Year', true), text('name', 'Name'),
      ],
    },
    {
      key: 'competition-classes', label: 'Classes', fields: [
        text('code', 'Code', true),
      ],
    },
    {
      key: 'rounds', label: 'Rounds', fields: [
        select('seasonId', 'Season', () => options(records.seasons)),
        select('hostFederationId', 'Guest federation (international round only)', () => options(records.federations), false),
        number('roundNumber', 'Round number', true), text('name', 'Name', true),
        { key: 'startsOn', label: 'Starts on', type: 'date' }, { key: 'endsOn', label: 'Ends on', type: 'date' },
        { key: 'status', label: 'Status', type: 'select', required: true, options: () => enumOptions(['SCHEDULED', 'COMPLETED', 'CANCELLED']) },
      ],
    },
    {
      key: 'sessions', label: 'Sessions', fields: [
        select('roundId', 'Round', () => options(records.rounds)), text('name', 'Name', true),
        { key: 'type', label: 'Type', type: 'select', required: true, options: () => enumOptions(['PRACTICE', 'WARM_UP', 'QUALIFYING', 'RACE', 'TIMED_RUN']) },
        number('sessionOrder', 'Order', true), { key: 'scheduledAt', label: 'Scheduled at', type: 'datetime-local' },
      ],
    },
  ], [records])

  const resource = resources.find((item) => item.key === activeResource)!

  useEffect(() => { void loadRecords() }, [])

  async function loadRecords() {
    setLoading(true)
    setError(null)
    try {
      await fetch('/csrf', { credentials: 'include' })
      const loaded = await Promise.all(resourcesForLoading.map(async (key) => [key, await api<RecordValue[]>(`/${key}`)] as const))
      setRecords(Object.fromEntries(loaded) as Record<ResourceKey, RecordValue[]>)
    } catch (requestError) {
      setError(messageFor(requestError))
    } finally {
      setLoading(false)
    }
  }

  function beginCreate() {
    setEditing(null)
    setFormValues(defaultValues(resource))
    setMessage(null)
  }

  function beginEdit(value: RecordValue) {
    setEditing(value)
    setFormValues(Object.fromEntries(resource.fields.map((field) => [field.key, toInputValue(value[field.key])])) )
    setMessage(null)
  }

  function changeResource(key: ResourceKey) {
    setActiveResource(key)
    setEditing(null)
    setFormValues({})
    setMessage(null)
  }

  async function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setError(null)
    try {
      const payload = Object.fromEntries(resource.fields.map((field) => [field.key, valueForApi(field, formValues[field.key])]))
      const path = editing ? `/${resource.key}/${editing.id}` : `/${resource.key}`
      await api(path, { method: editing ? 'PUT' : 'POST', body: JSON.stringify(payload) })
      setMessage(editing ? `${resource.label.slice(0, -1)} updated.` : `${resource.label.slice(0, -1)} created.`)
      setEditing(null)
      setFormValues({})
      await loadRecords()
    } catch (requestError) {
      setError(messageFor(requestError))
    }
  }

  async function remove(value: RecordValue) {
    if (!window.confirm(`Delete ${resource.label.slice(0, -1).toLowerCase()} “${labelFor(value)}”?`)) return
    setError(null)
    try {
      await api(`/${resource.key}/${value.id}`, { method: 'DELETE' })
      setMessage(`${resource.label.slice(0, -1)} deleted.`)
      await loadRecords()
    } catch (requestError) {
      setError(messageFor(requestError))
    }
  }

  async function importResults(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    const form = event.currentTarget
    if (!importSessionId || !importFile) return
    setError(null)
    setImportSummary(null)
    setImportErrors([])
    try {
      const formData = new FormData()
      formData.append('file', importFile)
      if (resultImportMode === 'two-runs') formData.append('secondSessionId', secondImportSessionId)
      const path = resultImportMode === 'two-runs'
        ? `/sessions/results/import-two-runs?firstSessionId=${importSessionId}`
        : `/sessions/${importSessionId}/results/import`
      const summary = await api<ImportSummary>(path, { method: 'POST', body: formData })
      setImportSummary(summary)
      setImportFile(null)
      form.reset()
      await loadRecords()
    } catch (requestError) {
      if (requestError instanceof ApiError && requestError.importErrors.length > 0) {
        setImportErrors(requestError.importErrors)
      } else {
        setError(messageFor(requestError))
      }
    }
  }

  async function importDrivers(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    const form = event.currentTarget
    if (!driverImportFile) return
    setError(null)
    setDriverImportSummary(null)
    setImportErrors([])
    try {
      const formData = new FormData()
      formData.append('file', driverImportFile)
      const summary = await api<DriverImportSummary>('/drivers/import', { method: 'POST', body: formData })
      setDriverImportSummary(summary)
      setDriverImportFile(null)
      form.reset()
    } catch (requestError) {
      if (requestError instanceof ApiError && requestError.importErrors.length > 0) {
        setImportErrors(requestError.importErrors)
      } else {
        setError(messageFor(requestError))
      }
    }
  }

  async function convertPdf(kind: 'drivers' | 'results') {
    const file = kind === 'drivers' ? driverPdf : resultsPdf
    if (!file) return
    setError(null)
    setConverting(kind)
    try {
      const formData = new FormData()
      formData.append('file', file)
      await downloadCsv(`/pdf-conversions/${kind === 'drivers' ? 'drivers' : 'session-results'}`, formData,
        kind === 'drivers' ? 'drivers-converted.csv' : 'session-results-converted.csv')
    } catch (requestError) {
      setError(messageFor(requestError))
    } finally {
      setConverting(null)
    }
  }

  return (
    <main className="app-shell">
      <header className="topbar">
        <div><p className="eyebrow">Racing Archive</p><h1>Administration</h1></div>
        <a className="login-link" href="/login">Sign in</a>
      </header>

      <section className="intro">
        <p>Set up the championship structure before importing session results. This area is available only to the administrator.</p>
      </section>

      <section className="import-panel">
        <div className="import-copy"><p className="eyebrow">Session results</p><h2>Import a CSV file</h2><p>Convert an official results PDF first, review the CSV, then import it into an existing session.</p></div>
        <form className="import-form" onSubmit={importResults}>
          <select aria-label="Result import mode" value={resultImportMode} onChange={(event) => setResultImportMode(event.target.value as 'single' | 'two-runs')}><option value="single">One session</option><option value="two-runs">Two timed runs</option></select>
          <select aria-label="Session" required value={importSessionId} onChange={(event) => setImportSessionId(event.target.value)}><option value="">Select a session…</option>{options(records.sessions).map((option) => <option key={option.value} value={option.value}>{option.label}</option>)}</select>
          {resultImportMode === 'two-runs' && <select aria-label="Second timed-run session" required value={secondImportSessionId} onChange={(event) => setSecondImportSessionId(event.target.value)}><option value="">Select timed run 2…</option>{options(records.sessions).map((option) => <option key={option.value} value={option.value}>{option.label}</option>)}</select>}
          <input aria-label="CSV file" type="file" accept=".csv,text/csv" required onChange={(event) => setImportFile(event.target.files?.[0] ?? null)} />
          <button className="primary" type="submit" disabled={!importSessionId || !importFile || (resultImportMode === 'two-runs' && !secondImportSessionId)}>Import results</button>
          <a className="template-link" href="/admin/import-templates/session-results">Download template</a>
        </form>
        <div className="pdf-converter"><label>Official results PDF<input aria-label="Official results PDF" type="file" accept="application/pdf,.pdf" onChange={(event) => setResultsPdf(event.target.files?.[0] ?? null)} /></label><button type="button" onClick={() => void convertPdf('results')} disabled={!resultsPdf || converting !== null}>{converting === 'results' ? 'Converting…' : 'Convert PDF to CSV'}</button><p>The converter uses the overall classification only. Review the downloaded CSV before importing.</p></div>
        {importSummary && <p className="import-summary">Imported {importSummary.importedRows} rows · new: {importSummary.createdDrivers} drivers, {importSummary.createdClubs} clubs, {importSummary.createdVehicles} vehicles, {importSummary.createdEntries} entries · updated results: {importSummary.updatedSessionResults}</p>}
      </section>

      <section className="import-panel driver-import-panel">
        <div className="import-copy"><p className="eyebrow">Driver directory</p><h2>Import drivers</h2><p>Convert a registration-list PDF or use the template, then review names and nationalities before importing.</p></div>
        <form className="import-form driver-import-form" onSubmit={importDrivers}>
          <input aria-label="Drivers CSV file" type="file" accept=".csv,text/csv" required onChange={(event) => setDriverImportFile(event.target.files?.[0] ?? null)} />
          <button className="primary" type="submit" disabled={!driverImportFile}>Import drivers</button>
          <a className="template-link" href="/admin/import-templates/drivers">Download drivers template</a>
        </form>
        <div className="pdf-converter"><label>Registration list PDF<input aria-label="Registration list PDF" type="file" accept="application/pdf,.pdf" onChange={(event) => setDriverPdf(event.target.files?.[0] ?? null)} /></label><button type="button" onClick={() => void convertPdf('drivers')} disabled={!driverPdf || converting !== null}>{converting === 'drivers' ? 'Converting…' : 'Convert PDF to CSV'}</button><p>This only produces first name, last name, and nationality columns. Review the downloaded CSV before importing.</p></div>
        {driverImportSummary && <p className="import-summary">Imported {driverImportSummary.importedRows} rows; new drivers: {driverImportSummary.createdDrivers}; existing drivers: {driverImportSummary.existingDrivers}</p>}
      </section>

      <nav className="resource-nav" aria-label="Administration sections">
        {resources.map((item) => <button key={item.key} className={item.key === activeResource ? 'active' : ''} onClick={() => changeResource(item.key)}>{item.label}</button>)}
      </nav>

      {error && <div className="notice error">{error} <a href="/login">Sign in</a></div>}
      {message && <div className="notice success">{message}</div>}
      {importErrors.length > 0 && <div className="modal-backdrop" role="presentation"><section className="error-modal" role="dialog" aria-modal="true" aria-labelledby="import-errors-title"><div className="modal-heading"><div><p className="eyebrow">Import not saved</p><h2 id="import-errors-title">Fix these {importErrors.length} errors</h2></div><button aria-label="Close errors" onClick={() => setImportErrors([])}>×</button></div><ul>{importErrors.map((importError, index) => <li key={`${importError.rowNumber}-${index}`}><strong>Row {importError.rowNumber} · {importError.subject}</strong><span>{importError.message}</span></li>)}</ul><button className="primary" onClick={() => setImportErrors([])}>Close</button></section></div>}

      <section className="workspace">
        <div className="list-panel">
          <div className="section-heading"><div><h2>{resource.label}</h2><p>{records[resource.key].length} records</p></div><button className="primary" onClick={beginCreate}>Add {resource.label.slice(0, -1)}</button></div>
          {loading ? <p className="empty">Loading…</p> : <RecordTable fields={resource.fields} values={records[resource.key]} onEdit={beginEdit} onDelete={remove} />}
        </div>

        <aside className="form-panel">
          <h2>{editing ? `Edit ${resource.label.slice(0, -1)}` : `Add ${resource.label.slice(0, -1)}`}</h2>
          <form onSubmit={submit}>
            {resource.fields.map((field) => <FieldInput key={field.key} field={field} value={formValues[field.key] ?? defaultValue(field)} onChange={(value) => setFormValues({ ...formValues, [field.key]: value })} />)}
            <div className="form-actions"><button className="primary" type="submit">{editing ? 'Save changes' : 'Create'}</button>{editing && <button type="button" onClick={beginCreate}>Cancel</button>}</div>
          </form>
        </aside>
      </section>
    </main>
  )
}

const resourcesForLoading: ResourceKey[] = ['federations', 'disciplines', 'championships', 'seasons', 'competition-classes', 'rounds', 'sessions']
const text = (key: string, label: string, required = false): Field => ({ key, label, required })
const number = (key: string, label: string, required = false): Field => ({ key, label, type: 'number', required })
const select = (key: string, label: string, options: () => { value: string; label: string }[], required = true): Field => ({ key, label, type: 'select', options, required })
const enumOptions = (values: string[]) => values.map((value) => ({ value, label: value.replaceAll('_', ' ') }))
const options = (values: RecordValue[]) => values.map((value) => ({ value: String(value.id), label: labelFor(value) }))
const labelFor = (value: RecordValue) => String(value.name ?? value.code ?? value.year ?? value.id)
const toInputValue = (value: unknown) => value == null ? '' : String(value).slice(0, 16)
const defaultValue = (field: Field) => field.key === 'status' ? 'SCHEDULED' : field.key === 'type' ? 'PRACTICE' : ''
const defaultValues = (resource: Resource) => Object.fromEntries(resource.fields.map((field) => [field.key, defaultValue(field)]))

function valueForApi(field: Field, value: string | undefined): unknown {
  value ??= defaultValue(field)
  if (!value) return field.required ? value : null
  return field.type === 'number' ? Number(value) : value
}

async function api<T>(path: string, init?: RequestInit): Promise<T> {
  const csrfToken = document.cookie.split('; ').find((value) => value.startsWith('XSRF-TOKEN='))?.split('=').slice(1).join('=')
  const isFormData = init?.body instanceof FormData
  const needsCsrfToken = init?.method != null && !['GET', 'HEAD', 'OPTIONS'].includes(init.method)
  const response = await fetch(`/admin${path}`, { credentials: 'include', headers: { Accept: 'application/json', ...(init?.body && !isFormData ? { 'Content-Type': 'application/json' } : {}), ...(needsCsrfToken && csrfToken ? { 'X-XSRF-TOKEN': decodeURIComponent(csrfToken) } : {}) }, ...init })
  if (!response.ok) {
    const body = await response.json().catch(() => null) as { detail?: string; errors?: ImportError[] } | null
    throw new ApiError(body?.detail ?? `Request failed (${response.status})`, body?.errors ?? [])
  }
  if (response.status === 204) return undefined as T
  if (!response.headers.get('content-type')?.includes('application/json')) throw new Error('Your administrator session has expired.')
  return response.json() as Promise<T>
}

async function downloadCsv(path: string, formData: FormData, fallbackFilename: string) {
  const csrfToken = document.cookie.split('; ').find((value) => value.startsWith('XSRF-TOKEN='))?.split('=').slice(1).join('=')
  const response = await fetch(`/admin${path}`, { method: 'POST', credentials: 'include', headers: { Accept: 'text/csv', ...(csrfToken ? { 'X-XSRF-TOKEN': decodeURIComponent(csrfToken) } : {}) }, body: formData })
  if (!response.ok) {
    const body = await response.json().catch(() => null) as { detail?: string } | null
    throw new Error(body?.detail ?? `Conversion failed (${response.status})`)
  }
  const filename = response.headers.get('content-disposition')?.match(/filename="?([^";]+)"?/)?.[1] ?? fallbackFilename
  const url = URL.createObjectURL(await response.blob())
  const link = document.createElement('a')
  link.href = url
  link.download = filename
  link.click()
  URL.revokeObjectURL(url)
}

function messageFor(error: unknown) { return error instanceof Error ? error.message : 'The request could not be completed.' }

class ApiError extends Error {
  readonly importErrors: ImportError[]

  constructor(message: string, importErrors: ImportError[]) {
    super(message)
    this.importErrors = importErrors
  }
}

function FieldInput({ field, value, onChange }: { field: Field; value: string; onChange: (value: string) => void }) {
  return <label>{field.label}{field.type === 'select' ? <select required={field.required} value={value} onChange={(event) => onChange(event.target.value)}><option value="">Select…</option>{field.options?.().map((option) => <option key={option.value} value={option.value}>{option.label}</option>)}</select> : <input type={field.type ?? 'text'} required={field.required} value={value} onChange={(event) => onChange(event.target.value)} />}</label>
}

function RecordTable({ fields, values, onEdit, onDelete }: { fields: Field[]; values: RecordValue[]; onEdit: (value: RecordValue) => void; onDelete: (value: RecordValue) => void }) {
  if (values.length === 0) return <p className="empty">No records yet.</p>
  return <div className="table-scroll"><table><thead><tr>{fields.map((field) => <th key={field.key}>{field.label}</th>)}<th>Actions</th></tr></thead><tbody>{values.map((value) => <tr key={String(value.id)}>{fields.map((field) => <td key={field.key}>{String(value[field.key] ?? '—')}</td>)}<td className="actions"><button onClick={() => onEdit(value)}>Edit</button><button className="danger" onClick={() => onDelete(value)}>Delete</button></td></tr>)}</tbody></table></div>
}

export default App
