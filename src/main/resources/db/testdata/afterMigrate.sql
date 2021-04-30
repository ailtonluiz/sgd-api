SET foreign_key_checks = 0;

DELETE FROM brand;
DELETE FROM country;
DELETE FROM city;
DELETE FROM state;
DELETE FROM client_group;
DELETE FROM client;


SET foreign_key_checks = 1;

ALTER TABLE brand AUTO_INCREMENT = 1;
ALTER TABLE country AUTO_INCREMENT = 1;
ALTER TABLE city AUTO_INCREMENT = 1;
ALTER TABLE state AUTO_INCREMENT = 1;
ALTER TABLE client_group AUTO_INCREMENT = 1;
ALTER TABLE client AUTO_INCREMENT = 1;

INSERT INTO brand (id, name)
VALUES (1, 'Sony');
INSERT INTO brand (id, name)
VALUES (2, 'Philips');
INSERT INTO brand (id, name)
VALUES (3, 'LG');

INSERT INTO country (id, name, short_name)
VALUES (1, 'Andorra', 'AND');
INSERT INTO country (id, name, short_name)
VALUES (2, 'Brasil', 'BR');
INSERT INTO country (id, name, short_name)
VALUES (3, 'Peru', 'PE');

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
VALUES (1, '2 ER 5','Pas de La Casa', '17','Carrer Major','ailtonluiz@icloud.com','AILTON LUIZ','CADASTRO DE TESTE','376 610-680','ACTIVE',1,1);