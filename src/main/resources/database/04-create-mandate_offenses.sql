--liquibase formatted sql
--changeset jpirko:4
CREATE TABLE `mandate_offense`
(
    `mandate_id`  int NOT NULL,
    `offense_id` int NOT NULL
)