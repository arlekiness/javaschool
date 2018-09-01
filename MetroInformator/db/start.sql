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
create table branches
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
create table users
(
	id int auto_increment
		primary key,
	firstName varchar(100) not null,
	lastName varchar(100) not null,
	login varchar(100) not null,
	password varchar(100) not null,
	constraint user_id_uindex
		unique (id)
)
engine=InnoDB
;
-- =====================================
-- =====================================
create table stations
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	status_id int null,
	constraint station_id_uindex
		unique (id),
	constraint station_status_id_fk
		foreign key (status_id) references status (id)
);
-- =====================================
-- =====================================
create table trains
(
	id int auto_increment
		primary key,
	trainName varchar(100) null,
  status_id int null,
	constraint train_id_uindex
		unique (id),
	constraint train_status_id_fk
		foreign key (status_id) references status (id)
);
-- =====================================
-- =====================================
create table seats
(
	id int auto_increment
		primary key,
	seat int null,
	train_id int null,
	constraint seat_id_uindex
		unique (id),
	constraint seat_train_id_fk
		foreign key (train_id) references trains (id)
)
engine=InnoDB
;
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
		foreign key (station_id) references stations (id),
	constraint schedule_train_id_fk
		foreign key (train_id) references trains (id),
	constraint schedule_endPointStation_id_fk
		foreign key (endPointStation_id) references stations (id)
)
engine=InnoDB
;
-- =====================================
-- =====================================

-- =====================================
-- =====================================

-- =====================================
-- =====================================
create table user_roles
(
  user_id int null,
  role_id int null,
  constraint user_role_id_fk
		foreign key (user_id) references users (id),
	constraint role_role_id_fk
		foreign key (role_id) references role (id)
)
engine=InnoDB
;
-- =====================================
-- =====================================
create table station_branches
(
  station_id int null,
  branch_id int null,
  station_on_branch_id int null,
  constraint station_station_id_fk
		foreign key (station_id) references stations (id),
	constraint branch_station_id_fk
		foreign key (branch_id) references branches (id)
)
engine=InnoDB
;

