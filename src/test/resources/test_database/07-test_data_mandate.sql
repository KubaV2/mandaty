--liquibase formatted sql
--changeset test:7
insert into mandate(id, pesel, date_time, points, amount, person_id) values (1, '94051913613', '2022-05-11 17:36:38', 10, 100, 2);
insert into mandate(id, pesel, date_time, points, amount, person_id) values (2, '94051913613', '2022-05-12 15:36:38', 14, 100, 2);
