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
    username    VARCHAR(20) NOT NULL,
    created     TIMESTAMP,
    updated     TIMESTAMP,
    version     BIGINT,
    is_enabled  BOOLEAN
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
        CONSTRAINT user_roles_user_id_fk REFERENCES users,
    role_id BIGINT NOT NULL
        CONSTRAINT user_roles_role_id_fk REFERENCES roles
);

CREATE INDEX IF NOT EXISTS user_roles$user_id$role_id
    ON user_roles (user_id, role_id);

--------------------------------CREATE TABLE USERS
--------------------------------------------------
CREATE SEQUENCE IF NOT EXISTS messages_id_seq INCREMENT BY 10;

CREATE TABLE IF NOT EXISTS messages
(
    id          BIGINT NOT NULL
        CONSTRAINT messages_pk
            PRIMARY KEY,
    user_id     BIGINT NOT NULL
        CONSTRAINT messages_user_id_fk REFERENCES users,
    request     VARCHAR,
    response    VARCHAR,
    created     TIMESTAMP,
    updated     TIMESTAMP,
    version     BIGINT,
    is_enabled  BOOLEAN
        default true
);

--------------------------------------INSERT ROLES
--------------------------------------------------
INSERT INTO roles(id, name) VALUES(1, 'ROLE_STUDENT');
INSERT INTO roles(id, name) VALUES(51, 'ROLE_MODERATOR');
INSERT INTO roles(id, name) VALUES(101, 'ROLE_ADMIN');