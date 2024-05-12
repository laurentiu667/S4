
-- =======================================================
-- Numéro de la question :  1
--                          
-- Objectif : Créer une vue qui affiche : nom du département, nom, prénom, numéro de l'employé, numéro du portable emprunté           
--                          
-- Réalisé par :  Laurentiu, modification : Gabriel        
--                          
-- =======================================================
CREATE OR REPLACE VIEW EMP.Vue_Info_Employes AS
	SELECT d.nom AS "Nom du département",
		   e.nom AS "Nom",
		   e.prenom AS "Prénom",
		   e.id_employe AS "Numéro de l''employé",
		   o.id_portable AS "Numéro du portable"
	FROM EMP.employes e
	JOIN EMP.departement d ON e.departement = d.id_departement
	LEFT JOIN MAT.portables o ON e.portable = o.id_portable
	ORDER BY d.nom ASC, e.nom ASC, e.prenom ASC;

-- Appeler la vue
--SELECT * FROM EMP.Vue_Info_Employes; 
-- =======================================================

-- :-)

-- =======================================================
-- Numéro de la question :  2
--                          
-- Objectif : Écrire une procédure qui rapporte des informations sur un employe existant a partir de son numero       
--                          
-- Réalisé par : Laurientiu, modification : Gabriel   
--                          
-- =======================================================
CREATE OR REPLACE PROCEDURE EMP.afficher_infos_employe(num_employe INTEGER)
AS $$
DECLARE
    dept_nom VARCHAR(32);
    poste_nom VARCHAR(32);
BEGIN
    -- Vérifier si l'employé existe
    SELECT d.nom INTO dept_nom FROM EMP.Employes e JOIN EMP.Departement d ON e.departement = d.id_departement WHERE e.id_employe = num_employe;
    SELECT o.nom INTO poste_nom FROM EMP.Employes e JOIN EMP.Poste o ON e.poste = o.id_poste WHERE e.id_employe = num_employe;

    IF dept_nom IS NOT NULL AND poste_nom IS NOT NULL THEN
        RAISE NOTICE 'Voici des informations sur l''employé %', num_employe;
        RAISE NOTICE 'Le nom du département de l''employé est %', dept_nom;
        RAISE NOTICE 'Le nom du poste de l''employé est %', poste_nom;
    ELSE
        RAISE NOTICE 'Aucun employé avec le numéro %', num_employe;
    END IF;
END;
$$ LANGUAGE plpgsql;

-- Appeler la procedure
--CALL EMP.afficher_infos_employe(10002);
-- =======================================================

-- :-)

-- =======================================================
-- Numéro de la question :  3
--                          
-- Objectif : Fonction qui calcule l'ancienneté d'un employé pour une date précise. Return String : nombre d'années, mois, jours.
--                          
-- Réalisé par : Laurientiu 
--                          
-- =======================================================
CREATE OR REPLACE FUNCTION EMP.calculer_anciennete(num_employe INTEGER, date_reference DATE)
RETURNS VARCHAR
AS $$
DECLARE
    anciennete DATE;
    annees INT;
    mois INT;
    jours INT;
BEGIN
    -- Vérifier si l'employé existe
    SELECT e.date_embauche INTO anciennete FROM EMP.Employes e WHERE id_employe = num_employe;
    
    IF anciennete IS NULL THEN
        RETURN num_employe || ' est inexistant';
    ELSIF date_reference < anciennete THEN
        RETURN date_reference || ' est plus petite que la date d’embauche ' || anciennete;
    ELSE
		annees = DATE_PART('year', date_reference) - DATE_PART('year', anciennete);
		
		mois = DATE_PART('month', date_reference) - DATE_PART('month', anciennete);

		jours = DATE_PART('day', date_reference) - DATE_PART('day', anciennete);
        
        RETURN annees || ' ans ' || mois || ' mois et ' || jours || ' jours';
    END IF;
END;
$$ LANGUAGE plpgsql;
-- Appeler la fonction
--SELECT EMP.calculer_anciennete(10002, '2024-04-25');
-- =======================================================

-- :-)

-- =======================================================
-- Numéro de la question :  4
--                          
-- Objectif : Verifier si le salaire lors d'un insertion d'un nouvel employe est plus plus grand ou egal au salaire minimum
--                          
-- Réalisé par : Laurientiu, modification : Gabriel,irvan
--                          
-- =======================================================
-- Fonction pour vérifier le salaire minimum
CREATE OR REPLACE FUNCTION verifier_salaire_min()
RETURNS TRIGGER AS $$
BEGIN
    -- Vérifier si le salaire est plus grand ou égal au salaire minimum
    IF NEW.salaire_horaire < CAST(15.25 AS MONEY) THEN
        RAISE EXCEPTION 'Le salaire doit être au moins égal au salaire minimum de 15.25$';
    END IF;

    -- Si le salaire est conforme, permettre l'insertion
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Déclencheur qui appelle la fonction avant l'insertion d'un nouvel employé
DROP TRIGGER IF EXISTS before_insert_verifier_salaire_min ON EMP.Employes;
CREATE TRIGGER before_insert_verifier_salaire_min
BEFORE INSERT ON EMP.Employes
FOR EACH ROW
EXECUTE FUNCTION verifier_salaire_min();
-- =======================================================

-- :-)

-- =======================================================
-- Numéro de la question :  5
--                          
-- Objectif : Verifier si le salaire lors d'un mise a jour d'un nouvel employe est plus plus grand ou egal au salaire minimum
--                          
-- Réalisé par : Laurientiu, modification : Gabriel,irvan
--                          
-- =======================================================
-- Création d'une fonction pour vérifier le salaire minimum
CREATE OR REPLACE FUNCTION verif_salaire_min()
RETURNS TRIGGER AS $$
BEGIN
    -- Vérifier si le nouveau salaire est inférieur au salaire minimum
    IF NEW.salaire_horaire < CAST(15.25 AS MONEY) THEN
        RAISE EXCEPTION 'Le salaire doit être supérieur ou égal au salaire minimum (15.25$).';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Création du déclencheur pour appeler la fonction avant la mise à jour du salaire
DROP TRIGGER IF EXISTS verif_salaire_min_trigger ON EMP.Employes;
CREATE TRIGGER verif_salaire_min_trigger
BEFORE UPDATE OF salaire_horaire ON EMP.Employes
FOR EACH ROW
EXECUTE FUNCTION verif_salaire_min();
-- =======================================================

-- :-)

-- =======================================================
-- Numéro de la question :  6
-- Objectif : affiche toutes les portables avec egalement leur logiciel
-- Réalisé par : Laurientiu et optimisé par Irvan                      
-- =======================================================
CREATE OR replace VIEW MAT.Vue_Portables_Logiciel AS
SELECT P.id_portable, P.manufacteur, P.modele, P.numero_serie, P.date_achat, P.prix, P.systeme_os, P.type_disque, P.taille_disque, P.type_memoire, P.nbr_memoire, P.type_reseau, P.adresse_mac, P.description, 
       string_agg(L.nom_logiciel, ', ') AS logiciels
FROM MAT.Portables P
INNER JOIN MAT.Portables_Logiciel PL ON P.id_portable = PL.id_portable
INNER JOIN MAT.Logiciel L ON PL.id_logiciel = L.id_logiciel
GROUP BY P.id_portable, P.manufacteur, P.modele, P.numero_serie, P.date_achat, P.prix, P.systeme_os, P.type_disque, P.taille_disque, P.type_memoire, P.nbr_memoire, P.type_reseau, P.adresse_mac, P.description
ORDER BY P.id_portable ASC;

-- Appeler la vue
--select * FROM MAT.Vue_Portables_Logiciel;  
-- =======================================================

-- =======================================================
-- Numéro de la question :  7
-- Objectif : ajoute un logiciel a un portable
-- Réalisé par :  Irvan                      
-- ======================================================= 

CREATE OR REPLACE PROCEDURE Mat.ajouer_logiciel_a_un_portable(
	--le logiciel que l'on desire inserer dans le portable
    p_nom_logiciel VARCHAR, 
	--l'id du'portable dans lequel sera inserer le logiciel
    p_id_portable INTEGER
	)
LANGUAGE plpgsql
AS $$
DECLARE
    v_id_logiciel MAT.Logiciel.id_logiciel%type;
	nouveau_logiciel MAT.Logiciel.nom_logiciel%type;
	logiciel_existant_dans_portable bool;
	portable_existant bool;
	v_id_portable MAT.portables.id_portable%type;
BEGIN
	--initialement on considere que le logiciel n'existe pas
	logiciel_existant_dans_portable = false;
	--verifie que le portable existe
	select exists(select 1 from mat.portables where id_portable = p_id_portable) into portable_existant;
	
	IF portable_existant THEN
	
		-- verifie si le logiciel existe
		select nom_logiciel,id_logiciel into nouveau_logiciel,v_id_logiciel
		from MAT.Logiciel where nom_logiciel ilike p_nom_logiciel;

		--insere le logiciel si n'existe pas encore
		IF nouveau_logiciel IS  NULL THEN
			RAISE NOTICE 'logiciel crée: %',p_nom_logiciel;

			INSERT INTO MAT.Logiciel (nom_logiciel)
			VALUES (p_nom_logiciel)
			RETURNING id_logiciel INTO v_id_logiciel;
		ELSE
		--si logiciel existe deja verifie que le portable ne le contient pas
			RAISE NOTICE ' logiciel trouvé %', nouveau_logiciel;
			
			SELECT EXISTS (
			SELECT 1
			FROM MAT.Portables_Logiciel
			WHERE id_logiciel = v_id_logiciel AND id_portable = p_id_portable
			) INTO logiciel_existant_dans_portable;

		END IF;
		--si le portable le contient l'ajoute sinon ne l'ajoute pas
		IF logiciel_existant_dans_portable then
			raise notice 'le logiciel existe deja dans le portbale';
		ELSE
			INSERT INTO MAT.Portables_Logiciel (id_portable, id_logiciel)
			VALUES (p_id_portable, v_id_logiciel);
			raise notice 'logiciel ajouté sur le portable';

		END IF;
		
	ELSE
		RAISE NOtice 'le portable avec l id: % est inexistant',p_id_portable;
	END IF;

END;
$$;
--exemple d'appel de la procedure
--CALL MAT.ajouer_logiciel_a_un_portable('Visual Studio', 70000002);

-- =======================================================
-- Numéro de la question :  8
-- Objectif : obtenir les couts par OS
-- Réalisé par :  Irvan                      
-- ======================================================= 

CREATE OR REPLACE FUNCTION MAT.cout_Totaux()
RETURNS TABLE(nom_os VARCHAR, cout_total MONEY) AS $$
DECLARE
    couts_totaux MONEY;
BEGIN
    -- Calcul du coût total pour tous les OS
    SELECT SUM(prix) INTO couts_totaux
    FROM MAT.Portables;

    -- Retour de chaque OS avec son coût total et le coût total général
    RETURN QUERY
    SELECT os.nom, SUM(p.prix)
    FROM MAT.Portables p
    JOIN MAT.OS os ON p.systeme_os = os.id_os
    GROUP BY os.nom, cout_total;
	--ligne personalisé pour les cots tataux
	return query
	select 'Tous les Os'::VARCHAR,couts_totaux;
END;
$$ LANGUAGE plpgsql;

-- Appeler la Fonction
--SELECT * FROM MAT.cout_Totaux();
-- ======================================================= 

-- =======================================================
-- Numéro de la question :  9
-- Objectif :   Vérifie que le prix du portable n'est pas négatif

-- Réalisé par :  Irvan  et laurentiu                    
-- ======================================================= 

CREATE OR REPLACE FUNCTION raise_portable_stats()
RETURNS TRIGGER AS $$
BEGIN
    -- RAISE NOTICE pour afficher les statistique du nouveau portable
    RAISE NOTICE 'Nouveau portable ajouté: ID %, Marque: %, Modele: %, Numéro de série: %, OS : %, Stockage : %, Mémoire ID: %, Carte réseau: %',
    NEW.id_portable, NEW.manufacteur, NEW.modele, NEW.numero_serie, NEW.systeme_os, NEW.type_disque, NEW.type_memoire, NEW.type_reseau;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
DROP TRIGGER IF EXISTS trigger_portable_stats ON MAT.Portables;

CREATE TRIGGER trigger_portable_stats
AFTER INSERT ON MAT.Portables
FOR EACH ROW
EXECUTE PROCEDURE raise_portable_stats();

-- ======================================================= 

