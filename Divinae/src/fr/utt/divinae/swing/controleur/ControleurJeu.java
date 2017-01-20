package fr.utt.divinae.swing.controleur;

import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.joueur.JoueurVirtuel;
import fr.utt.divinae.api.partie.Partie;
import fr.utt.divinae.swing.modele.ModeleJeu;
import fr.utt.divinae.swing.vue.InitialisationJeuDialog;
import fr.utt.divinae.swing.vue.VueJeu;

public class ControleurJeu {
	private ModeleJeu modeleJeu;
	private VueJeu vueJeu;
	private Partie partie;

	public ControleurJeu(ModeleJeu modeleJeu) {
		this.modeleJeu = modeleJeu;
		this.partie = modeleJeu.getPartie();
	}

	public void initialiserJeu(VueJeu vueJeu) {
		this.vueJeu = vueJeu;
		Joueur.setSelection(new SelectionGraphique(this.vueJeu));
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
		vueJeu.afficherTable();
		if (!partie.isPartieFinie()) {
			partie.debuterUnTour();
			vueJeu.afficherDe(partie.getDe());
			
			jouerTourJoueurReel(partie.getJoueurs().get(partie.getIndexJoueur1()));
		}
		else
		{
			vueJeu.alert("La partie est terminée");
			vueJeu.setVisible(false);
		}
	}
	
	public void jouerTourJoueursVirtuels()
	{
		for(int i = 0; i <  partie.getJoueurs().size(); i++) {
			Joueur joueurCourant = partie.getJoueurs().get(i);
			if (joueurCourant instanceof JoueurVirtuel)
			{
				vueJeu.afficherMessage(joueurCourant.getNom()+" joue ---------------->");
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
		vueJeu.afficherMessage(joueurCourant.getNom()+" joue ---------------->");

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
