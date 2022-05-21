--liquibase formatted sql
--changeset jpirko:1
CREATE TABLE `person`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `pesel`      varchar(45) NOT NULL,
    `first_name` varchar(45) NOT NULL,
    `last_name`  varchar(45) NOT NULL,
    `email`      varchar(45) NOT NULL,
    `points`     int         NOT NULL,
    PRIMARY KEY (`id`)
)
