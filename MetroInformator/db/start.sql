-- =====================================
-- =====================================
create table if not exists stations
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	color varchar(100) null,
	constraint station_id_uindex
		unique (id)
);
-- =====================================
-- =====================================
create table if not exists trains
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	constraint train_id_uindex
		unique (id)
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
	constraint schedule_id_uindex
		unique (id),
	constraint schedule_station_id_fk
		foreign key (station_id) references stations (id),
	constraint schedule_train_id_fk
		foreign key (train_id) references trains (id)
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
	birthDay varchar(100) null,
	sex varchar(10) null,
	constraint user_id_uindex
		unique (id)
)
engine=InnoDB
;
-- =====================================
-- =====================================
create table roles
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
create table user_roles
(
  user_id int null,
  role_id int null,
  constraint user_role_id_fk
		foreign key (user_id) references users (id),
	constraint role_role_id_fk
		foreign key (role_id) references roles (id)
)
engine=InnoDB
;