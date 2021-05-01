CREATE TABLE user
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(150) NOT NULL,
    phone         VARCHAR(13)  NOT NULL,
    password      VARCHAR(255) NOT NULL,
    status        VARCHAR(10)  NOT NULL,
    user_group_id BIGINT       NOT NULL,
        PRIMARY KEY (id),
    INDEX fk_user_group (user_group_id ASC) VISIBLE,
    CONSTRAINT fk_user_group FOREIGN KEY (user_group_id) REFERENCES user_group (id)


)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;