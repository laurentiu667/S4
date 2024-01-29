@ECHO OFF

REM Mot de passe de l'utilisateur "postgres"
SET PGPASSWORD=AAAaaa123

SET /p "nom_fichier=Entrez le nom du fichier : "

ECHO Vous avez saisi : "%nom_fichier%"

REM Commande pour sauvegarder le contenu de la base de données "DB_TEST"
"c:\Program Files\PostgreSQL\15\bin\pg_dump.exe" -U postgres -F p DB_TEST > %nom_fichier%

PAUSE
