CREATE TABLE `user`
(
    `id`              CHAR(36) PRIMARY KEY,
    `user_name`       VARCHAR(80),
    `display_name`    VARCHAR(80),
    `signature`       VARCHAR(140),
    `email`           VARCHAR(80),
    `created_at`      TIMESTAMP(6),
    `saved_at`        TIMESTAMP(6),
);
