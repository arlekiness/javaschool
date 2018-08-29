INSERT INTO testbase.stations (id, name, color) VALUES (1, 'Parnas', 'blue');
INSERT INTO testbase.stations (id, name, color) VALUES (2, 'Prosvescheniya pr-t', 'blue');
INSERT INTO testbase.stations (id, name, color) VALUES (3, 'Udelnaya', 'blue');
INSERT INTO testbase.stations (id, name, color) VALUES (4, 'Pionerskaya', 'blue');

INSERT INTO testbase.trains (id, name) VALUES (1, 'T1000');
INSERT INTO testbase.trains (id, name) VALUES (2, 'T666');

INSERT INTO testbase.seats (id, seat, train_id) VALUES (1, 1, 1);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (2, 2, 1);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (3, 3, 1);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (4, 4, 1);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (5, 1, 2);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (6, 2, 2);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (7, 3, 2);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (8, 4, 2);

INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (1, '2018-07-20 06:45:00', '2018-07-20 07:00:00' , 1, 1);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (2, '2018-07-20 08:00:00', '2018-07-20 08:15:00' , 2, 1);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (3, '2018-07-20 09:15:00', '2018-07-20 09:30:00' , 3, 1);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (4, '2018-07-20 10:30:00', '2018-07-20 10:45:00' , 4, 1);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (5, '2018-07-20 06:45:00', '2018-07-20 07:00:00' , 4, 2);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (6, '2018-07-20 08:00:00', '2018-07-20 08:15:00' , 3, 2);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (7, '2018-07-20 09:15:00', '2018-07-20 09:30:00' , 2, 2);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (8, '2018-07-20 10:30:00', '2018-07-20 10:45:00' , 1, 2);

INSERT INTO testbase.roles (id, type) VALUES (1, 'ADMIN');
INSERT INTO testbase.roles (id, type) VALUES (2, 'MANAGER');
INSERT INTO testbase.roles (id, type) VALUES (3, 'USER');

INSERT INTO testbase.users (id, firstName, lastName, login, password, birthDay, sex) VALUES (1, 'Alexey', 'Bystrov', 'volpert13@gmail.com', 'mementomori', '1990-12-18', 'male');
INSERT INTO testbase.users (id, firstName, lastName, login, password, birthDay, sex) VALUES (2, 'Irina', 'Pozhidaeva', 'cat163@yandex.ru', 'kozlina', '1990-03-127', 'female');

INSERT INTO testbase.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO testbase.user_roles (user_id, role_id) VALUES (2, 3);