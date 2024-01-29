@ECHO OFF

REM Mot de passe de l'utilisateur "postgres"
SET PGPASSWORD=AAAaaa123

SET /p "nom_fichier=Entrez le nom du fichier : "

ECHO Vous avez saisi : "%nom_fichier%"

REM Commande qui supprime la BD "DB_TEST" et recréer la BD "DB_TEST"
"c:\Program Files\PostgreSQL\15\bin\psql.exe" -U postgres < %nom_fichier%

PAUSE
