# Racing Archive

A read-only web archive for motorsport events, practice/race results and standings.
The initial focus is Montenegrin hillclimb racing, with a model that supports other
countries, federations, championships, disciplines and shared event weekends.

## How data is maintained

All published archive data is delivered through reviewed, versioned Liquibase
migrations. There are no manual editors, login, uploads, imports or PDF converters.

Tell the agent an event's name and dates. It prepares a folder in `archive/events/`
for your original documents. After you add them, the agent reads them, compares
existing identities and results, and presents new drivers/cars/teams and uncertain
matches for your verification. Once resolved, it writes the data migrations.

Original documents are preserved and downloadable from the event page after
registration in a migration. Unknown team, car, class or track details stay null
and may be filled in through later migrations. Same-name drivers remain separate
identities; confirmed aliases and notes record abbreviations and naming decisions.

Clubs and classes are established from reviewed sources rather than a predefined
list. The existing scoring configuration remains available; seasons with unknown
scoring rules show results without calculated points.

Season standings prefer published points from finalized class results. When
points are absent, they are calculated from the final class position using the
confirmed season rules: points schedule, class-size reduction (classified
finishers) and foreign-host exception. Totals that include calculated points are
marked with an asterisk; these are not stored as published awarded points.
Season drop rules apply to the combined total. Overall/category tables are not
counted twice, and published points are never reduced again. Unknown rules or
missing required scoring information still mean unavailable points, not zero.

See [the curation workflow](docs/curating-events.md) and [agent instructions](AGENTS.md).
Existing manually entered records are preserved by schema upgrades; capturing
legacy data into reviewed migrations is required before those records are also
reproducible on a clean database.

## Stack and layout

- `backend/`: Java 21, Spring Boot REST API, JPA, MySQL and Liquibase
- `frontend/`: React, TypeScript and Vite
- `archive/events/`: original event files, manifests and review decisions
- `archive/templates/`: event templates
- `tools/prepare-event.mjs`: optional event-folder preparation helper
- `docker-compose.yml`: local MySQL/backend and read-only source-file mount

## Run locally

On Windows, start Docker Desktop, then run from the repository root:

```powershell
.\start.cmd
```

The launcher installs frontend dependencies from the lockfile, builds the
frontend, rebuilds and starts MySQL/backend through Compose, waits for the API,
then runs the frontend at **http://localhost:5173**. Node.js/npm and Docker Desktop
are required. No administrator password is needed.

Keep its terminal open. Ctrl+C stops the frontend; the Docker services remain
running. Use `docker compose stop` to stop them without deleting database data.
The launcher uses port 5173 explicitly and reports an error if it is already in use.

To start the pieces manually, run `docker compose up -d --build`, then:

```powershell
cd frontend
npm.cmd ci
npm.cmd run dev
```

To run the backend outside Docker, start it from `backend/` with
`.\mvnw.cmd spring-boot:run`. Set `SPRING_DATASOURCE_URL`,
`SPRING_DATASOURCE_USERNAME` and `SPRING_DATASOURCE_PASSWORD` as needed.
Hibernate validates the schema; Liquibase performs schema and data changes.

`RACING_ARCHIVE_SOURCES_DIRECTORY` selects the source tree. The default is
`../archive/events` relative to the backend process's working directory.
Docker Compose mounts the repository's source tree at `/archive/events`.

Only registered documents can be downloaded. Missing files return 404; documents
whose bytes differ from the registered SHA-256 return 409. Preserve published
originals and add revisions as separate files and migrations.

## Checks

```powershell
cd backend
.\mvnw.cmd test
cd ../frontend
npm.cmd run build
npm.cmd run lint
```

Backend tests use isolated fixtures, including an H2 test of the new schema
upgrade. For a full MySQL replay, provision an empty disposable schema named
`racing_archive_verify_...` and set `RACING_ARCHIVE_TEST_MYSQL_URL`,
`RACING_ARCHIVE_TEST_MYSQL_USERNAME` and `RACING_ARCHIVE_TEST_MYSQL_PASSWORD`.
`MySqlArchiveMigrationTest` runs only when configured and refuses a nonempty
schema or the application database. It replays the curated event, checks the public
results and verifies original document downloads. Set
`RACING_ARCHIVE_TEST_MYSQL_UPGRADE_URL` to a second empty disposable schema to also
test upgrading from the migrations preceding Goč (through 016), preserving Kotor
results and legacy data while using different generated IDs for new records.
Both schemas must have names beginning
with `racing_archive_verify_`; use new empty schemas for another run.
Reporting and automated ingestion are outside the current scope.

The event-folder helper can be checked with `node --test tools/prepare-event.test.mjs`.
