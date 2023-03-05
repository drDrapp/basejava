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

INSERT INTO resume (uuid, full_name) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', '01');
INSERT INTO resume (uuid, full_name) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', '02');
INSERT INTO resume (uuid, full_name) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', '03');

INSERT INTO contact (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'SKYPE', 'SKYPE1');
INSERT INTO contact (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'LINKEDIN', 'LINKEDIN1');
INSERT INTO contact (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'STACKOVERFLOW', 'STACK1');
INSERT INTO contact (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'PHONE_HOME', 'PHONE_HOME1');
INSERT INTO contact (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'EMAIL', 'EMAIL1');
INSERT INTO contact (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'PHONE_MOBILE', 'MOBILE1');
INSERT INTO contact (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'PHONE', 'PHONE1');
INSERT INTO contact (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'GITHUB', 'GITHUB1');
INSERT INTO contact (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'HOME_PAGE', 'PAGE1');

INSERT INTO contact (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'SKYPE', 'SKYPE2');
INSERT INTO contact (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'LINKEDIN', 'LINKEDIN2');
INSERT INTO contact (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'STACKOVERFLOW', 'STACK2');
INSERT INTO contact (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'PHONE_HOME', 'PHONE_HOME2');
INSERT INTO contact (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'EMAIL', 'EMAIL2');
INSERT INTO contact (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'PHONE_MOBILE', 'MOBILE2');
INSERT INTO contact (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'PHONE', 'PHONE2');
INSERT INTO contact (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'GITHUB', 'GITHUB2');
INSERT INTO contact (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'HOME_PAGE', 'PAGE2');

INSERT INTO contact (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'SKYPE', 'SKYPE3');
INSERT INTO contact (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'LINKEDIN', 'LINKEDIN3');
INSERT INTO contact (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'STACKOVERFLOW', 'STACK3');
INSERT INTO contact (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'PHONE_HOME', 'PHONE_HOME3');
INSERT INTO contact (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'EMAIL', 'EMAIL3');
INSERT INTO contact (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'PHONE_MOBILE', 'MOBILE3');
INSERT INTO contact (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'PHONE', 'PHONE3');
INSERT INTO contact (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'GITHUB', 'GITHUB3');
INSERT INTO contact (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'HOME_PAGE', 'PAGE3');

INSERT INTO section (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'OBJECTIVE', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.TextSection","INSTANCE":{"text":"OBJECTIVE1"}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'PERSONAL', 		'{"CLASSNAME":"model.ru.drdrapp.webapp.TextSection","INSTANCE":{"text":"PERSONAL1"}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'ACHIEVEMENT', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.ListSection","INSTANCE":{"items":["ACHIEVEMENT1 01","ACHIEVEMENT1 02","ACHIEVEMENT1 03"]}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'QUALIFICATIONS','{"CLASSNAME":"model.ru.drdrapp.webapp.ListSection","INSTANCE":{"items":["QUALIFICATIONS1 01","QUALIFICATIONS1 02","QUALIFICATIONS1 03"]}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'EXPERIENCE', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.OrganizationsSection","INSTANCE":{"organizations":[{"title":{"title":"EXPERIENCE 01","url":"EX01 URL"},"periods":[{"position":"EX01 POSITION 1","description":"EX01 DESCRIPTION 2","dateFrom":"1001-01-01","dateTo":"1001-01-02"},{"position":"EX01 POSITION 2","description":"EX01 DESCRIPTION 2","dateFrom":"1001-01-03","dateTo":"1001-01-04"},{"position":"EX01 POSITION 3","description":"EX01 DESCRIPTION 3","dateFrom":"1001-01-06","dateTo":"1001-01-06"}]},{"title":{"title":"EXPERIENCE 02","url":"EX02 URL"},"periods":[{"position":"EX02 POSITION","description":"EX02 DESCRIPTION","dateFrom":"1001-02-01","dateTo":"1001-02-02"}]}]}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('fdccfeea-71f3-47c5-b0e9-a021c5053f58', 'EDUCATION', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.OrganizationsSection","INSTANCE":{"organizations":[{"title":{"title":"EDUCATION3 01","url":""},"periods":[{"position":"ED3 01 POSITION","description":"ED3 01 DESCRIPTION","dateFrom":"0003-01-01","dateTo":"0003-01-02"}]},{"title":{"title":"EDUCATION3 02","url":""},"periods":[{"position":"ED3 02 POSITION","description":"ED3 02 DESCRIPTION","dateFrom":"0003-02-01","dateTo":"0003-02-02"}]}]}}');

INSERT INTO section (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'OBJECTIVE', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.TextSection","INSTANCE":{"text":"OBJECTIVE2"}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'PERSONAL', 		'{"CLASSNAME":"model.ru.drdrapp.webapp.TextSection","INSTANCE":{"text":"PERSONAL2"}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'ACHIEVEMENT', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.ListSection","INSTANCE":{"items":["ACHIEVEMENT2 01","ACHIEVEMENT2 02","ACHIEVEMENT2 03"]}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'QUALIFICATIONS','{"CLASSNAME":"model.ru.drdrapp.webapp.ListSection","INSTANCE":{"items":["QUALIFICATIONS2 01","QUALIFICATIONS2 02","QUALIFICATIONS2 03"]}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'EXPERIENCE', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.OrganizationsSection","INSTANCE":{"organizations":[{"title":{"title":"EXPERIENCE2 01","url":"EX01 URL"},"periods":[{"position":"EX2 01 POSITION","description":"EX2 01 DESCRIPTION","dateFrom":"0002-01-01","dateTo":"0002-01-02"}]},{"title":{"title":"EXPERIENCE2 02","url":"EX02 URL"},"periods":[{"position":"EX2 02 POSITION","description":"EX2 02 DESCRIPTION","dateFrom":"0002-02-01","dateTo":"0002-02-02"}]}]}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('061c5070-f9e6-452e-88f7-53b6188c33ba', 'EDUCATION', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.OrganizationsSection","INSTANCE":{"organizations":[{"title":{"title":"EDUCATION2 01","url":""},"periods":[{"position":"ED2 01 POSITION","description":"ED2 01 DESCRIPTION","dateFrom":"0002-01-01","dateTo":"0002-01-02"}]},{"title":{"title":"EDUCATION2 02","url":""},"periods":[{"position":"ED2 02 POSITION","description":"ED2 02 DESCRIPTION","dateFrom":"0002-02-01","dateTo":"0002-02-02"}]}]}}');

INSERT INTO section (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'OBJECTIVE', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.TextSection","INSTANCE":{"text":"OBJECTIVE3"}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'PERSONAL', 		'{"CLASSNAME":"model.ru.drdrapp.webapp.TextSection","INSTANCE":{"text":"PERSONAL3"}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'ACHIEVEMENT', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.ListSection","INSTANCE":{"items":["ACHIEVEMENT3 01","ACHIEVEMENT3 02","ACHIEVEMENT3 03"]}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'QUALIFICATIONS','{"CLASSNAME":"model.ru.drdrapp.webapp.ListSection","INSTANCE":{"items":["QUALIFICATIONS3 01","QUALIFICATIONS3 02","QUALIFICATIONS3 03"]}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'EXPERIENCE', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.OrganizationsSection","INSTANCE":{"organizations":[{"title":{"title":"EXPERIENCE3 01","url":"EX01 URL"},"periods":[{"position":"EX3 01 POSITION","description":"EX3 01 DESCRIPTION","dateFrom":"0003-01-01","dateTo":"0003-01-02"}]},{"title":{"title":"EXPERIENCE3 02","url":"EX02 URL"},"periods":[{"position":"EX3 02 POSITION","description":"EX3 02 DESCRIPTION","dateFrom":"0003-02-01","dateTo":"0003-02-02"}]}]}}');
INSERT INTO section (resume_uuid, type, value) VALUES ('5435fa5b-f5ca-4b1a-84eb-afe040248da1', 'EDUCATION', 	'{"CLASSNAME":"model.ru.drdrapp.webapp.OrganizationsSection","INSTANCE":{"organizations":[{"title":{"title":"EDUCATION3 01","url":""},"periods":[{"position":"ED3 01 POSITION","description":"ED3 01 DESCRIPTION","dateFrom":"0003-01-01","dateTo":"0003-01-02"}]},{"title":{"title":"EDUCATION3 02","url":""},"periods":[{"position":"ED3 02 POSITION","description":"ED3 02 DESCRIPTION","dateFrom":"0003-02-01","dateTo":"0003-02-02"}]}]}}');
