CREATE TABLE purchase
(
    id            BIGINT         NOT NULL AUTO_INCREMENT,
    creation_date DATETIME(6)    NULL NOT NULL,
    note          VARCHAR(255)   NULL DEFAULT NULL,
    status        VARCHAR(12)    NULL NOT NULL,
    total_value   DECIMAL(19, 2) NULL NOT NULL,
    company_id    BIGINT         NULL NOT NULL,
    supplier_id   BIGINT         NULL NOT NULL,
    user_id       BIGINT         NULL NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_company_purchase (company_id ASC) VISIBLE,
    INDEX fk_supplier_purchase (supplier_id ASC) VISIBLE,
    INDEX fk_user_purchase (user_id ASC) VISIBLE,
    CONSTRAINT fk_company_purchase
        FOREIGN KEY (company_id)
            REFERENCES company (id),
    CONSTRAINT fk_supplier_purchase
        FOREIGN KEY (supplier_id)
            REFERENCES supplier (id),
    CONSTRAINT fk_user_purchase
        FOREIGN KEY (user_id)
            REFERENCES user (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE item_purchase
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    quantity    INT            NULL NOT NULL,
    unity_value DECIMAL(19, 2) NULL NOT NULL,
    product_id  BIGINT         NULL NOT NULL,
    purchase_id BIGINT         NULL NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_product_item_purchase (product_id ASC) VISIBLE,
    INDEX fk_purchase_item_purchase (purchase_id ASC) VISIBLE,
    CONSTRAINT fk_product_item_purchase
        FOREIGN KEY (product_id)
            REFERENCES product (id),
    CONSTRAINT fk_purchase_item_purchase
        FOREIGN KEY (purchase_id)
            REFERENCES purchase (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;