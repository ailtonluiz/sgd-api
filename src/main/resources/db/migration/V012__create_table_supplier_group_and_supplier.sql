CREATE TABLE supplier_group
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(90) NULL DEFAULT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE supplier
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    complement VARCHAR(150) NULL DEFAULT NULL,
    district   VARCHAR(50)  NULL NOT NULL,
    number     VARCHAR(5)   NULL DEFAULT NULL,
    street     VARCHAR(150) NULL NOT NULL,
    email      VARCHAR(255) NULL NOT NULL,
    name       VARCHAR(200) NULL NOT NULL,
    note       VARCHAR(255) NULL DEFAULT NULL,
    phone      VARCHAR(15)  NULL NOT NULL,
    status     VARCHAR(10)  NULL NOT NULL,
    city_id    BIGINT       NULL NOT NULL,
    supplier_group_id BIGINT  NULL NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_address_city (city_id ASC) VISIBLE,
    CONSTRAINT fk_address_city_supplier FOREIGN KEY (city_id) REFERENCES city (id),
    INDEX fk_supplier_group (supplier_group_id ASC) VISIBLE,
    CONSTRAINT fk_supplier_group FOREIGN KEY (supplier_group_id) REFERENCES supplier_group (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;