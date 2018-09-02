INSERT INTO testbase.status (id, statusName) VALUES (1, 'WORKED');
INSERT INTO testbase.status (id, statusName) VALUES (2, 'CLOSED');
INSERT INTO testbase.status (id, statusName) VALUES (3, 'NOT_USED');

INSERT INTO testbase.role (id, type) VALUES (1, 'ROLE_ADMIN');
INSERT INTO testbase.role (id, type) VALUES (2, 'ROLE_MANAGER');
INSERT INTO testbase.role (id, type) VALUES (3, 'ROLE_USER');

INSERT INTO testbase.branch (id, branchColor) VALUES (1, 'RED');
INSERT INTO testbase.branch (id, branchColor) VALUES (2, 'BLUE');

INSERT INTO testbase.station (id, name, status_id) VALUES (1, 'Sennaya ploschad', 1);
INSERT INTO testbase.station (id, name, status_id) VALUES (2, 'Technologicheskii institut', 1);
INSERT INTO testbase.station (id, name, status_id) VALUES (3, 'Frunzenskaya', 1);
INSERT INTO testbase.station (id, name, status_id) VALUES (4, 'Pushkinskaya', 1);
INSERT INTO testbase.station (id, name, status_id) VALUES (5, 'Baltiiskaya', 1);

INSERT INTO testbase.station_branch(station_id, branch_id, station_on_branch_id) values (1, 2, 1);
INSERT INTO testbase.station_branch(station_id, branch_id, station_on_branch_id) values (2, 2, 2);
INSERT INTO testbase.station_branch(station_id, branch_id, station_on_branch_id) values (3, 2, 3);
INSERT INTO testbase.station_branch(station_id, branch_id, station_on_branch_id) values (4, 1, 1);
INSERT INTO testbase.station_branch(station_id, branch_id, station_on_branch_id) values (2, 1, 2);
INSERT INTO testbase.station_branch(station_id, branch_id, station_on_branch_id) values (5, 1, 3);


INSERT INTO testbase.train (id, trainName, capacity, status_id) VALUES (1, 'T1000', 4, 1);
INSERT INTO testbase.train (id, trainName, capacity, status_id) VALUES (2, 'T666', 4, 1);


  INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (1, '2018-07-20 06:45:00', '2018-07-20 06:50:00' , 1, 1, 3);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2, '2018-07-20 07:50:00', '2018-07-20 07:55:00' , 2, 1, 3);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (3, '2018-07-20 08:55:00', '2018-07-20 09:00:00' , 3, 1, 3);
  INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (4, '2018-07-20 06:45:00', '2018-07-20 06:50:00' , 4, 2, 5);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (5, '2018-07-20 07:50:00', '2018-07-20 07:55:00' , 2, 2, 5);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (6, '2018-07-20 08:55:00', '2018-07-20 09:00:00' , 5, 2, 5);



INSERT INTO testbase.user (id, firstName, lastName, login, password) VALUES (1, 'Alexey', 'Bystrov', '13shut13@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, login, password) VALUES (2, 'Irina', 'Pozhidaeva', 'cat163@yandex.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, login, password) VALUES (3, 'Misha', 'Lisechkin', 'volpert13@gmail.com', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, login, password) VALUES (4, 'Andrey', 'Fetisov', 'fetisbs@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, login, password) VALUES (5, 'Kostya', 'Kashkin', 'kash@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');


INSERT INTO testbase.user_role (user_id, role_id) VALUES (1, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (2, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (4, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (5, 3);

INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (1, 1, 1, 1, 3, '2018-07-20', 200, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (2, 2, 1, 2, 3, '2018-07-20', 100, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (3, 4, 1, 1, 3, '2018-07-20', 200, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (4, 5, 1, 1, 3, '2018-07-20', 200, 2);