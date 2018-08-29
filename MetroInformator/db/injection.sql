INSERT INTO testbase.stations (id, name, color) VALUES (1, 'Parnas', 'blue');
INSERT INTO testbase.stations (id, name, color) VALUES (2, 'Prosvescheniya pr-t', 'blue');
INSERT INTO testbase.stations (id, name, color) VALUES (3, 'Udelnaya', 'blue');
INSERT INTO testbase.stations (id, name, color) VALUES (4, 'Pionerskaya', 'blue');

INSERT INTO testbase.trains (id, name) VALUES (5, 'T1000');
INSERT INTO testbase.trains (id, name) VALUES (6, 'T666');

INSERT INTO testbase.seats (id, seat, train_id) VALUES (7, 1, 5);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (8, 1, 5);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (9, 1, 5);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (10, 1, 5);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (11, 1, 6);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (12, 1, 6);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (13, 1, 6);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (14, 1, 6);

INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (15, '2018-07-20 06:45:00', '2018-07-20 07:00:00' , 1, 5);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (16, '2018-07-20 08:00:00', '2018-07-20 08:15:00' , 2, 5);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (17, '2018-07-20 09:15:00', '2018-07-20 09:30:00' , 3, 5);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (18, '2018-07-20 10:30:00', '2018-07-20 10:45:00' , 4, 5);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (19, '2018-07-20 06:45:00', '2018-07-20 07:00:00' , 4, 6);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (20, '2018-07-20 08:00:00', '2018-07-20 08:15:00' , 3, 6);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (21, '2018-07-20 09:15:00', '2018-07-20 09:30:00' , 2, 6);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id) VALUES (22, '2018-07-20 10:30:00', '2018-07-20 10:45:00' , 1, 6);


