-- Retire the old speculative reference list on clean installations.
-- Keep reviewed identities and records already referenced by historical data.
-- Scoring configuration is intentionally retained.
DELETE FROM clubs
WHERE archive_key IS NULL
  AND country_code = 'ME'
  AND (code, name) IN (
      ('KOTOR', 'AMSK Kotor'), ('AP SPORT', 'AMSK AP Sport'), ('PODI', 'AMSK Podi'),
      ('HN', 'AMSK Herceg Novi'), ('CRMNICA', 'AMSK Crmnica'), ('GORSTAK', 'AMSK Gorstak'),
      ('TIVAT', 'AMSK Tivat'), ('LOVCEN', 'AMKK Lovcen R.T.'), ('PG', 'AMK Podgorica R.T.'),
      ('MONTERACING', 'AMK Monteracing'), ('VLAHOVIC', 'AMSK Vlahovic'), ('NILOVIC', 'AMSK Nilovic Racing')
  )
  AND NOT EXISTS (SELECT 1 FROM entries WHERE entries.club_id = clubs.id)
  AND NOT EXISTS (SELECT 1 FROM identity_aliases WHERE identity_aliases.club_id = clubs.id);

DELETE FROM competition_classes
WHERE archive_key IS NULL
  AND code IN ('H 1000', 'N 1150', 'A 1150', 'H 1150', 'N 1400', 'A 1400', 'H 1400',
               'N 1600', 'A 1600', 'H 1600', 'N 2000', 'N 2000+', 'D/E2/CN', 'E2SH', 'E1 1200')
  AND NOT EXISTS (SELECT 1 FROM entries WHERE entries.competition_class_id = competition_classes.id)
  AND NOT EXISTS (SELECT 1 FROM classifications WHERE classifications.competition_class_id = competition_classes.id)
  AND NOT EXISTS (SELECT 1 FROM points_schemes WHERE points_schemes.competition_class_id = competition_classes.id)
  AND NOT EXISTS (SELECT 1 FROM club_scoring_rules WHERE club_scoring_rules.competition_class_id = competition_classes.id)
  AND NOT EXISTS (SELECT 1 FROM identity_aliases WHERE identity_aliases.competition_class_id = competition_classes.id);
