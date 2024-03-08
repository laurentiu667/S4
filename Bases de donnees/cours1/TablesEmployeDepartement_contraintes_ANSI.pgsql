-- -------------
-- Session H2024
-- -------------

-- ----------------------------------------------------------------------------
-- DDL
-- ----------------------------------------------------------------------------
ALTER TABLE IF EXISTS employe DROP CONSTRAINT IF EXISTS fk_emp_sup;
ALTER TABLE IF EXISTS employe DROP CONSTRAINT IF EXISTS fk_emp_dep;
ALTER TABLE IF EXISTS departement DROP CONSTRAINT IF EXISTS fk_dep_sup;
DROP TABLE IF EXISTS employe;
DROP TABLE IF EXISTS departement;

CREATE TABLE departement (
	id					SERIAL			NOT NULL,
	nom					VARCHAR(32)		NOT NULL,
	ville				VARCHAR(64)		NOT NULL,
	superviseur			INTEGER			NULL,

    CONSTRAINT pk_departement PRIMARY KEY (id)
);

CREATE TABLE employe (
	nas					INT				NOT NULL,
	nom					VARCHAR(32) 	NOT NULL,
	prenom				VARCHAR(32) 	NOT NULL,
	genre				CHAR			NOT NULL,
	date_embauche		DATE			NOT NULL DEFAULT CURRENT_DATE,
	salaire				NUMERIC(5, 2)	NOT NULL DEFAULT 20.0,		-- money est disponible avec PostgreSQL
	departement			INTEGER			NOT NULL,
	ville				VARCHAR(64)		NOT NULL,
	superviseur			INT				NULL DEFAULT NULL,
	commission			NUMERIC(5)		NULL DEFAULT NULL,

    CONSTRAINT pk_employe PRIMARY KEY (nas),
    CONSTRAINT cc_emp_emb CHECK(date_embauche >= '2000-01-01'),
    CONSTRAINT cc_emp_genre CHECK (genre IN ('f', 'h')),
    CONSTRAINT cc_emp_sal CHECK (salaire >= 12.5)
);

ALTER TABLE departement
    ADD CONSTRAINT fk_dep_sup
        FOREIGN KEY (superviseur) REFERENCES employe(nas);

ALTER TABLE employe 
    ADD CONSTRAINT fk_emp_dep 
        FOREIGN KEY (departement) REFERENCES departement(id),
    ADD CONSTRAINT fk_emp_sup 
        FOREIGN KEY (superviseur) REFERENCES employe(nas);
-- ----------------------------------------------------------------------------

