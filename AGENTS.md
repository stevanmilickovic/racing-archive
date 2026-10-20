# Racing Archive

Racing Archive is a read-only motorsport archive. React/TypeScript is in
`frontend/`; Java 21, Spring Boot, JPA, MySQL and Liquibase are in `backend/`.
Read `backend/AGENTS.md` before backend changes.

## Authoritative data

All published archive data and corrections are versioned Liquibase migrations.
There are no runtime editors, uploads, CSV imports or PDF converters.
Do not reintroduce them. Keep existing records intact when changing the schema.
Never change an applied migration, clear a database, or invent missing facts.
Add a new migration for corrections and newly established information.

## Event workflow

Follow `docs/curating-events.md` in full when preparing or processing an event.

1. When the programmer supplies the event name and dates, prepare
   `archive/events/YYYY/YYYY-MM-DD-event-slug/` with `event.json`,
   `review.md` and `files/`. The optional `tools/prepare-event.mjs`
   does this. Confirm the exact drop location. Do not guess the event's
   championship, track, sessions or results.
2. Wait until the programmer says the source documents have been added.
3. Inventory and hash originals. Read every relevant document and compare
   drivers, clubs/teams, vehicles, tracks and classes against existing migrations,
   confirmed aliases and, when available, the current read-only database/catalog.
4. Write the reconciliation to `review.md`: exact source spellings and locators,
   confirmed matches and their evidence, proposed new identities, missing facts,
   conflicting results and questions. Include every proposed new driver, vehicle,
   club/team and track for the programmer to verify.
5. Present new identities, ambiguous matches and inconsistencies to the
   programmer and wait for their decisions before writing event data migrations.
   This is an explicit requirement of this project's curation workflow.
   Continue independent document review while waiting. Record their decisions.
6. Once resolved, write append-only migrations with explicit identity references,
   source registrations and evidence locators. Record the migration paths in the
   event manifest and review. Validate in an isolated database and verify the
   public results and source downloads. Do not publish or deploy unless requested.

## Identity and uncertainty

- Names are labels, never identity keys. Different drivers may share a name.
  Do not merge people based only on spelling, nationality, or similarity.
- Assign immutable, unique `archive_key` values to new drivers, clubs/teams,
  vehicles, tracks, classes and rounds. Existing legacy records may lack keys;
  adopt them only with an explicit, unambiguous match.
- Use `identity_aliases` to preserve confirmed spellings, abbreviations and short
  car names, with the source document, page/row and explanation where available.
  An alias belongs to exactly one record, but the same spelling can belong to
  several distinct records. Aliases suggest candidates; they do not prove a match.
- Use `identity_notes` for established disambiguating facts. Do not store personal
  guesses. Never put credentials or private review material into public metadata.
- A vehicle currently represents the reusable car description, not proof of a
  specific chassis. Do not claim two entries used the same physical car without
  evidence. Current clubs represent the recorded club/team affiliation.
- Keep unknown entry club/team, vehicle, class and event track as SQL NULL.
  Do not create placeholder identities such as "Unknown car". A driver can also
  exist without entries. Fill gaps later with a new evidenced migration.
- Entries preserve affiliation, vehicle and class at that event. Do not rewrite
  past entries merely because a driver's current team/car/class changes.
- Clubs and classes are established from reviewed event sources; the historical
  speculative seed lists are retired. Keep the available scoring configuration,
  but leave a season's scoring system null until its rules are established.
  Unknown scoring means unavailable points, never assumed zero points.
- Season standings sum finalized class classification points and apply season
  drop rules. Do not recompute awarded points from individual runs, double-count
  category tables, or apply class-size reductions to published points again.
  A missing official points value is unknown, not an actual zero score.

## Sources and migrations

- Original documents live under the event's `files/`; preserve their bytes.
  Review files and manifests are not downloadable through the app.
- Only documents registered in `source_documents` are published. Register their
  event, title, relative file path, media type and SHA-256 in the event migration.
- Never overwrite a published document. Add a revision as a new file and source
  record. Link corrections to their evidence in the review and migration.
- Resolve foreign keys using stable archive keys or documented unique natural
  keys with preconditions; never copy local auto-increment IDs into migrations.
- Use explicit master changelog includes. Ensure clean replay and upgrades both
  work; missing identity lookups must fail, not silently omit or null results.
- A live database can contain historical manual data. Do not delete it or assume
  it is already reproducible from migrations. Capture verified legacy data into
  reviewed migrations before claiming a full rebuild reproduces it.

## Verification

Run `npm.cmd run build` and `npm.cmd run lint` in `frontend/`.
Run `.\mvnw.cmd test` in `backend/`. Tests use isolated fixtures, never the
developer's database. Report when full MySQL replay cannot be performed.
Run `node --test tools/prepare-event.test.mjs` when changing event preparation.
