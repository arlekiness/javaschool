
create table if not exists station
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	color varchar(100) null,
	constraint station_id_uindex
		unique (id)
);