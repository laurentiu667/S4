-- Database: DB_TEST

DROP DATABASE "DB_TEST" WITH (FORCE);

CREATE DATABASE "DB_TEST"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_Canada.1252'
    LC_CTYPE = 'French_Canada.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE "DB_TEST"
    IS 'Ma première base de données';
