import simulation_modele
import simulation_vue

class Controleur:
    def __init__(self):
        self.modele = simulation_modele.Modele()
        self.vue = simulation_vue.Vue(self)
        self.running = False
        self.vue.root.mainloop()

    def changer_pour_ecran_principal(self):
        # Supposons que l'écran principal est nommé "principal"
        self.vue.afficher_cadre("principal")
        # Initialiser et configurer l'écran principal ici si nécessaire

    def demarrer_simulation(self, largeur, hauteur, nb_loups, nb_lievres, nb_arbustes):
        self.modele = simulation_modele.Modele()
        self.modele.initialiser_simulation(hauteur, largeur, nb_loups, nb_lievres, nb_arbustes)
        # Vous pouvez ajouter ici d'autres logiques de démarrage
        self.vue.afficher_carte(self.modele) # dessine les chose permanentes
        self.running = True
        self.mise_a_jour()

    def mise_a_jour(self):
        if self.running:
            # Mettre à jour le modèle
            self.modele.mise_a_jour()

            # Signaler à la vue d'afficher l'état actuel de la simulation
            self.vue.afficher_simulation(self.modele)
            self.vue.afficher_statistiques(self.modele.statistiques)

            # Planifier la prochaine mise à jour
            self.vue.root.after(10, self.mise_a_jour)

    def arreter_simulation(self):
        # Logique pour arrêter la simulation
        self.running = False
        self.vue.afficher_cadre("principal")
        s
    
        pass

if __name__ == "__main__":
    controleur = Controleur()