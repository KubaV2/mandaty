--liquibase formatted sql
--changeset jpirko:3
insert into person(id, pesel, first_name, last_name, email) values (1, '49102431442', 'Jan', 'Nowak', 'nowak@gmail.com');
insert into person(id, pesel, first_name, last_name, email) values (2, '94051913613', 'Andrzej', 'Duda', 'pis@gmail.com');
insert into mandate(id, pesel, date_time, offense, points, amount, person_id) values (1, '49102431442', '2022-04-13T20:40:33.394609', 'Przekroczenie predkosci', '5', '150' ,'1');
insert into mandate(id, pesel, date_time, offense, points, amount, person_id) values (2, '94051913613', '2022-03-11T20:40:33.394609', 'Wymuszenie pierwszenstwa', '3', '200' ,'2');