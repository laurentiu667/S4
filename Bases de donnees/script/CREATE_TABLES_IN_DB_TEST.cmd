@ECHO OFF

REM Mot de passe de l'utilisateur "postgres"
SET PGPASSWORD=jaune

SET /p "nom_fichier=Entrez le nom du fichier : "

ECHO Vous avez saisi : "%nom_fichier%"

REM Commande qui supprime la table "employe" de la BD "DB_TEST" et recréer la table "employe"
"C:\Program Files\PostgreSQL\16\bin\psql.exe" -U postgres -d DB_TEST -f %nom_fichier%

PAUSE
