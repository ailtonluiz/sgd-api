CREATE TABLE state
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(150) NULL DEFAULT NULL,
    country_id BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE city
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    name     VARCHAR(150) NULL NOT NULL,
    state_id BIGINT       NULL NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_state_city (state_id ASC) VISIBLE,
    CONSTRAINT fk_state_city
        FOREIGN KEY (state_id)
            REFERENCES state (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;