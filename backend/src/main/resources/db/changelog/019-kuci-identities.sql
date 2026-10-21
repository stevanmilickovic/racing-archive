-- Kuči 2026: accepted programmer decisions and exact evidence are in the event review.
-- Preserve existing identities and historical event entries. New persons have no inferred nationality.
-- Manufacturer-only descriptions have model NULL. Megan and Cooper lack established manufacturers: no vehicle identity invented.
-- P p.1 row 1; source spelling Miloš Peruničić; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-milos-perunicic', 'MILOŠ', 'PERUNIČIĆ', NULL);
-- P p.1 row 12; source spelling Bojan Martinović; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-bojan-martinovic', 'BOJAN', 'MARTINOVIĆ', NULL);
-- P p.1 row 19; source spelling Ivan Vukčević; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-ivan-vukcevic', 'IVAN', 'VUKČEVIĆ', NULL);
-- P p.1 row 21; source spelling Miloš Milošev; registration only, no event entry or result.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-milos-milosev', 'MILOŠ', 'MILOŠEV', NULL);
-- P p.1 row 32; source spelling Božo Kotlaja; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-bozo-kotlaja', 'BOŽO', 'KOTLAJA', NULL);
-- P p.1 row 34; source spelling Aleksandar Brković; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-aleksandar-brkovic', 'ALEKSANDAR', 'BRKOVIĆ', NULL);
-- P p.2 row 18; source spelling Mladen Cebić; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-mladen-cebic', 'MLADEN', 'CEBIĆ', NULL);
-- P p.2 row 19; source spelling Nikola Brković; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-nikola-brkovic', 'NIKOLA', 'BRKOVIĆ', NULL);
-- P p.2 row 22; source spelling Aleksandar Krstevski; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-aleksandar-krstevski', 'ALEKSANDAR', 'KRSTEVSKI', NULL);
-- P p.2 row 24; source spelling Radomir Savićević; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-radomir-savicevic', 'RADOMIR', 'SAVIĆEVIĆ', NULL);
-- P p.2 row 28; source spelling Stanišić Veljko; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-veljko-stanisic', 'VELJKO', 'STANIŠIĆ', NULL);
-- T ranked row 57; source spelling IVANOVIĆ Igor; new person approved by programmer.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-igor-ivanovic', 'IGOR', 'IVANOVIĆ', NULL);
-- P p.2 row 18: Mladen Cebić; source club Mugoša; full formal name, country and federation are not established.
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-mugosa', 'Mugoša', NULL, NULL, NULL);
-- P p.1 row 4 Vojo Radanović [Peugeot]; P p.1 row 18 Marko Pejović [Peugeot]; P p.1 row 19 Ivan Vukčević [Peugeot]; P p.1 row 20 Dejan Đonović [Peugeot]; P p.1 row 21 Miloš Milošev [Peugeot]; P p.1 row 22 Miloš Vujnović [Peugeot]; P p.1 row 23 Đorđe Kovačević [Peugeot]; P p.1 row 24 Dejan Kovačević [Peugeot]; P p.1 row 25 Ilija Šapić [Peugeot]; P p.1 row 26 Nikolić Miljan [Peugeot]; P p.1 row 29 Milutin Krivokapić [Peugeot]; P p.1 row 31 Milan Bracanović [Peugeot]; P p.1 row 34 Aleksandar Brković [Peugeot]; P p.1 row 36 Slobodan Mugoša [Peugeot]; P p.2 row 7 Miloš Bojanović [Peugeot]; P p.2 row 20 Dejan Bulatović [Peugeot]
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-peugeot', 'PEUGEOT', NULL, 'PEUGEOT');
-- P p.1 row 33 Luka Šućur [Suzuki]; P p.2 row 9 Đuro Popović [Suzuki]
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-suzuki', 'SUZUKI', NULL, 'SUZUKI');
-- P p.2 row 5 Damjan Perović [Honda]; P p.2 row 12 Dušan Tujković [Honda]; P p.2 row 13 Marko Janković [Honda]; P p.2 row 14 Goran Vučetić [Honda]; P p.2 row 16 Radovan Masoničić [Honda]; P p.2 row 21 Milan Jakšić [Honda]; P p.2 row 24 Radomir Savićević [Honda]
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-honda', 'HONDA', NULL, 'HONDA');
-- P p.2 row 8 Balša Đuričković [Škoda]
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-skoda', 'ŠKODA', NULL, 'ŠKODA');
-- P p.2 row 15 Almin Ćorović [Opel]; P p.2 row 17 Radovan Radanović [Opel]
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-opel', 'OPEL', NULL, 'OPEL');
-- P p.2 row 19 Nikola Brković [Golf]; P p.2 row 23 Nikola Vlahović [Golf]
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-vw-golf', 'VW', 'GOLF', 'VW GOLF');
-- P p.2 row 22 Aleksandar Krstevski [Ford]
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-ford', 'FORD', NULL, 'FORD');
-- P p.2 row 26 Mile Malović [Seat]
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-seat', 'SEAT', NULL, 'SEAT');
-- P p.2 row 30 Baćko Vujović [Norma]
INSERT INTO vehicles (archive_key, manufacturer, model, display_name) VALUES ('vehicle-norma', 'NORMA', NULL, 'NORMA');
-- B p.5 art.2.1.5: KUČI 2026, 3900 m, width 6 m, Doljani to skretanje za Fundinu; M p.1 course diagram.
INSERT INTO tracks (archive_key, name, location, country_code, identity_notes) VALUES ('track-kuci', 'Kuči', 'Doljani – skretanje za Fundinu, Podgorica', 'ME', 'Pravilnik za Kuči 2026 navodi dužinu 3900 m i širinu 6 m; start Doljani, cilj skretanje za Fundinu. Konfiguracija se odnosi na događaj 2026.');
