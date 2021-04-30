CREATE TABLE company (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         accountable VARCHAR(150) NULL DEFAULT NULL,
                         complement VARCHAR(150) NULL DEFAULT NULL,
                         district VARCHAR(50) NULL DEFAULT NULL,
                         number VARCHAR(5) NULL DEFAULT NULL,
                         street VARCHAR(150) NULL NOT NULL,
                         company_fantasy VARCHAR(80) NULL NOT NULL,
                         company_name VARCHAR(100) NULL NOT NULL,
                         email VARCHAR(150) NULL NOT NULL,
                         manager VARCHAR(150) NULL NOT NULL,
                         phone VARCHAR(13) NULL NOT NULL,
                         city_id BIGINT NULL NOT NULL,
                         PRIMARY KEY (id))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;