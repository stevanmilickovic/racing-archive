import { useEffect, useMemo, useState } from 'react'
import type { FormEvent, ReactNode } from 'react'
import './App.css'

type RecordValue = Record<string, unknown>
type ResourceKey = 'federations' | 'disciplines' | 'championships' | 'seasons' | 'competition-classes' | 'rounds' | 'sessions' | 'drivers' | 'vehicles'

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
  if (new URLSearchParams(window.location.search).get('view') !== 'admin') return <PublicDashboardNew />
  const [records, setRecords] = useState<Record<ResourceKey, RecordValue[]>>({
    federations: [], disciplines: [], championships: [], seasons: [], 'competition-classes': [], rounds: [], sessions: [], drivers: [], vehicles: [],
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
  const [resultsSession, setResultsSession] = useState<RecordValue | null>(null)
  const [sessionResults, setSessionResults] = useState<RecordValue[]>([])
  const [scoringSystems, setScoringSystems] = useState<RecordValue[]>([])
  const [manualResult, setManualResult] = useState({ driverId: '', vehicleId: '', competitionClassId: '', elapsedTime: '', position: '', status: 'FINISHED' })

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
        number('year', 'Year', true), text('name', 'Name'), { key: 'active', label: 'Active', type: 'select', required: true, options: () => enumOptions(['true', 'false']) }, select('scoringSystemId', 'Scoring system', () => options(scoringSystems), false),
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
    { key: 'drivers', label: 'Drivers', fields: [text('firstName', 'First name', true), text('lastName', 'Last name', true), text('nationalityCode', 'Nationality')] },
    { key: 'vehicles', label: 'Car models', fields: [text('manufacturer', 'Manufacturer', true), text('model', 'Model'), text('displayName', 'Display name')] },
  ], [records, scoringSystems])

  const resource = resources.find((item) => item.key === activeResource)!

  useEffect(() => { void loadRecords() }, [])

  async function loadRecords() {
    setLoading(true)
    setError(null)
    try {
      await fetch('/csrf', { credentials: 'include' })
      const loaded = await Promise.all(resourcesForLoading.map(async (key) => [key, await api<RecordValue[]>(`/${key}`)] as const))
      setRecords(Object.fromEntries(loaded) as Record<ResourceKey, RecordValue[]>)
      setScoringSystems(await api<RecordValue[]>('/scoring-systems'))
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

  async function openSessionResults(session: RecordValue) {
    setError(null)
    try {
      setSessionResults(await api<RecordValue[]>(`/sessions/${session.id}/results`))
      setResultsSession(session)
    } catch (requestError) { setError(messageFor(requestError)) }
  }

  async function updateResultVehicle(resultId: unknown, vehicleId: string) {
    if (!vehicleId) return
    try {
      await api(`/session-results/${resultId}`, { method: 'PUT', body: JSON.stringify({ vehicleId: Number(vehicleId) }) })
      if (resultsSession) await openSessionResults(resultsSession)
    } catch (requestError) { setError(messageFor(requestError)) }
  }

  async function deleteResult(resultId: unknown) {
    if (!window.confirm('Delete this result from this session?')) return
    try {
      await api(`/session-results/${resultId}`, { method: 'DELETE' })
      if (resultsSession) await openSessionResults(resultsSession)
    } catch (requestError) { setError(messageFor(requestError)) }
  }

  async function saveResultTime(resultId: unknown, value: string) {
    try { await api(`/session-results/${resultId}`, { method: 'PUT', body: JSON.stringify({ elapsedTimeMillis: parseRaceTime(value) }) }); if (resultsSession) await openSessionResults(resultsSession) }
    catch (requestError) { setError(messageFor(requestError)) }
  }

  async function addManualResult(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    if (!resultsSession) return
    try {
      await api(`/sessions/${resultsSession.id}/results`, { method: 'POST', body: JSON.stringify({ driverId: Number(manualResult.driverId), vehicleId: Number(manualResult.vehicleId), competitionClassId: Number(manualResult.competitionClassId), elapsedTimeMillis: parseRaceTime(manualResult.elapsedTime), position: manualResult.position ? Number(manualResult.position) : null, status: manualResult.status }) })
      setManualResult({ driverId: '', vehicleId: '', competitionClassId: '', elapsedTime: '', position: '', status: 'FINISHED' })
      await openSessionResults(resultsSession)
    } catch (requestError) { setError(messageFor(requestError)) }
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

      {resultsSession && <section className="list-panel session-results"><div className="section-heading"><div><h2>{labelFor(resultsSession)} results</h2><p>Vehicle changes apply to this driver’s entry for the round.</p></div><button onClick={() => setResultsSession(null)}>Close</button></div><form className="manual-result-form" onSubmit={addManualResult}><select required value={manualResult.driverId} onChange={(event) => setManualResult({ ...manualResult, driverId: event.target.value })}><option value="">Driver…</option>{options(records.drivers).map((option) => <option key={option.value} value={option.value}>{option.label}</option>)}</select><select required value={manualResult.vehicleId} onChange={(event) => setManualResult({ ...manualResult, vehicleId: event.target.value })}><option value="">Vehicle…</option>{options(records.vehicles).map((option) => <option key={option.value} value={option.value}>{option.label}</option>)}</select><select required value={manualResult.competitionClassId} onChange={(event) => setManualResult({ ...manualResult, competitionClassId: event.target.value })}><option value="">Class…</option>{options(records['competition-classes']).map((option) => <option key={option.value} value={option.value}>{option.label}</option>)}</select><input placeholder="4:57.539" value={manualResult.elapsedTime} onChange={(event) => setManualResult({ ...manualResult, elapsedTime: event.target.value })} /><input type="number" placeholder="Position" value={manualResult.position} onChange={(event) => setManualResult({ ...manualResult, position: event.target.value })} /><button className="primary">Add result</button></form><div className="table-scroll"><table><thead><tr><th>Driver</th><th>Position</th><th>Time</th><th>Status</th><th>Vehicle</th><th>Actions</th></tr></thead><tbody>{sessionResults.map((result) => <tr key={String(result.id)}><td>{String(result.driverName)}</td><td>{String(result.position ?? '—')}</td><td><input className="time-input" defaultValue={formatRaceTime(result.elapsedTimeMillis)} onBlur={(event) => void saveResultTime(result.id, event.target.value)} /></td><td>{String(result.status)}</td><td><select value={String(result.vehicleId ?? '')} onChange={(event) => void updateResultVehicle(result.id, event.target.value)}><option value="">Select vehicle…</option>{options(records.vehicles).map((option) => <option key={option.value} value={option.value}>{option.label}</option>)}</select></td><td><button className="danger" onClick={() => void deleteResult(result.id)}>Delete result</button></td></tr>)}</tbody></table></div></section>}

      <section className="workspace">
        <div className="list-panel">
          <div className="section-heading"><div><h2>{resource.label}</h2><p>{records[resource.key].length} records</p></div><button className="primary" onClick={beginCreate}>Add {resource.label.slice(0, -1)}</button></div>
          {loading ? <p className="empty">Loading…</p> : <RecordTable fields={resource.fields} values={records[resource.key]} records={records} onEdit={beginEdit} onDelete={remove} onResults={resource.key === 'sessions' ? openSessionResults : undefined} />}
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

const resourcesForLoading: ResourceKey[] = ['federations', 'disciplines', 'championships', 'seasons', 'competition-classes', 'rounds', 'sessions', 'drivers', 'vehicles']
const text = (key: string, label: string, required = false): Field => ({ key, label, required })
const number = (key: string, label: string, required = false): Field => ({ key, label, type: 'number', required })
const select = (key: string, label: string, options: () => { value: string; label: string }[], required = true): Field => ({ key, label, type: 'select', options, required })
const enumOptions = (values: string[]) => values.map((value) => ({ value, label: value.replaceAll('_', ' ') }))
const options = (values: RecordValue[]) => values.map((value) => ({ value: String(value.id), label: labelFor(value) }))
const labelFor = (value: RecordValue) => String(value.displayName || [value.manufacturer, value.model].filter(Boolean).join(' ') || value.name || value.code || value.year || value.id)
const toInputValue = (value: unknown) => value == null ? '' : String(value).slice(0, 16)
const defaultValue = (field: Field) => field.key === 'status' ? 'SCHEDULED' : field.key === 'type' ? 'PRACTICE' : field.key === 'active' ? 'false' : ''
const defaultValues = (resource: Resource) => Object.fromEntries(resource.fields.map((field) => [field.key, defaultValue(field)]))

function valueForApi(field: Field, value: string | undefined): unknown {
  value ??= defaultValue(field)
  if (!value) return field.required ? value : null
  if (field.key === 'active') return value === 'true'
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

function formatRaceTime(value: unknown) { if (value == null) return ''; const total = Number(value); const minutes = Math.floor(total / 60000); const seconds = Math.floor((total % 60000) / 1000); const milliseconds = total % 1000; return `${minutes}:${String(seconds).padStart(2, '0')}.${String(milliseconds).padStart(3, '0')}` }
function parseRaceTime(value: string) { if (!value) return null; const match = value.match(/^(\d+):(\d{2})\.(\d{3})$/); if (!match || Number(match[2]) > 59) throw new Error('Time must use minutes:seconds.milliseconds, for example 4:57.539'); return (Number(match[1]) * 60 + Number(match[2])) * 1000 + Number(match[3]) }

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

function RecordTable({ fields, values, records, onEdit, onDelete, onResults }: { fields: Field[]; values: RecordValue[]; records: Record<ResourceKey, RecordValue[]>; onEdit: (value: RecordValue) => void; onDelete: (value: RecordValue) => void; onResults?: (value: RecordValue) => void }) {
  if (values.length === 0) return <p className="empty">No records yet.</p>
  return <div className="table-scroll"><table><thead><tr>{fields.map((field) => <th key={field.key}>{field.label}</th>)}<th>Actions</th></tr></thead><tbody>{values.map((value) => <tr key={String(value.id)}>{fields.map((field) => <td key={field.key}>{displayValue(field.key, value[field.key], records)}</td>)}<td className="actions">{onResults && <button onClick={() => void onResults(value)}>Results</button>}<button onClick={() => onEdit(value)}>Edit</button><button className="danger" onClick={() => onDelete(value)}>Delete</button></td></tr>)}</tbody></table></div>
}

function displayValue(key: string, value: unknown, records: Record<ResourceKey, RecordValue[]>) {
  const find = (items: RecordValue[]) => items.find((item) => String(item.id) === String(value))
  if (key === 'federationId' || key === 'hostFederationId') { const item = find(records.federations); return String(item?.countryCode ?? item?.name ?? '—') }
  if (key === 'disciplineId') return String(find(records.disciplines)?.code ?? '—')
  if (key === 'championshipId') { const item = find(records.championships); return String(item?.shortName ?? item?.name ?? '—') }
  if (key === 'seasonId') { const season = find(records.seasons); const championship = records.championships.find((item) => String(item.id) === String(season?.championshipId)); return `${season?.year ?? '—'} · ${championship?.name ?? '—'}` }
  return String(value ?? '—')
}

type PublicSeason = { id: number; championshipName: string; championshipShortName: string | null; year: number; completedRounds: { id: number; roundNumber: number; name: string; startsOn: string | null }[]; standings: { classCode: string; drivers: { driverId: number; driverName: string; rawPoints: number; adjustedPoints: number }[] }[] }
type PublicSession = { id: number; name: string; type: string; sessionOrder: number }
type PublicSessionResult = { driverId: number; driverName: string; classCode: string; position: number | null; elapsedTimeMillis: number | null; status: string }

function PublicDashboard() {
  const [language, setLanguage] = useState<'sr' | 'en'>('sr')
  const [seasons, setSeasons] = useState<PublicSeason[]>([])
  const [query, setQuery] = useState('')
  const [error, setError] = useState<string | null>(null)
  useEffect(() => { fetch('/public/dashboard').then(async (response) => { if (!response.ok) throw new Error('Unable to load dashboard'); return response.json() as Promise<PublicSeason[]> }).then(setSeasons).catch((reason) => setError(messageFor(reason))) }, [])
  const t = language === 'sr' ? { title: 'Racing Archive', active: 'Aktivne sezone', completed: 'Završene trke', raw: 'Sirovi poredak', live: 'Trenutni poredak', driver: 'Vozač', points: 'Bodovi', search: 'Pretraži vozače, sezone i prvenstva', empty: 'Nema aktivnih sezona.' } : { title: 'Racing Archive', active: 'Active seasons', completed: 'Completed rounds', raw: 'Raw standings', live: 'Adjusted standings', driver: 'Driver', points: 'Points', search: 'Search drivers, seasons and championships', empty: 'No active seasons.' }
  const matches = (value: string) => value.toLowerCase().includes(query.toLowerCase())
  return <main className="public-shell"><header className="public-header"><a href="/" className="brand">{t.title}</a><nav><a href="#seasons">{t.active}</a><a href="/?view=admin">Administracija</a><select value={language} onChange={(event) => setLanguage(event.target.value as 'sr' | 'en')}><option value="sr">Srpski</option><option value="en">English</option></select></nav></header><section className="public-hero"><p>{t.active}</p><h1>{t.title}</h1><input placeholder={t.search} value={query} onChange={(event) => setQuery(event.target.value)} /></section>{error && <p className="public-error">{error}</p>}<section id="seasons" className="season-grid">{seasons.filter((season) => matches(`${season.championshipName} ${season.year}`) || season.standings.some((group) => group.drivers.some((driver) => matches(driver.driverName)))).map((season) => <article className="season-card" key={season.id}><p>{season.year} · {season.championshipShortName ?? season.championshipName}</p><h2>{season.championshipName}</h2><h3>{t.completed}</h3><ol className="round-list">{season.completedRounds.map((round) => <li key={round.id}>{round.roundNumber}. {round.name}</li>)}</ol>{season.standings.map((group) => <section className="standing" key={group.classCode}><h3>{group.classCode}</h3><table><thead><tr><th>{t.driver}</th><th>{t.raw}</th><th>{t.live}</th></tr></thead><tbody>{group.drivers.filter((driver) => !query || matches(driver.driverName)).map((driver) => <tr key={driver.driverName}><td>{driver.driverName}</td><td>{driver.rawPoints}</td><td><strong>{driver.adjustedPoints}</strong></td></tr>)}</tbody></table></section>)}</article>)}{seasons.length === 0 && <p>{t.empty}</p>}</section></main>
}

function PublicDashboardNew() {
  const route = new URLSearchParams(window.location.search)
  if (route.get('page') === 'round') return <PublicRoundPage roundId={Number(route.get('id'))} />
  if (route.get('page') === 'session') return <PublicSessionPage sessionId={Number(route.get('id'))} />
  if (route.get('page') === 'driver') return <PublicDriverPage driverId={Number(route.get('id'))} />
  const [seasons, setSeasons] = useState<PublicSeason[]>([])
  useEffect(() => { void fetch('/public/dashboard').then((response) => response.json() as Promise<PublicSeason[]>).then(setSeasons) }, [])
  return <main className="public-shell"><header className="public-header"><a className="brand" href="/">Racing Archive</a><nav><a href="/?view=admin">Administracija</a><span>Srpski · English</span></nav></header><section className="public-hero"><p>Aktivne sezone</p><h1>Racing Archive</h1></section><section className="season-grid">{seasons.map((season) => <article className="season-card" key={season.id}><p>{season.year} · {season.championshipShortName ?? season.championshipName}</p><h2>{season.championshipName}</h2><h3>Završene trke</h3><ol className="round-list">{season.completedRounds.map((round) => <li key={round.id}><a href={`/?page=round&id=${round.id}`}>{round.roundNumber}. {round.name}</a></li>)}</ol>{season.standings.map((group) => <section className="standing" key={group.classCode}><h3>{group.classCode}</h3><table><thead><tr><th>Vozač</th><th>Sirovi poredak</th><th>Trenutni poredak</th></tr></thead><tbody>{group.drivers.map((driver) => <tr key={driver.driverName}><td>{driver.driverName}</td><td>{driver.rawPoints}</td><td><strong>{driver.adjustedPoints}</strong></td></tr>)}</tbody></table></section>)}</article>)}</section></main>
}

function PublicRoundPage({ roundId }: { roundId: number }) { const [sessions, setSessions] = useState<PublicSession[]>([]); useEffect(() => { void fetch(`/public/rounds/${roundId}/sessions`).then((response) => response.json() as Promise<PublicSession[]>).then(setSessions) }, [roundId]); return <PublicPage title="Sesije trke"><ul className="round-list">{sessions.map((session) => <li key={session.id}><a href={`/?page=session&id=${session.id}`}>{session.name} · {session.type}</a></li>)}</ul></PublicPage> }
function PublicSessionPage({ sessionId }: { sessionId: number }) { const [results, setResults] = useState<PublicSessionResult[]>([]); useEffect(() => { void fetch(`/public/sessions/${sessionId}/results`).then((response) => response.json() as Promise<PublicSessionResult[]>).then(setResults) }, [sessionId]); return <PublicPage title="Rezultati sesije"><table><thead><tr><th>Vozač</th><th>Klasa</th><th>Plasman</th><th>Vrijeme</th><th>Status</th></tr></thead><tbody>{results.map((result) => <tr key={`${result.driverId}-${result.classCode}`}><td><a href={`/?page=driver&id=${result.driverId}`}>{result.driverName}</a></td><td>{result.classCode}</td><td>{result.position ?? '—'}</td><td>{formatRaceTime(result.elapsedTimeMillis)}</td><td>{result.status}</td></tr>)}</tbody></table></PublicPage> }
function PublicDriverPage({ driverId }: { driverId: number }) { const [profile, setProfile] = useState<{ driverName: string; results: { roundName: string; sessionName: string; classCode: string; position: number | null; elapsedTimeMillis: number | null; status: string }[] } | null>(null); useEffect(() => { void fetch(`/public/drivers/${driverId}/results`).then((response) => response.json() as Promise<NonNullable<typeof profile>>).then(setProfile) }, [driverId]); return <PublicPage title={profile?.driverName ?? 'Vozač'}><table><thead><tr><th>Trka</th><th>Sesija</th><th>Klasa</th><th>Plasman</th><th>Vrijeme</th><th>Status</th></tr></thead><tbody>{profile?.results.map((result, index) => <tr key={index}><td>{result.roundName}</td><td>{result.sessionName}</td><td>{result.classCode}</td><td>{result.position ?? '—'}</td><td>{formatRaceTime(result.elapsedTimeMillis)}</td><td>{result.status}</td></tr>)}</tbody></table></PublicPage> }
function PublicPage({ title, children }: { title: string; children: ReactNode }) { return <main className="public-shell"><header className="public-header"><a className="brand" href="/">Racing Archive</a></header><section className="season-card"><a href="/">← Nazad</a><h1>{title}</h1>{children}</section></main> }

void PublicDashboard

export default App
