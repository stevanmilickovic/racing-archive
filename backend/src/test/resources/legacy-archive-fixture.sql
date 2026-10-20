CREATE TABLE drivers (id BIGINT PRIMARY KEY, first_name VARCHAR(100), last_name VARCHAR(100));
CREATE TABLE clubs (id BIGINT PRIMARY KEY, name VARCHAR(150), code VARCHAR(50), country_code VARCHAR(2));
CREATE TABLE vehicles (id BIGINT PRIMARY KEY);
CREATE TABLE competition_classes (id BIGINT PRIMARY KEY, code VARCHAR(50));
CREATE TABLE championship_rounds (id BIGINT PRIMARY KEY);
CREATE TABLE entries (
    id BIGINT PRIMARY KEY,
    driver_id BIGINT NOT NULL REFERENCES drivers(id),
    vehicle_id BIGINT REFERENCES vehicles(id),
    club_id BIGINT REFERENCES clubs(id),
    competition_class_id BIGINT NOT NULL REFERENCES competition_classes(id)
);
INSERT INTO drivers VALUES (1,'Legacy','Driver');
CREATE TABLE classifications (id BIGINT PRIMARY KEY, competition_class_id BIGINT REFERENCES competition_classes(id));
CREATE TABLE points_schemes (id BIGINT PRIMARY KEY, competition_class_id BIGINT REFERENCES competition_classes(id));
CREATE TABLE club_scoring_rules (id BIGINT PRIMARY KEY, competition_class_id BIGINT REFERENCES competition_classes(id));
INSERT INTO clubs (id) VALUES (1);
