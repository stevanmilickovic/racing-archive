-- Reviewed Kotor–Trojica sources; row mappings and programmer decisions are versioned with the event.
-- P = 2026-05-02-kotor-trojica-registrations-2026-04-30
-- R = 2026-05-02-kotor-trojica-akscg-official-2026-05-12
-- K = 2026-05-02-kotor-trojica-clubs-2026-05-13
-- No previously applied migration is changed. Existing unrelated data is preserved.
INSERT INTO federations (name, country_code) VALUES ('Auto i karting savez Crne Gore', 'ME');
INSERT INTO disciplines (code, name) VALUES ('HILLCLIMB', 'Brdske auto trke');
INSERT INTO championships (federation_id, discipline_id, name, short_name) VALUES ((SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), (SELECT id FROM disciplines WHERE code='HILLCLIMB'), 'Šampionat Crne Gore', 'AKSCG');
INSERT INTO seasons (championship_id, season_year, name, active, scoring_system_id) VALUES ((SELECT id FROM championships WHERE federation_id=(SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME') AND discipline_id=(SELECT id FROM disciplines WHERE code='HILLCLIMB') AND name='Šampionat Crne Gore'), 2026, 'Šampionat Crne Gore 2026', TRUE, NULL);
-- P str. 1, R.B. 1; R str. 2, kat. 1, #81
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-vojo-radanovic', 'VOJO', 'RADANOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 2; R str. 2, kat. 1, #500
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-nikola-toskovic', 'NIKOLA', 'TOŠKOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 3; R str. 2, kat. 1, #121
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-andrija-durisic', 'ANDRIJA', 'ĐURIŠIĆ', 'ME', NULL);
-- P str. 1, R.B. 4; R str. 2, kat. 1, #153
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-matija-markovic', 'MATIJA', 'MARKOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 5; R str. 3, kat. 1, #511
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-nikola-lagator', 'NIKOLA', 'LAGATOR', 'ME', NULL);
-- P str. 1, R.B. 6; R str. 2, kat. 1, #160
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-luka-brkan', 'LUKA', 'BRKAN', 'ME', NULL);
-- P str. 1, R.B. 7; R str. 3, kat. 1, #287
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-milorad-raznatovic', 'MILORAD', 'RAŽNATOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 8; R str. 3, kat. 1, #208
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-petar-zurovac', 'PETAR', 'ZUROVAC', 'ME', NULL);
-- P str. 1, R.B. 9; R str. 2, kat. 1, #155
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-igor-radmilovic', 'IGOR', 'RADMILOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 10; R str. 2, kat. 1, #76
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-stevan-milosev', 'STEVAN', 'MILOŠEV', 'ME', NULL);
-- P str. 1, R.B. 11; R str. 2, kat. 1, #510
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-stevan-milickovic', 'STEVAN', 'MILIČKOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 12; R str. 2, kat. 1, #200
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dragan-dzogaz', 'DRAGAN', 'DŽOGAZ', 'ME', NULL);
-- P str. 1, R.B. 13; R str. 3, kat. 1, #197
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-sava-nikcevic', 'SAVA', 'NIKČEVIĆ', 'ME', NULL);
-- P str. 1, R.B. 14; R str. 1, kat. 1, #318
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-zarko-lagator', 'ŽARKO', 'LAGATOR', 'ME', NULL);
-- P str. 1, R.B. 15; R str. 3, kat. 1, #442
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-spasoje-nikolic', 'SPASOJE', 'NIKOLIĆ', 'ME', NULL);
-- P str. 1, R.B. 16; R str. 2, kat. 1, #92
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-bozidar-doderovic', 'BOŽIDAR', 'DODEROVIĆ', 'ME', NULL);
-- P str. 1, R.B. 17; R str. 3, kat. 1, #236
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dragan-mudresa', 'DRAGAN', 'MUDREŠA', 'ME', NULL);
-- P str. 1, R.B. 18; R str. 1, kat. 1, #30
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-petar-jovanovic', 'PETAR', 'JOVANOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 19; R str. 2, kat. 1, #78
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-milan-roganovic', 'MILAN', 'ROGANOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 20; R str. 3, kat. 1, #224
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-miljan-nikolic', 'MILJAN', 'NIKOLIĆ', 'ME', NULL);
-- P str. 1, R.B. 21; R str. 2, kat. 1, #89
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dejan-donovic', 'DEJAN', 'ĐONOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 22; R str. 1, kat. 1, #231
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-marko-pejovic', 'MARKO', 'PEJOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 23; R str. 1, kat. 1, #13
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-milos-vujnovic', 'MILOŠ', 'VUJNOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 24; R str. 1, kat. 1, #19
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dejan-kovacevic', 'DEJAN', 'KOVAČEVIĆ', 'ME', NULL);
-- P str. 1, R.B. 25; R str. 2, kat. 1, #18
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dorde-kovacevic', 'ĐORĐE', 'KOVAČEVIĆ', 'ME', NULL);
-- P str. 1, R.B. 26; R str. 1, kat. 1, #17
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-ilija-sapic', 'ILIJA', 'ŠAPIĆ', 'ME', NULL);
-- P str. 1, R.B. 27; R str. 2, kat. 1, #75
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-nikola-vlahovic', 'NIKOLA', 'VLAHOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 28; R str. 2, kat. 1, #95
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-stefan-bokovac', 'STEFAN', 'BOKOVAC', 'ME', NULL);
-- P str. 1, R.B. 29; R str. 2, kat. 1, #119
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-sakib-durakovic', 'SAKIB', 'DURAKOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 30; R str. 1, kat. 1, #115
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-milutin-krivokapic', 'MILUTIN', 'KRIVOKAPIĆ', 'ME', NULL);
-- P str. 1, R.B. 31; R str. 1, kat. 1, #23
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-marko-adzic', 'MARKO', 'ADŽIĆ', 'ME', NULL);
-- P str. 1, R.B. 32; R str. 1, kat. 1, #91
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-milo-dukanovic', 'MILO', 'ĐUKANOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 33; R str. 2, kat. 1, #86
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-darko-ivanovic', 'DARKO', 'IVANOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 34; R str. 3, kat. 1, #230
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-lazar-kostic', 'LAZAR', 'KOSTIĆ', 'ME', NULL);
-- P str. 1, R.B. 35; R str. 1, kat. 1, #71
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-duro-popovic', 'ĐURO', 'POPOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 36; R str. 1, kat. 1, #222
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-luka-sucur', 'LUKA', 'ŠUĆUR', 'ME', NULL);
-- P str. 1, R.B. 37; R str. 2, kat. 1, #152
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-milan-bracanovic', 'MILAN', 'BRACANOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 28 (38. fizički red; štampano 28); R str. 2, kat. 1, #33
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-gligorije-susic', 'GLIGORIJE', 'ŠUŠIĆ', 'ME', NULL);
-- P str. 1, R.B. 39; R str. 1, kat. 1, #29
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-slobodan-mugosa', 'SLOBODAN', 'MUGOŠA', 'ME', NULL);
-- P str. 1, R.B. 40; R str. 3, kat. 1, #454
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-radivoje-janjusevic', 'RADIVOJE', 'JANJUŠEVIĆ', 'ME', NULL);
-- P str. 1, R.B. 41; R str. 2, kat. 1, #48
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-marija-ivanovic', 'MARIJA', 'IVANOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 42; R str. 2, kat. 1, #96
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-margarita-maltseva', 'MARGARITA', 'MALTSEVA', 'ME', NULL);
-- P str. 1, R.B. 43; R str. 1, kat. 1, #7
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-mirko-vujacic', 'MIRKO', 'VUJAČIĆ', 'ME', NULL);
-- P str. 1, R.B. 44; R str. 3, kat. 1, #220
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-krsto-radovic', 'KRSTO', 'RADOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 45; R str. 1, kat. 1, #416
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-damjan-perovic', 'DAMJAN', 'PEROVIĆ', 'ME', NULL);
-- P str. 1, R.B. 46; R str. 1, kat. 1, #31
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-milos-bojanovic', 'MILOŠ', 'BOJANOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 47; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-perica-mardonovic', 'PERICA', 'MARĐONOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 48; R str. 2, kat. 1, #94
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-zlatko-sindik', 'ZLATKO', 'SINDIK', 'ME', NULL);
-- P str. 1, R.B. 49; R str. 1, kat. 1, #151
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-darko-kokotovic', 'DARKO', 'KOKOTOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 50; R str. 2, kat. 1, #25
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-zeljko-radonjic', 'ŽELJKO', 'RADONJIĆ', 'ME', NULL);
-- P str. 1, R.B. 51; R str. 1, kat. 1, #88
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-tripo-pinjatic', 'TRIPO', 'PINJATIĆ', 'ME', NULL);
-- P str. 1, R.B. 52; R str. 1, kat. 1, #36
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-jovan-perunovic', 'JOVAN', 'PERUNOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 53; R str. 1, kat. 1, #262
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-balsa-durickovic', 'BALŠA', 'ĐURIČKOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 54; R str. 1, kat. 1, #26
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dusan-tujkovic', 'DUŠAN', 'TUJKOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 55; R str. 2, kat. 1, #80
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-marko-jankovic', 'MARKO', 'JANKOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 56; R str. 1, kat. 1, #68
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-goran-vucetic', 'GORAN', 'VUČETIĆ', 'ME', NULL);
-- P str. 1, R.B. 57; R str. 1, kat. 1, #43
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-radovan-masonicic', 'RADOVAN', 'MASONIČIĆ', 'ME', NULL);
-- P str. 1, R.B. 58; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-radovan-radanovic', 'RADOVAN', 'RADANOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 59; R str. 2, kat. 1, #506
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-almin-corovic', 'ALMIN', 'ĆOROVIĆ', 'ME', NULL);
-- P str. 1, R.B. 60; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-milan-jaksic', 'MILAN', 'JAKŠIĆ', 'ME', NULL);
-- P str. 1, R.B. 61; R str. 1, kat. 1, #12
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dejan-bulatovic', 'DEJAN', 'BULATOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 62; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-sead-spahic', 'SEAD', 'SPAHIĆ', 'ME', NULL);
-- P str. 1, R.B. 63; R str. 1, kat. 1, #104
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-goran-milosev', 'GORAN', 'MILOŠEV', 'ME', NULL);
-- P str. 1, R.B. 64; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-vlado-buric', 'VLADO', 'BURIĆ', 'ME', NULL);
-- P str. 1, R.B. 65; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-zoran-rajhel', 'ZORAN', 'RAJHEL', 'ME', NULL);
-- P str. 1, R.B. 66; R str. 2, kat. 1, #53
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-lazar-mujovic', 'LAZAR', 'MUJOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 67; R str. 2, kat. 1, #113
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-stefan-milinic', 'STEFAN', 'MILINIĆ', 'ME', NULL);
-- P str. 1, R.B. 68; R str. 3, kat. 1, #333
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dejan-suskavcevic', 'DEJAN', 'ŠUŠKAVČEVIĆ', 'ME', NULL);
-- P str. 1, R.B. 69; R str. 1, kat. 1, #217
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-efimin-dmitri', 'EFIMIN', 'DMITRI', 'ME', NULL);
-- P str. 1, R.B. 70; R str. 3, kat. 1, #237
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-mile-malovic', 'MILE', 'MALOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 71; R str. 1, kat. 1, #16
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-goran-vuckovic', 'GORAN', 'VUČKOVIĆ', 'ME', NULL);
-- P str. 1, R.B. 72; R str. 3, kat. 2, #14
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-backo-vujovic', 'BAĆKO', 'VUJOVIĆ', 'ME', NULL);
-- P str. 2, R.B. 73; R str. 3, kat. 2, #52
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-bozidar-jablan', 'BOŽIDAR', 'JABLAN', 'ME', NULL);
-- P str. 2, R.B. 74; R str. 3, kat. 2, #21
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-aldin-corovic', 'ALDIN', 'ĆOROVIĆ', 'ME', NULL);
-- P str. 2, R.B. 75; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-zlatko-brozovic', 'ZLATKO', 'BROZOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 76; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-nenad-grzancic', 'NENAD', 'GRŽANČIĆ', 'HR', NULL);
-- P str. 2, R.B. 77; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-ante-gacina', 'ANTE', 'GAĆINA', 'HR', NULL);
-- P str. 2, R.B. 78; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-sime-simunovic', 'ŠIME', 'ŠIMUNOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 79; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-marijan-hrgovic', 'MARIJAN', 'HRGOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 80; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-jurica-ivanovic', 'JURICA', 'IVANOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 81; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-sandro-poldrugovac', 'SANDRO', 'POLDRUGOVAC', 'HR', NULL);
-- P str. 2, R.B. 82; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-maro-slade', 'MARO', 'SLADE', 'HR', NULL);
-- P str. 2, R.B. 83; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dejan-dimitrijevic', 'DEJAN', 'DIMITRIJEVIĆ', 'HR', NULL);
-- P str. 2, R.B. 84; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-viliam-prodan', 'VILIAM', 'PRODAN', 'HR', NULL);
-- P str. 2, R.B. 85; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-niko-pulic', 'NIKO', 'PULIĆ', 'HR', NULL);
-- P str. 2, R.B. 86; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-simeron-jargic', 'SIMERON', 'JARGIĆ', 'HR', NULL);
-- P str. 2, R.B. 87; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-frano-dubreta', 'FRANO', 'DUBRETA', 'HR', NULL);
-- P str. 2, R.B. 88; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-luka-dubreta', 'LUKA', 'DUBRETA', 'HR', NULL);
-- P str. 2, R.B. 89; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-tamas-tomcsanyi', 'TAMAS', 'TOMCSANYI', 'HR', NULL);
-- P str. 2, R.B. 90; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-peter-jordan', 'PETER', 'JORDAN', 'HR', NULL);
-- P str. 2, R.B. 91; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-josip-pavic', 'JOSIP', 'PAVIĆ', 'HR', NULL);
-- P str. 2, R.B. 92; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-ante-baric', 'ANTE', 'BARIĆ', 'HR', NULL);
-- P str. 2, R.B. 93; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-ivan-batalic', 'IVAN', 'BATALIĆ', 'HR', NULL);
-- P str. 2, R.B. 94; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-stijepo-manenica', 'STIJEPO', 'MANENICA', 'HR', NULL);
-- P str. 2, R.B. 95; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-mladen-dajic', 'MLADEN', 'ĐAJIĆ', 'HR', NULL);
-- P str. 2, R.B. 96; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-fran-kranjac', 'FRAN', 'KRANJAC', 'HR', NULL);
-- P str. 2, R.B. 97; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-andelko-vrkic', 'ANĐELKO', 'VRKIĆ', 'HR', NULL);
-- P str. 2, R.B. 98; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-antonio-tomic', 'ANTONIO', 'TOMIĆ', 'HR', NULL);
-- P str. 2, R.B. 99; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-bego-sakanovic', 'BEGO', 'ŠAKANOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 100; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-ivica-dragosevic', 'IVICA', 'DRAGOŠEVIĆ', 'HR', NULL);
-- P str. 2, R.B. 101; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-boris-katic', 'BORIS', 'KATIĆ', 'HR', NULL);
-- P str. 2, R.B. 102; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-goran-sikirica', 'GORAN', 'SIKIRICA', 'HR', NULL);
-- P str. 2, R.B. 103; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-andrej-bule', 'ANDREJ', 'BULE', 'HR', NULL);
-- P str. 2, R.B. 104; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-kornel-nyeki', 'KORNEL', 'NYEKI', 'HR', NULL);
-- P str. 2, R.B. 105; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dino-dominikovic', 'DINO', 'DOMINIKOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 106; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-marijo-barovic', 'MARIJO', 'BAROVIĆ', 'HR', NULL);
-- P str. 2, R.B. 107; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-nino-jelicic', 'NINO', 'JELIČIĆ', 'HR', NULL);
-- P str. 2, R.B. 108; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-ivan-pejkovic', 'IVAN', 'PEJKOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 109; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-mato-milkovic', 'MATO', 'MILKOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 110; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-milan-tatalic', 'MILAN', 'TATALIĆ', 'HR', NULL);
-- P str. 2, R.B. 111; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-maro-franic', 'MARO', 'FRANIĆ', 'HR', NULL);
-- P str. 2, R.B. 112; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-nikola-radnjic', 'NIKOLA', 'RADNJIĆ', 'HR', NULL);
-- P str. 2, R.B. 113; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-igor-nikolaus', 'IGOR', 'NIKOLAUS', 'HR', NULL);
-- P str. 2, R.B. 114; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-deni-kmetovic', 'DENI', 'KMETOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 115; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-stipe-bekavac', 'STIPE', 'BEKAVAC', 'HR', NULL);
-- P str. 2, R.B. 116; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-toni-bebic', 'TONI', 'BEBIĆ', 'HR', NULL);
-- P str. 2, R.B. 117; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-ivan-grozdanic', 'IVAN', 'GROZDANIĆ', 'HR', NULL);
-- P str. 2, R.B. 118; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-ivan-zivkovic', 'IVAN', 'ŽIVKOVIĆ', 'HR', NULL);
-- P str. 2, R.B. 119; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-dino-anticevic', 'DINO', 'ANTIČEVIĆ', 'HR', NULL);
-- P str. 2, R.B. 120; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-marin-grljusic', 'MARIN', 'GRLJUŠIĆ', 'HR', NULL);
-- P str. 2, R.B. 121; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-jure-mornar', 'JURE', 'MORNAR', 'HR', NULL);
-- P str. 2, R.B. 122; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-ivan-bekavac', 'IVAN', 'BEKAVAC', 'HR', NULL);
-- P str. 2, R.B. 123; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-petar-dajak', 'PETAR', 'DAJAK', 'HR', NULL);
-- P str. 2, R.B. 124; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-filip-magdic', 'FILIP', 'MAGDIĆ', 'HR', NULL);
-- P str. 2, R.B. 125; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-aleksandar-vidosavljevic', 'ALEKSANDAR', 'VIDOSAVLJEVIĆ', 'RS', NULL);
-- P str. 2, R.B. 126; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-matija-brusnskole', 'MATIJA', 'BRUSNSKOLE', 'SI', NULL);
-- P str. 2, R.B. 127; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-hysein-iseni', 'HYSEIN', 'ISENI', NULL, 'Lista prijava bilježi oznaku Nac: KOS.');
-- P str. 2, R.B. 128; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-burim-asllani', 'BURIM', 'ASLLANI', NULL, 'Lista prijava bilježi oznaku Nac: KOS.');
-- P str. 2, R.B. 129; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-leutrim-bytyqi', 'LEUTRIM', 'BYTYQI', NULL, 'Lista prijava bilježi oznaku Nac: KOS.');
-- P str. 2, R.B. 130; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-valon-rexhepi', 'VALON', 'REXHEPI', NULL, 'Lista prijava bilježi oznaku Nac: KOS.');
-- P str. 2, R.B. 131; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-smail-smani', 'SMAIL', 'SMANI', NULL, 'Lista prijava bilježi oznaku Nac: KOS.');
-- P str. 2, R.B. 132; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-bradhil-canolli', 'BRADHIL', 'CANOLLI', NULL, 'Lista prijava bilježi oznaku Nac: KOS.');
-- P str. 2, R.B. 133; Identity only: no entry or result at this event.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code, identity_notes) VALUES ('driver-gentjam-shaqiri', 'GENTJAM', 'SHAQIRI', 'AL', NULL);
-- R str. 2, kat. 1, #48; K str. 3, AMK MONTERACING, #48
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amk-monteracing', 'AMK MONTERACING', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 1, kat. 1, #231; K str. 3, AMK PODGORICA RT, #231
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amk-podgorica-rt', 'AMK PODGORICA RT', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 1, kat. 1, #318; K str. 3, AMKK LOVĆEN R.T., #318
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amkk-lovcen-r-t', 'AMKK LOVĆEN R.T.', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 1, kat. 1, #29; K str. 1, AMSK AP SPORT, #29
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amsk-ap-sport', 'AMSK AP SPORT', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 1, kat. 1, #88; K str. 2, AMSK CRMNICA, #88
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amsk-crmnica', 'AMSK CRMNICA', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 1, kat. 1, #31; K str. 3, AMSK GORŠTAK, #31
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amsk-gorstak', 'AMSK GORŠTAK', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 1, kat. 1, #115; K str. 2, AMSK HERCEG NOVI, #115
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amsk-herceg-novi', 'AMSK HERCEG NOVI', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 1, kat. 1, #16; K str. 1, AMSK KOTOR, #16
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amsk-kotor', 'AMSK KOTOR', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 3, kat. 1, #208; K str. 4, AMSK NILOVIĆ RACING, #208
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amsk-nilovic-racing', 'AMSK NILOVIĆ RACING', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 1, kat. 1, #36; K str. 1, AMSK PODI, #36
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amsk-podi', 'AMSK PODI', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 2, kat. 1, #160; K str. 3, AMSK TIVAT, #160
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amsk-tivat', 'AMSK TIVAT', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 2, kat. 1, #75; K str. 3, AMSK VLAHOVIĆ, #75
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-amsk-vlahovic', 'AMSK VLAHOVIĆ', 'ME', (SELECT id FROM federations WHERE name='Auto i karting savez Crne Gore' AND country_code='ME'), NULL);
-- R str. 3, kat. 1, #333; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-bmw', 'BMW', NULL, 'BMW');
-- R str. 2, kat. 1, #53; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-bmw-e46', 'BMW', 'E46', 'BMW E46');
-- R str. 1, kat. 1, #217; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-bmw-m3', 'BMW', 'M3', 'BMW M3');
-- R str. 1, kat. 1, #7; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-citroen-c2', 'CITROEN', 'C2', 'CITROEN C2');
-- R str. 1, kat. 1, #29; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-citroen-saxo', 'CITROEN', 'SAXO', 'CITROEN SAXO');
-- R str. 1, kat. 1, #88; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-fiat-500', 'FIAT', '500', 'FIAT 500');
-- R str. 1, kat. 1, #36; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-fiat-750', 'FIAT', '750', 'FIAT 750');
-- R str. 2, kat. 1, #78; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-fiat-siecento', 'FIAT', 'SIECENTO', 'FIAT SIECENTO');
-- R str. 3, kat. 2, #21; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-global', 'GLOBAL', NULL, 'GLOBAL');
-- R str. 3, kat. 2, #52; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-global-light', 'GLOBAL', 'LIGHT', 'GLOBAL LIGHT');
-- R str. 1, kat. 1, #26; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-honda-civic', 'HONDA', 'CIVIC', 'HONDA CIVIC');
-- R str. 2, kat. 1, #500; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-nisan-micra', 'NISAN', 'MICRA', 'NISAN MICRA');
-- R str. 3, kat. 2, #14; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-norma-n202b', 'NORMA', 'N202B', 'NORMA N202B');
-- R str. 2, kat. 1, #506; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-opel-astra', 'OPEL', 'ASTRA', 'OPEL ASTRA');
-- R str. 1, kat. 1, #151; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-opel-corsa', 'OPEL', 'CORSA', 'OPEL CORSA');
-- R str. 1, kat. 1, #31; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-peugeot-106', 'PEUGEOT', '106', 'PEUGEOT 106');
-- R str. 1, kat. 1, #12; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-peugeot-306', 'PEUGEOT', '306', 'PEUGEOT 306');
-- R str. 2, kat. 1, #119; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-rover', 'ROVER', NULL, 'ROVER');
-- R str. 1, kat. 1, #23; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-rover-mg', 'ROVER', 'MG', 'ROVER MG');
-- R str. 3, kat. 1, #237; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-seat-leon', 'SEAT', 'LEON', 'SEAT LEON');
-- R str. 1, kat. 1, #71; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-suzuki-swift', 'SUZUKI', 'SWIFT', 'SUZUKI SWIFT');
-- R str. 1, kat. 1, #222; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-suzuki-swift-gti', 'SUZUKI', 'SWIFT GTI', 'SUZUKI SWIFT GTI');
-- R str. 1, kat. 1, #104; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-vw-golf-i', 'VW', 'GOLF I', 'VW GOLF I');
-- R str. 1, kat. 1, #16; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-vw-golf-v', 'VW', 'GOLF V', 'VW GOLF V');
-- R str. 2, kat. 1, #95; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-vw-polo', 'VW', 'POLO', 'VW POLO');
-- R str. 1, kat. 1, #19; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-wv-polo', 'WV', 'POLO', 'WV POLO');
-- R str. 1, kat. 1, #30; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-yugo', 'YUGO', NULL, 'YUGO');
-- R str. 1, kat. 1, #262; reusable description only, not a chassis identity.
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-skoda-felicia', 'ŠKODA', 'FELICIA', 'ŠKODA FELICIA');
-- R p.4, table KLASA A 1150.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-a-1150', 'A 1150', NULL, 1);
-- R p.5, table KLASA A 1400.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-a-1400', 'A 1400', NULL, 2);
-- R p.6, table KLASA A 1600.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-a-1600', 'A 1600', NULL, 3);
-- R p.7, table KLASA A 2000.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-a-2000', 'A 2000', NULL, 4);
-- R p.7, table KLASA D/E2/CN.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-d-e2-cn', 'D/E2/CN', NULL, 5);
-- R p.5, table KLASA E1 1200.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-e1-1200', 'E1 1200', NULL, 6);
-- R p.5, table KLASA E2SH.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-e2sh', 'E2SH', NULL, 7);
-- R p.3, table KLASA H 1000.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-h-1000', 'H 1000', NULL, 8);
-- R p.5, table KLASA H 1400.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-h-1400', 'H 1400', NULL, 9);
-- R p.6, table KLASA H 1600.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-h-1600', 'H 1600', NULL, 10);
-- R p.7, table KLASA H 2000.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-h-2000', 'H 2000', NULL, 11);
-- R p.7, table KLASA H 2000+.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-h-2000-plus', 'H 2000+', NULL, 12);
-- R p.3, table KLASA N 1150.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-n-1150', 'N 1150', NULL, 13);
-- R p.4, table KLASA N 1400.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-n-1400', 'N 1400', NULL, 14);
-- R p.6, table KLASA N 1600.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-n-1600', 'N 1600', NULL, 15);
-- R p.6, table KLASA N 2000.
INSERT INTO competition_classes (archive_key, code, name, display_order) VALUES ('class-akscg-n-2000', 'N 2000', NULL, 16);
-- R footer: KOTOR,TRACK trojica L=4350 m. This does not establish any other year's course configuration.
INSERT INTO tracks (archive_key, name, location, country_code, identity_notes) VALUES ('track-kotor-trojica', 'Kotor–Trojica', 'Kotor', 'ME', 'Konačni rezultati trke 03.05.2026. navode dužinu staze 4350 m.');
