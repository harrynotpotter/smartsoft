CREATE TABLE history
(
    id          INTEGER UNIQUE PRIMARY KEY NOT NULL,
    date         VARCHAR(255),
    from_value  VARCHAR(255),
    from_valute VARCHAR(255),
    to_value    VARCHAR(255),
    to_valute   VARCHAR(255)
);

