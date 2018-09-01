INSERT INTO testbase.status (id, statusName) VALUES (1, 'WORKED');
INSERT INTO testbase.status (id, statusName) VALUES (2, 'CLOSED');
INSERT INTO testbase.status (id, statusName) VALUES (3, 'NOT_USED');

INSERT INTO testbase.role (id, type) VALUES (1, 'ROLE_ADMIN');
INSERT INTO testbase.role (id, type) VALUES (2, 'ROLE_MANAGER');
INSERT INTO testbase.role (id, type) VALUES (3, 'ROLE_USER');

INSERT INTO testbase.branches (id, branchColor) VALUES (1, 'RED');
INSERT INTO testbase.branches (id, branchColor) VALUES (2, 'BLUE');

INSERT INTO testbase.stations (id, name, status_id) VALUES (1, 'Technologicheskii institut', 1);
INSERT INTO testbase.stations (id, name, status_id) VALUES (2, 'Baltiiskaya', 1);
INSERT INTO testbase.stations (id, name, status_id) VALUES (3, 'Frunzenskaya', 1);
INSERT INTO testbase.stations (id, name, status_id) VALUES (4, 'Sennaya ploschad', 1);
INSERT INTO testbase.stations (id, name, status_id) VALUES (5, 'Pushkinskaya', 1);

INSERT INTO testbase.station_branches(station_id, branch_id, station_on_branch_id) values (1, 1, 2);
INSERT INTO testbase.station_branches(station_id, branch_id, station_on_branch_id) values (1, 2, 2);
INSERT INTO testbase.station_branches(station_id, branch_id, station_on_branch_id) values (2, 1, 3);
INSERT INTO testbase.station_branches(station_id, branch_id, station_on_branch_id) values (3, 2, 3);
INSERT INTO testbase.station_branches(station_id, branch_id, station_on_branch_id) values (4, 2, 1);
INSERT INTO testbase.station_branches(station_id, branch_id, station_on_branch_id) values (5, 1, 1);


INSERT INTO testbase.trains (id, trainName, status_id) VALUES (1, 'T1000', 1);
INSERT INTO testbase.trains (id, trainName, status_id) VALUES (2, 'T666', 1);

INSERT INTO testbase.seats (id, seat, train_id) VALUES (1, 1, 1);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (2, 2, 1);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (3, 3, 1);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (4, 4, 1);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (5, 1, 2);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (6, 2, 2);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (7, 3, 2);
INSERT INTO testbase.seats (id, seat, train_id) VALUES (8, 4, 2);

  INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (1, '2018-07-20 06:45:00', '2018-07-20 06:50:00' , 4, 1, 3);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2, '2018-07-20 07:50:00', '2018-07-20 07:55:00' , 1, 1, 3);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (3, '2018-07-20 08:55:00', '2018-07-20 09:00:00' , 3, 1, 3);
  INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (4, '2018-07-20 06:45:00', '2018-07-20 06:50:00' , 5, 2, 2);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (5, '2018-07-20 07:50:00', '2018-07-20 07:55:00' , 1, 2, 2);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (6, '2018-07-20 08:55:00', '2018-07-20 09:00:00' , 2, 2, 2);




INSERT INTO testbase.users (id, firstName, lastName, login, password) VALUES (1, 'Alexey', 'Bystrov', '13shut13@mail.ru', '$2a$10$Xr.w2C7ocMfHFLKJxCls2e/G9kMz.jVZBA3TY452NG5dHtftnrBDG');
INSERT INTO testbase.users (id, firstName, lastName, login, password) VALUES (2, 'Irina', 'Pozhidaeva', 'cat163@yandex.ru', '$2a$10$Xr.w2C7ocMfHFLKJxCls2e/G9kMz.jVZBA3TY452NG5dHtftnrBDG');
INSERT INTO testbase.users (id, firstName, lastName, login, password) VALUES (3, 'Misha', 'Pisechkin', 'volpert13@gmail.com', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');


INSERT INTO testbase.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO testbase.user_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO testbase.user_roles (user_id, role_id) VALUES (3, 1);