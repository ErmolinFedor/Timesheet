
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