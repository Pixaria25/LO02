package divinae.swing.controleur;

import divinae.api.cartes.types.Capacite;
import divinae.api.joueur.Joueur;
import divinae.api.joueur.JoueurVirtuel;
import divinae.api.partie.Partie;
import divinae.console.ActionSuivanteConsole;
import divinae.swing.modele.ModeleJeu;
import divinae.swing.vue.InitialisationJeuDialog;
import divinae.swing.vue.VueJeu;

public class ControleurJeu {
	private ModeleJeu modeleJeu;
	private VueJeu vueJeu;
	private Partie partie;

	public ControleurJeu(ModeleJeu modeleJeu) {
		this.modeleJeu = modeleJeu;
		this.partie = modeleJeu.getPartie();
		Capacite.setActionSuivante(new ActionSuivanteGraphique());
	}

	public void initialiserJeu(VueJeu vueJeu) {
		this.vueJeu = vueJeu;
		InitialisationJeuDialog dialog = new InitialisationJeuDialog(null, "Définir les joueurs", true);
		dialog.setVisible(true);
		String nomJoueurReel = dialog.getNomJoueurReel().getText();
		int nombreJoueurVirtuel = Integer.valueOf(dialog.getNombreJoueurVirtuel().getText()).intValue();
		modeleJeu.initialiserPartie(nomJoueurReel, nombreJoueurVirtuel);
	}

	public void jouer() {
		partie.distribuerLesDivinites();
		partie.remplirPioche();
		partie.distribuerCartes();
		jouerTourSuivant();
	}

	public void jouerTourSuivant() {
		if (!partie.isPartieFinie()) {
			partie.debuterUnTour();
			vueJeu.afficherMessage("Influence de Dé Cosmogonie : "+partie.getDe().getInfluence());
			
			jouerTourJoueurReel(partie.getJoueurs().get(partie.getIndexJoueur1()));
		}
	}
	
	public void jouerTourJoueursVirtuels()
	{
		for(int i = 0; i <  partie.getJoueurs().size(); i++) {
			Joueur joueurCourant = partie.getJoueurs().get(i);
			if (joueurCourant instanceof JoueurVirtuel)
			{
				vueJeu.afficherMessage(joueurCourant.getNom()+" joue.");
				joueurCourant.jouer();
				partie.setCroyantsRattachables();
			}
		}
		partie.preparerTourProchain();
		jouerTourSuivant();
	}
	
	private void jouerTourJoueurReel(Joueur joueurCourant) {
		vueJeu.afficherMessage(joueurCourant.afficherPoints());
		vueJeu.afficherMessage(joueurCourant.afficherMain());
		vueJeu.afficherMessage(joueurCourant.getNom()+" joue.");

		int nombreCartes = 7-joueurCourant.getMain().size();
		if (nombreCartes > 0)
		{
			vueJeu.afficherMessage("Pioche de " + nombreCartes + " cartes.");
		}
		joueurCourant.completerMain();
		vueJeu.afficherJoueurDivinite(joueurCourant.getDivinite());
		vueJeu.initialiserVueJoueur();
		vueJeu.afficherMain(joueurCourant.getMain());
	}
}
