CREATE TABLE stock
(
    id                     BIGINT         NOT NULL AUTO_INCREMENT,
    box_quantity           INT            NULL DEFAULT NULL,
    cost_price             DECIMAL(19, 2) NULL NOT NULL,
    packing_quantity       DOUBLE         NULL DEFAULT NULL,
    sale_price             DECIMAL(19, 2) NULL NOT NULL,
    status                 VARCHAR(10)    NULL NOT NULL,
    stock_minimum          BIGINT         NULL DEFAULT NULL,
    stock_pending_quantity BIGINT         NULL DEFAULT NULL,
    stock_quantity         BIGINT         NULL DEFAULT NULL,
    company_id             BIGINT         NULL NOT NULL,
    product_id             BIGINT         NULL NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_company_stock (company_id ASC) VISIBLE,
    INDEX fk_product_stock (product_id ASC) VISIBLE,
    CONSTRAINT fk_company_stock
        FOREIGN KEY (company_id)
            REFERENCES company (id),
    CONSTRAINT fk_product_stock
        FOREIGN KEY (product_id)
            REFERENCES product (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;