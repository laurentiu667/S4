import random
from helper import Helper as hp

class GenerateurID:
    numero_id = 0

    @classmethod
    def generer_id(cls):
        cls.numero_id += 1
        return f"id_{cls.numero_id}"

class Statistiques:
    def __init__(self,parent):
        self.parent = parent
        self.nombre_lievres_manges = 0
        self.nombre_lievres_vivants = 0

    def lievre_mange(self):
        self.nombre_lievres_manges += 1
        self.nombre_lievres_vivants -= 1

    def lievre_ajoute(self):
        self.nombre_lievres_vivants += 1

    def obtenir_stats(self):
        return {
            'lievres_manges': self.nombre_lievres_manges,
            'lievres_vivants': self.nombre_lievres_vivants,
        }

class Modele:
    def __init__(self):
        self.largeur = 1000
        self.hauteur = 800
        self.statistiques = Statistiques(self)
        self.loups = {}
        self.lievres = {}
        self.arbustes = {}
        self.objets_morts =[]

    def initialiser_simulation(self, largeur, hauteur, nb_loups, nb_lievres, nb_arbustes):
        self.largeur = largeur
        self.hauteur = hauteur

        self.statistiques.nombre_lievres_vivants = nb_lievres

        for _ in range(nb_loups):
            id_objet = GenerateurID.generer_id()
            position = [random.randint(0, self.largeur), random.randint(0, self.hauteur)]
            self.loups[id_objet] = Loup(self,id_objet, position)

        for _ in range(nb_lievres):
            id_objet = GenerateurID.generer_id()
            position = [random.randint(0, self.largeur), random.randint(0, self.hauteur)]
            self.lievres[id_objet] = Lievre(self, id_objet, position)

        for _ in range(nb_arbustes):
            id_objet = GenerateurID.generer_id()
            position = [random.randint(0, self.largeur), random.randint(0, self.hauteur)]
            self.arbustes[id_objet] = Arbuste(self, id_objet, position)

    def mise_a_jour(self):
        for loup in self.loups.values():
            loup.mise_a_jour()

        for lievre in self.lievres.values():
            lievre.mise_a_jour()

        for arbuste in self.arbustes.values():
            arbuste.mise_a_jour()

    def obtenir_lievres(self):
        return self.lievres

    def supprimer(self,cle,id_objet):
        dico = None
        if cle == "loups":
            dico = self.loups
        elif cle == "lievres":
            self.statistiques.lievre_mange()
            dico = self.lievres
        elif cle == "arbustes":
            dico = self.arbustes
        if dico:
            del dico[id_objet]

class Loup:
    def __init__(self, parent, id_objet, position):
        self.parent = parent
        self.id_objet = id_objet
        self.sante = 100
        self.champ_de_vision = 300
        self.position = position
        self.vitesse = 3
        self.proie_visee = None
        self.faim = True

    def mise_a_jour(self):
        if self.proie_visee:
            self.deplacer()
        else:
            self.chasser()

    def chasser(self):
        # Logique de chasse aux lièvres
        lievres = self.parent.obtenir_lievres()
        for c,i in lievres.items():
            dist = hp.calcDistance(self.position[0],self.position[1],
                                   i.position[0],i.position[1])
            if dist < self.champ_de_vision:
                self.proie_visee = i

    def deplacer(self):
        # Logique de déplacement
        if self.proie_visee.id_objet in self.parent.lievres:
            angle_deplacement = hp.calcAngle(self.position[0],self.position[1],
                                             self.proie_visee.position[0],self.proie_visee.position[1])
            self.position = hp.getAngledPoint(angle_deplacement,self.vitesse,
                                              self.position[0],self.position[1])

            dist = hp.calcDistance(self.position[0], self.position[1],
                                   self.proie_visee.position[0],self.proie_visee.position[1])
            if dist <= self.vitesse:
                self.parent.supprimer("lievres",self.proie_visee.id_objet)
                self.sante += self.proie_visee.valeur_nutrition
                self.proie_visee = None
        else:
            self.proie_visee = None

class Lievre:
    def __init__(self, parent, id_objet, position):
        self.parent = parent
        self.id_objet = id_objet
        self.sante = 100
        self.valeur_nutrition = 20
        self.position = position
        self.vitesse = 2
        self.faim = True

    def mise_a_jour(self):
        pass

    def manger(self, arbustes):
        # Logique de manger des arbustes
        
        
        pass

    def deplacer(self):
        # Logique de déplacement
        
        pass

class Arbuste:
    def __init__(self, parent, id_objet, position):
        self.parent = parent
        self.id_objet = id_objet
        self.taille = 3
        self.taille_max = random.randint(6,12)
        self.position = position

    def mise_a_jour(self):
        pass

    def croitre(self):
        # Logique de croissance
        if self.taille < self.taille_max:
            self.taille += 0.1




            

