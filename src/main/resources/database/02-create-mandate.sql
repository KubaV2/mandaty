--liquibase formatted sql
--changeset jpirko:2
CREATE TABLE `mandate`
(
    `id`        int            NOT NULL AUTO_INCREMENT,
    `pesel`     varchar(45)    NOT NULL,
    `date_time` datetime       NOT NULL,
    `offense`   varchar(45)    NOT NULL,
    `points`    int            NOT NULL,
    `amount`    decimal(10, 0) NOT NULL,
    `person_id` int            NOT NULL,
    PRIMARY KEY (`id`)
)