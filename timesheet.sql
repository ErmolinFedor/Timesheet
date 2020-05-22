CREATE DATABASE TIMESHEET;

CREATE TABLE Employees(
	id 	     SERIAL PRIMARY KEY,
	name         varchar(40) NOT NULL,
	surname      varchar(40) NOT NULL,
	position     varchar(40) NOT NULL
);

CREATE TABLE Departments(
	id 	     SERIAL PRIMARY KEY,
	name         varchar(40) NOT NULL
);
CREATE TABLE WorkPlace(
	idDepartment    integer NOT NULL,
	idEmployee      integer NOT NULL,
	FOREIGN KEY 	(idDepartment) REFERENCES Departments (id) ON DELETE CASCADE,
	FOREIGN KEY 	(idEmployee) REFERENCES Employees (id) ON DELETE CASCADE
);

CREATE TYPE dayType AS ENUM (
	'Я',
	'Н',
	'В',
	'Рв',
	'Б',
	'К',
	'ОТ',
	'До',
	'Хд',
	'У',
	'Ож'
);

CREATE TABLE PersonCalendar (
	day 	     date,
	idEmployee   integer NOT NULL,
	idDepartment integer NOT NULL,
	workType     dayType,
	FOREIGN KEY  (idDepartment) REFERENCES Departments (id) ON DELETE CASCADE,
	FOREIGN KEY  (idEmployee) REFERENCES Employees (id) ON DELETE CASCADE
);

insert into Employees(name, surname, position ) values(
'Иван', 'Иванов' , 'дизайнер');
insert into Employees(name, surname, position ) values(
'Евгений', 'Смирнов' , 'менеджер');
insert into Employees(name, surname, position ) values(
'Андрей', 'Сидоров' , 'разработчик');
insert into Employees(name, surname, position ) values(
'Александр', 'Ургант' , 'комик');
insert into Employees(name, surname, position ) values(
'Влажимр', 'Познер' , 'ведущий');
insert into Employees(name, surname, position ) values(
'Дмитрий', 'Медвежев' , 'фотограф');
insert into Employees(name, surname, position ) values(
'Борис', 'Немцов' , 'руководитель');

insert into Departments(name ) values(
'Разработка');
insert into Departments(name ) values(
'Пиар');
insert into Departments(name ) values(
'Продакшн');

insert into WorkPlace(idDepartment, idEmployee ) values(
3,  1);
insert into WorkPlace(idDepartment, idEmployee ) values(
3,  2);
insert into WorkPlace(idDepartment, idEmployee ) values(
1,  3);
insert into WorkPlace(idDepartment, idEmployee ) values(
2,  4);
insert into WorkPlace(idDepartment, idEmployee ) values(
2,  5);
insert into WorkPlace(idDepartment, idEmployee ) values(
2,  6);
insert into WorkPlace(idDepartment, idEmployee ) values(
1,  8);
insert into WorkPlace(idDepartment, idEmployee ) values(
2,  8);

insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-01', 1, 3, 'Я');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-02', 1, 3, 'В');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-03', 1, 3, 'К');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-01', 2, 3, 'Я');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-02', 2, 3, 'Б');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-03', 2, 3, 'Я');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-01', 3, 1, 'Ож');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-02', 3, 1, 'Рв');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-03', 3, 1, 'Я');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-02-02', 3, 1, 'Я');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-03', 4, 2, 'Я');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-02', 5, 2, 'Я');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-03', 5, 2, 'ОТ');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-01', 8, 2, 'Я');
insert into PersonCalendar (day, idEmployee, idDepartment, workType) values(
'2020-01-02', 8, 1, 'В');

