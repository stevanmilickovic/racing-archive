# Podgorica – Kuči 2026: source reconciliation

Status: programmer decisions accepted on 21 October 2026; migration 019 verified in an isolated H2 database, with full MySQL replay unavailable. Earlier proposal tables below are preserved as the review trail; the accepted mappings in this section and reconciliation.json supersede them.

## Accepted decisions — 21 October 2026

- All 12 proposed new drivers and Mugoša club are approved. Kuči is the reviewed source-established track. New driver nationality and unknown club country/federation stay NULL.
- The programmer personally confirmed the earlier driver **MILAN BRACANOVIĆ**. Reuse `driver-milan-bracanovic`; do not create Miloš Bracanović. Registration #751 is retained because no timed #751 is present, following the earlier conditional instruction. Preserve timing name BRACANOVIĆ Miloš and bib #752 with their exact source locators. This same-person confirmation supersedes the initial uncertainty.
- Savićević and Cebić have no previous race entries in migrations through 018; apply the programmer’s fallback to their timed classes H 1400 and H 2000+.
- Preserve make/model detail established for this event. No prior model is carried forward from a driver alone. New manufacturer-only descriptions: PEUGEOT, SUZUKI, HONDA, ŠKODA, OPEL, FORD, SEAT, NORMA (model NULL); VW GOLF retains the established model family without generation. Existing catalog VW GOLF I/V establishes the VW make for Golf. Megan and Cooper lack an established manufacturer in these sources, so those two vehicle links remain NULL and their original labels stay in the review. No Renault/Mini expansion was assumed.
- Keep the original existing canonical names for reviewed spelling variants; record the source spellings as evidenced aliases. Nikolić Miljan and Stanišić Veljko are inverted name fields in P, reconciled with the timing names and entry context.
- Preserve long practice times #510 Run 2 21:59.700 and #81 Run 2 21:26.509. The programmer confirmed that long practice times are normal.
- Unknown facts stay NULL. Goran Vučković and Igor Ivanović have no established current club/car; registration-only rows do not become event entries, and missing results do not become invented DNS/DNF.
- Use this as the requested next AKSCG 2026 calendar round after rounds 1 and 2 (position 3); this is the calendar placement requested by the programmer, not a claim that a supplied document prints the ordinal. No separate cup season is created.

## Confirmed decision

The programmer explicitly confirmed **“those results are final”** on 21 October 2026. Treat R as final on that authority. Preserve the original printed **REZULTATI - NEZVANIČNI** label and bytes; do not represent it as an original stamped final revision. This resolves race finality only.

## Event, dates and source scope

The initially supplied date 20 June is the practice day. B p.3 program and p.8 arts.10.2.2/10.3.1 establish two practice runs on 20 June and two race runs on 21 June. Proposed public weekend is **20–21 June 2026**; administrative scrutineering began 19 June (B p.3/p.7). Keep directory/key `2026-06-20-kuci`.

B p.1 calls the event PODGORICA - KUČI 2026, and p.3 names organizer AMK MONTERACING. B p.5 art.2.1.4 names both ŠAMPIONAT CRNE GORE and KUP KUČI 2026. The requested AKSCG event uses the supplied final results, with no separately invented cup season. Existing rounds 1 (Kotor) and 2 (Goč) establish the preceding calendar positions; round 3 is the requested next placement, not an ordinal printed by a supplied document. Retain the existing CG 2026 scoring configuration from migration 016; these sources publish no awarded points.

## Originals and inventory

All five originals remain byte-for-byte unchanged in `files/`. Source URLs and acquisition dates are unknown. PDF creation dates below are metadata, not proof of publication. Review/manifests/reconciliation are development records and must never be source downloads.

| Code | Filename / proposed source key | Bytes / pages | Content date / finality |
| --- | --- | --- | --- |
| T | kuci 2026 trening rezultati.jpg / 2026-06-20-kuci-practice-results | 498508 / 1 | 2026-06-20 / published-practice-table |
| R | kuci 2026 trka rezultati.jpg / 2026-06-20-kuci-race-results | 487112 / 1 | 2026-06-21 / confirmed-final-by-programmer |
| P | Lista prijava KUČI 2026 (1).pdf / 2026-06-20-kuci-registrations | 477499 / 3 | undated / registration-list-not-start-evidence |
| B | Pravilnik trke Kuči 2026.pdf / 2026-06-20-kuci-regulations | 348220 / 10 | 2026-06-05 / event-regulations |
| M | Skica staze Kuči 2026.pdf / 2026-06-20-kuci-course-map | 69385 / 1 | undated / undated-course-diagram |

- **T** SHA-256: `58d092cd6b873de745d916520d87252605bc4b61b3a9372724bb767cc6e61016`. Two practice runs, best-of-two ranking and explicit DNS/DNF lists; 62 unique drivers.

- **R** SHA-256: `398f157029ac482d820d1cdbb6a228f7261953c62822730d5760fb274eec4d2d`. 49 race finishers: both run times, sum, overall/class rank and gap. Programmer confirmed finality on 2026-10-21.

- **P** SHA-256: `a85527117b7362a5ae901ad1611fe000489a9b3f3f07d202ec61f63b2ccbf8b8`. 66 registrations on pp.1–2; p.3 blank. No printed issue date; PDF creation date is metadata only. PDF creation metadata: 2026-06-18.

- **B** SHA-256: `1be7709eb70c3c6b19063c4a5b01b5e9ce02776a0f771cd25336bf75eadc2b48`. Organization, championship/cup, 19–21 June program, course and competition rules. PDF creation metadata: 2026-06-15.

- **M** SHA-256: `681ea0c7ae852c37096d1d9e739335f6ad1da2065f2c5f681f42ef6a290c23ad`. Image-only diagram: start/finish, paddock, parc fermé, marshal posts and four numbered chicanes. PDF creation metadata: 2026-06-08.

Locators: P rows restart on each PDF page, excluding headers (36 rows p.1, 30 p.2); T/R ranked rows are the printed overall ranks. Status locators use section and bib. M is image-only and was visually read; P p.3 was visually confirmed blank. All ten B pages were read.

## Catalog comparison and limits

Compared committed migrations through 018, accepted Kotor/Goč reconciliations and 949 source-linked alias rows from migrations 015/018, respecting the Polo consolidation in 017. The previous catalog snapshot (190 identities / 312 aliases) was supplementary and predates 017/018. The current read-only API at localhost:8080 was unreachable, including outside the sandbox; Docker’s engine pipe was absent. Current unversioned legacy identities could not be ruled out. No database data was changed. Recheck the live catalog before finalizing inserts when available.

## Reviewed questions — resolved by the accepted decisions above

### Q1

Confirm AKSCG 2026 round number 3 and that the supplied race/class table applies to that championship, identifying any cup-only entrants.

Programmer requested AKSCG; B p.5 lists both Šampionat Crne Gore and Kup Kuči, but timing sheets do not name the championship. Prior migrations contain rounds 1 and 2; ordinal 3 is a proposal, not a source fact.

### Q2

Approve the 12 proposed new driver identities below (including registration-only Miloš Milošev), the Mugoša club, and the Kuči track; provide any corrections.

All new immutable keys require programmer verification. No nationalities or club federation are inferred for new records.

### Q3

Is T #752 BRACANOVIĆ Miloš the registered Milan Bracanović #751, or a separate person?

P p.1 row 31: Milan, AP SPORT, Peugeot, H 1400, #751. Existing driver-milan-bracanovic: Kotor #152, AMSK AP SPORT, PEUGEOT 106, H 1400. T ranked row 23: Miloš #752, H 1400; club and vehicle absent. Class agrees; first name and number differ. Peugeot is compatible with the prior make but lacks model; T cannot verify the model or club. Do not merge without a decision.

### Q4

Confirm name aliases and source-field inversions: Gligorije Šučić → Šušić; Margarita Malteševa → MALTSEVA; Dimitri Efemkin → EFIMIN DMITRI; ĐURAKOVIĆ Sakib → SAKIB DURAKOVIĆ; Nikolić Miljan → MILJAN NIKOLIĆ; Stanišić Veljko → VELJKO STANIŠIĆ.

Detailed vehicle/number/club/class comparisons follow. Names alone are insufficient.

### Q5

Confirm timing classes for Radomir Savićević #118 (H 1400 instead of registered H 2000) and Mladen Cebić #58 (H 2000+ instead of registered A 2000).

Practice and race agree with each other for both entrants, and differ from P. Proposed event entries use the timing class, preserving the registration discrepancy.

### Q6

Supply exact vehicle models or approve the proposed partial descriptions below; confirm Renault Mégane, VW Golf and Mini Cooper if those expansions are correct. Confirm shortened Corsa/Saxo/C2/Polo aliases too.

Do not inherit a prior model just because the driver is the same. Manufacturer-only records can have model NULL. Megan/Golf/Cooper lack an explicitly supplied manufacturer; those tentative expansions require confirmation, otherwise entry vehicle stays NULL.

### Q7

Keep practice run 2 times #510 21:59.700 and #81 21:26.509 as printed, or provide an evidenced correction?

Both unusually long times are legible in T. The best-of-two totals and gaps are arithmetically consistent. No digit correction has been invented.

### Q8

Approve NULL club/vehicle for timing-only Goran Vučković #16 and Igor Ivanović #237, and for Miloš Bracanović #752 if separate; approve no race result for practice-only entrants and no event entry for registration-only rows.

A prior affiliation/car or absence from a final finishers table does not establish an event value or DNS/DNF. Igor Ivanović is not matched to Mile Malović solely because Kotor used #237 for Mile.

## Proposed new drivers — verify each

These are candidates absent from committed driver identities, not claims that an unavailable live database lacks them. New nationality fields remain NULL. Vehicle/club/class below are exact registration descriptions unless noted.

| Proposed archive key | Source name | Bib P → T/R | Club / vehicle | Class P → T/R | Evidence |
| --- | --- | --- | --- | --- | --- |
| driver-milos-perunicic | Miloš Peruničić | not supplied → 558 | Monteracing / Yugo | H 1000 → H 1000 | P p.1 row 1; R ranked row 37; #558 |
| driver-bojan-martinovic | Bojan Martinović | 93 → 93 | AP SPORT / Yugo | A 1150 → A 1150 | P p.1 row 12; T ranked row 24; #93 |
| driver-ivan-vukcevic | Ivan Vukčević | not supplied → 896 | Monteracing / Peugeot | N 1400 → N 1400 | P p.1 row 19; R ranked row 21; #896 |
| driver-milos-milosev | Miloš Milošev | 221 → registration only | Herceg Novi / Peugeot | N 1400 → not timed | P p.1 row 21 |
| driver-bozo-kotlaja | Božo Kotlaja | 183 → 183 | PODI / Yugo | H 1400 → H 1400 | P p.1 row 32; T ranked row 53; #183 |
| driver-aleksandar-brkovic | Aleksandar Brković | 260 → 260 | Monteracing / Peugeot | N 1600 → N 1600 | P p.1 row 34; R ranked row 48; #260 |
| driver-mladen-cebic | Mladen Cebić | not supplied → 58 | Mugoša / Megan | A 2000 → H 2000 + | P p.2 row 18; R ranked row 34; #58 |
| driver-nikola-brkovic | Nikola Brković | 526 → 526 | Monteracing / Golf | H 2000 → H 2000 | P p.2 row 19; T ranked row 55; #526 |
| driver-aleksandar-krstevski | Aleksandar Krstevski | not supplied → 705 | Crmnica / Ford | H 2000 → H 2000 | P p.2 row 22; R ranked row 24; #705 |
| driver-radomir-savicevic | Radomir Savićević | 118 → 118 | Gorštak / Honda | H 2000 → H 1400 | P p.2 row 24; R ranked row 11; #118 |
| driver-veljko-stanisic | Stanišić Veljko | 87 → 87 | PODI / Cooper | H 2000+ → H 2000 + | P p.2 row 28; R ranked row 7; #87 |
| driver-igor-ivanovic | IVANOVIĆ Igor | not registered → 237 | NULL / NULL | H 2000 + | T ranked row 57; Run 1 8:13.445; Run 2 blank |

Miloš Milošev #221 is registration-only, with Peugeot / Herceg Novi / N 1400. Keep separate from Stevan Milošev #76 (Yugo / Herceg Novi / N 1150); surname and club do not prove identity. Igor Ivanović #237 has no club or vehicle in T. Kotor used #237 for Mile Malović (SEAT LEON, Podgorica, H 2000+), whose Kuči registration says Seat / Podgorica and no number. Do not merge Igor and Mile on number/class.

The initial separate-person proposal **driver-milos-bracanovic** was rejected after the programmer personally confirmed the existing Milan Bracanović identity. The accepted key is **driver-milan-bracanovic**; no additional person is created.

## Proposed new club and track

| Kind / proposed key | Source spelling / details | Evidence / missing facts |
| --- | --- | --- |
| club / club-mugosa | Mugoša | P p.2 row 18 (Mladen Cebić). Do not equate with AP SPORT merely because Slobodan Mugoša is a driver. Country, federation and full formal name remain unestablished. |
| track / track-kuci | KUČI 2026; Doljani → skretanje za Fundinu, Podgorica; ME; 3900 m | B p.5 art.2.1.5; M p.1. Width 6 m. No match to existing Kotor–Trojica or Goč. Length/configuration are evidenced for 2026 only. |

M visually labels ŠIKANA 1, 2, 3, 4; B p.5 says two bypasses and two slowing chicanes. Preserve both descriptions without asserting they describe identical obstacles. B p.5 art.3.1.1 cites 2025 technical rules while p.5 art.2.1.1 cites 2026 general rules. No regulation text is silently corrected. B p.8 specifies 1/100 second timing precision; supplied tables publish milliseconds, which are preserved as printed.

## Close name mismatches and comparative evidence

| Current spelling / proposed key | Current evidence | Comparison / agreement and differences |
| --- | --- | --- |
| P Gligorije Šučić → driver-gligorije-susic | P p.2 row 1; #33 / PODI / Saxo / N 1600. T row 34 and R row 31 use ŠUŠIĆ Gligorije. | Kotor and Goč GLIGORIJE ŠUŠIĆ, #33 / AMSK PODI / CITROEN SAXO / N 1600. Model, bib, club and class agree; P surname differs. Confirm P alias; source does not prove chassis. |
| P Margarita Malteševa → driver-margarita-maltseva | P p.2 row 4; #96 / Crmnica / Saxo / A 1600. T row 40 MALTSEVA Margarita. | Kotor MARGARITA MALTSEVA #96; Goč #961; both AMSK CRMNICA / CITROEN SAXO / A 1600. Same model, club/class; number agrees with Kotor and differs from Goč; P spelling differs. |
| P Dimitri Efemkin; T/R DMITRI Efimin → driver-efimin-dmitri | P p.2 row 27; #217 / Crmnica / BMW / H 2000+. T row 14/R row 15. | Kotor/Goč EFIMIN DMITRI #217 / AMSK CRMNICA / BMW M3 / H 2000+. Number, club/class agree. BMW is compatible with prior make but current model is unprinted; do not claim M3 or physical chassis without confirmation. P spelling/order differ. |
| T/R ĐURAKOVIĆ Sakib → driver-sakib-durakovic | P p.1 row 28 Sakib Duraković; #119 / Crmnica / Rover / A 1400. T row 38/R row 36 use ĐURAKOVIĆ. | Kotor SAKIB DURAKOVIĆ #119 / AMSK CRMNICA / ROVER / A 1400; Goč Sakib DURKOVIĆ #119 / same club / ROVER / A 1400 was already confirmed. Number, recorded vehicle description, club and class agree; timing surname has initial Đ. |
| P Nikolić Miljan → driver-miljan-nikolic | P p.1 row 26 puts Nikolić under IME, Miljan under PREZIME; #224 / Nilović / Peugeot / N 1400. T row 21/R row 16 NIKOLIĆ Miljan. | Kotor/Goč MILJAN NIKOLIĆ #224 / AMSK NILOVIĆ RACING / Peugeot 106 (Rally) / N 1400. Number, club/class agree; Peugeot make compatible but model absent; registration name fields inverted. |
| P Stanišić Veljko → driver-veljko-stanisic (new) | P p.2 row 28 puts Stanišić under IME, Veljko under PREZIME; #87 / PODI / Cooper / H 2000+. T row 7/R row 7 STANIŠIĆ Veljko. | No existing person matched. Registration field order inversion only; proposed new given name Veljko, surname Stanišić. Confirm. |
| T BRACANOVIĆ Miloš #752 ↔ registered Milan Bracanović #751 | P p.1 row 31: Milan / AP SPORT / Peugeot / H 1400; T row 23: Miloš / #752 / H 1400, with no vehicle or club. | Existing MILAN BRACANOVIĆ Kotor #152 / AMSK AP SPORT / PEUGEOT 106 / H 1400. P agrees on club/class and make only, lacks model, number changed. T first name and number differ from P; T model/club unavailable. Separate unresolved candidate until Q3. |

Other spelling differences retain exact source spellings as proposed aliases after confirmation. Existing confirmed aliases are recorded per registration in reconciliation.json. New alias suggestions never authorize person merging.

## Every registration: exact descriptions, candidates and prior evidence

Candidate means a proposed match based on the combined evidence below, never a final decision derived from a name alone. For existing exact names compare bib, class, club and vehicle; conflicting details remain visible. Short makes are not expanded into earlier models.

| P locator / name | P bib / club / vehicle / class | Proposed driver key / state | T/R bib and source class | Previous evidenced entry |
| --- | --- | --- | --- | --- |
| P p.1 row 1; Miloš Peruničić | NULL / Monteracing / Yugo / H 1000 | driver-milos-perunicic / proposed-new | #558; H 1000 | No matching prior entry established. |
| P p.1 row 2; Nikola Tošković | 500 / AP SPORT / Yugo / H 1000 | driver-nikola-toskovic / existing-candidate | #500; H 1000 | NIKOLA TOŠKOVIĆ; #500; AMSK AP SPORT; H 1000; NISAN MICRA; Kotor R p.2 #500 |
| P p.1 row 3; Andrija Đurišić | 12 / AP SPORT / Yugo / H 1000 | driver-andrija-durisic / existing-candidate | #12; H 1000 | ANDRIJA ĐURIŠIĆ; #121; AMSK AP sport; H 1000; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 23; #121 |
| P p.1 row 4; Vojo Radanović | 81 / Herceg Novi / Peugeot / H 1000 | driver-vojo-radanovic / existing-candidate | #81; H 1000 | VOJO RADANOVIĆ; #81; AMSK Herceg Novi; H 1000; Peugeot 106; originals/generalni_plasman.html; table res-results-tbody row 39; #81 |
| P p.1 row 5; Luka Brkan | 160 / Tivat / Yugo / N 1150 | driver-luka-brkan / existing-candidate | #160; N 1150 | LUKA BRKAN; #160; ASK TIVAT; N 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 34; #160 |
| P p.1 row 6; Milorad Ražnatović | 287 / Tivat / Yugo / N 1150 | driver-milorad-raznatovic / existing-candidate | #287; N 1150 | MILORAD RAŽNATOVIĆ; #287; ASK TIVAT; N 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 32; #287 |
| P p.1 row 7; Stevan Miličković | 510 / AP SPORT / Yugo / N 1150 | driver-stevan-milickovic / existing-candidate | #510; N 1150 | STEVAN MILIČKOVIĆ; #510; AMSK AP sport; N 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 33; #510 |
| P p.1 row 8; Dragan Džogaz | 200 / AP SPORT / Yugo / N 1150 | driver-dragan-dzogaz / existing-candidate | #200; N 1150 | DRAGAN DŽOGAZ; #200; AMSK AP sport; N 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 35; #200 |
| P p.1 row 9; Stevan Milošev | 76 / Herceg Novi / Yugo / N 1150 | driver-stevan-milosev / existing-candidate | #76; N 1150 | STEVAN MILOŠEV; #76; AMSK Herceg Novi; N 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 36; #76 |
| P p.1 row 10; Igor Radmilović | 155 / Herceg Novi / Yugo / N 1150 | driver-igor-radmilovic / existing-candidate | #155; N 1150 | IGOR RADMILOVIĆ; #155; AMSK Herceg Novi; N 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 29; #155 |
| P p.1 row 11; Matija Marković | 153 / Lovćen / Yugo / N 1150 | driver-matija-markovic / existing-candidate | #153; N 1150 | MATIJA MARKOVIĆ; #353; AMKK LOVĆEN racing team; N 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 30; #353 |
| P p.1 row 12; Bojan Martinović | 93 / AP SPORT / Yugo / A 1150 | driver-bojan-martinovic / proposed-new | #93; A 1150 | No matching prior entry established. |
| P p.1 row 13; Sava Nikčević | 197 / Goršak / Yugo / A 1150 | driver-sava-nikcevic / existing-candidate | #197; A 1150 | SAVA NIKČEVIĆ; #197; AMSK GORŠTAK; A 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 22; #197 |
| P p.1 row 14; Božidar Doderović | 92 / PODI / Yugo / A 1150 | driver-bozidar-doderovic / existing-candidate | #92; A 1150 | BOŽIDAR DODEROVIĆ; #92; AMSK PODI; A 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 18; #92 |
| P p.1 row 15; Dragan Mudreša | 236 / PODI / Yugo / A 1150 | driver-dragan-mudresa / existing-candidate | #236; A 1150 | DRAGAN MUDREŠA; #236; AMSK PODI; A 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 17; #236 |
| P p.1 row 16; Spasoje Nikolić | 442 / PODI / Yugo / A 1150 | driver-spasoje-nikolic / existing-candidate | #442; A 1150 | SPASOJE NIKOLIĆ; #442; AMSK PODI; A 1150; ZASTAVA YUGO; Kotor R p.3 #442 |
| P p.1 row 17; Petar Jovanović | 30 / PODI / Yugo / A 1150 | driver-petar-jovanovic / existing-candidate | #30; A 1150 | PETAR JOVANOVIĆ; #30; ASU Zeka Auto sport; A 1150; Zastava Yugo 55; originals/generalni_plasman.html; table res-results-tbody row 21; #30 |
| P p.1 row 18; Marko Pejović | NULL / Podgorica / Peugeot / N 1400 | driver-marko-pejovic / existing-candidate | #231; N 1400 | MARKO PEJOVIĆ; #231; AMK PODGORICA RT; N 1400; PEUGEOT 106; Kotor R p.1 #231 |
| P p.1 row 19; Ivan Vukčević | NULL / Monteracing / Peugeot / N 1400 | driver-ivan-vukcevic / proposed-new | #896; N 1400 | No matching prior entry established. |
| P p.1 row 20; Dejan Đonović | 89 / Crmnica / Peugeot / N 1400 | driver-dejan-donovic / existing-candidate | #89; N 1400 | DEJAN ĐONOVIĆ; #89; AMSK CRMNICA; N 1400; Peugeot 106 Rally; originals/generalni_plasman.html; table res-results-tbody row 13; #89 |
| P p.1 row 21; Miloš Milošev | 221 / Herceg Novi / Peugeot / N 1400 | driver-milos-milosev / proposed-new | registration only | No matching prior entry established. |
| P p.1 row 22; Miloš Vujnović | 13 / Herceg Novi / Peugeot / N 1400 | driver-milos-vujnovic / existing-candidate | #13; N 1400 | MILOŠ VUJNOVIĆ; #13; AMSK Herceg Novi; N 1400; Peugeot 106; originals/generalni_plasman.html; table res-results-tbody row 16; #13 |
| P p.1 row 23; Đorđe Kovačević | 18 / Herceg Novi / Peugeot / N 1400 | driver-dorde-kovacevic / existing-candidate | #18; N 1400 | ĐORĐE KOVAČEVIĆ; #18; AMSK Herceg Novi; N 1400; Peugeot 106; originals/generalni_plasman.html; table res-results-tbody row 31; #18 |
| P p.1 row 24; Dejan Kovačević | 19 / Herceg Novi / Peugeot / N 1400 | driver-dejan-kovacevic / existing-candidate | #19; N 1400 | DEJAN KOVAČEVIĆ; #19; AMSK Herceg Novi; N 1400; VolksWagen Polo; originals/generalni_plasman.html; table res-results-tbody row 15; #19 |
| P p.1 row 25; Ilija Šapić | 17 / Herceg Novi / Peugeot / N 1400 | driver-ilija-sapic / existing-candidate | registration only | ILIJA ŠAPIĆ; #17; AMSK Herceg Novi; N 1400; Peugeot 106; originals/generalni_plasman.html; table res-results-tbody row 42; #17 |
| P p.1 row 26; Nikolić Miljan | 224 / Nilović / Peugeot / N 1400 | driver-miljan-nikolic / name-conflict-candidate | #224; N 1400 | MILJAN NIKOLIĆ; #224; AMSK NILOVIĆ Racing; N 1400; Peugeot 106 Rally; originals/generalni_plasman.html; table res-results-tbody row 46; #224 |
| P p.1 row 27; Stefan Bokovac | 95 / Crmnica / Polo / A 1400 | driver-stefan-bokovac / existing-candidate | #95; A 1400 | STEFAN BOKOVAC; #95; AMSK CRMNICA; A 1400; VW POLO; Kotor R p.2 #95 |
| P p.1 row 28; Sakib Duraković | 119 / Crmnica / Rover / A 1400 | driver-sakib-durakovic / existing-candidate | #119; A 1400 | SAKIB DURAKOVIĆ; #119; AMSK CRMNICA; A 1400; Rover; originals/generalni_plasman.html; table res-results-tbody row 27; #119 |
| P p.1 row 29; Milutin Krivokapić | 115 / Herceg Novi / Peugeot / A 1400 | driver-milutin-krivokapic / existing-candidate | #115; A 1400 | MILUTIN KRIVOKAPIĆ; #115; AMSK Herceg Novi; A 1400; Peugeot 106; originals/generalni_plasman.html; table res-results-tbody row 14; #115 |
| P p.1 row 30; Marko Adžić | 23 / PODI / Rover / A 1400 | driver-marko-adzic / existing-candidate | #23; A 1400 | MARKO ADŽIĆ; #23; AMSK PODI; A 1400; Rover MG ZR 25; originals/generalni_plasman.html; table res-results-tbody row 11; #23 |
| P p.1 row 31; Milan Bracanović | 751 / AP SPORT / Peugeot / H 1400 | driver-milan-bracanovic / existing-candidate | registration only | MILAN BRACANOVIĆ; #152; AMSK AP SPORT; H 1400; PEUGEOT 106; Kotor R p.2 #152 |
| P p.1 row 32; Božo Kotlaja | 183 / PODI / Yugo / H 1400 | driver-bozo-kotlaja / proposed-new | #183; H 1400 | No matching prior entry established. |
| P p.1 row 33; Luka Šućur | 222 / PODI / Suzuki / H 1400 | driver-luka-sucur / existing-candidate | #222; H 1400 | LUKA ŠUĆUR; #222; AMSK PODI; H 1400; Suzuki Swift GTI; originals/generalni_plasman.html; table res-results-tbody row 25; #222 |
| P p.1 row 34; Aleksandar Brković | 260 / Monteracing / Peugeot / N 1600 | driver-aleksandar-brkovic / proposed-new | #260; N 1600 | No matching prior entry established. |
| P p.1 row 35; Ena Plakalo | 660 / AP SPORT / Saxo / N 1600 | driver-ena-plakalo / existing-candidate | #666; N 1600 | ENA PLAKALO; #666; AMSK AP sport; N 1600; Citroën Saxo; originals/generalni_plasman.html; table res-results-tbody row 26; #666 |
| P p.1 row 36; Slobodan Mugoša | 29 / AP SPORT / Peugeot / N 1600 | driver-slobodan-mugosa / existing-candidate | #29; N 1600 | SLOBODAN MUGOŠA; #29; AMSK AP sport; N 1600; Peugeot 106; originals/generalni_plasman.html; table res-results-tbody row 12; #29 |
| P p.2 row 1; Gligorije Šučić | 33 / PODI / Saxo / N 1600 | driver-gligorije-susic / name-conflict-candidate | #33; N 1600 | GLIGORIJE ŠUŠIĆ; #33; AMSK PODI; N 1600; Citroën Saxo; originals/generalni_plasman.html; table res-results-tbody row 28; #33 |
| P p.2 row 2; Mirko Vujačić | 7 / Crmnica / C2 / A 1600 | driver-mirko-vujacic / existing-candidate | #7; A 1600 | MIRKO VUJAČIĆ; #7; AMSK CRMNICA; A 1600; Citroen C2; originals/generalni_plasman.html; table res-results-tbody row 5; #7 |
| P p.2 row 3; Marija Ivanović | 48 / Monteracing / Saxo / A 1600 | driver-marija-ivanovic / existing-candidate | #48; A 1600 | MARIJA IVANOVIĆ; #48; AMK MONTE racing; A 1600; Citroën Saxo; originals/generalni_plasman.html; table res-results-tbody row 20; #48 |
| P p.2 row 4; Margarita Malteševa | 96 / Crmnica / Saxo / A 1600 | driver-margarita-maltseva / name-conflict-candidate | #96; A 1600 | MARGARITA MALTSEVA; #961; AMSK CRMNICA; A 1600; Citroën Saxo; originals/generalni_plasman.html; table res-results-tbody row 24; #961 |
| P p.2 row 5; Damjan Perović | 416 / Kotor / Honda / H 1600 | driver-damjan-perovic / existing-candidate | #416; H 1600 | DAMJAN PEROVIĆ; #416; AMSK KOTOR; H 1600; ; originals/generalni_plasman.html; table res-results-tbody row 43; #416 |
| P p.2 row 6; Darko Kokotović | 31 / Herceg Novi / Corsa / H 1600 | driver-darko-kokotovic / existing-candidate | #151; H 1600 | DARKO KOKOTOVIĆ; #151; AMSK HERCEG NOVI; H 1600; OPEL CORSA; Kotor R p.1 #151 |
| P p.2 row 7; Miloš Bojanović | 31 / Gorštak / Peugeot / H 1600 | driver-milos-bojanovic / existing-candidate | #31; H 1600 | MILOŠ BOJANOVIĆ; #310; AMSK GORŠTAK; H 1600; Peugeot 106; originals/generalni_plasman.html; table res-results-tbody row 6; #310 |
| P p.2 row 8; Balša Đuričković | 262 / Gorštak / Škoda / E1 MOTO | driver-balsa-durickovic / existing-candidate | #262; E1 1200 | BALŠA ĐURIČKOVIĆ; #262; AMSK GORŠTAK; E1 1200; ŠKODA FELICIA; Kotor R p.1 #262 |
| P p.2 row 9; Đuro Popović | 71 / PODI / Suzuki / E1 MOTO | driver-duro-popovic / existing-candidate | #71; E1 1200 | ĐURO POPOVIĆ; #71; AMSK PODI; E1 1200; SUZUKI SWIFT; Kotor R p.1 #71 |
| P p.2 row 10; Tripo Pinjatić | 88 / Crmnica / Fiat 500 / E2 SH | driver-tripo-pinjatic / existing-candidate | #88; E2SH | TRIPO PINJATIĆ; #88; AMSK CRMNICA; E2SH; FIAT 500; Kotor R p.1 #88 |
| P p.2 row 11; Jovan Perunović | 36 / PODI / Fiat 750 / E2 Sh | driver-jovan-perunovic / existing-candidate | #36; E2SH | JOVAN PERUNOVIĆ; #36; AMSK PODI; E2SH; Fiat 750; originals/generalni_plasman.html; table res-results-tbody row 2; #36 |
| P p.2 row 12; Dušan Tujković | 26 / Kotor / Honda / N 2000 | driver-dusan-tujkovic / existing-candidate | #26; N 2000 | DUŠAN TUJKOVIĆ; #26; AMSK KOTOR; N 2000; Honda Civic; originals/generalni_plasman.html; table res-results-tbody row 8; #26 |
| P p.2 row 13; Marko Janković | 80 / Gorštak / Honda / N 2000 | driver-marko-jankovic / existing-candidate | #80; N 2000 | MARKO JANKOVIĆ; #80; AMSK GORŠTAK; N 2000; Honda Civic; originals/generalni_plasman.html; table res-results-tbody row 4; #80 |
| P p.2 row 14; Goran Vučetić | 68 / PODI / Honda / N 2000 | driver-goran-vucetic / existing-candidate | #68; N 2000 | GORAN VUČETIĆ; #681; AMSK PODI; N 2000; Honda Civic Type R; originals/generalni_plasman.html; table res-results-tbody row 45; #681 |
| P p.2 row 15; Almin Ćorović | 506 / AP SPORT / Opel / A 2000 | driver-almin-corovic / existing-candidate | #506; A 2000 | ALMIN ĆOROVIĆ; #506; AMSK AP sport; A 2000; Opel Astra; originals/generalni_plasman.html; table res-results-tbody row 19; #506 |
| P p.2 row 16; Radovan Masoničić | 43 / Crmnica / Honda / A 2000 | driver-radovan-masonicic / existing-candidate | #43; A 2000 | RADOVAN MASONIČIĆ; #43; AMSK CRMNICA; A 2000; Honda Civic Type R; originals/generalni_plasman.html; table res-results-tbody row 9; #43 |
| P p.2 row 17; Radovan Radanović | 930 / Heceg Novi / Opel / A 2000 | driver-radovan-radanovic / existing-candidate | registration only | RADOVAN RADANOVIĆ; #930; AMSK Herceg Novi; A 2000; Opel Astra; originals/generalni_plasman.html; table res-results-tbody row 37; #930 |
| P p.2 row 18; Mladen Cebić | NULL / Mugoša / Megan / A 2000 | driver-mladen-cebic / proposed-new | #58; H 2000 + | No matching prior entry established. |
| P p.2 row 19; Nikola Brković | 526 / Monteracing / Golf / H 2000 | driver-nikola-brkovic / proposed-new | #526; H 2000 | No matching prior entry established. |
| P p.2 row 20; Dejan Bulatović | 12 / Kotor / Peugeot / H 2000 | driver-dejan-bulatovic / existing-candidate | registration only | DEJAN BULATOVIĆ; #12; AMSK KOTOR; H 2000; Peugeot 306; originals/generalni_plasman.html; table res-results-tbody row 41; #12 |
| P p.2 row 21; Milan Jakšić | 44 / Kotor / Honda / H 2000 | driver-milan-jaksic / existing-candidate | registration only | MILAN JAKŠIĆ; #44; AMSK KOTOR; 1 H H 2000 AKSCG KUP KUP; HONDA TYPER; Kotor P p.1 R.B.60 |
| P p.2 row 22; Aleksandar Krstevski | NULL / Crmnica / Ford / H 2000 | driver-aleksandar-krstevski / proposed-new | #705; H 2000 | No matching prior entry established. |
| P p.2 row 23; Nikola Vlahović | 47 / Vlahović / Golf / H 2000 | driver-nikola-vlahovic / existing-candidate | #47; H 2000 | NIKOLA VLAHOVIĆ; #75; AMSK VLAHOVIĆ; N 1400; PEUGEOT 106; Kotor R p.2 #75 |
| P p.2 row 24; Radomir Savićević | 118 / Gorštak / Honda / H 2000 | driver-radomir-savicevic / proposed-new | #118; H 1400 | No matching prior entry established. |
| P p.2 row 25; Lazar Mujović | 53 / PODI / BMW / H 2000 | driver-lazar-mujovic / existing-candidate | #53; H 2000 | LAZAR MUJOVIĆ; #53; AMSK PODI; H 2000; BMW E46; Kotor R p.2 #53 |
| P p.2 row 26; Mile Malović | NULL / Podgorica / Seat / H 2000+ | driver-mile-malovic / existing-candidate | registration only | MILE MALOVIĆ; #237; AMK PODGORICA RT; H 2000+; SEAT LEON; Kotor R p.3 #237 |
| P p.2 row 27; Dimitri Efemkin | 217 / Crmnica / BMW / H 2000+ | driver-efimin-dmitri / name-conflict-candidate | #217; H 2000 + | EFIMIN DMITRI; #217; AMSK CRMNICA; H 2000+; BMW M3; originals/generalni_plasman.html; table res-results-tbody row 10; #217 |
| P p.2 row 28; Stanišić Veljko | 87 / PODI / Cooper / H 2000+ | driver-veljko-stanisic / proposed-new | #87; H 2000 + | No matching prior entry established. |
| P p.2 row 29; Aldin Ćorović | 21 / AP SPORT / Global / CN FORMULA | driver-aldin-corovic / existing-candidate | #21; DC N/ E2 / CN | ALDIN ĆOROVIĆ; #21; AMSK AP sport; E2SH; GLOBAL GT LIGHTS; originals/generalni_plasman.html; table res-results-tbody row 40; #21 |
| P p.2 row 30; Baćko Vujović | 4 / Gorštak / Norma / CN FORMULA | driver-backo-vujovic / existing-candidate | #4; DC N/ E2 / CN | BAĆKO VUJOVIĆ; #14; AMSK GORŠTAK; E2SH; NORMA M20; originals/generalni_plasman.html; table res-results-tbody row 1; #14 |

Goran Vučković #16 is absent from P but present in T/R, name/bib/class agree with previous #16 / H 2000+; prior AMSK KOTOR / VW GOLF V cannot establish this event’s club or model. Keep both NULL unless programmer supplies evidence. Do not misassign #12 registration Dejan Bulatović to #12 timed Andrija Đurišić, or #31 registration Darko Kokotović to timed Miloš Bojanović.

## Clubs: all source spellings

| Source spelling | Candidate key | Evidence / concerns |
| --- | --- | --- |
| Monteracing | club-amk-monteracing | P p.1 row 1 Miloš Peruničić; P p.1 row 19 Ivan Vukčević; P p.1 row 34 Aleksandar Brković; P p.2 row 3 Marija Ivanović; P p.2 row 19 Nikola Brković |
| AP SPORT | club-amsk-ap-sport | P p.1 row 2 Nikola Tošković; P p.1 row 3 Andrija Đurišić; P p.1 row 7 Stevan Miličković; P p.1 row 8 Dragan Džogaz; P p.1 row 12 Bojan Martinović; P p.1 row 31 Milan Bracanović; P p.1 row 35 Ena Plakalo; P p.1 row 36 Slobodan Mugoša; P p.2 row 15 Almin Ćorović; P p.2 row 29 Aldin Ćorović |
| Herceg Novi | club-amsk-herceg-novi | P p.1 row 4 Vojo Radanović; P p.1 row 9 Stevan Milošev; P p.1 row 10 Igor Radmilović; P p.1 row 21 Miloš Milošev; P p.1 row 22 Miloš Vujnović; P p.1 row 23 Đorđe Kovačević; P p.1 row 24 Dejan Kovačević; P p.1 row 25 Ilija Šapić; P p.1 row 29 Milutin Krivokapić; P p.2 row 6 Darko Kokotović |
| Tivat | club-amsk-tivat | P p.1 row 5 Luka Brkan; P p.1 row 6 Milorad Ražnatović |
| Lovćen | club-amkk-lovcen-r-t | P p.1 row 11 Matija Marković |
| Goršak | club-amsk-gorstak | P p.1 row 13 Sava Nikčević; typo candidate requiring confirmation; compare same drivers and vehicles/classes in registration/prior-entry table |
| PODI | club-amsk-podi | P p.1 row 14 Božidar Doderović; P p.1 row 15 Dragan Mudreša; P p.1 row 16 Spasoje Nikolić; P p.1 row 17 Petar Jovanović; P p.1 row 30 Marko Adžić; P p.1 row 32 Božo Kotlaja; P p.1 row 33 Luka Šućur; P p.2 row 1 Gligorije Šučić; P p.2 row 9 Đuro Popović; P p.2 row 11 Jovan Perunović; P p.2 row 14 Goran Vučetić; P p.2 row 25 Lazar Mujović; P p.2 row 28 Stanišić Veljko |
| Podgorica | club-amk-podgorica-rt | P p.1 row 18 Marko Pejović; P p.2 row 26 Mile Malović |
| Crmnica | club-amsk-crmnica | P p.1 row 20 Dejan Đonović; P p.1 row 27 Stefan Bokovac; P p.1 row 28 Sakib Duraković; P p.2 row 2 Mirko Vujačić; P p.2 row 4 Margarita Malteševa; P p.2 row 10 Tripo Pinjatić; P p.2 row 16 Radovan Masoničić; P p.2 row 22 Aleksandar Krstevski; P p.2 row 27 Dimitri Efemkin |
| Nilović | club-amsk-nilovic-racing | P p.1 row 26 Nikolić Miljan |
| Kotor | club-amsk-kotor | P p.2 row 5 Damjan Perović; P p.2 row 12 Dušan Tujković; P p.2 row 20 Dejan Bulatović; P p.2 row 21 Milan Jakšić |
| Gorštak | club-amsk-gorstak | P p.2 row 7 Miloš Bojanović; P p.2 row 8 Balša Đuričković; P p.2 row 13 Marko Janković; P p.2 row 24 Radomir Savićević; P p.2 row 30 Baćko Vujović |
| Heceg Novi | club-amsk-herceg-novi | P p.2 row 17 Radovan Radanović; typo candidate requiring confirmation; compare same drivers and vehicles/classes in registration/prior-entry table |
| Mugoša | club-mugosa (new; approved) | P p.2 row 18 Mladen Cebić |
| Vlahović | club-amsk-vlahovic | P p.2 row 23 Nikola Vlahović |

Petar Jovanović is recorded as PODI here, matching Kotor, whereas Goč recorded ASU Zeka Auto sport. Use this event’s source affiliation if approved; never rewrite Goč. Club mappings use participant context and prior evidence, not abbreviation alone.

## Vehicles: known descriptions and proposed shortened aliases

| Source spelling | Existing candidate key | Context |
| --- | --- | --- |
| Yugo | vehicle-yugo | P p.1 row 1 Miloš Peruničić; P p.1 row 2 Nikola Tošković; P p.1 row 3 Andrija Đurišić; P p.1 row 5 Luka Brkan; P p.1 row 6 Milorad Ražnatović; P p.1 row 7 Stevan Miličković; P p.1 row 8 Dragan Džogaz; P p.1 row 9 Stevan Milošev; P p.1 row 10 Igor Radmilović; P p.1 row 11 Matija Marković; P p.1 row 12 Bojan Martinović; P p.1 row 13 Sava Nikčević; P p.1 row 14 Božidar Doderović; P p.1 row 15 Dragan Mudreša; P p.1 row 16 Spasoje Nikolić; P p.1 row 17 Petar Jovanović; P p.1 row 32 Božo Kotlaja |
| Polo | vehicle-vw-polo | P p.1 row 27 Stefan Bokovac |
| Rover | vehicle-rover | P p.1 row 28 Sakib Duraković; P p.1 row 30 Marko Adžić |
| Saxo | vehicle-citroen-saxo | P p.1 row 35 Ena Plakalo; P p.2 row 1 Gligorije Šučić; P p.2 row 3 Marija Ivanović; P p.2 row 4 Margarita Malteševa |
| C2 | vehicle-citroen-c2 | P p.2 row 2 Mirko Vujačić |
| Corsa | vehicle-opel-corsa | P p.2 row 6 Darko Kokotović |
| Fiat 500 | vehicle-fiat-500 | P p.2 row 10 Tripo Pinjatić |
| Fiat 750 | vehicle-fiat-750 | P p.2 row 11 Jovan Perunović |
| BMW | vehicle-bmw | P p.2 row 25 Lazar Mujović; P p.2 row 27 Dimitri Efemkin |
| Global | vehicle-global | P p.2 row 29 Aldin Ćorović |

Yugo, BMW, Rover, Fiat 500/750 and Global directly match existing reusable descriptions. Saxo→CITROEN SAXO, C2→CITROEN C2, Corsa→OPEL CORSA and Polo→VW POLO are shortened-model candidates, supported by participant context and established catalog descriptions; verify them in Q6. BMW remains the less specific existing vehicle-bmw, not automatically M3. Rover remains vehicle-rover, not automatically ROVER MG. No mapping claims a physical chassis.

## Every proposed new or unresolved vehicle description — verify each

Partial known makes below are valid reusable descriptions with model NULL if approved; they are not fabricated Unknown-car placeholders. These proposals are alternatives to a programmer-confirmed existing model, not requests to create both. For Megan/Golf/Cooper, manufacturing identities are tentative and must be explicitly confirmed or the event vehicle stays NULL.

| Exact source spelling / locators | Proposed new key | Proposed description / required confirmation | Previous-model comparison |
| --- | --- | --- | --- |
| Peugeot; P p.1 row 4 Vojo Radanović; P p.1 row 18 Marko Pejović; P p.1 row 19 Ivan Vukčević; P p.1 row 20 Dejan Đonović; P p.1 row 21 Miloš Milošev; P p.1 row 22 Miloš Vujnović; P p.1 row 23 Đorđe Kovačević; P p.1 row 24 Dejan Kovačević; P p.1 row 25 Ilija Šapić; P p.1 row 26 Nikolić Miljan; P p.1 row 29 Milutin Krivokapić; P p.1 row 31 Milan Bracanović; P p.1 row 34 Aleksandar Brković; P p.1 row 36 Slobodan Mugoša; P p.2 row 7 Miloš Bojanović; P p.2 row 20 Dejan Bulatović | vehicle-peugeot | PEUGEOT; model NULL | Prior entries distinguish Peugeot 106 and Peugeot 306. Manufacturer alone does not select either. Dejan Kovačević previously VW POLO; Nikola Tošković now Yugo instead of NISAN MICRA. |
| Suzuki; P p.1 row 33 Luka Šućur; P p.2 row 9 Đuro Popović | vehicle-suzuki | SUZUKI; model NULL | Đuro Popović and Luka Šućur previously SUZUKI SWIFT GTI; brand alone cannot establish the current model. |
| Honda; P p.2 row 5 Damjan Perović; P p.2 row 12 Dušan Tujković; P p.2 row 13 Marko Janković; P p.2 row 14 Goran Vučetić; P p.2 row 16 Radovan Masoničić; P p.2 row 21 Milan Jakšić; P p.2 row 24 Radomir Savićević | vehicle-honda | HONDA; model NULL | Existing drivers have evidenced HONDA CIVIC descriptions; Radomir Savićević is new. Do not expand every Honda into Civic without confirmation. |
| Škoda; P p.2 row 8 Balša Đuričković | vehicle-skoda | ŠKODA; model NULL | Balša Đuričković previously ŠKODA FELICIA; current source supplies only make. |
| Opel; P p.2 row 15 Almin Ćorović; P p.2 row 17 Radovan Radanović | vehicle-opel | OPEL; model NULL | Almin Ćorović and Radovan Radanović previously OPEL ASTRA; model absent here. |
| Megan; P p.2 row 18 Mladen Cebić | vehicle-renault-megane | Renault Mégane ONLY IF programmer confirms manufacturer and spelling | New description; Mladen Cebić. Source says Megan, not manufacturer. Otherwise keep vehicle NULL. |
| Golf; P p.2 row 19 Nikola Brković; P p.2 row 23 Nikola Vlahović | vehicle-vw-golf | VW Golf without generation ONLY IF programmer confirms | Nikola Brković and Nikola Vlahović. Current source gives no generation; previous Vlahović was VW GOLF I. Otherwise keep vehicle NULL. |
| Ford; P p.2 row 22 Aleksandar Krstevski | vehicle-ford | FORD; model NULL | Aleksandar Krstevski is new; model not supplied. |
| Seat; P p.2 row 26 Mile Malović | vehicle-seat | SEAT; model NULL | Mile Malović previously SEAT LEON; registration only here. |
| Cooper; P p.2 row 28 Stanišić Veljko | vehicle-mini-cooper | Mini Cooper ONLY IF programmer confirms manufacturer/model | Veljko Stanišić is new; source says Cooper. Otherwise keep vehicle NULL. |
| Norma; P p.2 row 30 Baćko Vujović | vehicle-norma | NORMA; model NULL | Baćko Vujović previously NORMA N202B; Goč NORMA M20 explicitly mapped to that description for him. Current source only Norma; can reuse existing key if confirmed. |

Damjan Perović’s Goč vehicle was NULL; Kuči P supplies Honda only. Nikola Tošković’s Kuči registration Yugo differs from Kotor NISAN MICRA. Dejan Kovačević’s Kuči Peugeot differs from previously confirmed VW POLO. These are new event descriptions, never corrections of historical entries.

## Classes and sessions

| Exact source class | Candidate existing key | Evidence |
| --- | --- | --- |
| H 1000 | class-akscg-h-1000 | No new class proposed. |
| N 1150 | class-akscg-n-1150 | No new class proposed. |
| A 1150 | class-akscg-a-1150 | No new class proposed. |
| N 1400 | class-akscg-n-1400 | No new class proposed. |
| A 1400 | class-akscg-a-1400 | No new class proposed. |
| H 1400 | class-akscg-h-1400 | No new class proposed. |
| N 1600 | class-akscg-n-1600 | No new class proposed. |
| A 1600 | class-akscg-a-1600 | No new class proposed. |
| H 1600 | class-akscg-h-1600 | No new class proposed. |
| E1 MOTO | class-akscg-e1-1200 | P p.2 rows 8–9; T uses E1 1200 for #262/#71 and R #71. No new class proposed. |
| E2 SH | class-akscg-e2sh | No new class proposed. |
| E2 Sh | class-akscg-e2sh | No new class proposed. |
| N 2000 | class-akscg-n-2000 | No new class proposed. |
| A 2000 | class-akscg-a-2000 | No new class proposed. |
| H 2000 | class-akscg-h-2000 | No new class proposed. |
| H 2000+ | class-akscg-h-2000-plus | No new class proposed. |
| CN FORMULA | class-akscg-d-e2-cn | P p.2 rows 29–30; T #21/#4 use DC N/ E2 / CN. No new class proposed. |
| DC N/ E2 / CN | class-akscg-d-e2-cn | No new class proposed. |
| E2SH | class-akscg-e2sh | No new class proposed. |
| H 2000 + | class-akscg-h-2000-plus | No new class proposed. |
| E1 1200 | class-akscg-e1-1200 | No new class proposed. |

Use E2SH separately for Jovan Perunović and Tripo Pinjatić; Aldin/Baćko belong to D/E2/CN candidate, matching the accepted Goč distinction. Q5 must resolve #118 and #58 registration/timing differences.

| Proposed session | Date | Evidenced rows | How ranks are treated |
| --- | --- | --- | --- |
| Trening 1 / PRACTICE | 2026-06-20 | 62: 54 timed + 2 DNS + 6 DNF | No individual-run ranks printed; leave them NULL. |
| Trening 2 / PRACTICE | 2026-06-20 | 47: 42 timed + 2 DNS + 3 DNF | 15 other drivers have blank Run 2 without explicit status; create no outcome for those blanks. |
| Trka 1 / RACE | 2026-06-21 | 49 timed finishers | Published overall/class rank is for the aggregate, not this run; NULL individual-run ranks. |
| Trka 2 / RACE | 2026-06-21 | 49 timed finishers | No invented DNS/DNF for other entrants. |

T best-of-two Time/rank is an aggregate, never a third practice run. R sum/rank is the combined final classification, never a third race run. Proposed final race classifications: overall plus 16 classes (17 tables, 98 classification rows if scope/identities are confirmed). Copy the printed class position, not a position rederived from single runs. Awarded points remain NULL; calculated season points can use the previously confirmed rules only after the event’s required championship/class context is resolved.

## Race rows: full transcription and checks

All 49 sums, gaps, position order and class ranks match. R has no DNS/DNF/DSQ section or awarded points. Finality is programmer-confirmed as recorded above.

| R rank / bib / source name | Run 1 | Run 2 | Total | Gap | Class / rank |
| --- | --- | --- | --- | --- | --- |
| 1 / #21 / ĆOROVIĆ Aldin | 2:02.948 | 2:05.449 | 4:08.397 | winner | DC N/ E2 / CN / 1 |
| 2 / #36 / PERUNOVIĆ Jovan | 2:10.196 | 2:10.212 | 4:20.408 | 12.011 | E2SH / 1 |
| 3 / #88 / PINJATIĆ Tripo | 2:15.796 | 2:14.311 | 4:30.107 | 21.710 | E2SH / 2 |
| 4 / #31 / BOJANOVIĆ Miloš | 2:16.436 | 2:14.224 | 4:30.660 | 22.263 | H 1600 / 1 |
| 5 / #7 / VUJAČIĆ Mirko | 2:15.755 | 2:15.726 | 4:31.481 | 23.084 | A 1600 / 1 |
| 6 / #16 / VUČKOVIĆ Goran | 2:17.525 | 2:14.701 | 4:32.226 | 23.829 | H 2000 + / 1 |
| 7 / #87 / STANIŠIĆ Veljko | 2:16.987 | 2:16.118 | 4:33.105 | 24.708 | H 2000 + / 2 |
| 8 / #80 / JANKOVIĆ Marko | 2:19.219 | 2:19.244 | 4:38.463 | 30.066 | N 2000 / 1 |
| 9 / #26 / TUJKOVIĆ Dušan | 2:20.402 | 2:19.850 | 4:40.252 | 31.855 | N 2000 / 2 |
| 10 / #68 / VUČETIĆ Goran | 2:20.742 | 2:21.052 | 4:41.794 | 33.397 | N 2000 / 3 |
| 11 / #118 / SAVIĆEVIĆ Radomir | 2:22.402 | 2:22.936 | 4:45.338 | 36.941 | H 1400 / 1 |
| 12 / #71 / POPOVIĆ Đuro | 2:22.918 | 2:25.038 | 4:47.956 | 39.559 | E1 1200 / 1 |
| 13 / #416 / PEROVIĆ Damjan | 2:23.832 | 2:24.396 | 4:48.228 | 39.831 | H 1600 / 2 |
| 14 / #43 / MASONIČIĆ Radovan | 2:25.446 | 2:23.077 | 4:48.523 | 40.126 | A 2000 / 1 |
| 15 / #217 / DMITRI Efimin | 2:25.834 | 2:24.089 | 4:49.923 | 41.526 | H 2000 + / 3 |
| 16 / #224 / NIKOLIĆ Miljan | 2:32.390 | 2:31.545 | 5:03.935 | 55.538 | N 1400 / 1 |
| 17 / #89 / ĐONOVIĆ Dejan | 2:30.862 | 2:33.289 | 5:04.151 | 55.754 | N 1400 / 2 |
| 18 / #23 / ADŽIĆ Marko | 2:34.500 | 2:31.391 | 5:05.891 | 57.494 | A 1400 / 1 |
| 19 / #47 / VLAHOVIĆ Nikola | 2:27.115 | 2:39.759 | 5:06.874 | 58.477 | H 2000 / 1 |
| 20 / #231 / PEJOVIĆ Marko | 2:33.785 | 2:33.558 | 5:07.343 | 58.946 | N 1400 / 3 |
| 21 / #896 / VUKČEVIĆ Ivan | 2:33.493 | 2:33.852 | 5:07.345 | 58.948 | N 1400 / 4 |
| 22 / #13 / VUJNOVIĆ Miloš | 2:34.983 | 2:34.285 | 5:09.268 | 1:00.871 | N 1400 / 5 |
| 23 / #506 / ĆOROVIĆ Almin | 2:34.606 | 2:34.688 | 5:09.294 | 1:00.897 | A 2000 / 2 |
| 24 / #705 / KRSTEVSKI Aleksandar | 2:34.407 | 2:35.760 | 5:10.167 | 1:01.770 | H 2000 / 2 |
| 25 / #19 / KOVAČEVIĆ Dejan | 2:34.818 | 2:36.067 | 5:10.885 | 1:02.488 | N 1400 / 6 |
| 26 / #197 / NIKČEVIĆ Sava | 2:36.367 | 2:35.442 | 5:11.809 | 1:03.412 | A 1150 / 1 |
| 27 / #236 / MUDREŠA Dragan | 2:35.346 | 2:37.220 | 5:12.566 | 1:04.169 | A 1150 / 2 |
| 28 / #92 / DODEROVIĆ Božidar | 2:34.878 | 2:37.958 | 5:12.836 | 1:04.439 | A 1150 / 3 |
| 29 / #95 / BOKOVAC Stefan | 2:37.641 | 2:36.094 | 5:13.735 | 1:05.338 | A 1400 / 2 |
| 30 / #48 / IVANOVIĆ Marija | 2:38.878 | 2:37.436 | 5:16.314 | 1:07.917 | A 1600 / 2 |
| 31 / #33 / ŠUŠIĆ Gligorije | 2:39.712 | 2:37.476 | 5:17.188 | 1:08.791 | N 1600 / 1 |
| 32 / #666 / PLAKALO Ena | 2:38.754 | 2:38.560 | 5:17.314 | 1:08.917 | N 1600 / 2 |
| 33 / #18 / KOVAČEVIĆ Đorđe | 2:41.686 | 2:39.728 | 5:21.414 | 1:13.017 | N 1400 / 7 |
| 34 / #58 / CEBIĆ Mladen | 2:45.056 | 2:41.152 | 5:26.208 | 1:17.811 | H 2000 + / 4 |
| 35 / #12 / ĐURIŠIĆ Andrija | 2:49.232 | 2:39.000 | 5:28.232 | 1:19.835 | H 1000 / 1 |
| 36 / #119 / ĐURAKOVIĆ Sakib | 2:43.348 | 2:50.290 | 5:33.638 | 1:25.241 | A 1400 / 3 |
| 37 / #558 / PERUNIČIĆ Miloš | 2:47.181 | 2:46.519 | 5:33.700 | 1:25.303 | H 1000 / 2 |
| 38 / #155 / RADMILOVIĆ Igor | 2:47.738 | 2:47.376 | 5:35.114 | 1:26.717 | N 1150 / 1 |
| 39 / #287 / RAŽNATOVIĆ Milorad | 2:48.493 | 2:48.187 | 5:36.680 | 1:28.283 | N 1150 / 2 |
| 40 / #153 / MARKOVIĆ Matija | 2:48.964 | 2:48.702 | 5:37.666 | 1:29.269 | N 1150 / 3 |
| 41 / #76 / MILOŠEV Stevan | 2:50.813 | 2:49.039 | 5:39.852 | 1:31.455 | N 1150 / 4 |
| 42 / #200 / DŽOGAZ Dragan | 2:50.865 | 2:50.024 | 5:40.889 | 1:32.492 | N 1150 / 5 |
| 43 / #510 / MILIČKOVIĆ Stevan | 2:52.051 | 2:49.757 | 5:41.808 | 1:33.411 | N 1150 / 6 |
| 44 / #160 / BRKAN Luka | 2:51.295 | 2:50.716 | 5:42.011 | 1:33.614 | N 1150 / 7 |
| 45 / #442 / NIKOLIĆ Spasoje | 2:51.482 | 2:52.015 | 5:43.497 | 1:35.100 | A 1150 / 4 |
| 46 / #53 / MUJOVIĆ Lazar | 2:54.626 | 2:50.997 | 5:45.623 | 1:37.226 | H 2000 / 3 |
| 47 / #81 / RADANOVIĆ Vojo | 2:58.127 | 2:54.226 | 5:52.353 | 1:43.956 | H 1000 / 3 |
| 48 / #260 / BRKOVIĆ Aleksandar | 2:57.189 | 2:59.929 | 5:57.118 | 1:48.721 | N 1600 / 3 |
| 49 / #222 / ŠUĆUR Luka | 3:11.183 | 3:06.533 | 6:17.716 | 2:09.319 | H 1400 / 2 |

## Practice rows: full transcription and checks

All 57 best-of-two times, gaps, order and class ranks match the source. Status subsections repeat some ranked drivers; they are not additional entries or aggregate results.

| T rank / bib / source name | Run 1 | Run 2 | Best | Gap | Class / rank |
| --- | --- | --- | --- | --- | --- |
| 1 / #21 / ĆOROVIĆ Aldin | 2:10.864 | 2:04.999 | 2:04.999 | winner | DC N/ E2 / CN / 1 |
| 2 / #4 / VUJOVIĆ Baćko | 2:06.918 | 2:05.412 | 2:05.412 | 0.413 | DC N/ E2 / CN / 2 |
| 3 / #36 / PERUNOVIĆ Jovan | 2:16.916 | 2:13.010 | 2:13.010 | 8.011 | E2SH / 1 |
| 4 / #31 / BOJANOVIĆ Miloš | 2:21.334 | 2:15.171 | 2:15.171 | 10.172 | H 1600 / 1 |
| 5 / #7 / VUJAČIĆ Mirko | 2:17.918 | 2:15.774 | 2:15.774 | 10.775 | A 1600 / 1 |
| 6 / #88 / PINJATIĆ Tripo | 2:18.654 | 2:17.638 | 2:17.638 | 12.639 | E2SH / 2 |
| 7 / #87 / STANIŠIĆ Veljko | 2:19.310 | NULL | 2:19.310 | 14.311 | H 2000 + / 1 |
| 8 / #80 / JANKOVIĆ Marko | 2:20.020 | 2:19.404 | 2:19.404 | 14.405 | N 2000 / 1 |
| 9 / #118 / SAVIĆEVIĆ Radomir | 2:24.140 | 2:22.727 | 2:22.727 | 17.728 | H 1400 / 1 |
| 10 / #26 / TUJKOVIĆ Dušan | 2:32.429 | 2:23.080 | 2:23.080 | 18.081 | N 2000 / 2 |
| 11 / #16 / VUČKOVIĆ Goran | 2:23.403 | NULL | 2:23.403 | 18.404 | H 2000 + / 2 |
| 12 / #68 / VUČETIĆ Goran | 2:24.067 | 2:23.692 | 2:23.692 | 18.693 | N 2000 / 3 |
| 13 / #43 / MASONIČIĆ Radovan | 2:26.800 | NULL | 2:26.800 | 21.801 | A 2000 / 1 |
| 14 / #217 / DMITRI Efimin | 2:27.425 | NULL | 2:27.425 | 22.426 | H 2000 + / 3 |
| 15 / #416 / PEROVIĆ Damjan | 2:29.970 | 2:27.643 | 2:27.643 | 22.644 | H 1600 / 2 |
| 16 / #71 / POPOVIĆ Đuro | 2:28.867 | 2:28.522 | 2:28.522 | 23.523 | E1 1200 / 1 |
| 17 / #89 / ĐONOVIĆ Dejan | 2:33.082 | 2:31.980 | 2:31.980 | 26.981 | N 1400 / 1 |
| 18 / #47 / VLAHOVIĆ Nikola | 2:32.055 | NULL | 2:32.055 | 27.056 | H 2000 / 1 |
| 19 / #115 / KRIVOKAPIĆ Milutin | 2:39.974 | 2:32.641 | 2:32.641 | 27.642 | A 1400 / 1 |
| 20 / #231 / PEJOVIĆ Marko | 2:34.689 | 2:33.388 | 2:33.388 | 28.389 | N 1400 / 2 |
| 21 / #224 / NIKOLIĆ Miljan | 2:36.632 | 2:34.087 | 2:34.087 | 29.088 | N 1400 / 3 |
| 22 / #13 / VUJNOVIĆ Miloš | 2:35.708 | 2:37.170 | 2:35.708 | 30.709 | N 1400 / 4 |
| 23 / #752 / BRACANOVIĆ Miloš | NULL | 2:35.732 | 2:35.732 | 30.733 | H 1400 / 2 |
| 24 / #93 / MARTINOVIĆ Bojan | 2:38.141 | 2:35.977 | 2:35.977 | 30.978 | A 1150 / 1 |
| 25 / #896 / VUKČEVIĆ Ivan | 2:39.326 | 2:36.213 | 2:36.213 | 31.214 | N 1400 / 5 |
| 26 / #23 / ADŽIĆ Marko | NULL | 2:37.014 | 2:37.014 | 32.015 | A 1400 / 2 |
| 27 / #506 / ĆOROVIĆ Almin | 2:37.331 | 2:41.060 | 2:37.331 | 32.332 | A 2000 / 2 |
| 28 / #19 / KOVAČEVIĆ Dejan | NULL | 2:38.098 | 2:38.098 | 33.099 | N 1400 / 6 |
| 29 / #236 / MUDREŠA Dragan | 2:43.019 | 2:38.385 | 2:38.385 | 33.386 | A 1150 / 2 |
| 30 / #197 / NIKČEVIĆ Sava | 2:39.200 | 2:38.404 | 2:38.404 | 33.405 | A 1150 / 3 |
| 31 / #92 / DODEROVIĆ Božidar | 2:38.755 | 2:38.903 | 2:38.755 | 33.756 | A 1150 / 4 |
| 32 / #30 / JOVANOVIĆ Petar | 2:40.329 | 2:38.860 | 2:38.860 | 33.861 | A 1150 / 5 |
| 33 / #95 / BOKOVAC Stefan | 2:39.407 | 2:41.403 | 2:39.407 | 34.408 | A 1400 / 3 |
| 34 / #33 / ŠUŠIĆ Gligorije | 2:40.181 | NULL | 2:40.181 | 35.182 | N 1600 / 1 |
| 35 / #705 / KRSTEVSKI Aleksandar | 2:40.313 | NULL | 2:40.313 | 35.314 | H 2000 / 2 |
| 36 / #48 / IVANOVIĆ Marija | 2:43.069 | 2:41.129 | 2:41.129 | 36.130 | A 1600 / 2 |
| 37 / #12 / ĐURIŠIĆ Andrija | 2:42.334 | NULL | 2:42.334 | 37.335 | H 1000 / 1 |
| 38 / #119 / ĐURAKOVIĆ Sakib | 2:42.571 | 2:43.046 | 2:42.571 | 37.572 | A 1400 / 4 |
| 39 / #18 / KOVAČEVIĆ Đorđe | 2:44.107 | NULL | 2:44.107 | 39.108 | N 1400 / 7 |
| 40 / #96 / MALTSEVA Margarita | 2:47.468 | 2:44.132 | 2:44.132 | 39.133 | A 1600 / 3 |
| 41 / #558 / PERUNIČIĆ Miloš | 2:45.743 | 2:44.986 | 2:44.986 | 39.987 | H 1000 / 2 |
| 42 / #500 / TOŠKOVIĆ Nikola | 2:47.517 | NULL | 2:47.517 | 42.518 | H 1000 / 3 |
| 43 / #153 / MARKOVIĆ Matija | 2:49.740 | 2:47.642 | 2:47.642 | 42.643 | N 1150 / 1 |
| 44 / #155 / RADMILOVIĆ Igor | 2:50.717 | 2:49.114 | 2:49.114 | 44.115 | N 1150 / 2 |
| 45 / #287 / RAŽNATOVIĆ Milorad | 2:50.593 | 2:50.199 | 2:50.199 | 45.200 | N 1150 / 3 |
| 46 / #76 / MILOŠEV Stevan | 2:52.768 | 2:50.528 | 2:50.528 | 45.529 | N 1150 / 4 |
| 47 / #200 / DŽOGAZ Dragan | 2:53.030 | 2:52.687 | 2:52.687 | 47.688 | N 1150 / 5 |
| 48 / #510 / MILIČKOVIĆ Stevan | 2:53.534 | 21:59.700 | 2:53.534 | 48.535 | N 1150 / 6 |
| 49 / #160 / BRKAN Luka | 2:55.123 | 2:53.926 | 2:53.926 | 48.927 | N 1150 / 7 |
| 50 / #442 / NIKOLIĆ Spasoje | 3:07.554 | 2:54.934 | 2:54.934 | 49.935 | A 1150 / 6 |
| 51 / #81 / RADANOVIĆ Vojo | 2:58.929 | 21:26.509 | 2:58.929 | 53.930 | H 1000 / 4 |
| 52 / #260 / BRKOVIĆ Aleksandar | 2:58.992 | 4:06.055 | 2:58.992 | 53.993 | N 1600 / 2 |
| 53 / #183 / KOTLAJA Božo | 2:59.788 | NULL | 2:59.788 | 54.789 | H 1400 / 3 |
| 54 / #151 / KOKOTOVIĆ Darko | 3:00.492 | NULL | 3:00.492 | 55.493 | H 1600 / 3 |
| 55 / #526 / BRKOVIĆ Nikola | 3:05.199 | NULL | 3:05.199 | 1:00.200 | H 2000 / 3 |
| 56 / #53 / MUJOVIĆ Lazar | 3:24.598 | NULL | 3:24.598 | 1:19.599 | H 2000 / 4 |
| 57 / #237 / IVANOVIĆ Igor | 8:13.445 | NULL | 8:13.445 | 6:08.446 | H 2000 + / 4 |

| Exact status section | Bib / source name | Class | Other printed run cells |
| --- | --- | --- | --- |
| DNS - Run 1 | 58 / CEBIĆ Mladen | H 2000 + | blank / blank |
| DNS - Run 1 | 666 / PLAKALO Ena | N 1600 | blank / blank |
| DNF - Run 1 | 19 / KOVAČEVIĆ Dejan | N 1400 | blank / 2:38.098 |
| DNF - Run 1 | 23 / ADŽIĆ Marko | A 1400 | blank / 2:37.014 |
| DNF - Run 1 | 29 / MUGOŠA Slobodan | N 1600 | blank / blank |
| DNF - Run 1 | 222 / ŠUĆUR Luka | H 1400 | blank / blank |
| DNF - Run 1 | 262 / ĐURIČKOVIĆ Balša | E1 1200 | blank / blank |
| DNF - Run 1 | 752 / BRACANOVIĆ Miloš | H 1400 | blank / 2:35.732 |
| DNS - Run 2 | 12 / ĐURIŠIĆ Andrija | H 1000 | 2:42.334 / blank |
| DNS - Run 2 | 262 / ĐURIČKOVIĆ Balša | E1 1200 | blank / blank |
| DNF - Run 2 | 18 / KOVAČEVIĆ Đorđe | N 1400 | 2:44.107 / blank |
| DNF - Run 2 | 33 / ŠUŠIĆ Gligorije | N 1600 | 2:40.181 / blank |
| DNF - Run 2 | 500 / TOŠKOVIĆ Nikola | H 1000 | 2:47.517 / blank |

Q7 flags #510 Run 2 21:59.700 and #81 Run 2 21:26.509. T #237 Run 1 8:13.445 and #260 Run 2 4:06.055 are also slower but internally valid; preserve. DNS #666 Ena Plakalo and #58 Mladen Cebić is explicit for Run 1 only; their Run 2 blanks do not prove another DNS. #262 has explicit DNF Run 1 and DNS Run 2.

## Absence, optional information and conflicting numbers

Registration-only rows (no proven event start; no invented DNS/DNF or session entries): Miloš Milošev #221; Ilija Šapić #17; Milan Bracanović #751; Radovan Radanović #930; Dejan Bulatović #12; Milan Jakšić #44; Mile Malović without bib. Milan Bracanović remains subject to Q3. New Miloš Milošev can be identity-only if approved, consistent with the prior Kotor policy.

Practice-sheet drivers absent from R: VUJOVIĆ Baćko #4; KRIVOKAPIĆ Milutin #115; BRACANOVIĆ Miloš #752; MARTINOVIĆ Bojan #93; JOVANOVIĆ Petar #30; MALTSEVA Margarita #96; TOŠKOVIĆ Nikola #500; KOTLAJA Božo #183; KOKOTOVIĆ Darko #151; BRKOVIĆ Nikola #526; IVANOVIĆ Igor #237; MUGOŠA Slobodan #29; ĐURIČKOVIĆ Balša #262. Their race outcome is unavailable, not automatically zero points or DNS/DNF.

Number corrections supported by named timing rows: Darko Kokotović P #31 → T #151 (Kotor #151); Ena Plakalo P #660 → T/R #666 (Goč #666). Missing registration numbers supplied by timing: Ivan Vukčević #896, Marko Pejović #231, Mladen Cebić #58, Aleksandar Krstevski #705, Miloš Peruničić #558. #12 is printed for both P Andrija Đurišić and P Dejan Bulatović; timing #12 names Andrija. P #31 names both Darko Kokotović and Miloš Bojanović; T/R #31 names Miloš. Do not use bib as person identity.

Unknown driver nationalities, current undocumented affiliations/vehicles, exact start clock times, published points, club points/classification, cup eligibility and official ordinal remain unavailable until evidenced or explicitly resolved. No fictional club/class/track or prior model is supplied to fill a gap.

## Migrations and verification

Prepared append-only migration 019 with explicit master include:

- `backend/src/main/resources/db/changelog/019-kuci-2026.yaml`
- `backend/src/main/resources/db/changelog/019-kuci-identities.sql`
- `backend/src/main/resources/db/changelog/019-kuci-event.sql`
- `backend/src/main/resources/db/changelog/019-kuci-sources.sql`

Accepted data: **12 new drivers, 1 club, 9 reusable vehicle descriptions, 1 track, 62 event entries, 4 sessions, 207 session results, 17 final classifications / 98 classification rows, 5 registered originals and 501 source-linked aliases**. Six registration-only rows produce no event entry or invented result; new Miloš Milošev is identity-only. Existing historical entries and all applied migration files are unchanged.

Frontend `npm.cmd run build` and `npm.cmd run lint`: passed. Backend `.\mvnw.cmd test`: **29 tests, 27 passed, 0 failures/errors, 2 MySQL tests skipped**.

New isolated H2 verification passed clean-fixture replay through 019, repeat application, upgrade from 018 with generated IDs shifted to 10000, preservation of preceding entries/results/classifications/aliases and an unrelated legacy driver, and HALT on missing existing identities or conflicting legacy names. The H2 baseline omits only historical MySQL index repair 009; production includes remain unchanged. H2 is not represented as a full MySQL replay. The opt-in MySQL clean/upgrade tests now cover 019, but cannot run while Docker/MySQL is unavailable.

Real isolated Spring Boot HTTP verification passed: weekend/track and 3-round dashboard, all 4 session row counts and times/statuses, canonical Milan Bracanović name without a duplicate person, all 17 classifications, finalized class-based calculated points including the domestic two-finisher H 1400 reduction to 12.5, source listing and all 5 original downloads with media type and matching SHA-256. No club results or published awarded points were invented. Unregistered review/manifests/reconciliation are not source downloads.

All originals were rehashed against the initial inventory and are unchanged. All 49 race sums/gaps and 57 practice bests/gaps/class ranks pass. Every source row maps explicitly to immutable keys in reconciliation.json and migration evidence comments. No deploy, publication to the running application, or modification of the developer database occurred. Current unversioned legacy identities could not be checked because the application catalog is unavailable; collision preconditions deliberately halt rather than silently adopting them.

## Accepted event-entry mappings

| Source bib / spelling | Canonical driver key / name | Stored bib | Club key | Vehicle key | Class key |
| --- | --- | --- | --- | --- | --- |
| 21 / ĆOROVIĆ Aldin | driver-aldin-corovic / ALDIN ĆOROVIĆ | 21 | club-amsk-ap-sport | vehicle-global | class-akscg-d-e2-cn |
| 36 / PERUNOVIĆ Jovan | driver-jovan-perunovic / JOVAN PERUNOVIĆ | 36 | club-amsk-podi | vehicle-fiat-750 | class-akscg-e2sh |
| 88 / PINJATIĆ Tripo | driver-tripo-pinjatic / TRIPO PINJATIĆ | 88 | club-amsk-crmnica | vehicle-fiat-500 | class-akscg-e2sh |
| 31 / BOJANOVIĆ Miloš | driver-milos-bojanovic / MILOŠ BOJANOVIĆ | 31 | club-amsk-gorstak | vehicle-peugeot | class-akscg-h-1600 |
| 7 / VUJAČIĆ Mirko | driver-mirko-vujacic / MIRKO VUJAČIĆ | 7 | club-amsk-crmnica | vehicle-citroen-c2 | class-akscg-a-1600 |
| 16 / VUČKOVIĆ Goran | driver-goran-vuckovic / GORAN VUČKOVIĆ | 16 | NULL | NULL | class-akscg-h-2000-plus |
| 87 / STANIŠIĆ Veljko | driver-veljko-stanisic / VELJKO STANIŠIĆ | 87 | club-amsk-podi | NULL | class-akscg-h-2000-plus |
| 80 / JANKOVIĆ Marko | driver-marko-jankovic / MARKO JANKOVIĆ | 80 | club-amsk-gorstak | vehicle-honda | class-akscg-n-2000 |
| 26 / TUJKOVIĆ Dušan | driver-dusan-tujkovic / DUŠAN TUJKOVIĆ | 26 | club-amsk-kotor | vehicle-honda | class-akscg-n-2000 |
| 68 / VUČETIĆ Goran | driver-goran-vucetic / GORAN VUČETIĆ | 68 | club-amsk-podi | vehicle-honda | class-akscg-n-2000 |
| 118 / SAVIĆEVIĆ Radomir | driver-radomir-savicevic / RADOMIR SAVIĆEVIĆ | 118 | club-amsk-gorstak | vehicle-honda | class-akscg-h-1400 |
| 71 / POPOVIĆ Đuro | driver-duro-popovic / ĐURO POPOVIĆ | 71 | club-amsk-podi | vehicle-suzuki | class-akscg-e1-1200 |
| 416 / PEROVIĆ Damjan | driver-damjan-perovic / DAMJAN PEROVIĆ | 416 | club-amsk-kotor | vehicle-honda | class-akscg-h-1600 |
| 43 / MASONIČIĆ Radovan | driver-radovan-masonicic / RADOVAN MASONIČIĆ | 43 | club-amsk-crmnica | vehicle-honda | class-akscg-a-2000 |
| 217 / DMITRI Efimin | driver-efimin-dmitri / EFIMIN DMITRI | 217 | club-amsk-crmnica | vehicle-bmw | class-akscg-h-2000-plus |
| 224 / NIKOLIĆ Miljan | driver-miljan-nikolic / MILJAN NIKOLIĆ | 224 | club-amsk-nilovic-racing | vehicle-peugeot | class-akscg-n-1400 |
| 89 / ĐONOVIĆ Dejan | driver-dejan-donovic / DEJAN ĐONOVIĆ | 89 | club-amsk-crmnica | vehicle-peugeot | class-akscg-n-1400 |
| 23 / ADŽIĆ Marko | driver-marko-adzic / MARKO ADŽIĆ | 23 | club-amsk-podi | vehicle-rover | class-akscg-a-1400 |
| 47 / VLAHOVIĆ Nikola | driver-nikola-vlahovic / NIKOLA VLAHOVIĆ | 47 | club-amsk-vlahovic | vehicle-vw-golf | class-akscg-h-2000 |
| 231 / PEJOVIĆ Marko | driver-marko-pejovic / MARKO PEJOVIĆ | 231 | club-amk-podgorica-rt | vehicle-peugeot | class-akscg-n-1400 |
| 896 / VUKČEVIĆ Ivan | driver-ivan-vukcevic / IVAN VUKČEVIĆ | 896 | club-amk-monteracing | vehicle-peugeot | class-akscg-n-1400 |
| 13 / VUJNOVIĆ Miloš | driver-milos-vujnovic / MILOŠ VUJNOVIĆ | 13 | club-amsk-herceg-novi | vehicle-peugeot | class-akscg-n-1400 |
| 506 / ĆOROVIĆ Almin | driver-almin-corovic / ALMIN ĆOROVIĆ | 506 | club-amsk-ap-sport | vehicle-opel | class-akscg-a-2000 |
| 705 / KRSTEVSKI Aleksandar | driver-aleksandar-krstevski / ALEKSANDAR KRSTEVSKI | 705 | club-amsk-crmnica | vehicle-ford | class-akscg-h-2000 |
| 19 / KOVAČEVIĆ Dejan | driver-dejan-kovacevic / DEJAN KOVAČEVIĆ | 19 | club-amsk-herceg-novi | vehicle-peugeot | class-akscg-n-1400 |
| 197 / NIKČEVIĆ Sava | driver-sava-nikcevic / SAVA NIKČEVIĆ | 197 | club-amsk-gorstak | vehicle-yugo | class-akscg-a-1150 |
| 236 / MUDREŠA Dragan | driver-dragan-mudresa / DRAGAN MUDREŠA | 236 | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 |
| 92 / DODEROVIĆ Božidar | driver-bozidar-doderovic / BOŽIDAR DODEROVIĆ | 92 | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 |
| 95 / BOKOVAC Stefan | driver-stefan-bokovac / STEFAN BOKOVAC | 95 | club-amsk-crmnica | vehicle-vw-polo | class-akscg-a-1400 |
| 48 / IVANOVIĆ Marija | driver-marija-ivanovic / MARIJA IVANOVIĆ | 48 | club-amk-monteracing | vehicle-citroen-saxo | class-akscg-a-1600 |
| 33 / ŠUŠIĆ Gligorije | driver-gligorije-susic / GLIGORIJE ŠUŠIĆ | 33 | club-amsk-podi | vehicle-citroen-saxo | class-akscg-n-1600 |
| 666 / PLAKALO Ena | driver-ena-plakalo / ENA PLAKALO | 666 | club-amsk-ap-sport | vehicle-citroen-saxo | class-akscg-n-1600 |
| 18 / KOVAČEVIĆ Đorđe | driver-dorde-kovacevic / ĐORĐE KOVAČEVIĆ | 18 | club-amsk-herceg-novi | vehicle-peugeot | class-akscg-n-1400 |
| 58 / CEBIĆ Mladen | driver-mladen-cebic / MLADEN CEBIĆ | 58 | club-mugosa | NULL | class-akscg-h-2000-plus |
| 12 / ĐURIŠIĆ Andrija | driver-andrija-durisic / ANDRIJA ĐURIŠIĆ | 12 | club-amsk-ap-sport | vehicle-yugo | class-akscg-h-1000 |
| 119 / ĐURAKOVIĆ Sakib | driver-sakib-durakovic / SAKIB DURAKOVIĆ | 119 | club-amsk-crmnica | vehicle-rover | class-akscg-a-1400 |
| 558 / PERUNIČIĆ Miloš | driver-milos-perunicic / MILOŠ PERUNIČIĆ | 558 | club-amk-monteracing | vehicle-yugo | class-akscg-h-1000 |
| 155 / RADMILOVIĆ Igor | driver-igor-radmilovic / IGOR RADMILOVIĆ | 155 | club-amsk-herceg-novi | vehicle-yugo | class-akscg-n-1150 |
| 287 / RAŽNATOVIĆ Milorad | driver-milorad-raznatovic / MILORAD RAŽNATOVIĆ | 287 | club-amsk-tivat | vehicle-yugo | class-akscg-n-1150 |
| 153 / MARKOVIĆ Matija | driver-matija-markovic / MATIJA MARKOVIĆ | 153 | club-amkk-lovcen-r-t | vehicle-yugo | class-akscg-n-1150 |
| 76 / MILOŠEV Stevan | driver-stevan-milosev / STEVAN MILOŠEV | 76 | club-amsk-herceg-novi | vehicle-yugo | class-akscg-n-1150 |
| 200 / DŽOGAZ Dragan | driver-dragan-dzogaz / DRAGAN DŽOGAZ | 200 | club-amsk-ap-sport | vehicle-yugo | class-akscg-n-1150 |
| 510 / MILIČKOVIĆ Stevan | driver-stevan-milickovic / STEVAN MILIČKOVIĆ | 510 | club-amsk-ap-sport | vehicle-yugo | class-akscg-n-1150 |
| 160 / BRKAN Luka | driver-luka-brkan / LUKA BRKAN | 160 | club-amsk-tivat | vehicle-yugo | class-akscg-n-1150 |
| 442 / NIKOLIĆ Spasoje | driver-spasoje-nikolic / SPASOJE NIKOLIĆ | 442 | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 |
| 53 / MUJOVIĆ Lazar | driver-lazar-mujovic / LAZAR MUJOVIĆ | 53 | club-amsk-podi | vehicle-bmw | class-akscg-h-2000 |
| 81 / RADANOVIĆ Vojo | driver-vojo-radanovic / VOJO RADANOVIĆ | 81 | club-amsk-herceg-novi | vehicle-peugeot | class-akscg-h-1000 |
| 260 / BRKOVIĆ Aleksandar | driver-aleksandar-brkovic / ALEKSANDAR BRKOVIĆ | 260 | club-amk-monteracing | vehicle-peugeot | class-akscg-n-1600 |
| 222 / ŠUĆUR Luka | driver-luka-sucur / LUKA ŠUĆUR | 222 | club-amsk-podi | vehicle-suzuki | class-akscg-h-1400 |
| 4 / VUJOVIĆ Baćko | driver-backo-vujovic / BAĆKO VUJOVIĆ | 4 | club-amsk-gorstak | vehicle-norma | class-akscg-d-e2-cn |
| 115 / KRIVOKAPIĆ Milutin | driver-milutin-krivokapic / MILUTIN KRIVOKAPIĆ | 115 | club-amsk-herceg-novi | vehicle-peugeot | class-akscg-a-1400 |
| 752 / BRACANOVIĆ Miloš | driver-milan-bracanovic / MILAN BRACANOVIĆ | 751 | club-amsk-ap-sport | vehicle-peugeot | class-akscg-h-1400 |
| 93 / MARTINOVIĆ Bojan | driver-bojan-martinovic / BOJAN MARTINOVIĆ | 93 | club-amsk-ap-sport | vehicle-yugo | class-akscg-a-1150 |
| 30 / JOVANOVIĆ Petar | driver-petar-jovanovic / PETAR JOVANOVIĆ | 30 | club-amsk-podi | vehicle-yugo | class-akscg-a-1150 |
| 96 / MALTSEVA Margarita | driver-margarita-maltseva / MARGARITA MALTSEVA | 96 | club-amsk-crmnica | vehicle-citroen-saxo | class-akscg-a-1600 |
| 500 / TOŠKOVIĆ Nikola | driver-nikola-toskovic / NIKOLA TOŠKOVIĆ | 500 | club-amsk-ap-sport | vehicle-yugo | class-akscg-h-1000 |
| 183 / KOTLAJA Božo | driver-bozo-kotlaja / BOŽO KOTLAJA | 183 | club-amsk-podi | vehicle-yugo | class-akscg-h-1400 |
| 151 / KOKOTOVIĆ Darko | driver-darko-kokotovic / DARKO KOKOTOVIĆ | 151 | club-amsk-herceg-novi | vehicle-opel-corsa | class-akscg-h-1600 |
| 526 / BRKOVIĆ Nikola | driver-nikola-brkovic / NIKOLA BRKOVIĆ | 526 | club-amk-monteracing | vehicle-vw-golf | class-akscg-h-2000 |
| 237 / IVANOVIĆ Igor | driver-igor-ivanovic / IGOR IVANOVIĆ | 237 | NULL | NULL | class-akscg-h-2000-plus |
| 29 / MUGOŠA Slobodan | driver-slobodan-mugosa / SLOBODAN MUGOŠA | 29 | club-amsk-ap-sport | vehicle-peugeot | class-akscg-n-1600 |
| 262 / ĐURIČKOVIĆ Balša | driver-balsa-durickovic / BALŠA ĐURIČKOVIĆ | 262 | club-amsk-gorstak | vehicle-skoda | class-akscg-e1-1200 |
