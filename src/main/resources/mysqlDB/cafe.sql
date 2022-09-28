USE cafe;

INSERT INTO franchises (name)
VALUES ('UA'),
('PL'),
('GB');

INSERT INTO cafe_addresses (street_name, building_num, city, franchises_id, country)
VALUES ('Harmatna', 57, 'Kyiv', 1, 'Ukraine'),
('Sobornosti', 44, 'Poltava', 1, 'Ukraine'),
('Bazylianska', 3, 'Warsaw', 2, 'Poland'),
('Kubusia Puchatka', 1, 'Warsaw', 2, 'Poland'),
('Baker Street', 4, 'London', 3, 'Great Britain'),
('Independency Square', 1, 'Kyiv', 1, 'Ukraine');

INSERT INTO menu (menu_type)
VALUES ('Vegan menu'),
('Dessert menu'),
('Main menu'),
('Wine cart'),
('Drink menu');

INSERT INTO menu_item (position_name, serving_portion, measurement_unit, price, menu_id)
VALUES ('Seawed Tofu Soup', 330, 'ml', 20, 1),
('Corn with Egg Flower Soup', 330, 'ml', 22, 1),
('Sweet & Sour Walnuts', 220, 'g', 35, 1),
('Vegetables in Curry', 460, 'g', 43, 1),
('Grean tea', 500, 'ml', 15, 5),
('Coffee', 220, 'ml', 17, 5),
('Combucha', 330, 'ml', 19, 5),
('Tiramisu', 175, 'g', 21, 2),
('Cheesecake New York', 190, 'g', 25, 2),
('Red dry wine', 150, 'ml', 26, 4),
('White dry wine', 150, 'ml', 26, 4),
('Bolognese', 370, 'g', 30, 3),
('Beaf Steak',  250, 'g', 40, 3),
('Cream Soup', 330, 'ml', 25, 3);

INSERT INTO cafes(cafe_addresses_id, menu_id, cafe_name)
VALUES (6, 3, 'JONSONUK'),
(1, 1, 'AVIATOR'),
(2, 3, 'ZSU'),
(3, 2, 'SANKTSIYI'),
(4, 4, 'SLOZY RUSNI'),
(5, 5, 'UKRAINE');

ALTER TABLE employees MODIFY phone_num VARCHAR(15);
ALTER TABLE employees AUTO_INCREMENT = 1;

INSERT INTO employees (name, last_name, phone_num, position, cafes_id)
VALUES ('Artem', 'Kruk', '+380999453876', 'waiter', 1),
('Stepan', 'Bandera', '+480974558862', 'head', 4),
('Viktoriia', 'Masko', '+380764552385', 'admin', 2),
('Pavlo', 'Bilyk', '+380995632754', 'chef', 3),
('Ann', 'Pysar', '+48055997635', 'waiter', 5),
('Benedikt', 'Camdber', '+440877665239', 'admin', 6),
('Bob', 'Marley', '+4408578988', 'lawyer', 6);

ALTER TABLE tables AUTO_INCREMENT = 1;

INSERT INTO tables (seating_size, cafes_id)
VALUES (1, 1), (2,1), (3,1), (1, 1), (2,1), (3,1),
(1, 2), (2,2), (3,2), (1, 2), (2,2), (3,2),
(1, 3), (2,3), (3,3), (1, 3), (2,3), (3,3),
(1, 4), (2,4), (3,4), (1, 4), (2,4), (3,4),
(1, 5), (2,5), (3,5), (1, 5), (2,5), (3,5),
(1, 6), (2,6), (3,6), (1, 6), (2,6), (3,6);

INSERT INTO bookings (time, tables_id)
VALUES ('2022-09-20 17:30:00', 2),
('2022-09-19 20:00:00', 3),
('2022-09-21 16:00:00', 32),
('2022-09-22 10:30:00', 29),
('2022-10-01 13:15:00', 25);

ALTER TABLE guests AUTO_INCREMENT = 1;

INSERT INTO guests (name, last_name, bookings_id)
VALUES ('Olha', 'Kril', 1),
('Evhen', 'Star', 3),
('Oleh', 'Pavlov', 4),
('Maryna', 'Kravch', 5);

INSERT INTO orders(guests_id)
VALUES (1), (2), (3), (4);

INSERT INTO order_details(menu_items_qty, menu_item_id, orders_id)
VALUES (1, 8, 4),
(2, 12, 3),
(4, 11, 2),
(1, 5, 1);

INSERT INTO services (type)
VALUES ('Wedding'), ('Birthday'), ('Banquet'), ('Coworking');

INSERT INTO discounts (discount_type, discount_size)
VALUES ('regular', 10),
('birthday', 15),
('happy guest', 13),
('no discount', 0),
('present', 50);

INSERT INTO payments(total_price, orders_id, discounts_id)
VALUES (18.9, 4, 1),
(44.2, 3, 2);

INSERT INTO orders_has_services (ordserv_id, orders_id, services_id)
VALUES (1, 4, 1),
(2, 1, 3);

DELETE FROM employees
WHERE employee_id > 6;

DELETE FROM discounts
WHERE discount_id > 4;

DELETE FROM cafe_addresses
WHERE address_id > 6;

UPDATE services
SET type = 'Birthday Party' WHERE service_id = 2;

UPDATE cafe_addresses
SET building_num = 5
WHERE address_id = 5;

UPDATE menu_item
SET serving_portion = 210
WHERE item_id = 9;

SELECT * FROM employees 
WHERE employee_id = 2 AND position = 'head';

SELECT * FROM menu_item
WHERE price BETWEEN 20 AND 30;

SELECT * FROM cafe_addresses
WHERE street_name = 'Baker Street' OR address_id = 5;

SELECT * FROM menu_item
ORDER BY position_name;

SELECT COUNT(item_id), item_id
FROM menu_item
GROUP BY item_id;

SELECT employees.name, employees.last_name, cafes.cafe_name
FROM employees
LEFT JOIN cafes ON employees.cafes_id = cafe_id
ORDER BY cafe_name ASC;

SELECT bookings.time, tables.cafes_id, tables.seating_size
FROM bookings
INNER JOIN tables ON tables_id = table_id
ORDER BY cafes_id ASC;

SELECT guests.name, guests.last_name, bookings.tables_id
FROM guests
LEFT JOIN bookings ON bookings_id = booking_id
UNION
SELECT guests.name, guests.last_name, bookings.tables_id
FROM guests
RIGHT JOIN bookings ON bookings_id = booking_id;

SELECT tables.cafes_id, tables.seating_size, bookings.time
FROM tables
JOIN bookings ON table_id = tables_id
ORDER BY tables_id ASC;