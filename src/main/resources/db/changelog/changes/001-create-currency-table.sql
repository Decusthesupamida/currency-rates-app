CREATE TABLE if not exists currency
(
    id   bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    code VARCHAR(255) not null unique
)
