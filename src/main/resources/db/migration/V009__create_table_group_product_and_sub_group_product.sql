CREATE TABLE product_group
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NULL NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE product_sub_group
(
    id               BIGINT      NOT NULL AUTO_INCREMENT,
    name             VARCHAR(60) NULL NOT NULL,
    product_group_id BIGINT      NULL NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_product_group_product_subgroup (product_group_id ASC) VISIBLE,
    CONSTRAINT fk_product_group_product_subgroup
        FOREIGN KEY (product_group_id)
            REFERENCES product_group (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;