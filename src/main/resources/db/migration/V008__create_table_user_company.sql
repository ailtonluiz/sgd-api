CREATE TABLE company_user
(
    company_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    INDEX fk_company_user_user_id (user_id ASC) VISIBLE,
    INDEX fk_company_user_company_id (company_id ASC) VISIBLE,
    CONSTRAINT fk_company_user_company_id
        FOREIGN KEY (company_id)
            REFERENCES sgdapi.company (id),
    CONSTRAINT fk_company_user_user_id
        FOREIGN KEY (user_id)
            REFERENCES sgdapi.user (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;