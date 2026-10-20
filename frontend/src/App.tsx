import { useEffect, useState } from 'react'
import type { ReactNode } from 'react'
import './App.css'

const labels = {
  en: {
    seasons: 'Active seasons', rounds: 'Completed events', driver: 'Driver', raw: 'Total points',
    adjusted: 'Points after drops', sessions: 'Sessions', sources: 'Source documents', download: 'Download original',
    standingsBasis: 'Published points take precedence; missing points are calculated from finalized class positions using the season rules. Season drop rules are applied to the total.',
    calculatedPoints: '* Includes points calculated using the season rules, not published awarded points.',
    pointsIncomplete: 'Some results lack the information needed to calculate points. Incomplete totals are shown as —.',
    noStandings: 'No finalized class results have been published yet.',
    noSources: 'No source documents published for this event yet.', noSessions: 'No sessions published yet.',
    noSeasons: 'No active seasons published yet.', noResults: 'No results published yet.',
    results: 'Session results', event: 'Event', session: 'Session', competitionClass: 'Class',
    position: 'Class position', time: 'Time', status: 'Status', team: 'Club / team', car: 'Car',
    back: 'Back to seasons', loading: 'Loading…', error: 'Unable to load archive data.',
    unknown: 'Unknown', original: 'Original source URL', checksum: 'SHA-256', track: 'Track',
    scoringUnknown: 'Scoring rules are not yet known for this season. Points are unavailable.',
    classifications: 'Official classifications', clubs: 'Published club results', points: 'Awarded points',
    overallPosition: 'Position', raceNumber: 'Race number', backToEvent: 'Back to event',
  },
  sr: {
    seasons: 'Aktivne sezone', rounds: 'Završene trke', driver: 'Vozač', raw: 'Ukupni bodovi',
    adjusted: 'Bodovi nakon odbacivanja', sessions: 'Sesije', sources: 'Izvorni dokumenti', download: 'Preuzmi original',
    standingsBasis: 'Objavljeni bodovi imaju prednost; nedostajući se računaju iz konačnog klasnog plasmana prema pravilima sezone. Na zbir se primjenjuju pravila odbacivanja rezultata.',
    calculatedPoints: '* Zbir uključuje bodove izračunate prema pravilima sezone, a ne objavljene dodijeljene bodove.',
    pointsIncomplete: 'Za neke rezultate nedostaju podaci potrebni za obračun bodova. Nepotpuni zbirovi prikazani su kao —.',
    noStandings: 'Još nema objavljenih konačnih klasnih rezultata.',
    noSources: 'Izvorni dokumenti za ovu trku još nisu objavljeni.', noSessions: 'Nema objavljenih sesija.',
    noSeasons: 'Nema objavljenih aktivnih sezona.', noResults: 'Nema objavljenih rezultata.',
    results: 'Rezultati sesije', event: 'Trka', session: 'Sesija', competitionClass: 'Klasa',
    position: 'Plasman u klasi', time: 'Vrijeme', status: 'Status', team: 'Klub / tim', car: 'Automobil',
    back: 'Nazad na sezone', loading: 'Učitavanje…', error: 'Nije moguće učitati podatke arhive.',
    unknown: 'Nepoznato', original: 'Originalni izvor', checksum: 'SHA-256', track: 'Staza',
    scoringUnknown: 'Pravila bodovanja za ovu sezonu još nisu poznata. Bodovi nisu dostupni.',
    classifications: 'Zvanične klasifikacije', clubs: 'Objavljeni poredak klubova', points: 'Dodijeljeni bodovi',
    overallPosition: 'Plasman', raceNumber: 'Startni broj', backToEvent: 'Nazad na trku',
  },
}
type Labels = typeof labels.en
type Round = { id: number; roundNumber: number; name: string; startsOn: string | null }
type Season = {
  id: number; championshipName: string; championshipShortName: string | null; year: number
  scoringKnown: boolean; completedRounds: Round[]
  standings: { classCode: string; drivers: { driverId: number; driverName: string; rawPoints: number | null; adjustedPoints: number | null; hasCalculatedPoints: boolean }[] }[]
}
type Session = { id: number; name: string; type: string; sessionOrder: number }
type Result = {
  driverId: number; driverName: string; classCode: string | null; position: number | null
  elapsedTimeMillis: number | null; status: string; clubName: string | null; vehicleName: string | null
}
type DriverResult = Result & { roundId: number; roundName: string; sessionName: string }
type Source = { id: number; title: string; mediaType: string; sha256: string; originalUrl: string | null; notes: string | null; downloadUrl: string }
type RoundDetail = { id: number; name: string; startsOn: string | null; endsOn: string | null; trackName: string | null }
type Classification = { id: number; roundId: number; name: string; status: string; classCode: string | null }
type ClassifiedResult = Result & { raceNumber: number | null; points: number | null }
type ClubResult = { position: number; clubName: string; points: number }

function useArchive<T>(path: string) {
  const [state, setState] = useState<{ data: T | null; error: boolean }>({ data: null, error: false })
  useEffect(() => {
    const controller = new AbortController()
    fetch(path, { signal: controller.signal })
      .then(async (response) => {
        if (!response.ok) throw new Error(String(response.status))
        return response.json() as Promise<T>
      })
      .then((data) => setState({ data, error: false }))
      .catch(() => { if (!controller.signal.aborted) setState({ data: null, error: true }) })
    return () => controller.abort()
  }, [path])
  return state
}

function App() {
  const [language, setLanguage] = useState<'sr' | 'en'>('sr')
  const t = labels[language]
  const route = new URLSearchParams(window.location.search)
  const id = Number(route.get('id'))
  const page = route.get('page')
  const validId = Number.isSafeInteger(id) && id > 0
  let content: ReactNode
  if (validId && page === 'round') content = <RoundPage key={id} id={id} t={t} />
  else if (validId && page === 'session') content = <SessionPage key={id} id={id} t={t} />
  else if (validId && page === 'driver') content = <DriverPage key={id} id={id} t={t} />
  else if (validId && page === 'classification') content = <ClassificationPage key={id} id={id} t={t} />
  else content = <Dashboard t={t} />
  return <main className="public-shell">
    <header className="public-header">
      <a className="brand" href="/">Racing Archive</a>
      <nav><a href="/">{t.seasons}</a><select aria-label="Language" value={language} onChange={(event) => setLanguage(event.target.value as 'sr' | 'en')}>
        <option value="sr">Srpski</option><option value="en">English</option>
      </select></nav>
    </header>
    {content}
  </main>
}

function LoadState({ data, error, t }: { data: unknown; error: boolean; t: Labels }) {
  if (error) return <p role="alert" className="public-error">{t.error}</p>
  return data === null ? <p className="empty">{t.loading}</p> : null
}

function Dashboard({ t }: { t: Labels }) {
  const { data: seasons, error } = useArchive<Season[]>('/public/dashboard')
  return <>
    <section className="public-hero"><p>{t.seasons}</p><h1>Racing Archive</h1></section>
    <LoadState data={seasons} error={error} t={t} />
    <section className="season-grid">{seasons?.map((season) => <article className="season-card" key={season.id}>
      <p>{season.year} · {season.championshipShortName ?? season.championshipName}</p>
      <h2>{season.championshipName}</h2><h3>{t.rounds}</h3>
      <ol className="round-list">{season.completedRounds.map((round) => <li key={round.id}>
        <a href={`/?page=round&id=${round.id}`}>{round.roundNumber}. {round.name}</a>{round.startsOn && ` · ${round.startsOn}`}
      </li>)}</ol>
      {!season.scoringKnown && <p>{t.scoringUnknown}</p>}
      {season.scoringKnown && season.standings.length > 0 && <p>{t.standingsBasis}</p>}
      {season.scoringKnown && season.standings.some((group) => group.drivers.some((driver) => driver.hasCalculatedPoints && driver.rawPoints !== null)) && <p>{t.calculatedPoints}</p>}
      {season.scoringKnown && season.standings.some((group) => group.drivers.some((driver) => driver.rawPoints === null)) && <p>{t.pointsIncomplete}</p>}
      {season.standings.length === 0 && <p className="empty">{t.noStandings}</p>}
      {season.standings.map((group) => <section className="standing" key={group.classCode}>
        <h3>{group.classCode}</h3><div className="table-scroll"><table>
          <thead><tr><th>{t.driver}</th>{season.scoringKnown && <><th>{t.raw}</th><th>{t.adjusted}</th></>}</tr></thead>
          <tbody>{group.drivers.map((driver) => <tr key={driver.driverId}>
            <td><a href={`/?page=driver&id=${driver.driverId}`}>{driver.driverName}</a></td>
            {season.scoringKnown && <>
              <td>{driver.rawPoints ?? '—'}{driver.hasCalculatedPoints && driver.rawPoints !== null && <abbr title={t.calculatedPoints}>*</abbr>}</td>
              <td><strong>{driver.adjustedPoints ?? '—'}{driver.hasCalculatedPoints && driver.adjustedPoints !== null && <abbr title={t.calculatedPoints}>*</abbr>}</strong></td>
            </>}
          </tr>)}</tbody>
        </table></div>
      </section>)}
    </article>)}</section>
    {seasons?.length === 0 && <p className="empty">{t.noSeasons}</p>}
  </>
}

function RoundPage({ id, t }: { id: number; t: Labels }) {
  const round = useArchive<RoundDetail>(`/public/rounds/${id}`)
  const sessions = useArchive<Session[]>(`/public/rounds/${id}/sessions`)
  const sources = useArchive<Source[]>(`/public/rounds/${id}/sources`)
  const classifications = useArchive<Classification[]>(`/public/rounds/${id}/classifications`)
  const clubs = useArchive<ClubResult[]>(`/public/rounds/${id}/club-results`)
  return <Page title={round.data?.name ?? t.event} t={t}>
    <LoadState {...round} t={t} />
    {round.data && <p>{round.data.startsOn}{round.data.endsOn && round.data.endsOn !== round.data.startsOn && ` – ${round.data.endsOn}`}
      {round.data.trackName && ` · ${t.track}: ${round.data.trackName}`}</p>}
    <h2>{t.classifications}</h2><LoadState {...classifications} t={t} />
    <ul className="round-list">{classifications.data?.map((classification) => <li key={classification.id}>
      <a href={`/?page=classification&id=${classification.id}`}>{classification.name}</a> · {classification.status}
    </li>)}</ul>
    {classifications.data?.length === 0 && <p className="empty">{t.noResults}</p>}
    <LoadState {...clubs} t={t} />
    {clubs.data && clubs.data.length > 0 && <>
      <h2>{t.clubs}</h2><div className="table-scroll"><table>
        <thead><tr><th>{t.overallPosition}</th><th>{t.team}</th><th>{t.points}</th></tr></thead>
        <tbody>{clubs.data.map((club) => <tr key={club.clubName}>
          <td>{club.position}</td><td>{club.clubName}</td><td>{club.points}</td>
        </tr>)}</tbody>
      </table></div>
    </>}
    <h2>{t.sessions}</h2><LoadState {...sessions} t={t} />
    <ul className="round-list">{sessions.data?.map((session) => <li key={session.id}>
      <a href={`/?page=session&id=${session.id}`}>{session.name} · {session.type}</a>
    </li>)}</ul>
    {sessions.data?.length === 0 && <p className="empty">{t.noSessions}</p>}
    <h2>{t.sources}</h2><LoadState {...sources} t={t} />
    <ul className="source-list">{sources.data?.map((source) => <li key={source.id}>
      <strong>{source.title}</strong>
      <a href={source.downloadUrl}>{t.download}</a>
      {source.notes && <p>{source.notes}</p>}
      <details><summary>{t.checksum}</summary><code>{source.sha256}</code>
        {source.originalUrl && <p>{t.original}: {source.originalUrl}</p>}
      </details>
    </li>)}</ul>
    {sources.data?.length === 0 && <p className="empty">{t.noSources}</p>}
  </Page>
}

function SessionPage({ id, t }: { id: number; t: Labels }) {
  const { data, error } = useArchive<Result[]>(`/public/sessions/${id}/results`)
  return <Page title={t.results} t={t}>
    <LoadState data={data} error={error} t={t} />
    {data && <div className="table-scroll"><table><thead><tr>
      <th>{t.driver}</th><th>{t.team}</th><th>{t.car}</th><ResultHeadings t={t} />
    </tr></thead><tbody>{data.map((result) => <tr key={result.driverId}>
      <td><a href={`/?page=driver&id=${result.driverId}`}>{result.driverName}</a></td>
      <td>{result.clubName ?? t.unknown}</td><td>{result.vehicleName ?? t.unknown}</td><ResultCells result={result} t={t} />
    </tr>)}</tbody></table></div>}
    {data?.length === 0 && <p className="empty">{t.noResults}</p>}
  </Page>
}

function ClassificationPage({ id, t }: { id: number; t: Labels }) {
  const { data, error } = useArchive<{ classification: Classification; results: ClassifiedResult[] }>(`/public/classifications/${id}`)
  return <Page title={data?.classification.name ?? t.classifications} t={t}>
    <LoadState data={data} error={error} t={t} />
    {data && <>
      <a href={`/?page=round&id=${data.classification.roundId}`}>{t.backToEvent}</a>
      <p>{data.classification.status}</p>
      <div className="table-scroll"><table><thead><tr>
        <th>{t.overallPosition}</th><th>{t.raceNumber}</th><th>{t.driver}</th><th>{t.team}</th><th>{t.car}</th>
        <th>{t.competitionClass}</th><th>{t.time}</th><th>{t.status}</th><th>{t.points}</th>
      </tr></thead><tbody>{data.results.map((result) => <tr key={result.driverId}>
        <td>{result.position ?? '—'}</td><td>{result.raceNumber ?? '—'}</td>
        <td><a href={`/?page=driver&id=${result.driverId}`}>{result.driverName}</a></td>
        <td>{result.clubName ?? t.unknown}</td><td>{result.vehicleName ?? t.unknown}</td>
        <td>{result.classCode ?? t.unknown}</td><td>{formatRaceTime(result.elapsedTimeMillis)}</td>
        <td>{result.status}</td><td>{result.points ?? '—'}</td>
      </tr>)}</tbody></table></div>
      {data.results.length === 0 && <p className="empty">{t.noResults}</p>}
    </>}
  </Page>
}

function DriverPage({ id, t }: { id: number; t: Labels }) {
  const { data, error } = useArchive<{ driverName: string; results: DriverResult[] }>(`/public/drivers/${id}/results`)
  return <Page title={data?.driverName ?? t.driver} t={t}>
    <LoadState data={data} error={error} t={t} />
    {data && <div className="table-scroll"><table><thead><tr>
      <th>{t.event}</th><th>{t.session}</th><th>{t.team}</th><th>{t.car}</th><ResultHeadings t={t} />
    </tr></thead><tbody>{data.results.map((result, index) => <tr key={index}>
      <td><a href={`/?page=round&id=${result.roundId}`}>{result.roundName}</a></td>
      <td>{result.sessionName}</td><td>{result.clubName ?? t.unknown}</td><td>{result.vehicleName ?? t.unknown}</td>
      <ResultCells result={result} t={t} />
    </tr>)}</tbody></table></div>}
    {data?.results.length === 0 && <p className="empty">{t.noResults}</p>}
  </Page>
}

function ResultHeadings({ t }: { t: Labels }) {
  return <><th>{t.competitionClass}</th><th>{t.position}</th><th>{t.time}</th><th>{t.status}</th></>
}

function ResultCells({ result, t }: { result: Result; t: Labels }) {
  return <><td>{result.classCode ?? t.unknown}</td><td>{result.position ?? '—'}</td>
    <td>{formatRaceTime(result.elapsedTimeMillis)}</td><td>{result.status}</td></>
}

function Page({ title, children, t }: { title: string; children: ReactNode; t: Labels }) {
  return <section className="season-card detail-page"><a href="/">{t.back}</a><h1>{title}</h1>{children}</section>
}

function formatRaceTime(millis: number | null) {
  if (millis === null) return '—'
  const minutes = Math.floor(millis / 60000)
  const seconds = Math.floor((millis % 60000) / 1000)
  return `${minutes}:${String(seconds).padStart(2, '0')}.${String(millis % 1000).padStart(3, '0')}`
}

export default App
