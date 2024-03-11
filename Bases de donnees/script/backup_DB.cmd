@ECHO OFF

REM Mot de passe de l'utilisateur "postgres"
SET PGPASSWORD=jaune

SET /p "nom_fichier=Entrez le nom du fichier .TAR : "

ECHO Vous avez saisi : "%nom_fichier%"

REM Commande pour sauvegarder le contenu de la base de données "DB_TEST"
"C:\Program Files\PostgreSQL\16\bin\pg_dump.exe" -U postgres -Ft DB_TEST > %nom_fichier%

PAUSE
