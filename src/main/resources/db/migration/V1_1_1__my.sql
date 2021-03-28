CREATE TABLE val_curs
(
    name VARCHAR(255) PRIMARY KEY NOT NULL,
    date DATE
);

CREATE TABLE valute
(
    id        VARCHAR(255) PRIMARY KEY UNIQUE NOT NULL,
    char_code VARCHAR(255),
    name      VARCHAR(255),
    nominal   INTEGER,
    num_code  VARCHAR(255),
    value     NUMERIC,
    curs_date VARCHAR(255),

    CONSTRAINT fk_val_curs
        FOREIGN KEY (curs_date)
            REFERENCES val_curs (name)
);

CREATE TABLE history
(
    id          INTEGER PRIMARY KEY NOT NULL,
    date         VARCHAR(255),
    from_value  VARCHAR(255),
    from_valute VARCHAR(255),
    to_value    VARCHAR(255),
    to_valute   VARCHAR(255)
);

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  boolean      NOT NULL,
    PRIMARY KEY (username)
);


CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,

    CONSTRAINT authorities_idx UNIQUE (username, authority),

    CONSTRAINT authorities_ibfk_1
        FOREIGN KEY (username)
            REFERENCES users (username)
);

INSERT INTO users
VALUES ('user1', '{noop}user1', true),
       ('user2', '{noop}user2', true);


INSERT INTO authorities
VALUES ('user1', 'ROLE_ADMIN'),
       ('user2', 'ROLE_USER');

