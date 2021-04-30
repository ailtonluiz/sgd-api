CREATE TABLE unity (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       description VARCHAR(60) NULL NOT NULL,
                       short_description VARCHAR(3) NULL NOT NULL,
                       PRIMARY KEY (id))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;