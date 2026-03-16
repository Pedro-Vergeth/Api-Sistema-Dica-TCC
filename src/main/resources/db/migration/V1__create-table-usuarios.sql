create table usuarios (
    id serial primary key,
    email varchar(255) not null unique,
    password varchar(255) not null
);