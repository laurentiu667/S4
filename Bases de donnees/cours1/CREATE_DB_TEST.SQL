-- Supprimer la base de données si elle existe
DROP DATABASE IF EXISTS "DB_TEST";

-- Créer la base de données en utilisant template0 comme modèle
CREATE DATABASE "DB_TEST"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    TEMPLATE = template0
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Définir la collation et le type de caractères après la création de la base de données
ALTER DATABASE "DB_TEST" 
    OWNER TO postgres 
    LC_COLLATE = 'French_Canada.1252' 
    LC_CTYPE = 'French_Canada.1252';

-- Ajouter un commentaire à la base de données
COMMENT ON DATABASE "DB_TEST" IS 'Ma première base de données';
