package divinae.swing.controleur;

import divinae.api.joueur.Joueur;
import divinae.api.joueur.JoueurVirtuel;
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
		if (!(joueurCourant instanceof JoueurVirtuel)) {
			vueJeu.afficherJoueurDivinite(joueurCourant.getDivinite());
			vueJeu.afficherMain(joueurCourant.getMain());
		}
		vueJeu.afficherMessage(joueurCourant.afficherMain());
	}
}
