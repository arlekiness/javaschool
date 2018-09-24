INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDateDeparture, ticketDateArrival, price, branch_id, valid) VALUES (1, 3, 87, 19, 1, '2018-11-11 6:50:00', '2018-11-11 17:15:00', 500, 1, 'VALID');
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDateDeparture, ticketDateArrival, price, branch_id, valid) VALUES (2, 3, 87, 19, 1, '2018-11-11 6:50:00', '2018-11-11 17:15:00', 500, 1, 'VALID');
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDateDeparture, ticketDateArrival, price, branch_id, valid) VALUES (3, 3, 87, 19, 1, '2018-11-11 6:50:00', '2018-11-11 17:15:00', 500, 1, 'VALID');
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDateDeparture, ticketDateArrival, price, branch_id, valid) VALUES (4, 3, 87, 19, 1, '2018-11-11 6:50:00', '2018-11-11 17:15:00', 500, 1, 'VALID');
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDateDeparture, ticketDateArrival, price, branch_id, valid) VALUES (5, 3, 87, 19, 1, '2018-11-11 6:50:00', '2018-11-11 17:15:00', 500, 1, 'VALID');


INSERT into testbase.train (id, trainName, capacity, status_id) VALUES (173, 'T-173', 6, 1);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2205, '2018-11-12 06:45:00', '2018-11-12 06:50:00' , 1, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2206, '2018-11-12 07:20:00', '2018-11-12 07:25:00' , 2, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2207, '2018-11-12 07:55:00', '2018-11-12 08:00:00' , 3, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2208, '2018-11-12 08:30:00', '2018-11-12 08:35:00' , 4, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2209, '2018-11-12 09:05:00', '2018-11-12 09:10:00' , 5, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2210, '2018-11-12 09:40:00', '2018-11-12 09:45:00' , 6, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2211, '2018-11-12 10:15:00', '2018-11-12 10:20:00' , 7, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2212, '2018-11-12 10:50:00', '2018-11-12 10:55:00' , 8, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2213, '2018-11-12 11:25:00', '2018-11-12 11:30:00' , 9, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2214, '2018-11-12 12:00:00', '2018-11-12 12:05:00' , 10, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2215, '2018-11-12 12:35:00', '2018-11-12 12:40:00' , 11, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2216, '2018-11-12 13:10:00', '2018-11-12 13:15:00' , 12, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2217, '2018-11-12 13:45:00', '2018-11-12 13:50:00' , 13, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2218, '2018-11-12 14:20:00', '2018-11-12 14:25:00' , 14, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2219, '2018-11-12 14:55:00', '2018-11-12 15:00:00' , 15, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2220, '2018-11-12 15:30:00', '2018-11-12 15:35:00' , 16, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2221, '2018-11-12 16:05:00', '2018-11-12 16:10:00' , 17, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2222, '2018-11-12 16:40:00', '2018-11-12 16:45:00' , 18, 173, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2223, '2018-11-12 17:15:00', '2018-11-12 17:20:00' , 19, 173, 19);


INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2224, '2018-11-12 07:20:00', '2018-11-12 07:25:00' , 1, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2225, '2018-11-12 07:55:00', '2018-11-12 08:00:00' , 2, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2226, '2018-11-12 08:30:00', '2018-11-12 08:35:00' , 3, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2227, '2018-11-12 09:05:00', '2018-11-12 09:10:00' , 4, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2228, '2018-11-12 09:40:00', '2018-11-12 09:45:00' , 5, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2229, '2018-11-12 10:15:00', '2018-11-12 10:20:00' , 6, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2230, '2018-11-12 10:50:00', '2018-11-12 10:55:00' , 7, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2231, '2018-11-12 11:25:00', '2018-11-12 11:30:00' , 8, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2232, '2018-11-12 12:00:00', '2018-11-12 12:05:00' , 9, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2233, '2018-11-12 12:35:00', '2018-11-12 12:40:00' , 10, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2234, '2018-11-12 13:10:00', '2018-11-12 13:15:00' , 11, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2235, '2018-11-12 13:45:00', '2018-11-12 13:50:00' , 12, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2236, '2018-11-12 14:20:00', '2018-11-12 14:25:00' , 13, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2237, '2018-11-12 14:55:00', '2018-11-12 15:00:00' , 14, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2238, '2018-11-12 15:30:00', '2018-11-12 15:35:00' , 15, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2239, '2018-11-12 16:05:00', '2018-11-12 16:10:00' , 16, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2240, '2018-11-12 16:40:00', '2018-11-12 16:45:00' , 17, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2241, '2018-11-12 17:15:00', '2018-11-12 17:20:00' , 18, 2, 19);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2242, '2018-11-12 17:50:00', '2018-11-12 17:55:00' , 19, 2, 19);
