--liquibase formatted sql
--changeset test:5
insert into person(id, pesel, first_name, last_name, email, points) values (1, '49102431442', 'Jan', 'Testowy', 'jan@test.com', 0);
insert into person(id, pesel, first_name, last_name, email, points) values (2, '94051913613', 'Tomasz', 'Test', 'tomasz@test.com', 24);
