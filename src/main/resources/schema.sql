DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE GLOBAL_SEQ;

CREATE TABLE users
(
    id               INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    email            VARCHAR                NOT NULL,
    password         VARCHAR                NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx
    ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE internal_log
(
    id               INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    created_date     DATETIME               NOT NULL default CURRENT_TIMESTAMP,
    request            VARCHAR,
    response           VARCHAR
);

CREATE TABLE external_log
(
    id               INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    created_date     DATETIME               NOT NULL default CURRENT_TIMESTAMP,
    request            VARCHAR,
    response           VARCHAR
);
