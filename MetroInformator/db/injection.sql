-- ===========================================
-- ============STATUSES=======================
-- ===========================================
INSERT INTO testbase.status (id, statusName) VALUES (1, 'WORKED');
INSERT INTO testbase.status (id, statusName) VALUES (2, 'CLOSED');
INSERT INTO testbase.status (id, statusName) VALUES (3, 'DESTROYED BY MUTANTS');
-- ===========================================
-- ============ROLES==========================
-- ===========================================
INSERT INTO testbase.role (id, type) VALUES (1, 'ROLE_ADMIN');
INSERT INTO testbase.role (id, type) VALUES (2, 'ROLE_MANAGER');
INSERT INTO testbase.role (id, type) VALUES (3, 'ROLE_USER');
-- ===========================================
-- ============BRANCHES=======================
-- ===========================================
INSERT INTO testbase.branch (id, branchColor) VALUES (1, 'RED');
INSERT INTO testbase.branch (id, branchColor) VALUES (2, 'BLUE');
INSERT INTO testbase.branch (id, branchColor) VALUES (3, 'GREEN');
INSERT INTO testbase.branch (id, branchColor) VALUES (4, 'ORANGE');
INSERT INTO testbase.branch (id, branchColor) VALUES (5, 'PURPLE');
-- ===========================================
-- ============STATIONS=======================
-- ===========================================
-- Red branch 1
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (1, 'Devyatkino', 1, 1, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (2, 'Grazhdansky Prospekt', 1, 2, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (3, 'Akademicheskaya', 1, 3, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (4, 'Politekhnicheskaya', 1, 4, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (5, 'Ploschad Muzhestva', 1, 5, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (6, 'Lesnaya', 1, 6, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (7, 'Vyborgskaya', 1, 7, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (8, 'Ploshchad Lenina', 1, 8, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (9, 'Chernyshevskaya', 1, 9, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (10, 'Ploshchad Vosstaniya', 1, 10, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (11, 'Vladimirskaya', 1, 11, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (12, 'Pushkinskaya', 1, 12, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (13, 'Tekhnologichesky Institut-1', 1, 13, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (14, 'Baltiyskaya', 1, 14, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (15, 'Narvskaya', 1, 15, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (16, 'Kirovskiy Zavod', 1, 16, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (17, 'Avtovo', 1, 17, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (18, 'Leninsky Prospekt', 1, 18, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (19, 'Prospekt Veteranov', 1, 19, 1);
-- Blue branch 2
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (20, 'Parnas', 2, 1, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (21, 'Prospekt Prosvescheniya', 2, 2, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (22, 'Ozerki', 2, 3, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (23, 'Udelnaya', 2, 4, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (24, 'Pionerskaya', 2, 5, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (25, 'Chornaya Rechka', 2, 6, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (26, 'Petrogradskaya', 2, 7, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (27, 'Gorkovskaya', 2, 8, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (28, 'Nevsky Prospekt', 2, 9, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (29, 'Sennaya Ploschad', 2, 10, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (30, 'Tekhnologichesky Institut-2', 2, 11, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (31, 'Frunzenskaya', 2, 12, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (32, 'Moskovskiye Vorota', 2, 13, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (33, 'Elektrosila', 2, 14, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (34, 'Park Pobedy', 2, 15, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (35, 'Moskovskaya', 2, 16, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (36, 'Zvyozdnaya', 2, 17, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (37, 'Kupchino', 2, 18, 1);
-- Green branch 3
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (38, 'Begovaya', 3, 1, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (39, 'Novokrestovskaya', 3, 2, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (40, 'Primorskaya', 3, 3, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (41, 'Vasileostrovskaya', 3, 4, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (42, 'Gostiny Dvor', 3, 5, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (43, 'Mayakovskaya', 3, 6, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (44, 'Ploshchad Alexandra Nevskogo-1', 3, 7, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (45, 'Yelizarovskaya', 3, 8, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (46, 'Lomonosovskaya', 3, 9, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (47, 'Proletarskaya', 3, 10, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (48, 'Obukhovo', 3, 11, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (49, 'Rybatskoye', 3, 12, 1);
-- Orange branch 4
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (50, 'Spasskaya', 4, 1, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (51, 'Dostoyevskaya', 4, 2, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (52, 'Ligovsky Prospekt', 4, 3, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (53, 'Ploshchad Alexandra Nevskogo-2', 4, 4, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (54, 'Novocherkasskaya', 4, 5, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (55, 'Ladozhskaya', 4, 6, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (56, 'Prospekt Bolshevikov', 4, 7, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (57, 'Ulitsa Dybenko', 4, 8, 1);
-- Purple branch 5
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (58, 'Komendantsky Prospekt', 5, 1, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (59, 'Staraya Derevnya', 5, 2, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (60, 'Krestovsky Ostrov', 5, 3, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (61, 'Chkalovskaya', 5, 4, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (62, 'Sportivnaya', 5, 5, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (63, 'Admiralteyskaya', 5, 6, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (64, 'Sadovaya', 5, 7, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (65, 'Zvenigorodskaya', 5, 8, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (66, 'Obvodny Kanal', 5, 9, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (67, 'Volkovskaya', 5, 10, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (68, 'Bukharestskaya', 5, 11, 1);
INSERT INTO testbase.station (id, name, branch_id, numberOnBranch, status_id) VALUES (69, 'Mezhdunarodnaya', 5, 12, 1);
-- ===========================================
-- ============TRANSITIONS====================
-- ===========================================
-- Nevskii-Gostinii
INSERT INTO testbase.transition (id, station_1_id, station_2_id, status_id) VALUES (1, 28, 42, 1);
-- Vosstaniya-Mayakovskaya
INSERT INTO testbase.transition (id, station_1_id, station_2_id, status_id) VALUES (2, 10, 43, 1);
-- Sennaya-Sadovaya-Spasskaya
INSERT INTO testbase.transition (id, station_1_id, station_2_id, status_id) VALUES (3, 29, 64, 1);
INSERT INTO testbase.transition (id, station_1_id, station_2_id, status_id) VALUES (4, 29, 50, 1);
INSERT INTO testbase.transition (id, station_1_id, station_2_id, status_id) VALUES (5, 50, 64, 1);
-- Vladimirskaya-Dostoyevskaya
INSERT INTO testbase.transition (id, station_1_id, station_2_id, status_id) VALUES (6, 11, 51, 1);
-- Alexandra Nevskogo 1/2
INSERT INTO testbase.transition (id, station_1_id, station_2_id, status_id) VALUES (7, 44, 53, 1);
-- Pushkinskaya-Zvenigorodskaya
INSERT INTO testbase.transition (id, station_1_id, station_2_id, status_id) VALUES (8, 12, 65, 1);
-- Technologka 1/2
INSERT INTO testbase.transition (id, station_1_id, station_2_id, status_id) VALUES (9, 13, 30, 1);
-- ===========================================
-- ============TRAINS=========================
-- ===========================================
INSERT INTO testbase.train (id, trainName, capacity, status_id) VALUES (1, 'T2-Parnas-Kupchino-0645', 6, 1);
INSERT INTO testbase.train (id, trainName, capacity, status_id) VALUES (2, 'T2-Kupchino-Parnas-0645', 6, 1);
INSERT INTO testbase.train (id, trainName, capacity, status_id) VALUES (3, 'T2-Parnas-Kupchino-0830', 6, 1);
INSERT INTO testbase.train (id, trainName, capacity, status_id) VALUES (4, 'T2-Kupchino-Parnas-0830', 6, 1);
-- ===========================================
-- ============SCHEDULES======================
-- ===========================================
--
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (1, '2018-04-09 06:45:00', '2018-04-09 06:50:00' , 20, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (2, '2018-04-09 07:20:00', '2018-04-09 07:25:00' , 21, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (3, '2018-04-09 07:55:00', '2018-04-09 08:00:00' , 22, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (4, '2018-04-09 08:30:00', '2018-04-09 08:35:00' , 23, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (5, '2018-04-09 09:05:00', '2018-04-09 09:10:00' , 24, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (6, '2018-04-09 09:40:00', '2018-04-09 09:45:00' , 25, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (7, '2018-04-09 10:15:00', '2018-04-09 10:20:00' , 26, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (8, '2018-04-09 10:50:00', '2018-04-09 10:55:00' , 27, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (9, '2018-04-09 11:25:00', '2018-04-09 11:30:00' , 28, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (10, '2018-04-09 12:00:00', '2018-04-09 12:05:00' , 29, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (11, '2018-04-09 12:35:00', '2018-04-09 12:40:00' , 30, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (12, '2018-04-09 13:10:00', '2018-04-09 13:15:00' , 31, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (13, '2018-04-09 13:45:00', '2018-04-09 13:50:00' , 32, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (14, '2018-04-09 14:20:00', '2018-04-09 14:25:00' , 33, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (15, '2018-04-09 14:55:00', '2018-04-09 15:00:00' , 34, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (16, '2018-04-09 15:30:00', '2018-04-09 15:35:00' , 35, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (17, '2018-04-09 16:05:00', '2018-04-09 16:10:00' , 36, 1, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (18, '2018-04-09 16:40:00', '2018-04-09 16:45:00' , 37, 1, 37);
--
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (19, '2018-04-09 06:45:00', '2018-04-09 06:50:00' , 37, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (20, '2018-04-09 07:20:00', '2018-04-09 07:25:00' , 36, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (21, '2018-04-09 07:55:00', '2018-04-09 08:00:00' , 35, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (22, '2018-04-09 08:30:00', '2018-04-09 08:35:00' , 34, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (23, '2018-04-09 09:05:00', '2018-04-09 09:10:00' , 33, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (24, '2018-04-09 09:40:00', '2018-04-09 09:45:00' , 32, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (25, '2018-04-09 10:15:00', '2018-04-09 10:20:00' , 31, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (26, '2018-04-09 10:50:00', '2018-04-09 10:55:00' , 30, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (27, '2018-04-09 11:25:00', '2018-04-09 11:30:00' , 29, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (28, '2018-04-09 12:00:00', '2018-04-09 12:05:00' , 28, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (29, '2018-04-09 12:35:00', '2018-04-09 12:40:00' , 27, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (30, '2018-04-09 13:10:00', '2018-04-09 13:15:00' , 26, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (31, '2018-04-09 13:45:00', '2018-04-09 13:50:00' , 25, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (32, '2018-04-09 14:20:00', '2018-04-09 14:25:00' , 24, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (33, '2018-04-09 14:55:00', '2018-04-09 15:00:00' , 23, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (34, '2018-04-09 15:30:00', '2018-04-09 15:35:00' , 22, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (35, '2018-04-09 16:05:00', '2018-04-09 16:10:00' , 21, 2, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (36, '2018-04-09 16:40:00', '2018-04-09 16:45:00' , 20, 2, 20);
--
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (37, '2018-04-09 08:30:00', '2018-04-09 08:35:00' , 20, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (38, '2018-04-09 09:05:00', '2018-04-09 09:10:00' , 21, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (39, '2018-04-09 09:40:00', '2018-04-09 09:45:00' , 22, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (40, '2018-04-09 10:15:00', '2018-04-09 10:20:00' , 23, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (41, '2018-04-09 10:50:00', '2018-04-09 10:55:00' , 24, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (42, '2018-04-09 11:25:00', '2018-04-09 11:30:00' , 25, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (43, '2018-04-09 12:00:00', '2018-04-09 12:05:00' , 26, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (44, '2018-04-09 12:35:00', '2018-04-09 12:40:00' , 27, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (45, '2018-04-09 13:10:00', '2018-04-09 13:15:00' , 28, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (46, '2018-04-09 13:45:00', '2018-04-09 13:50:00' , 29, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (47, '2018-04-09 14:20:00', '2018-04-09 14:25:00' , 30, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (48, '2018-04-09 14:55:00', '2018-04-09 15:00:00' , 31, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (49, '2018-04-09 15:30:00', '2018-04-09 15:35:00' , 32, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (50, '2018-04-09 16:05:00', '2018-04-09 16:10:00' , 33, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (51, '2018-04-09 16:40:00', '2018-04-09 16:45:00' , 34, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (52, '2018-04-09 17:15:00', '2018-04-09 17:20:00' , 35, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (53, '2018-04-09 17:50:00', '2018-04-09 17:55:00' , 36, 3, 37);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (54, '2018-04-09 18:25:00', '2018-04-09 18:30:00' , 37, 3, 37);
--
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (55, '2018-04-09 08:30:00', '2018-04-09 08:35:00' , 37, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (56, '2018-04-09 09:05:00', '2018-04-09 09:10:00' , 36, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (57, '2018-04-09 09:40:00', '2018-04-09 09:45:00' , 35, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (58, '2018-04-09 10:15:00', '2018-04-09 10:20:00' , 34, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (59, '2018-04-09 10:50:00', '2018-04-09 10:55:00' , 33, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (60, '2018-04-09 11:25:00', '2018-04-09 11:30:00' , 32, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (61, '2018-04-09 12:00:00', '2018-04-09 12:05:00' , 31, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (62, '2018-04-09 12:35:00', '2018-04-09 12:40:00' , 30, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (63, '2018-04-09 13:10:00', '2018-04-09 13:15:00' , 29, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (64, '2018-04-09 13:45:00', '2018-04-09 13:50:00' , 28, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (65, '2018-04-09 14:20:00', '2018-04-09 14:25:00' , 27, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (66, '2018-04-09 14:55:00', '2018-04-09 15:00:00' , 26, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (67, '2018-04-09 15:30:00', '2018-04-09 15:35:00' , 25, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (68, '2018-04-09 16:05:00', '2018-04-09 16:10:00' , 24, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (69, '2018-04-09 16:40:00', '2018-04-09 16:45:00' , 23, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (70, '2018-04-09 17:15:00', '2018-04-09 17:20:00' , 22, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (71, '2018-04-09 17:50:00', '2018-04-09 17:55:00' , 21, 4, 20);
INSERT INTO testbase.schedule (id, date_arrival, date_departure, station_id, train_id, endPointStation_id) VALUES (72, '2018-04-09 18:25:00', '2018-04-09 18:30:00' , 20, 4, 20);
-- ===========================================
-- ============USERS==========================
-- ===========================================
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (1, 'Alexey', 'Bystrov', '1990-12-18', '13shut13@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (2, 'Irina', 'Pozhidaeva', '1990-12-18', 'cat163@yandex.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (3, 'Misha', 'Lisechkin', '1990-12-18', 'volpert13@gmail.com', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (4, 'Andrey', 'Fetisov', '1990-12-18', 'fetisbs@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (5, 'Kostya', 'Kashkin', '1990-12-18', 'kash@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (6, 'aa', 'bb', 'aa@aa.ru', '1990-12-18', '$2a$10$yhFtlspzvWJHhoNAmAG8GeyTl.k3LvF95RPhKMsQA8KsS5/44wQq6');
-- ===========================================
-- ============USER-ROLES=====================
-- ===========================================
INSERT INTO testbase.user_role (user_id, role_id) VALUES (1, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (2, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (4, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (5, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (6, 1);
-- ===========================================
-- ============TICKETS========================
-- ===========================================
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (1, 1, 1, 29, 31, '2018-07-20', 200, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (2, 2, 1, 30, 31, '2018-07-20', 100, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (3, 4, 1, 29, 31, '2018-07-20', 200, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (4, 5, 1, 31, 32, '2018-07-20', 100, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (5, 5, 1, 30, 32, '2018-07-20', 200, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (6, 5, 1, 29, 32, '2018-07-20', 300, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (7, 5, 2, 31, 30, '2018-07-20', 300, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (8, 5, 2, 32, 29, '2018-07-20', 300, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (9, 5, 2, 31, 29, '2018-07-20', 300, 2);
INSERT INTO testbase.ticket (id, user_id, train_id, stationBegin_id, stationEnd_id, ticketDate, price, branch_id) VALUES (10, 5, 2, 32, 31, '2018-07-20', 300, 2);