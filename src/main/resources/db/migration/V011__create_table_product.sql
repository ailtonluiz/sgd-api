CREATE TABLE product
(
    id                  BIGINT       NOT NULL AUTO_INCREMENT,
    bar_code            VARCHAR(255) NULL NOT NULL,
    description         VARCHAR(255) NULL NOT NULL,
    reference           VARCHAR(255) NULL NOT NULL,
    brand_id            BIGINT       NULL NOT NULL,
    product_subgroup_id BIGINT       NULL NOT NULL,
    unity_id            BIGINT       NULL NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_brand_product (brand_id ASC) VISIBLE,
    INDEX fk_product_subgroup_product (product_subgroup_id ASC) VISIBLE,
    INDEX fk_unity_product (unity_id ASC) VISIBLE,
    CONSTRAINT fk_brand_product
        FOREIGN KEY (brand_id)
            REFERENCES brand (id),
    CONSTRAINT fk_product_subgroup_product
        FOREIGN KEY (product_subgroup_id)
            REFERENCES product_sub_group (id),
    CONSTRAINT fk_unity_product
        FOREIGN KEY (unity_id)
            REFERENCES unity (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;