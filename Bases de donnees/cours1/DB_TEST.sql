--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: employe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employe (
    nas integer NOT NULL,
    nom character varying(32) NOT NULL,
    prenom character varying(32) NOT NULL,
    genre character(1) NOT NULL,
    date_embauche date NOT NULL,
    salaire numeric(5,2) NOT NULL,
    departement character varying(16),
    ville character varying(64) NOT NULL,
    superviseur integer,
    commission numeric(5,0)
);


ALTER TABLE public.employe OWNER TO postgres;

--
-- Data for Name: employe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employe (nas, nom, prenom, genre, date_embauche, salaire, departement, ville, superviseur, commission) FROM stdin;
111	Dupuis	Lancelot	h	2000-01-28	55.00	ventes	Montréal	\N	1500
222	Bordeleau	Marina	f	2000-05-12	25.00	ventes	Montréal	111	2500
333	Fontaine	Bella	f	2000-05-12	25.00	ventes	Montréal	222	0
444	Lebel	Bob	h	2000-09-13	15.00	achats	Laval	111	\N
555	Tangay	Gäétan	h	2001-01-01	30.50	r&d	Longueuil	111	\N
666	Brochant	Pierre	h	2001-12-25	25.50	achats	Montréal	222	\N
777	Brochant	Christine	f	2002-02-14	20.00	ventes	Montréal	222	3000
888	Pignon	François	h	2002-07-07	13.13	r&d	Laval	555	\N
999	Leblanc	Juste	h	2002-07-08	30.00	r&d	Montréal	555	\N
123	Sasseur	Marlène	f	2002-07-08	15.00	administration	Longueuil	111	\N
234	Bourassa	Alex	x	2002-02-05	19.00	\N	Longueuil	\N	\N
\.


--
-- PostgreSQL database dump complete
--

