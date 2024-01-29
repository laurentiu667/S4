from tkinter import *

class Vue:
    def __init__(self, controleur):
        self.controleur = controleur
        self.root = Tk()
        self.root.geometry("800x600")  # Taille de la fenêtre

        self.cadres = {}
        self.cadre_actif = None  # Variable pour le cadre actif

        self.cadres["accueil"] = self.creer_cadre_accueil()
        self.cadres["principal"] = self.creer_cadre_principal()
        # Ajouter d'autres cadres ici

        self.afficher_cadre("accueil")

    def creer_cadre_accueil(self):
        cadre = Frame(self.root)
        Label(cadre, text="Bienvenue dans l'Application de Simulation de Milieu Naturel", font=("Arial", 20)).pack(
            pady=20)
        # Ajouter d'autres éléments graphiques ici

        Button(cadre, text="Commencer", command=self.commencer_application).pack()
        return cadre


    def creer_cadre_principal(self):
        self.cadre_principal = Frame(self.root)

        # Cadre pour les paramètres de la simulation
        cadre_parametres = self.creer_cadre_parametres()
        # Ajoutez ici les widgets pour les paramètres

        # Cadre pour le canvas de la simulation
        cadre_canvas = self.creer_cadre_canvas()

        # Cadre pour les statistiques
        cadre_statistiques = self.creer_cadre_statistiques()

        return self.cadre_principal
    def creer_cadre_statistiques(self):

        cadre_statistiques = Frame(self.cadre_principal, borderwidth=2, relief="groove")
        cadre_statistiques.pack(side="left", fill="y")
        Label(cadre_statistiques, text="Statistiques").pack(pady=10)
        # Ajoutez ici les widgets pour les statistiques
        Label(cadre_statistiques, text="Nb de lievres").pack(pady=5)
        self.nb_lievres = Label(cadre_statistiques, text="Inconnu")
        self.nb_lievres.pack(pady=5)

    def creer_cadre_canvas(self):
        frame = Frame(self.cadre_principal, width=500, height=500)
        frame.pack(side="left",expand=1, fill=BOTH)
        self.canevas = Canvas(frame, bg='red', width=300, height=300, scrollregion=(0, 0, 1500, 1500))
        vbar = Scrollbar(self.canevas, orient=VERTICAL)
        vbar.pack(side=RIGHT, fill=Y)
        vbar.config(command=self.canevas.yview)

        hbar = Scrollbar(self.canevas, orient=HORIZONTAL)
        hbar.pack(side=BOTTOM, fill=X)
        hbar.config(command=self.canevas.xview)
        # canvas.config(width=300, height=300)
        self.canevas.config(yscrollcommand=vbar.set)
        self.canevas.config(xscrollcommand=hbar.set)
        self.canevas.pack(side=LEFT, expand=True, fill=BOTH)

    def creer_cadre_parametres(self):
        self.cadre_parametres = Frame(self.cadre_principal, borderwidth=2, relief="groove")

        self.cadre_parametres.pack(side="left", fill="y")
        Label(self.cadre_parametres, text="Paramètres de la Simulation").pack(pady=10)

        self.cadre_parametres.pack(side="left", fill="y")
        # Paramètre pour la taille de la carte de la simulation
        Label(self.cadre_parametres, text="Largeur").pack(pady=5)
        self.largeur = Entry(self.cadre_parametres)
        self.largeur.pack()
        self.largeur.insert(0,2000)

        Label(self.cadre_parametres, text="hauteur").pack(pady=5)
        self.hauteur = Entry(self.cadre_parametres)
        self.hauteur.pack()
        self.hauteur.insert(0,2000)

        # Paramètre pour le nombre de loups
        Label(self.cadre_parametres, text="Nombre de Loups:").pack(pady=5)
        self.nombre_loups = Entry(self.cadre_parametres)
        self.nombre_loups.pack()
        self.nombre_loups.insert(0,10)

        # Paramètre pour le nombre de lièvres
        Label(self.cadre_parametres, text="Nombre de Lièvres:").pack(pady=5)
        self.nombre_lievres = Entry(self.cadre_parametres)
        self.nombre_lievres.pack()
        self.nombre_lievres.insert(0,200)

        # Paramètre pour le nombre d'arbustes
        Label(self.cadre_parametres, text="Nombre d'Arbustes:").pack(pady=5)
        self.nombre_arbustes = Entry(self.cadre_parametres)
        self.nombre_arbustes.pack()
        self.nombre_arbustes.insert(0,300)

        # Boutons pour démarrer et arrêter la simulation
        Button(self.cadre_parametres, text="Démarrer la Simulation", command=self.lire_parametres_et_demarrer).pack(pady=10)
        Button(self.cadre_parametres, text="Arrêter la Simulation", command=self.controleur.arreter_simulation).pack()

        return self.cadre_parametres

    def afficher_cadre(self, nom_cadre):
        if self.cadre_actif is not None:
            self.cadre_actif.pack_forget()

        cadre = self.cadres[nom_cadre]
        cadre.pack(fill="both", expand=True)
        self.cadre_actif = cadre

    def commencer_application(self):
        self.controleur.changer_pour_ecran_principal()

    def lire_parametres_et_demarrer(self):
        largeur = int(self.largeur.get())
        hauteur = int(self.hauteur.get())
        nb_loups = int(self.nombre_loups.get())
        nb_lievres = int(self.nombre_lievres.get())
        nb_arbustes = int(self.nombre_arbustes.get())
        self.controleur.demarrer_simulation(largeur, hauteur, nb_loups, nb_lievres, nb_arbustes)

    def afficher_carte(self,modele):
        self.canevas.config(scrollregion=(0, 0, modele.largeur, modele.hauteur))

        taille = 10
        for k,v in modele.arbustes.items():
            self.canevas.create_oval(v.position[0]-taille,v.position[1]-taille,
                                     v.position[0]+taille,v.position[1]+taille, fill="green",
                                     tags = ("statique",))
        self.afficher_statistiques(modele.statistiques)

    def afficher_simulation(self,modele):
        self.canevas.delete("dynamique")
        taille = 12
        for k,v in modele.loups.items():
            self.canevas.create_oval(v.position[0]-taille,v.position[1]-taille,
                                     v.position[0]+taille,v.position[1]+taille, fill="grey50",
                                     tags = ("dynamique",))


        taille = 6
        for k,v in modele.lievres.items():
            self.canevas.create_oval(v.position[0]-taille,v.position[1]-taille,
                                     v.position[0]+taille,v.position[1]+taille, fill="grey90",
                                     tags = ("dynamique",))
    def afficher_statistiques(self, stats):
        # Mettre à jour les éléments d'interface utilisateur avec les nouvelles stats
        # Exemple: self.label_lievres_vivants.config(text=f"Lievres Vivants: {stats['lievres_vivants']}")
        self.nb_lievres.config(text =str(stats.nombre_lievres_vivants))