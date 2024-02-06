@ECHO OFF

REM Mot de passe de l'utilisateur "postgres"
SET PGPASSWORD=jaune

SET /p "nom_fichier=Entrez le nom du fichier .TAR : "

ECHO Vous avez saisi : "%nom_fichier%"

REM La première commande force la suppression de la base de données DB_TEST
REM La deuxième commande crée la base de données DB_TEST
REM La troisième commande restaure le contenu de la base de données DB_TEST
"C:\Program Files\PostgreSQL\16\bin\dropdb.exe" -U postgres -f DB_TEST
"C:\Program Files\PostgreSQL\16\bin\createdb.exe" -U postgres DB_TEST
"C:\Program Files\PostgreSQL\16\bin\pg_restore.exe" -U postgres -d DB_TEST %nom_fichier%

PAUSE
