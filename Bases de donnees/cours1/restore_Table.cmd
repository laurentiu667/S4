@ECHO OFF

REM Mot de passe de l'utilisateur "postgres"
SET PGPASSWORD=AAAaaa123

SET /p "nom_fichier=Entrez le nom du fichier : "

ECHO Vous avez saisi : "%nom_fichier%"

REM Commande qui supprime la table "employe" de la BD "DB_TEST" et recréer la table "employe"
"c:\Program Files\PostgreSQL\15\bin\psql.exe" -U postgres -d DB_TEST < %nom_fichier%

PAUSE
