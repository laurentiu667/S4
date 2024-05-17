-- INSÉRER DES DONNÉES DANS LE SCHÉMA MAT
INSERT INTO MAT.OS (nom)
	VALUES ('Windows 10 22H2'),
		   ('Windows 10 23H2'),
		   ('Windows 11 23H2');

INSERT INTO MAT.Stockage (nom)
	VALUES ('SSD'),
		   ('HDD');

INSERT INTO MAT.Memoire (nom)
	VALUES ('DDR3'),
		   ('DDR4'),
		   ('DDR5');

INSERT INTO MAT.Carte_Reseau (nom)
	VALUES ('Ethernet'),
		   ('WIFI'), 
		   ('Bluetooth');

INSERT INTO MAT.Logiciel(nom_logiciel)
    VALUES ('Final Cut Pro'),
		   ('PhotoShop'),
		   ('MovieMaker'),
		   ('GIMP'),
		   ('VisualStudio'),
		   ('Blender'),
		   ('Unity'),
		   ('Cinema4D'),
		   ('PostgreSQL'),
		   ('PyCharm'),
		   ('Calculator'),
		   ('Desmos'),
		   ('GeoGebra'),
		   ('Excel'),
		   ('Word'),
		   ('ETAP'),
		   ('AutoCAD'),
		   ('AfterEffects');
		
-- INSÉRER DES DONNÉES SUPPLÉMENTAIRES DANS LE SCHÉMA MAT
INSERT INTO MAT.portables (id_portable, manufacteur, modele, numero_serie, date_achat, prix, systeme_os, type_disque, taille_disque, type_memoire, nbr_memoire, type_reseau, adresse_mac, description)
VALUES 
    ((nextval('MAT.codebar_portable')),
	'Aorus', 'AORUS 17X', 'AMBP1', '2021-05-21', 7000, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 10 22H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    1000, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR5'), 
    64, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'WIFI'), 
    '00:1A:2B:3C:4D:5E', 'Ordinateur puissant'),

	((nextval('MAT.codebar_portable')),
	'Aorus', 'AORUS 17X', 'AMBP2', '2021-05-21', 7000, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 10 22H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    1000, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR5'), 
    64, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'WIFI'), 
    '00:2A:2B:3C:4D:5E', 'Ordinateur puissant'),
	
	((nextval('MAT.codebar_portable')),
	'Aorus', 'AORUS 17X', 'AMBP3', '2021-05-21', 7000, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 10 22H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    1000, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR5'), 
    64, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'WIFI'), 
    '00:3A:2B:3C:4D:5E', 'Ordinateur puissant'),
	
	((nextval('MAT.codebar_portable')),
	'Aorus', 'AORUS 17X', 'AMBP4', '2021-05-21', 7000, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 10 22H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    1000, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR5'), 
    64, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'WIFI'), 
    '00:4A:2B:3C:4D:5E', 'Ordinateur puissant'),

    ((nextval('MAT.codebar_portable')),
	'Lenovo', 'ThinkPad X1', 'LTPX1C', '2022-03-15', 1800, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 10 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'HDD'), 
    512, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR4'), 
    16, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'Ethernet'), 
    '00:1B:2C:3D:4E:5F', 'Ultra-léger et puissant'),
	
	((nextval('MAT.codebar_portable')),
	'Lenovo', 'ThinkPad X1', 'LTPX2C', '2022-03-15', 1800, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 10 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'HDD'), 
    512, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR4'), 
    16, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'Ethernet'), 
    '00:2B:2C:3D:4E:5F', 'Ultra-léger et puissant'),
	
	((nextval('MAT.codebar_portable')),
	'Lenovo', 'ThinkPad X1', 'LTPX3C', '2022-03-15', 1800, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 10 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'HDD'), 
    512, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR4'), 
    16, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'Ethernet'), 
    '00:3B:2C:3D:4E:5F', 'Ultra-léger et puissant'),
	
	((nextval('MAT.codebar_portable')),
	'Lenovo', 'ThinkPad X1', 'LTPX4C', '2022-03-15', 1800, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 10 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'HDD'), 
    512, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR4'), 
    16, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'Ethernet'), 
    '00:4B:2C:3D:4E:5F', 'Ultra-léger et puissant'),

    ((nextval('MAT.codebar_portable')),
	'Dell', 'XPS 15', 'DXPS15', '2022-06-20', 2500, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 11 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    1024, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR3'), 
    32, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'WIFI'), 
    '08:01:2b:01:02:03', 'Hautes performances et écran tactile'),
	
	((nextval('MAT.codebar_portable')),
	'Dell', 'XPS 15', 'DXPS25', '2022-06-20', 2500, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 11 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    1024, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR3'), 
    32, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'WIFI'), 
    '08:02:2b:01:02:03', 'Hautes performances et écran tactile'),
	
	((nextval('MAT.codebar_portable')),
	'Dell', 'XPS 15', 'DXPS35', '2022-06-20', 2500, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 11 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    1024, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR3'), 
    32, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'WIFI'), 
    '08:03:2b:01:02:03', 'Hautes performances et écran tactile'),
	
	((nextval('MAT.codebar_portable')),
	'Dell', 'XPS 15', 'DXPS45', '2022-06-20', 2500, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 11 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    1024, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR3'), 
    32, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'WIFI'), 
    '08:04:2b:01:02:03', 'Hautes performances et écran tactile'),

    ((nextval('MAT.codebar_portable')),
	'Microsoft', 'Surface Laptop 4', 'MSLS4', '2023-01-10', 2000, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 11 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    512, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR4'), 
    16, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'Bluetooth'), 
    '00:1A:2C:3D:4E:5F', 'Design élégant et polyvalent'),
	
	((nextval('MAT.codebar_portable')),
	'Microsoft', 'Surface Laptop 4', 'MSLS8', '2023-01-10', 2000, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 11 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    512, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR4'), 
    16, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'Bluetooth'), 
    '00:2A:2C:3D:4E:5F', 'Design élégant et polyvalent'),
	
	((nextval('MAT.codebar_portable')),
	'Microsoft', 'Surface Laptop 4', 'MSLS16', '2023-01-10', 2000, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 11 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    512, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR4'), 
    16, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'Bluetooth'), 
    '00:3A:2C:3D:4E:5F', 'Design élégant et polyvalent'),
	
	((nextval('MAT.codebar_portable')),
	'Microsoft', 'Surface Laptop 4', 'MSLS32', '2023-01-10', 2000, 
    (SELECT id_os FROM MAT.OS WHERE nom = 'Windows 11 23H2'), 
    (SELECT id_stockage FROM MAT.Stockage WHERE nom = 'SSD'), 
    512, 
    (SELECT id_memoire FROM MAT.Memoire WHERE nom = 'DDR4'), 
    16, 
    (SELECT id_carte_reseau FROM MAT.Carte_Reseau WHERE nom = 'Bluetooth'), 
    '00:4A:2C:3D:4E:5F', 'Design élégant et polyvalent');

INSERT INTO MAT.Portables_Logiciel (id_portable, id_logiciel)
VALUES 
	-- PORTABLE 1 : PHOTGRAPHIE
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'AMBP1'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'Final Cut Pro')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'AMBP2'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'PhotoShop')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'AMBP3'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'GIMP')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'AMBP4'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'AfterEffects')),
	-- PORTABLE 2 : INFORMATIQUE
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'LTPX1C'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'Unity')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'LTPX2C'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'VisualStudio')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'LTPX3C'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'Blender')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'LTPX4C'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'PostgreSQL')),
	-- PORTABLE 3 : MATHÉMATIQUE
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'DXPS15'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'Calculator')), 
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'DXPS25'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'Desmos')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'DXPS35'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'GeoGebra')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'DXPS45'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'Excel')),
	-- PORTABLE 4 : ÉLECTRICITÉ 
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'MSLS4'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'ETAP')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'MSLS8'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'AutoCAD')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'MSLS16'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'Word')),
	((SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'MSLS32'), (SELECT id_logiciel FROM MAT.Logiciel WHERE nom_logiciel = 'Excel'));


-- INSÉRER DES DONNÉES DANS LE SCHÉMA EMP
INSERT INTO EMP.Poste (nom, numero_poste)
	VALUES ('Technicien', 'INFO'), 
		   ('Photographe', 'PHOT'), 
		   ('Professeur', 'MATH'), 
		   ('Électricien', 'ELEC');

INSERT INTO EMP.Departement (nom)
	VALUES ('Informatique'), 
		   ('Photographie'), 
		   ('Mathématique'), 
		   ('Électricité');

INSERT INTO EMP.Employes (id_employe, nas, date_embauche, salaire_horaire, nom, prenom, poste, departement, genre, courriel, portable)
VALUES 
    ((nextval('EMP.matricule_employe')),
	'111-222-333', '2020-01-03', 16.30, 'Veilleux', 'Gabriel', (SELECT id_poste from emp.poste where nom = 'Technicien'), (SELECT id_departement from emp.departement where nom = 'Informatique'), 'X', 'gabriel.veilleux@cvm.ca', (SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'LTPX2C')), 
    ((nextval('EMP.matricule_employe')), 
	'222-333-444', '2019-09-12', 30.20, 'Simard', 'William', (SELECT id_poste from emp.poste where nom = 'Photographe'), (SELECT id_departement from emp.departement where nom = 'Photographie'), 'H', 'william.simard@cvm.ca', (SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'AMBP2')), 
    ((nextval('EMP.matricule_employe')), 
	'333-444-555', '2017-03-23', 20.20, 'Dilion', 'Laurentiu', (SELECT id_poste from emp.poste where nom = 'Professeur'), (SELECT id_departement from emp.departement where nom = 'Mathématique'), 'F', 'laurentiu.dilion@cvm.ca', (SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'DXPS15')), 
    ((nextval('EMP.matricule_employe')),
	'444-555-666', '2016-09-30', 21.30, 'Nayaradou', 'Irvan', (SELECT id_poste from emp.poste where nom = 'Électricien'), (SELECT id_departement from emp.departement where nom = 'Électricité'), 'H', 'irvan.nayaradou@cvm.qc.ca', (SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'MSLS4')),
	((nextval('EMP.matricule_employe')),
	'555-666-777', '2014-02-22', 15.30, 'Lannister', 'Tyrion', (SELECT id_poste from emp.poste where nom = 'Professeur'), (SELECT id_departement from emp.departement where nom = 'Informatique'), 'H', 'Tyrion.Lannister@cvm.qc.ca', (SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'LTPX1C')),
	((nextval('EMP.matricule_employe')),
	'666-777-888', '2018-08-11', 18.22, 'Targaryen', 'Daenerys', (SELECT id_poste from emp.poste where nom = 'Photographe'), (SELECT id_departement from emp.departement where nom = 'Photographie'), 'F', 'Daenerys.Targaryen@cvm.qc.ca', (SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'AMBP1')),
	((nextval('EMP.matricule_employe')),
	'777-888-999', '2011-09-01', 18.44, 'Uchiwa', 'Sasuke', (SELECT id_poste from emp.poste where nom = 'Technicien'), (SELECT id_departement from emp.departement where nom = 'Électricité'), 'H', 'Sasuke.Uchiwa@cvm.qc.ca', (SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'MSLS32')),
	((nextval('EMP.matricule_employe')),
	'888-999-000', '2011-10-7', 17.10, 'Zoldyck', 'Killua', (SELECT id_poste from emp.poste where nom = 'Électricien'), (SELECT id_departement from emp.departement where nom = 'Électricité'), 'H', 'Killua.Zoldyck@cvm.qc.ca', (SELECT id_portable FROM MAT.Portables WHERE numero_serie = 'MSLS8'));


-- select * from emp.employes
-- select * from emp.departement
-- select * from emp.poste

-- select * from mat.portables
-- select * from mat.portables_logiciel
-- select * from mat.logiciel
-- select * from mat.carte_reseau
-- select * from mat.memoire
-- select * from mat.os
-- select * from mat.stockage