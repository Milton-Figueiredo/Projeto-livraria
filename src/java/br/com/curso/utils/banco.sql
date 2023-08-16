/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  muril
 * Created: 15/05/2023
 */
create table autor(
idautor serial primary key,
	descricao varchar(100) not null
);

create table editora(
ideditora serial primary key,
	descricao varchar(100) not null
);

create table genero(
idgenero serial primary key,
	descricao varchar(100)
);

insert into autor (descricao) values ('Victory Aveyard');
insert into autor (descricao) values ('Carina Rissi') ;
insert into autor (descricao) values ('H.P. Lovecraft');

insert into editora (descricao) values ('Seguinte');
insert into editora (descricao) values ('Verus');
insert into editora (descricao) values ('DarkSide');

insert into genero (descricao) values ('Distopia');
insert into genero (descricao) values ('Romance');
insert into genero (descricao) values ('Terror');

