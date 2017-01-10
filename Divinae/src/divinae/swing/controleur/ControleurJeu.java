package divinae.swing.controleur;

import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;
import divinae.swing.modele.ModeleJeu;
import divinae.swing.vue.InitialisationJeuDialog;
import divinae.swing.vue.VueJeu;

public class ControleurJeu {
	private ModeleJeu modeleJeu;
	private VueJeu vueJeu;
	private Partie partie;

	public ControleurJeu(ModeleJeu modeleJeu, VueJeu vueJeu) {
		this.modeleJeu = modeleJeu;
		this.vueJeu = vueJeu;
		this.partie = modeleJeu.getPartie();
	}

	public void initialiserJeu() {
		InitialisationJeuDialog dialog = new InitialisationJeuDialog(null, "Définir les joueurs", true);
		dialog.setVisible(true);
		String nomJoueurReel = dialog.getNomJoueurReel().getText();
		int nombreJoueurVirtuel = Integer.valueOf(dialog.getNombreJoueurVirtuel().getText()).intValue();
		modeleJeu.initialiserPartie(nomJoueurReel, nombreJoueurVirtuel);
	}

	public void jouer() {
		if (!this.partie.isPartieFinie())
		{
			jouerUnTour();
			this.partie.preparerTourProchain();
		}
	}

	public void jouerUnTour() {
		partie.debuterUnTour();
		int indexCourant = partie.getIndexJoueur1();
		Joueur joueurCourant = partie.getJoueurs().get(indexCourant);
		vueJeu.afficherMessage(joueurCourant.getNom()+" joue.");

		int nombreCartes = 7-joueurCourant.getMain().size();
		if (nombreCartes > 0)
		{
			vueJeu.afficherMessage("Pioche de " + nombreCartes + " cartes.");
		}
		joueurCourant.completerMain();
		vueJeu.afficherJoueurDivinite(joueurCourant.getDivinite());
		vueJeu.afficherMain(joueurCourant.lireCartes());
		vueJeu.afficherMessage(joueurCourant.afficherMain());
	}

	private void defausser(Joueur joueur) {
		vueJeu.afficherMessage("Voulez-vous defausser des cartes ? (y/n)");
		String reponse = "";

		//do{
			// TODO reponse = scanner.next();
			//if(!(reponse.equals("n") || reponse.equals("y"))) {
				//vueJeu.afficherMessage("Reponse invalide.");
			//}
		//} while(!(reponse.equals("n") || reponse.equals("y")));
		int nombreCartes = 0;
		if(reponse.equals("y")) {
			vueJeu.afficherMessage("Combien de cartes voulez-vous defausser ?");

			boolean aDefausse = false;
			//do{
				// TODO nombreCartes = scanner.nextInt();
				if(nombreCartes >= 0 || nombreCartes <= joueur.getMain().size()) {
					aDefausse = true;
				} else {
					vueJeu.afficherMessage("Ce nombre de cartes est invalide.");
				}
			//} while(!aDefausse);
			vueJeu.afficherMessage("Quelles cartes voulez-vous defausser ?");

			//Donner toutes les cartes a defausser ? Donner le numero de la carte a defausser ? Donner le nombre de cartes a defausser puis donner lesquels ?
			// TODO joueur.defausser(nombreCartes);
		}
	}
}
