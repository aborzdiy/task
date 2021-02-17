DELETE FROM USER_ROLES;
DELETE FROM USERS;

ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO USERS (EMAIL, PASSWORD)
VALUES ('user@yandex.ru',   '{noop}password'),  -- 1
       ('admin@gmail.com',  '{noop}admin');     -- 2

INSERT INTO USER_ROLES (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);
