DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS resume;

CREATE TABLE resume
(
    uuid      CHAR(36) NOT NULL
        CONSTRAINT resume_pk
            PRIMARY KEY,
    full_name TEXT     NOT NULL
);

CREATE TABLE contact
(
    id          SERIAL
        CONSTRAINT contact_pk
            PRIMARY KEY,
    resume_uuid CHAR(36) NOT NULL
        CONSTRAINT contact_resume_uuid_fk
            REFERENCES resume
            ON UPDATE RESTRICT ON DELETE CASCADE,
    type        TEXT     NOT NULL,
    value       TEXT     NOT NULL
);

CREATE UNIQUE INDEX contact_resume_uuid_type_index
    ON contact (resume_uuid, type);

ALTER TABLE IF EXISTS resume
    OWNER TO postgres;

ALTER TABLE contact
    OWNER TO postgres;