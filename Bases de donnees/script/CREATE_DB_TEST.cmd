@ECHO OFF

REM Mot de passe de l'utilisateur "postgres"
SET PGPASSWORD=jaune

SET /p "nom_fichier=Entrez le nom du fichier : "

ECHO Vous avez saisi : "%nom_fichier%"

REM Commande qui supprime la BD "DB_TEST" et recréer la BD "DB_TEST"
"C:\Program Files\PostgreSQL\15\bin\psql.exe" -U postgres -f %nom_fichier%

PAUSE
