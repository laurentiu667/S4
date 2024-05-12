DROP SCHEMA IF EXISTS EMP CASCADE;
DROP SCHEMA IF EXISTS MAT CASCADE;

-- SCHEMAS
CREATE SCHEMA EMP;
CREATE SCHEMA MAT;

-- TYPE POUR TABLE EMPLOYE 
CREATE TYPE EMP.genre AS ENUM('H', 'F', 'X');

-- SÉQUENCES
CREATE SEQUENCE IF NOT EXISTS EMP.Matricule_Employe
	START 10000
   	INCREMENT BY 1
   	MAXVALUE 99999;

CREATE SEQUENCE IF NOT EXISTS MAT.CodeBar_Portable
   START 70000000
   INCREMENT BY 1
   MAXVALUE 70099999;

-- MAIN TABLE 1:
CREATE TABLE EMP.Employes (
    id_employe INTEGER PRIMARY KEY NOT NULL,									-- PK, 10000 à 99999, sequence
    nas VARCHAR(11) UNIQUE NOT NULL 
		CHECK(nas ~ '^\d{3}-\d{3}-\d{3}$'),										-- REGEX : ###-###-###
	date_embauche date NOT NULL,												-- MIN : 1976-08-01
	salaire_horaire MONEY NOT NULL,												-- MIN : 15.25$
	nom VARCHAR(32) NOT NULL,					
	prenom VARCHAR(32) NOT NULL,	
	poste INTEGER NOT NULL,													-- FK : 1
	departement INTEGER NOT NULL,											-- FK : 2
	genre EMP.genre NOT NULL,														-- F, H ou X
	courriel VARCHAR(32) UNIQUE NOT NULL 
		CHECK(courriel ~ '^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),		-- REGEX : ...@...
	portable INTEGER UNIQUE NOT NULL											-- FK : 3
);
-- SOUS TABLE MAIN 1
CREATE TABLE EMP.Departement (
	id_departement SERIAL PRIMARY KEY NOT NULL,	-- PK : 2
	nom VARCHAR(32) UNIQUE NOT NULL
);
CREATE TABLE EMP.Poste (
	id_poste SERIAL PRIMARY KEY NOT NULL,		-- PK : 1
	nom VARCHAR(32) UNIQUE NOT NULL,
	numero_poste VARCHAR(4) UNIQUE NOT NULL
);


-- MAIN TABLE 2:
CREATE TABLE MAT.Portables (
    id_portable INTEGER PRIMARY KEY NOT NULL,	-- PK : 3, 70000000 à 70099999, sequence
	manufacteur VARCHAR(16) NOT NULL,
	modele VARCHAR(16) NOT NULL,
	numero_serie VARCHAR(32) NOT NULL,
	date_achat DATE NOT NULL,
	prix MONEY NOT NULL,						-- >= 0
	systeme_os INTEGER NOT NULL,			-- FK : 4
	type_disque INTEGER NOT NULL,			-- FK : 5		
	taille_disque INTEGER NOT NULL,				-- GB >= 1				
	type_memoire INTEGER NOT NULL,			-- FK : 6
	nbr_memoire INTEGER NOT NULL,				-- GB >= 1
	type_reseau INTEGER NOT NULL,			-- FK : 7
	adresse_mac MACADDR UNIQUE NOT NULL,
	description VARCHAR(1024) NOT NULL
);
-- SOUS TABLE MAIN 2
CREATE TABLE MAT.Logiciel (
	id_logiciel SERIAL PRIMARY KEY NOT NULL,	-- PK : 8
	nom_logiciel VARCHAR(32) NOT NULL
);
CREATE TABLE MAT.Portables_Logiciel (
	id_pl SERIAL PRIMARY KEY NOT NULL,
	id_portable SERIAL NOT NULL,				-- FK : 3
	id_logiciel SERIAL NOT NULL					-- FK : 8
);

CREATE TABLE MAT.OS (
	id_os SERIAL PRIMARY KEY NOT NULL,				-- FK : 4
	nom VARCHAR(16) NOT NULL
);
CREATE TABLE MAT.Stockage (	
	id_stockage SERIAL PRIMARY KEY NOT NULL,				-- FK : 5
	nom VARCHAR(16) NOT NULL
);
CREATE TABLE MAT.Memoire (
	id_memoire SERIAL PRIMARY KEY NOT NULL,				-- FK : 6
	nom VARCHAR(16) NOT NULL
);
CREATE TABLE MAT.Carte_Reseau (
	id_carte_reseau SERIAL PRIMARY KEY NOT NULL,				-- FK : 7
	nom VARCHAR(16) NOT NULL
);


-- FOREIGN KEYS
-- SCHEMA MAT
ALTER TABLE MAT.Portables_Logiciel
	add CONSTRAINT FK_ID_PORTABLE
		FOREIGN KEY (id_portable) REFERENCES MAT.Portables(id_portable),
	add CONSTRAINT FK_ID_LOGICIEL 
		FOREIGN KEY (id_logiciel) REFERENCES MAT.Logiciel(id_logiciel);
		
ALTER TABLE MAT.Portables
	add CONSTRAINT FK_ID_OS
		FOREIGN KEY (systeme_os) REFERENCES MAT.OS(id_os),
	add CONSTRAINT FK_ID_STORAGE
		FOREIGN KEY (type_disque) REFERENCES MAT.Stockage(id_stockage),
	add CONSTRAINT FK_ID_MEMOIRE
		FOREIGN KEY (type_memoire) REFERENCES MAT.Memoire(id_memoire),
	add CONSTRAINT FK_ID_CARTE_RESEAU
		FOREIGN KEY (type_reseau) REFERENCES MAT.Carte_Reseau(id_carte_reseau);
		
-- SCHEMA EMP 
ALTER TABLE EMP.Employes
	add CONSTRAINT FK_ID_POSTE 
		FOREIGN KEY (poste) REFERENCES EMP.Poste(id_poste),
	add CONSTRAINT FK_ID_DEPARTEMENT
		FOREIGN KEY (departement) REFERENCES EMP.Departement(id_departement),
	add CONSTRAINT FK_ID_PORTABLE
		FOREIGN KEY (portable) REFERENCES MAT.Portables(id_portable);
		
-- INDEX
CREATE INDEX IF NOT EXISTS index_Nom_EMP ON EMP.Employes(nom, prenom);
CREATE INDEX IF NOT EXISTS index_MAC_MAT ON MAT.Portables(adresse_mac);

-- VIEW
CREATE OR REPLACE VIEW EMP.nbr_emp_poste AS
	SELECT poste AS Poste, COUNT(*) AS Nombre
	FROM EMP.Employes
	GROUP BY poste;
	

