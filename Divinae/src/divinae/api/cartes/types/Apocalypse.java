package divinae.api.cartes.types;

import java.util.List;

import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Apocalypse extends  CarteAction {

	public Apocalypse(Origine origine, int id) {
		super("Apocalypse", "",origine, "Lance une apocalypse.", id);
	}

	@Override
	public void activerCapacite() {
		Partie partie = getJoueurLie().getPartie();
		List<Joueur> joueurs = partie.getJoueurs();
		Joueur dernierJoueur = joueurs.get((partie.getIndexJoueur1() - 1)%partie.getJoueurs().size());
		int indexJoueurCourant = joueurs.indexOf(getJoueurLie());
		
		if (dernierJoueur.getNom() == joueurs.get(indexJoueurCourant + joueurs.size()-1).getNom()) {
			Utilitaire.resetAutorisations(partie);
		}
		getJoueurLie().getPartie().setIndexJoueur1(getJoueurLie().getPartie().getJoueurs().indexOf(getJoueurLie()));
		Capacite.lancerApocalypse(getJoueurLie().getPartie());
		Capacite.getActionSuivante().messageRecap("Le gagnant est "+partie.getJoueurs().get(partie.getIndexGagnant()).getNom());
		System.exit(0);
	}

	@Override
	public void poserCarteAction() {
		activerCapacite();
		getJoueurLie().tuerCarte(this);
	}

	@Override
	public String toString() {
		return "Apocalypse " + super.toString();
	}
}
