--liquibase formatted sql
--changeset jpirko:4
CREATE TABLE `mandate_offenses`
(
    `mandate_id`  int NOT NULL,
    `offenses_id` int NOT NULL
)