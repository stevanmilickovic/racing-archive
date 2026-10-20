# Racing Archive Domain Model

This document describes the initial domain model for Racing Archive. It is intended to support Montenegrin hillclimb racing first, while keeping the model suitable for other federations, championships, and motorsport disciplines.

## Core hierarchy

Published archive data is now curated through versioned migrations. Read
`../../docs/curating-events.md` for the event-source and identity-review workflow.
The season dashboard sums points from finalized class classifications and applies
the configured season drop rules. Category classifications are not counted again.
Published class-size reductions are already included in official points and are
not reapplied. Raw session results remain available separately. Missing official
points produce an unavailable total rather than an assumed zero.

```text
Federation
  └─ Championship
       └─ Season
            └─ ChampionshipRound
                 ├─ Session
                 ├─ Entry
                 └─ Classification
```

### Federation

A governing or sanctioning body, such as a national motorsport federation (the local "Savez"). A federation can organize multiple championships.

### Discipline

A reference value that identifies a motorsport discipline, such as hillclimb or circuit racing. A championship belongs to one discipline.

### Championship

A long-lived competition series belonging to a federation and using one discipline. For example, the Montenegrin Hillclimb Championship.

### Season

One edition of a championship, normally identified by a calendar year. A season contains the rounds organized during that year.

### ChampionshipRound

One scheduled competition or race weekend within a season. The term "round" avoids ambiguity with an individual hillclimb run or circuit race. A round can record its host federation separately from the championship federation, which supports international and shared ex-Yugoslav events.

### Session

A component of a round. Examples include practice, warm-up, hillclimb run one, hillclimb run two, circuit race one, and circuit race two. A session has a type, order, and relevant date/time information.

## Competitors and entries

### Driver

A reusable person record. A driver can compete in many rounds, seasons, championships, and disciplines.

### Club

A club represented by drivers. Club membership may change over time.

### Vehicle

A reusable vehicle record. A driver may use different vehicles in different rounds or seasons.

### CompetitionClass

A category that groups competitors with comparable machinery. Competition classes are global reference data and can be reused across seasons. A round entry stores the class used by a driver in that round, preserving historic results when a driver changes class.

### Entry

An entry is a driver's participation in one championship round. It records the driver, vehicle, club, competition class, race number, and participation status.

Club/team, vehicle and competition class are optional. Missing details remain
NULL and can be supplied later by a new migration. Session and driver result views
keep such entries visible; class positions and class standings exclude entries
whose class is unknown. Drivers may also exist without any round entries.

Tracks are reusable identities and a round may link to a track when known.
Drivers, clubs/teams, vehicles, classes, tracks and rounds have immutable archive
keys for migration references and optional identity notes. Legacy records may
initially have no key. Confirmed alternate labels live in `identity_aliases`, which
links each alias to exactly one canonical identity. Labels need not be globally
unique: two distinct drivers can have the same name or alias.

Each round can have multiple `source_documents` containing the original file's
title, relative path, media type, SHA-256, optional original URL and public notes.
The source files themselves live under `archive/events/` and are deployed with
the app. Unregistered documents and developer review notes are not downloadable.

`Entry` is the historical snapshot of a driver's representation at a round. Club, vehicle, and class must not be stored only on `Driver`, because each can change between events.

## Results and classifications

### SessionResult

The factual result for one entry in one session. It records values such as elapsed time, position, and status.

Result status must be distinct from placing and should support at least `FINISHED`, `DNF`, `DNS`, and `DSQ`.

### Classification

An official result produced for a round, normally within one competition class. A classification may be based on one or more sessions.

This separates raw timing from discipline-specific result calculation:

- Hillclimb classifications can aggregate two timed runs and rank entries by total time.
- Circuit classifications can rank entries by finishing position for each scoring race session.
- A round may therefore have multiple scoring classifications, such as two separate circuit races.

### ClassificationResult

An entry's official position, points, and result status in a classification. This is the source for championship and club standings.

```text
SessionResult        = raw factual outcome of one session
ClassificationResult = official ranked and scored outcome derived from sessions
```

## Scoring and standings

Scoring rules are versioned configuration associated with a season or competition class. Calculation belongs in application services, not database triggers or entity-specific logic.

- `PointsScheme` belongs to one competition class and defines its minimum competitor threshold, reduced-points factor, and foreign-host exception.
- `PointsSchemePosition` defines the points awarded for each finishing position in a points scheme.
- `DropScoreRule` belongs to a season and defines how many results are deducted and whether disqualifications may be deducted.
- `ClubScoringRule` belongs to one competition class and either counts all results or a configured number of the best driver results.

The initial rules that need to be represented are:

- Points by finishing position.
- Hillclimb half-points when fewer than three competitors start in a class.
- Full points for rounds hosted by another federation, irrespective of class starter count.
- Season drop-score rules, such as removing a driver's lowest eligible result.
- Disqualified results being ineligible for a drop score.
- Club scoring rules, such as counting the best three driver point scores per round.

Awarded points should be stored in `ClassificationResult` when results are finalized. This preserves official historic results even if rules or calculation code later change.

`ClassificationResult.elapsedTimeMillis` preserves an official combined time
separately from each run's `SessionResult`. Published club positions and awarded
points are stored in `round_club_results` with their source document and locator.
The public event page lists recorded classifications and club results; these use
published values even when the season's scoring configuration is unknown.

Standings may initially be calculated from official classification results. They can be persisted later if auditing or performance makes that necessary.

## Future considerations

The first implementation can remain focused on drivers. The model should leave room for later additions such as teams, co-drivers, constructors, licences, eligibility, organizers, championship registrations, and detailed vehicle specifications.

Rounds and classifications should support a provisional/finalized publication status, so preliminary data is not treated as official scoring data.
