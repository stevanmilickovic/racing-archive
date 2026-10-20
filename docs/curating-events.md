# Curating an event

The programmer supplies an event name and the dates of its practice/race weekend.
The agent prepares a folder, waits for the documents, reviews the evidence and
proposes identity matches and new records. The programmer confirms new identities
and resolves questions before the agent writes versioned data migrations.

## 1. Prepare a place for originals

From the repository root:

```powershell
node tools/prepare-event.mjs --name "Event name" --starts-on 2026-05-16 --ends-on 2026-05-17
```

Alternatively, create the same structure with the templates in `archive/templates/`:

```text
archive/events/2026/2026-05-16-event-name/
  event.json
  review.md
  files/
    original-results.pdf
    practice-results.pdf
```

The helper uses Node.js (already required by the frontend) and refuses to overwrite an existing event. Supply `--slug` if the
automatically generated spelling is unsuitable. Dates use ISO YYYY-MM-DD.
Preparation does not create database records or assume a federation/season.
The programmer copies originals into `files/` and tells the agent they are ready.
Do not populate a fictional event or treat `examples/` as confirmed event data.

## 2. Read and reconcile

Inventory filenames, byte counts, SHA-256 hashes, source URLs (when known), the
document's purpose, date, revision and provisional/final status in `event.json`.
Keep filenames simple and use forward slashes in published relative paths.
Preserve the original bytes, including when OCR or temporary extraction is needed.
The app contains no format-specific parser; document interpretation happens during
development using available reading tools. Report unreadable pages or missing OCR
capabilities; ask for help rather than guessing. Do not build a universal converter.

Compare with committed data migrations and the identity catalog. When the app is
running, `GET /public/catalog` returns canonical identity records, archive keys,
notes and aliases. Read-only SQL is also acceptable when a database is available.
If it is unavailable, state what was compared and flag unresolved legacy records.

For each source row, record its session, page/table/row, source spelling, proposed
canonical archive key, and the evidence for matching. Cover drivers, clubs/teams,
car descriptions, tracks, classes, event identity and shared championship seasons.
Also compare timings, positions, race numbers, statuses and document revisions.
Do not treat a final combined classification as another timed run, or infer a
missing run from an aggregate. Repeated overall/class tables are not extra results.
Keep raw session results distinct from official classifications and awarded points.
Do not assign the retained modern scoring system to a season without evidence.
Leave `scoring_system_id` null when its rules are unknown. The dashboard keeps
events and participants visible but hides calculated points until the season has
a known scoring system and a points schedule. Clubs and classes are created only
after source review; fresh installations do not retain the old speculative seeds.
Use official classifications for official points when supplied; do not silently
replace them with the dashboard's existing calculated standings.

List new identities, uncertain matches, contradictory documents and missing facts
in `review.md`. Show all proposed new drivers, cars and teams to the programmer.
Use a separate proposed key for same-name drivers until their identity is resolved.
Unknown teams/cars/classes stay null; unknown identity must never be silently merged.

## 3. Record decisions before migrations

Ask the programmer to verify new records and resolve ambiguities. Record their
answers and the supporting document locators in the review. Do not make event data
migrations until those decisions are supplied. Unknown optional details may remain
null if the programmer accepts the gap; unresolved driver identity requires a
decision. Keep an explicit mapping from each source spelling to a canonical key.

Aliases are confirmed alternate names, not automatic fuzzy matching rules:

- A driver alias may share its spelling with another driver's alias.
- Club abbreviations and vehicle short names can have multiple candidates.
- `identity_aliases` requires exactly one of driver, club, vehicle, track or class.
- Include `source_document_id`, `source_locator` and explanatory notes where known.
- Use identity notes for established distinctions; a vehicle model match does not
  establish that two drivers used the same chassis.

## 4. Write append-only migrations

Create the next numbered changelog under
`backend/src/main/resources/db/changelog/` and include it explicitly in the
master. A YAML changeset may reference an adjacent SQL file with
`relativeToChangelogFile: true`. Never edit an already applied changeset.

Assign immutable `archive_key` values to new identities and rounds. The legacy
tables accept null keys to preserve existing installations. To adopt an existing
row, require a reviewed unique match and assign its key in a new migration.
Names and auto-generated numeric IDs are not portable identity references.

Use Liquibase `sqlCheck` preconditions with `onFail: HALT` for every assumed
existing identity, season, class and parent. For example, before using
`(SELECT id FROM drivers WHERE archive_key = 'reviewed-driver-key')`, require
that the SELECT matches exactly one row. Insert new records before their children.
Do not use `INSERT IGNORE` or `ON DUPLICATE KEY UPDATE` to conceal identity conflicts.
For verified legacy data already present in an upgrade database, explicitly adopt
the exact existing record or insert it on a clean database; halt on differences.

Order data by dependency: reference data and identities, event/round, all
host/guest `round_season_participations`, sessions, entries, session results,
official classifications, source documents and evidenced aliases. A source
registration must point to its actual round. The current event representation is
`ChampionshipRound`; a shared weekend is one round with multiple season links.

Source fields:

| Field | Meaning |
| --- | --- |
| source_key | Stable document key, including revision where relevant |
| round_id | Event resolved from its immutable archive key |
| title | Public description of this document |
| file_path | Relative to archive/events, e.g. 2026/2026-05-16-event-name/files/results.pdf |
| media_type | e.g. application/pdf |
| sha256 | Lowercase hex SHA-256 of the original file |
| original_url | Optional known original location |
| notes | Optional public context, such as provisional/final/revised |

Keep per-row evidence in `review.md` and migration comments, including document
source keys and page/table/row locators. Record the migration paths and resolution
status in the manifest. Manifests/reviews are versioned developer records, not an
upload queue consumed by the running app. SQL migrations publish the data.

## 5. Verify and deliver

Test clean MySQL replay and upgrade from the preceding release in disposable
databases when available. Run normal backend and frontend checks. Check source row
counts, duplicate identities, missing references, timings, statuses, NULL gaps,
class results and shared-season participation against the originals.
Check event source listings and downloads. Downloads validate the stored hash and
refuse files outside the configured source directory, including symlink escapes.

Commit original files, manifest, reviewed decisions and migrations together.
Deploy the corresponding source tree with the release; Docker Compose mounts
`archive/events/` read-only. Outside Docker, set
`RACING_ARCHIVE_SOURCES_DIRECTORY` to that directory (the default when running
from `backend/` is `../archive/events`). Unregistered files are never published.
Large files may require a later storage/LFS policy; do not silently ignore originals.

Correct later discoveries with new migrations and new document revisions. Preserve
historical entry affiliations and source spellings. No reporting system is included.

## Existing manually entered data

Schema upgrades preserve existing records. They cannot automatically make data
from an unavailable local database reproducible. Before retiring such a database,
inventory its records read-only, match them to originals and create reviewed,
upgrade-safe migrations that also reproduce those records on a clean installation.
Do not export credentials, reuse local numeric IDs as portable keys, or discard
unverified records to obtain a clean replay.
