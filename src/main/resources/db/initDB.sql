DROP TABLE IF EXISTS shelf;
DROP TABLE IF EXISTS publishers;

CREATE TABLE shelf
(
    id    INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE publishers
(
    id     INTEGER ,
    publisher VARCHAR(100) NOT NULL,
    publisher_id        INTEGER NOT NULL AUTO_INCREMENT primary key ,
    CONSTRAINT fk_shelf FOREIGN KEY (id) REFERENCES shelf (id)
        ON DELETE CASCADE
) ENGINE = InnoDB;

