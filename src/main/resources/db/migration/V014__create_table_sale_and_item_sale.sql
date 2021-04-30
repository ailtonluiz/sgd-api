CREATE TABLE sale
(
    id            BIGINT         NOT NULL AUTO_INCREMENT,
    creation_date DATETIME(6)    NULL NOT NULL,
    note          VARCHAR(255)   NULL DEFAULT NULL,
    status        VARCHAR(12)    NULL NOT NULL,
    total_value   DECIMAL(19, 2) NULL NOT NULL,
    client_id     BIGINT         NULL NOT NULL,
    company_id    BIGINT         NULL NOT NULL,
    user_id       BIGINT         NULL NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_client_sale (client_id ASC) VISIBLE,
    INDEX fk_company_sale (company_id ASC) VISIBLE,
    INDEX fk_user_sale (user_id ASC) VISIBLE,
    CONSTRAINT fk_client_sale
        FOREIGN KEY (client_id)
            REFERENCES client (id),
    CONSTRAINT fk_company_sale
        FOREIGN KEY (company_id)
            REFERENCES company (id),
    CONSTRAINT fk_user_sale
        FOREIGN KEY (user_id)
            REFERENCES user (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE item_sale
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    quantity    INT            NULL DEFAULT NULL,
    unity_value DECIMAL(19, 2) NULL DEFAULT NULL,
    product_id  BIGINT         NULL DEFAULT NULL,
    sale_id     BIGINT         NULL DEFAULT NULL,
    PRIMARY KEY (id),
    INDEX fk_product_item_sale (product_id ASC) VISIBLE,
    INDEX fk_sale_item_sale (sale_id ASC) VISIBLE,
    CONSTRAINT fk_product_item_sale
        FOREIGN KEY (product_id)
            REFERENCES product (id),
    CONSTRAINT fk_sale_item_sale
        FOREIGN KEY (sale_id)
            REFERENCES sale (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;