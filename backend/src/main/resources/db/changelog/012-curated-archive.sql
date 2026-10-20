-- Existing records remain intact. Assign archive keys to legacy records only after reviewing their identity.
ALTER TABLE drivers ADD COLUMN archive_key VARCHAR(160);
ALTER TABLE drivers ADD CONSTRAINT uk_drivers_archive_key UNIQUE (archive_key);
ALTER TABLE drivers ADD COLUMN identity_notes TEXT;

ALTER TABLE clubs ADD COLUMN archive_key VARCHAR(160);
ALTER TABLE clubs ADD CONSTRAINT uk_clubs_archive_key UNIQUE (archive_key);
ALTER TABLE clubs ADD COLUMN identity_notes TEXT;

ALTER TABLE vehicles ADD COLUMN archive_key VARCHAR(160);
ALTER TABLE vehicles ADD CONSTRAINT uk_vehicles_archive_key UNIQUE (archive_key);
ALTER TABLE vehicles ADD COLUMN identity_notes TEXT;

ALTER TABLE competition_classes ADD COLUMN archive_key VARCHAR(160);
ALTER TABLE competition_classes ADD CONSTRAINT uk_competition_classes_archive_key UNIQUE (archive_key);
ALTER TABLE competition_classes ADD COLUMN identity_notes TEXT;

ALTER TABLE championship_rounds ADD COLUMN archive_key VARCHAR(160);
ALTER TABLE championship_rounds ADD CONSTRAINT uk_championship_rounds_archive_key UNIQUE (archive_key);
ALTER TABLE championship_rounds ADD COLUMN identity_notes TEXT;

CREATE TABLE tracks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    archive_key VARCHAR(160) NOT NULL UNIQUE,
    name VARCHAR(200) NOT NULL,
    location VARCHAR(200),
    country_code VARCHAR(2),
    identity_notes TEXT
);

ALTER TABLE championship_rounds ADD COLUMN track_id BIGINT;
ALTER TABLE championship_rounds ADD CONSTRAINT fk_round_track FOREIGN KEY (track_id) REFERENCES tracks(id);

CREATE TABLE source_documents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    source_key VARCHAR(200) NOT NULL UNIQUE,
    round_id BIGINT NOT NULL,
    title VARCHAR(250) NOT NULL,
    file_path VARCHAR(500) NOT NULL UNIQUE,
    media_type VARCHAR(100) NOT NULL,
    sha256 VARCHAR(64) NOT NULL,
    original_url VARCHAR(1000),
    notes TEXT,
    CONSTRAINT fk_source_document_round FOREIGN KEY (round_id) REFERENCES championship_rounds(id)
);

CREATE TABLE identity_aliases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    alias VARCHAR(250) NOT NULL,
    driver_id BIGINT,
    club_id BIGINT,
    vehicle_id BIGINT,
    track_id BIGINT,
    competition_class_id BIGINT,
    source_document_id BIGINT,
    source_locator VARCHAR(250),
    notes TEXT,
    CONSTRAINT fk_alias_driver FOREIGN KEY (driver_id) REFERENCES drivers(id),
    CONSTRAINT fk_alias_club FOREIGN KEY (club_id) REFERENCES clubs(id),
    CONSTRAINT fk_alias_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
    CONSTRAINT fk_alias_track FOREIGN KEY (track_id) REFERENCES tracks(id),
    CONSTRAINT fk_alias_class FOREIGN KEY (competition_class_id) REFERENCES competition_classes(id),
    CONSTRAINT fk_alias_source FOREIGN KEY (source_document_id) REFERENCES source_documents(id),
    CONSTRAINT ck_alias_one_identity CHECK (
        (CASE WHEN driver_id IS NULL THEN 0 ELSE 1 END) +
        (CASE WHEN club_id IS NULL THEN 0 ELSE 1 END) +
        (CASE WHEN vehicle_id IS NULL THEN 0 ELSE 1 END) +
        (CASE WHEN track_id IS NULL THEN 0 ELSE 1 END) +
        (CASE WHEN competition_class_id IS NULL THEN 0 ELSE 1 END) = 1
    )
);

CREATE INDEX ix_identity_alias_name ON identity_aliases(alias);
