DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS section;
DROP TABLE IF EXISTS resume;

CREATE TABLE IF NOT EXISTS resume
(
    uuid      CHAR(36) NOT NULL
        CONSTRAINT resume_pk
            PRIMARY KEY,
    full_name TEXT     NOT NULL
);

CREATE TABLE IF NOT EXISTS contact
(
    id          SERIAL
        CONSTRAINT contact_pk
            PRIMARY KEY,
    resume_uuid CHAR(36) NOT NULL
        CONSTRAINT contact_resume_uuid_fk
            REFERENCES resume
            ON UPDATE RESTRICT
            ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS contact_resume_uuid_type_index
    ON contact (resume_uuid, type);

CREATE TABLE IF NOT EXISTS section
(
    id          SERIAL
        CONSTRAINT section_pk
            PRIMARY KEY,
    resume_uuid CHAR(36) NOT NULL
        CONSTRAINT section_resume_uuid_fk
            REFERENCES resume
            ON UPDATE RESTRICT
            ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS section_resume_uuid_type_index
    ON section (resume_uuid, type);

ALTER TABLE IF EXISTS resume
    OWNER TO postgres;

ALTER TABLE contact
    OWNER TO postgres;

ALTER TABLE IF EXISTS section
    OWNER TO postgres;