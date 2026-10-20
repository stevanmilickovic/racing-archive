-- The programmer confirmed these descriptions are the same model while reviewing Goč.
-- Keep the canonical archive key vehicle-vw-polo; do not remove any historical entry or evidence.
-- Original WV POLO evidence: 2026-05-02-kotor-trojica-akscg-official-2026-05-12,
-- p.1 category 1 #19 / p.4 class N 1400 #19 (Dejan Kovačević).
UPDATE entries
SET vehicle_id=(SELECT id FROM vehicles WHERE archive_key='vehicle-vw-polo')
WHERE vehicle_id=(SELECT id FROM vehicles WHERE archive_key='vehicle-wv-polo');

UPDATE identity_aliases
SET vehicle_id=(SELECT id FROM vehicles WHERE archive_key='vehicle-vw-polo')
WHERE vehicle_id=(SELECT id FROM vehicles WHERE archive_key='vehicle-wv-polo');

-- Removing only the confirmed duplicate after transferring all references is explicitly authorized.
DELETE FROM vehicles WHERE archive_key='vehicle-wv-polo';
