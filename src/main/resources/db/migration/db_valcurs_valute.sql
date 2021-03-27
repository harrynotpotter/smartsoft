CREATE TABLE val_curs
(
    name VARCHAR(255) PRIMARY KEY NOT NULL,
    date DATE
);

CREATE TABLE valute
(
    id        VARCHAR(255) UNIQUE NOT NULL,
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

