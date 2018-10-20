DROP SCHEMA IF EXISTS myTestDb;

CREATE SCHEMA myTestDb;

USE myTestDb;

create table status
(
	id int auto_increment
		primary key,
	statusName varchar(100) null,
	constraint status_id_uindex
		unique (id)
)
engine=InnoDB
;
-- =====================================
-- =====================================
create table branch
(
	id int auto_increment
		primary key,
	branchColor varchar(100) null,
	constraint branch_id_uindex
		unique (id)
)
engine=InnoDB
;
-- =====================================
-- =====================================
create table role
(
	id int auto_increment
		primary key,
	type varchar(50) null,
	constraint role_id_uindex
		unique (id)
)
engine=InnoDB
;
-- =====================================
-- =====================================
create table user
(
	id int auto_increment
		primary key,
	firstName varchar(100) not null,
	lastName varchar(100) not null,
	birthDay varchar(100) null,
	login varchar(100) not null,
	password varchar(100) not null,
	phone varchar(100) not null,
	constraint user_id_uindex
		unique (id)
)
engine=InnoDB
;
-- =====================================
-- =====================================
create table station
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	branch_id int not null,
	numberOnBranch int not null,
	status_id int null,
	constraint station_id_uindex
		unique (id),
	constraint station_branch_id_fk
		foreign key (branch_id) references branch (id),
	constraint station_status_id_fk
		foreign key (status_id) references status (id)
);
-- =====================================
-- =====================================
create table train
(
	id int auto_increment
		primary key,
	trainName varchar(100) null,
	capacity int not null,
  status_id int null,
	constraint train_id_uindex
		unique (id),
	constraint train_status_id_fk
		foreign key (status_id) references status (id)
);
-- =====================================
-- =====================================

-- =====================================
-- =====================================
create table schedule
(
	id int auto_increment
		primary key,
	date_arrival timestamp null,
	date_departure timestamp null,
	station_id int null,
	train_id int null,
	endPointStation_id int null,
	constraint schedule_id_uindex
		unique (id),
	constraint schedule_station_id_fk
		foreign key (station_id) references station(id),
	constraint schedule_train_id_fk
		foreign key (train_id) references train(id),
	constraint schedule_endPointStation_id_fk
		foreign key (endPointStation_id) references station(id)
)
engine=InnoDB
;

CREATE INDEX date_departure_id_fk
  ON schedule (date_departure)
;

CREATE INDEX date_arrival_id_fk
  ON schedule (date_arrival)
;

-- CREATE INDEX schedule_train_id_fk
--   ON schedule (train_id)
-- ;
--
-- CREATE INDEX schedule_station_id_fk
--   ON schedule (station_id)
-- ;
-- =====================================
-- =====================================
create table user_role
(
  user_id int null,
  role_id int null,
  constraint user_role_id_fk
		foreign key (user_id) references user(id),
	constraint role_role_id_fk
		foreign key (role_id) references role (id)
)
engine=InnoDB
;
-- =====================================
-- =====================================
-- =====================================
-- =====================================
create table ticket
(
	id int auto_increment
		primary key,
	user_id int not null,
	train_id int null,
	stationBegin_id int not null,
	stationEnd_id int not null,
	ticketDateDeparture timestamp null,
	ticketDateArrival timestamp null,
	price int null,
	branch_id int not null,
	valid varchar(10) not null,
	constraint ticket_id_uindex
		unique (id),
	constraint ticket_train_id_fk
		foreign key (train_id) references train(id) on delete set null,
	constraint ticket_user_id_fk
		foreign key (user_id) references user(id),
	constraint station_begin_fk
		foreign key (stationBegin_id) references station(id),
  constraint station_end_id_fk
		foreign key (stationEnd_id) references station(id),
	constraint ticket_branch_id_fk
		foreign key (branch_id) references branch(id)
)
engine=InnoDB
;
-- =====================================

-- =====================================
-- =====================================
create table graph
(
  id int auto_increment
		primary key,
  stationFrom_id int null,
  stationTo_id int null,
  weight int null,
  oldWeight int null,
  constraint ticket_id_uindex
		unique (id),
  constraint station_from_graph_id_fk
		foreign key (stationFrom_id) references station(id),
	constraint station_to_graph_id_fk
		foreign key (stationTo_id) references station(id)
)
engine=InnoDB
;

-- =====================================
-- =====================================
-- =====================================
-- =====================================

-- ===========================================
-- ============STATUSES=======================
-- ===========================================
INSERT INTO myTestDb.status (id, statusName) VALUES (1, 'WORKED');
INSERT INTO myTestDb.status (id, statusName) VALUES (2, 'CLOSED');
INSERT INTO myTestDb.status (id, statusName) VALUES (3, 'DESTROYED BY MUTANTS');
-- ===========================================
-- ============ROLES==========================
-- ===========================================
INSERT INTO myTestDb.role (id, type) VALUES (1, 'ROLE_ADMIN');
INSERT INTO myTestDb.role (id, type) VALUES (2, 'ROLE_MANAGER');
INSERT INTO myTestDb.role (id, type) VALUES (3, 'ROLE_USER');
-- ===========================================
-- ============BRANCHES=======================
-- ===========================================
INSERT INTO myTestDb.branch (id, branchColor) VALUES (1, 'RED');
INSERT INTO myTestDb.branch (id, branchColor) VALUES (2, 'BLUE');
INSERT INTO myTestDb.branch (id, branchColor) VALUES (3, 'GREEN');
INSERT INTO myTestDb.branch (id, branchColor) VALUES (4, 'ORANGE');
INSERT INTO myTestDb.branch (id, branchColor) VALUES (5, 'PURPLE');
-- ===========================================
-- ============STATIONS=======================
-- ===========================================
-- Red branch 1
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (1, 'Devyatkino', 1, 1, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (2, 'Grazhdansky Prospekt', 1, 2, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (3, 'Akademicheskaya', 1, 3, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (4, 'Politekhnicheskaya', 1, 4, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (5, 'Ploschad Muzhestva', 1, 5, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (6, 'Lesnaya', 1, 6, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (7, 'Vyborgskaya', 1, 7, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (8, 'Ploshchad Lenina', 1, 8, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (9, 'Chernyshevskaya', 1, 9, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (10, 'Ploshchad Vosstaniya', 1, 10, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (11, 'Vladimirskaya', 1, 11, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (12, 'Pushkinskaya', 1, 12, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (13, 'Tekhnologichesky Institut-1', 1, 13, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (14, 'Baltiyskaya', 1, 14, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (15, 'Narvskaya', 1, 15, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (16, 'Kirovskiy Zavod', 1, 16, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (17, 'Avtovo', 1, 17, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (18, 'Leninsky Prospekt', 1, 18, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (19, 'Prospekt Veteranov', 1, 19, 1);
-- Blue branch 2
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (20, 'Parnas', 2, 1, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (21, 'Prospekt Prosvescheniya', 2, 2, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (22, 'Ozerki', 2, 3, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (23, 'Udelnaya', 2, 4, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (24, 'Pionerskaya', 2, 5, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (25, 'Chornaya Rechka', 2, 6, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (26, 'Petrogradskaya', 2, 7, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (27, 'Gorkovskaya', 2, 8, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (28, 'Nevsky Prospekt', 2, 9, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (29, 'Sennaya Ploschad', 2, 10, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (30, 'Tekhnologichesky Institut-2', 2, 11, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (31, 'Frunzenskaya', 2, 12, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (32, 'Moskovskiye Vorota', 2, 13, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (33, 'Elektrosila', 2, 14, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (34, 'Park Pobedy', 2, 15, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (35, 'Moskovskaya', 2, 16, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (36, 'Zvyozdnaya', 2, 17, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (37, 'Kupchino', 2, 18, 1);
-- Green branch 3
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (38, 'Begovaya', 3, 1, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (39, 'Novokrestovskaya', 3, 2, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (40, 'Primorskaya', 3, 3, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (41, 'Vasileostrovskaya', 3, 4, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (42, 'Gostiny Dvor', 3, 5, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (43, 'Mayakovskaya', 3, 6, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (44, 'Ploshchad Alexandra Nevskogo-1', 3, 7, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (45, 'Yelizarovskaya', 3, 8, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (46, 'Lomonosovskaya', 3, 9, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (47, 'Proletarskaya', 3, 10, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (48, 'Obukhovo', 3, 11, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (49, 'Rybatskoye', 3, 12, 1);
-- Orange branch 4
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (50, 'Spasskaya', 4, 1, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (51, 'Dostoyevskaya', 4, 2, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (52, 'Ligovsky Prospekt', 4, 3, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (53, 'Ploshchad Alexandra Nevskogo-2', 4, 4, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (54, 'Novocherkasskaya', 4, 5, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (55, 'Ladozhskaya', 4, 6, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (56, 'Prospekt Bolshevikov', 4, 7, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (57, 'Ulitsa Dybenko', 4, 8, 1);
-- Purple branch 5
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (58, 'Komendantsky Prospekt', 5, 1, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (59, 'Staraya Derevnya', 5, 2, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (60, 'Krestovsky Ostrov', 5, 3, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (61, 'Chkalovskaya', 5, 4, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (62, 'Sportivnaya', 5, 5, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (63, 'Admiralteyskaya', 5, 6, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (64, 'Sadovaya', 5, 7, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (65, 'Zvenigorodskaya', 5, 8, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (66, 'Obvodny Kanal', 5, 9, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (67, 'Volkovskaya', 5, 10, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (68, 'Bukharestskaya', 5, 11, 1);
INSERT INTO myTestDb.station (id, name, branch_id, numberOnBranch, status_id) VALUES (69, 'Mezhdunarodnaya', 5, 12, 1);
-- ===========================================
-- ============TRANSITIONS====================
-- ===========================================
-- Nevskii-Gostinii

-- ===========================================
-- ============TRAINS=========================
-- ===========================================
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (1, 'T-1', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (2, 'T-2', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (3, 'T-3', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (4, 'T-4', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (5, 'T-5', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (6, 'T-6', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (7, 'T-7', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (8, 'T-8', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (9, 'T-9', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (10, 'T-10', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (11, 'T-11', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (12, 'T-12', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (13, 'T-13', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (14, 'T-14', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (15, 'T-15', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (16, 'T-16', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (17, 'T-17', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (18, 'T-18', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (19, 'T-19', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (20, 'T-20', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (21, 'T-21', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (22, 'T-22', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (23, 'T-23', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (24, 'T-24', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (25, 'T-25', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (26, 'T-26', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (27, 'T-27', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (28, 'T-28', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (29, 'T-29', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (30, 'T-30', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (31, 'T-31', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (32, 'T-32', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (33, 'T-33', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (34, 'T-34', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (35, 'T-35', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (36, 'T-36', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (37, 'T-37', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (38, 'T-38', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (39, 'T-39', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (40, 'T-40', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (41, 'T-41', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (42, 'T-42', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (43, 'T-43', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (44, 'T-44', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (45, 'T-45', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (46, 'T-46', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (47, 'T-47', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (48, 'T-48', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (49, 'T-49', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (50, 'T-50', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (51, 'T-51', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (52, 'T-52', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (53, 'T-53', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (54, 'T-54', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (55, 'T-55', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (56, 'T-56', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (57, 'T-57', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (58, 'T-58', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (59, 'T-59', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (60, 'T-60', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (61, 'T-61', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (62, 'T-62', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (63, 'T-63', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (64, 'T-64', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (65, 'T-65', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (66, 'T-66', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (67, 'T-67', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (68, 'T-68', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (69, 'T-69', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (70, 'T-70', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (71, 'T-71', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (72, 'T-72', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (73, 'T-73', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (74, 'T-74', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (75, 'T-75', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (76, 'T-76', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (77, 'T-77', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (78, 'T-78', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (79, 'T-79', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (80, 'T-80', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (81, 'T-81', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (82, 'T-82', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (83, 'T-83', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (84, 'T-84', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (85, 'T-85', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (86, 'T-86', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (87, 'T-87', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (88, 'T-88', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (89, 'T-89', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (90, 'T-90', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (91, 'T-91', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (92, 'T-92', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (93, 'T-93', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (94, 'T-94', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (95, 'T-95', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (96, 'T-96', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (97, 'T-97', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (98, 'T-98', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (99, 'T-99', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (100, 'T-100', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (101, 'T-101', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (102, 'T-102', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (103, 'T-103', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (104, 'T-104', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (105, 'T-105', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (106, 'T-106', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (107, 'T-107', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (108, 'T-108', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (109, 'T-109', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (110, 'T-110', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (111, 'T-111', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (112, 'T-112', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (113, 'T-113', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (114, 'T-114', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (115, 'T-115', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (116, 'T-116', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (117, 'T-117', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (118, 'T-118', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (119, 'T-119', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (120, 'T-120', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (121, 'T-121', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (122, 'T-122', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (123, 'T-123', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (124, 'T-124', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (125, 'T-125', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (126, 'T-126', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (127, 'T-127', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (128, 'T-128', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (129, 'T-129', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (130, 'T-130', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (131, 'T-131', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (132, 'T-132', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (133, 'T-133', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (134, 'T-134', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (135, 'T-135', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (136, 'T-136', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (137, 'T-137', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (138, 'T-138', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (139, 'T-139', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (140, 'T-140', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (141, 'T-141', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (142, 'T-142', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (143, 'T-143', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (144, 'T-144', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (145, 'T-145', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (146, 'T-146', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (147, 'T-147', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (148, 'T-148', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (149, 'T-149', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (150, 'T-150', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (151, 'T-151', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (152, 'T-152', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (153, 'T-153', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (154, 'T-154', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (155, 'T-155', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (156, 'T-156', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (157, 'T-157', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (158, 'T-158', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (159, 'T-159', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (160, 'T-160', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (161, 'T-161', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (162, 'T-162', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (163, 'T-163', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (164, 'T-164', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (165, 'T-165', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (166, 'T-166', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (167, 'T-167', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (168, 'T-168', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (169, 'T-169', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (170, 'T-170', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (171, 'T-171', 6, 1);
INSERT into myTestDb.train (id, trainName, capacity, status_id) VALUES (172, 'T-172', 6, 1);
-- ===========================================
-- ============USERS==========================
-- ===========================================
INSERT INTO myTestDb.user (id, firstName, lastName, birthDay, login, password, phone) VALUES (1, 'Alexey', 'Bystrov', '1990-12-18', '13shut13@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO', '79523696505');
INSERT INTO myTestDb.user (id, firstName, lastName, birthDay, login, password, phone) VALUES (3, 'Misha', 'Lisechkin', '1990-12-18', 'volpert13@gmail.com', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO', '79523696505');
INSERT INTO myTestDb.user (id, firstName, lastName, birthDay, login, password, phone) VALUES (4, 'Andrey', 'Fetisov', '1990-12-18', 'fetisbs@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO', '79523696505');
INSERT INTO myTestDb.user (id, firstName, lastName, birthDay, login, password, phone) VALUES (5, 'Kostya', 'Kashkin', '1990-12-18', 'kash@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO', '79523696505');
INSERT INTO myTestDb.user (id, firstName, lastName, birthDay, login, password, phone) VALUES (6, 'aa', 'bb', 'aa@aa.ru', '1990-12-18', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO', '79523696505');
INSERT INTO myTestDb.user (id, firstName, lastName, birthDay, login, password, phone) VALUES (7, 'Misha', 'Lisechkin', '1990-12-18', 'dummymailmetro@gmail.com', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO', '79523696505');
-- ===========================================
-- ============USER-ROLES=====================
-- ===========================================
INSERT INTO myTestDb.user_role (user_id, role_id) VALUES (1, 3);
INSERT INTO myTestDb.user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO myTestDb.user_role (user_id, role_id) VALUES (4, 3);
INSERT INTO myTestDb.user_role (user_id, role_id) VALUES (5, 3);
INSERT INTO myTestDb.user_role (user_id, role_id) VALUES (6, 1);