--liquibase formatted sql
--changeset test:1
CREATE TABLE `person`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `pesel`      varchar(11) NOT NULL,
    `first_name` varchar(45) NOT NULL,
    `last_name`  varchar(45) NOT NULL,
    `email`      varchar(50) NOT NULL,
    `points`     int         NOT NULL,
    PRIMARY KEY (`id`)
)