--liquibase formatted sql
--changeset jpirko:3
CREATE TABLE `offense`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `description` varchar(400) NOT NULL,
    `optgroup`       varchar(100)  NOT NULL,
    PRIMARY KEY (`id`)
)