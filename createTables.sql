CREATE TABLE user
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(64) NOT NULL,
    lastname  VARCHAR(64) NOT NULL,
    username  VARCHAR(64) NOT NULL UNIQUE,
    password  VARCHAR(64) NOT NULL,
    role      VARCHAR(32),
    state     VARCHAR(32)
);

CREATE TABLE account
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    number  VARCHAR(64) NOT NULL,
    balance DECIMAL,
    state   VARCHAR(32),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT
);

CREATE TABLE card
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    number     VARCHAR(64) NOT NULL,
    state      VARCHAR(32),
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE RESTRICT
);

CREATE TABLE payment
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    sum          DECIMAL,
    comment      VARCHAR(128),
    status       VARCHAR(32),
    lastModified TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    account_id   INT,
    card_id      INT,
    FOREIGN KEY (account_id) REFERENCES account (id) ON DELETE RESTRICT,
    FOREIGN KEY (card_id) REFERENCES card (id) ON DELETE RESTRICT
);