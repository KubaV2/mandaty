--liquibase formatted sql
--changeset jpirko:3
CREATE TABLE `offense`
(
    `id`      int          NOT NULL AUTO_INCREMENT,
    `offense` varchar(400) NOT NULL,
    PRIMARY KEY (`id`)
)