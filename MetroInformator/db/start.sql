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
	login varchar(100) not null,
	password varchar(100) not null,
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
	status_id int null,
	constraint station_id_uindex
		unique (id),
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
create table station_branch
(
  station_id int null,
  branch_id int null,
  station_on_branch_id int not null,
  constraint station_station_id_fk
		foreign key (station_id) references station(id),
	constraint branch_station_id_fk
		foreign key (branch_id) references branch(id)
)
engine=InnoDB
;
--
-- =====================================
-- =====================================
create table ticket
(
	id int auto_increment
		primary key,
	user_id int not null,
	train_id int not null,
	stationBegin_id int not null,
	stationEnd_id int not null,
	ticketDate timestamp null,
	price int null,
	branch_id int not null,
	constraint ticket_id_uindex
		unique (id),
	constraint ticket_train_id_fk
		foreign key (train_id) references train(id),
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
