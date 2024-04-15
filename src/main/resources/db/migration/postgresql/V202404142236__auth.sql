--------------------------------CREATE TABLE USERS
--------------------------------------------------
CREATE SEQUENCE IF NOT EXISTS users_id_seq INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT NOT NULL
        CONSTRAINT users_pk
            PRIMARY KEY,
    email       VARCHAR(50) NOT NULL,
    password    VARCHAR(120) NOT NULL,
    username    VARCHAR(20) NOT NULL
);

--------------------------------CREATE TABLE ROLES
--------------------------------------------------
CREATE SEQUENCE IF NOT EXISTS roles_id_seq INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS roles
(
    id      BIGINT NOT NULL
        CONSTRAINT roles_pk
            PRIMARY KEY,
    name    VARCHAR(20) NOT NULL
);

---------------------------CREATE TABLE USER_ROLES
--------------------------------------------------
CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT NOT NULL
        CONSTRAINT fk_user_roles_user_id REFERENCES users,
    role_id BIGINT NOT NULL
        CONSTRAINT fk_user_roles_role_id REFERENCES roles
);

CREATE INDEX IF NOT EXISTS in_consent$tester_id$consent_document_id
    ON user_roles (user_id, role_id);

--------------------------------------INSERT ROLES
--------------------------------------------------
INSERT INTO roles(id, name) VALUES(1, 'ROLE_STUDENT');
INSERT INTO roles(id, name) VALUES(51, 'ROLE_MODERATOR');
INSERT INTO roles(id, name) VALUES(101, 'ROLE_ADMIN');