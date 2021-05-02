SET foreign_key_checks = 0;

DELETE
FROM brand;
DELETE
FROM city;
DELETE
FROM state;
DELETE
FROM client_group;
DELETE
FROM client;
DELETE
FROM supplier_group;
DELETE
FROM supplier;
DELETE
FROM company;
DELETE
FROM company_user;
DELETE
FROM item_purchase;
DELETE
FROM purchase;
DELETE
FROM item_sale;
DELETE
FROM sale;
DELETE
FROM unity;
DELETE
FROM product_sub_group;
DELETE
FROM product_group;
DELETE
FROM product;
DELETE
FROM stock;
DELETE
FROM user_group;
DELETE
FROM user;



SET foreign_key_checks = 1;

ALTER TABLE brand
    AUTO_INCREMENT = 1;

ALTER TABLE city
    AUTO_INCREMENT = 1;
ALTER TABLE state
    AUTO_INCREMENT = 1;
ALTER TABLE client_group
    AUTO_INCREMENT = 1;
ALTER TABLE client
    AUTO_INCREMENT = 1;
ALTER TABLE supplier_group
    AUTO_INCREMENT = 1;
ALTER TABLE supplier
    AUTO_INCREMENT = 1;
ALTER TABLE company
    AUTO_INCREMENT = 1;
ALTER TABLE company_user
    AUTO_INCREMENT = 1;
ALTER TABLE item_purchase
    AUTO_INCREMENT = 1;
ALTER TABLE purchase
    AUTO_INCREMENT = 1;
ALTER TABLE item_sale
    AUTO_INCREMENT = 1;
ALTER TABLE sale
    AUTO_INCREMENT = 1;
ALTER TABLE unity
    AUTO_INCREMENT = 1;
ALTER TABLE product_sub_group
    AUTO_INCREMENT = 1;
ALTER TABLE product_group
    AUTO_INCREMENT = 1;
ALTER TABLE product
    AUTO_INCREMENT = 1;
ALTER TABLE stock
    AUTO_INCREMENT = 1;
ALTER TABLE user_group
    AUTO_INCREMENT = 1;
ALTER TABLE user
    AUTO_INCREMENT = 1;


INSERT INTO brand (id, name)
VALUES (1, 'Sony');
INSERT INTO brand (id, name)
VALUES (2, 'Philips');
INSERT INTO brand (id, name)
VALUES (3, 'LG');



INSERT INTO state (id, name, short_name, country_id)
VALUES (1, 'Andorra', 'AND', 1);
INSERT INTO state (id, name, short_name, country_id)
VALUES (2, 'Goiás', 'GO', 2);
INSERT INTO state (id, name, short_name, country_id)
VALUES (3, 'São Paulo', 'SP', 2);

INSERT INTO city (id, name, state_id)
VALUES (1, 'Pas de La Casa', 1);
INSERT INTO city (id, name, state_id)
VALUES (2, 'Andorra', 1);
INSERT INTO city (id, name, state_id)
VALUES (3, 'Canillo', 1);
INSERT INTO city (id, name, state_id)
VALUES (4, 'Goiânia', 2);
INSERT INTO city (id, name, state_id)
VALUES (5, 'Aparecida de Goiânia', 2);
INSERT INTO city (id, name, state_id)
VALUES (6, 'São Paulo', 3);
INSERT INTO city (id, name, state_id)
VALUES (7, 'Santos', 3);

INSERT INTO client_group (id, name)
VALUES (1, 'General');

INSERT INTO client (id, complement, district, number, street, email, name, note, phone, status, city_id,
                    client_group_id)
VALUES (1, '2 ER 5', 'Pas de La Casa', '17', 'Carrer Major', 'ailtonluiz@icloud.com', 'AILTON LUIZ',
        'CADASTRO DE TESTE', '376 610-680', 'ACTIVE', 1, 1);

INSERT INTO supplier_group (id, name)
VALUES (1, 'General');

INSERT INTO supplier (id, complement, district, number, street, email, name, note, phone, status, city_id,
                      supplier_group_id)
VALUES (1, '2 ER 5', 'Pas de La Casa', '17', 'Carrer Major', 'ailtonluiz@icloud.com', 'Fornecedor Padrao',
        'CADASTRO DE TESTE', '376 610-680', 'ACTIVE', 1, 1);

INSERT INTO company (id, accountable, complement, district, number, street, company_fantasy, company_name, email,
                     manager, phone, city_id)
VALUES (1, 'Responsavel', 'teste', 'Bairro', '1', 'RUA', 'NOME FANTASIA', 'EMPRESA PADRAO', 'email@email.com',
        'Gerente', '1245798', '1');

INSERT INTO user_group (id, name)
VALUES (1, 'Administrator');

INSERT INTO user (id, name, email, phone, password, status, user_group_id)
VALUES (1, 'Ailton Luiz', 'ailtonluiz@icloud.com', '376610680', 'PASSWORD', 'ACTIVE', 1);

INSERT INTO company_user (company_id, user_id)
VALUES (1, 1);
