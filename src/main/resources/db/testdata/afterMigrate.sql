SET foreign_key_checks = 0;

DELETE FROM brand;
DELETE FROM country;
DELETE FROM city;
DELETE FROM state;


SET foreign_key_checks = 1;

ALTER TABLE brand AUTO_INCREMENT = 1;
ALTER TABLE country AUTO_INCREMENT = 1;
ALTER TABLE city AUTO_INCREMENT = 1;
ALTER TABLE state AUTO_INCREMENT = 1;

INSERT INTO brand (id, name) values (1, 'Sony');
INSERT INTO country (id, name, short_name) values (1, 'Andorra', 'AND');
INSERT INTO state (id, name, country_id) values (1, 'Andorra',1);
INSERT INTO city (id, name, state_id) values (1, 'Pas de La Casa',1);