


drop table if exists commandes;
drop table if exists clients;
drop table if exists billets;
drop table if exists spectacles;
drop table if exists salles;

create table salles (
    id serial primary key,
    nom VARCHAR(64) not null unique,
    ville VARCHAR(64) not null,
    capacite integer not null check (capacite >= 0),
    site_web VARCHAR(64)
);

create table spectacles (
    id serial primary key,
    nom VARCHAR(64) not null unique,
    date_spectacle date not null check (date_spectacle >= '2020-01-01'),
    heure time not null check (heure >= '19:00:00'),
    description VARCHAR(1024)
);

create table billets(
    id serial primary key,
    spectacle integer not null,
    salle integer not null,
    no_siege varchar(32) not null,
    prix_siege MONEY NOT NULL CHECK (prix_siege::NUMERIC >= 0), 
    foreign key (spectacle) references spectacles(id),
    foreign key (salle) references salles(id),
    constraint unique_billet unique (spectacle, salle, no_siege)
);

create table clients (
    id serial primary key,
    nom varchar(64) not null,
    prenom varchar(64) not null,
    adresse varchar(256) not null,
    telephone varchar(15) not null,
    courriel varchar(64) not null unique,
    information varchar(1024)
);

create table commandes (
    client integer not null,
    siege integer not null,
    date_paiement date not null,
    foreign key (client) references clients(id),
    foreign key (siege) references billets(id)
);












