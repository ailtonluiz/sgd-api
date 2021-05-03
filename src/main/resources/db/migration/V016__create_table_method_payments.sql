CREATE TABLE payment_mehtod
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    description VARCHAR(60) NOT NULL,
    date_update DATETIME NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

