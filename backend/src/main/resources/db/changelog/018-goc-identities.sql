-- Goč 2026: all identity decisions are recorded in the event review.
-- Only two new people, one new club, one track and the SAKSS host are created.
-- Existing canonical names EFIMIN DMITRI and SAKIB DURAKOVIĆ remain unchanged; Goč spellings become evidenced aliases.
INSERT INTO federations (name, country_code) VALUES ('Sportski auto i karting savez Srbije', 'RS');
-- originals/generalni_plasman.html; table res-results-tbody row 7; #4; participant CSV line 2. Nationality is not inferred from championship membership.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-goran-dojcinovic', 'GORAN', 'DOJČINOVIĆ', NULL);
-- originals/generalni_plasman.html; table res-results-tbody row 26; #666; participant CSV line 44. Nationality is not inferred from championship membership.
INSERT INTO drivers (archive_key, first_name, last_name, nationality_code) VALUES ('driver-ena-plakalo', 'ENA', 'PLAKALO', NULL);
-- Public SCG tables, Petar Jovanović #30. Club country/federation not established; preserve NULL.
INSERT INTO clubs (archive_key, name, country_code, federation_id, code) VALUES ('club-asu-zeka-auto-sport', 'ASU Zeka Auto sport', NULL, NULL, NULL);
-- originals/pravilnik.pdf p.5 articles 4.1-4.2, p.15 map; original bytes registered below.
INSERT INTO tracks (archive_key, name, location, country_code, identity_notes) VALUES ('track-goc', 'Goč', 'Goč, Kraljevo', 'RS', 'Pravilnik za Goč 2026 navodi dužinu 2950 m i visinsku razliku 205 m. Navedena konfiguracija odnosi se na događaj 2026.');
