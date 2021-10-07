DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS clients;

CREATE TABLE users
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR not null,
    last_name  VARCHAR not null,
    patronymic VARCHAR


);

CREATE TABLE accounts
(
    id      INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id INTEGER     NOT NULL,
    number  VARCHAR(20) not null,
    balance DECIMAL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX account_number_unique_idx ON accounts (number);

CREATE TABLE cards
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    account_id INTEGER     NOT NULL,
    number     VARCHAR(16) not null,
    FOREIGN KEY (account_id) REFERENCES accounts (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX card_number_unique_idx ON cards (number);



