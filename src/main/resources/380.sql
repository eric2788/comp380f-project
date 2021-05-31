CREATE TABLE Account
(
    username VARCHAR(20)  NOT NULL,
    password VARCHAR(20)  NOT NULL,
    phone    INTEGER      NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    address  VARCHAR(100) NOT NULL,
    admin    BOOLEAN      NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE ShopItem
(
    id          INTEGER     NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name        VARCHAR(20) NOT NULL,
    description VARCHAR(50) NOT NULL,
    price       DOUBLE      NOT NULL,
    available   BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE Comment
(
    id       INTEGER     NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(20) NOT NULL,
    body     VARCHAR(50) NOT NULL,
    item_id  INTEGER     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (username) REFERENCES Account (username),
    FOREIGN KEY (item_id) REFERENCES ShopItem (id)
);

CREATE TABLE Photo
(
    id      BIGINT  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    content BLOB    NOT NULL,
    item_id INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES ShopItem (id)
);
