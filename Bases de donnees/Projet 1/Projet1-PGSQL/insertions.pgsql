-- Inserts pour la table salles
INSERT INTO salles (id, nom, ville, capacite, site_web)
VALUES (100, 'Centre Bell', 'Montreal', 21000, 'www.centrebell.com'),
       (200, 'Centre Videotron', 'Quebec', 21000, 'www.centrevideotron.com'),
       (300, 'Place des Arts', 'Montreal', 8000, 'www.placedesarts.com');

-- Inserts pour la table spectacles
INSERT INTO spectacles (id, nom, date_spectacle, heure, description)
VALUES (10, 'Aladin', '2024-04-10', '19:00', 'Un spectacle de magie et d''aventure'),
       (20, 'Pinoccio', '2024-04-11', '19:00', 'Une adaptation moderne d''un conte classique'),
       (30, 'Ariele la petite sirene', '2024-04-12', '19:00', 'Un conte sous-marin enchanteur');

-- Inserts pour la table billets
INSERT INTO billets (id, spectacle, salle, no_siege, prix_siege)
VALUES (1, (SELECT id FROM spectacles WHERE nom = 'Aladin'), (SELECT id FROM salles WHERE nom = 'Centre Bell'), 'A12', 110.15),
       (2, (SELECT id FROM spectacles WHERE nom = 'Aladin'), (SELECT id FROM salles WHERE nom = 'Centre Videotron'), 'A13', 110.15),
	   (3, (SELECT id FROM spectacles WHERE nom = 'Aladin'), (SELECT id FROM salles WHERE nom = 'Centre Bell'), 'A14', 110.15),
	   (4, (SELECT id FROM spectacles WHERE nom = 'Aladin'), (SELECT id FROM salles WHERE nom = 'Centre Videotron'), 'A15', 110.15),


       (5, (SELECT id FROM spectacles WHERE nom = 'Pinoccio'), (SELECT id FROM salles WHERE nom = 'Centre Videotron'), 'B21', 125.15),
       (6, (SELECT id FROM spectacles WHERE nom = 'Pinoccio'), (SELECT id FROM salles WHERE nom = 'Place des Arts'), 'B22', 125.15),
	   (7, (SELECT id FROM spectacles WHERE nom = 'Pinoccio'), (SELECT id FROM salles WHERE nom = 'Place des Arts'), 'B23', 125.15),
	   (8, (SELECT id FROM spectacles WHERE nom = 'Pinoccio'), (SELECT id FROM salles WHERE nom = 'Centre Videotron'), 'B24', 125.15),


       (9, (SELECT id FROM spectacles WHERE nom = 'Ariele la petite sirene'), (SELECT id FROM salles WHERE nom = 'Place des Arts'), 'C31', 135.15),
       (10, (SELECT id FROM spectacles WHERE nom = 'Ariele la petite sirene'), (SELECT id FROM salles WHERE nom = 'Place des Arts'), 'C32', 135.15),
       (11, (SELECT id FROM spectacles WHERE nom = 'Ariele la petite sirene'), (SELECT id FROM salles WHERE nom = 'Centre Bell'), 'C33', 135.15),
       (12, (SELECT id FROM spectacles WHERE nom = 'Ariele la petite sirene'), (SELECT id FROM salles WHERE nom = 'Centre Bell'), 'C34', 135.15);




-- Inserts pour la table clients
INSERT INTO clients (id, nom, prenom, adresse, telephone, courriel, information)
VALUES (1, 'Veilleux', 'Gabriel', '905 rue Belval', '5142287874', 'gabriel.veilleux@cvm.qc.ca', NULL),
       (2, 'Simard', 'William', '302 chemin vert', '5140943245', 'william.simard@cvm.qc.ca', NULL),
       (3, 'Dilion', 'Laurentiu', '495 rue du cheval', '5140344284', 'laurentiu.dilion@cvm.qc.ca', NULL),
       (4, 'Nayaradou', 'Irvan', '888 rue du telescope', '5149870936', 'irvan.nayaradou@cvm.qc.ca', NULL);

-- Inserts pour la table commandes
INSERT INTO commandes (client, siege, date_paiement)
VALUES ((SELECT id FROM clients WHERE nom = 'Veilleux'), (SELECT id FROM billets WHERE id = 1), '2024-02-12'),
       ((SELECT id FROM clients WHERE nom = 'Veilleux'), (SELECT id FROM billets WHERE id = 5), '2024-01-12'),
       ((SELECT id FROM clients WHERE nom = 'Veilleux'), (SELECT id FROM billets WHERE id = 9), '2024-03-09'),
       ((SELECT id FROM clients WHERE nom = 'Simard'), (SELECT id FROM billets WHERE id = 2), '2023-04-12'),
       ((SELECT id FROM clients WHERE nom = 'Simard'), (SELECT id FROM billets WHERE id = 6), '2024-01-04'),
       ((SELECT id FROM clients WHERE nom = 'Simard'), (SELECT id FROM billets WHERE id = 10), '2024-04-06'),
       ((SELECT id FROM clients WHERE nom = 'Dilion'), (SELECT id FROM billets WHERE id = 3), '2024-03-28'),
       ((SELECT id FROM clients WHERE nom = 'Dilion'), (SELECT id FROM billets WHERE id = 7), '2023-12-12'),
       ((SELECT id FROM clients WHERE nom = 'Dilion'), (SELECT id FROM billets WHERE id = 11), '2023-09-12'),
       ((SELECT id FROM clients WHERE nom = 'Nayaradou'), (SELECT id FROM billets WHERE id = 4), '2024-07-22'),
       ((SELECT id FROM clients WHERE nom = 'Nayaradou'), (SELECT id FROM billets WHERE id = 8), '2023-12-09'),
       ((SELECT id FROM clients WHERE nom = 'Nayaradou'), (SELECT id FROM billets WHERE id = 12), '2024-09-22');
