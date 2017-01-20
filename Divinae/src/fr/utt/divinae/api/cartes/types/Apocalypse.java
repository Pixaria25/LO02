package fr.utt.divinae.api.cartes.types;

import fr.utt.divinae.api.partie.Partie;

public class Apocalypse extends  CarteAction {

	public Apocalypse(Origine origine, int id) {
		super("Apocalypse", "",origine, "Lance une apocalypse.", id);
	}

	@Override
	public void activerCapacite() {
		Partie partie = Partie.getInstance();
		
		if (isCapaciteBloque()) {
			System.out.println(getNom() + " a été bloqué !");
			
		} else {
			getJoueurLie().getPartie().setIndexJoueur1(getJoueurLie().getPartie().getJoueurs().indexOf(getJoueurLie()));
			Capacite.lancerApocalypse(getJoueurLie().getPartie());
			getJoueurLie().messageRecap(getJoueurLie().getNom() + " joue " + getNom());
			getJoueurLie().messageRecap("Le gagnant est "+partie.getJoueurs().get(partie.getIndexGagnant()).getNom());

		}
	}

	@Override
	public void poserCarteAction() {
		activerCapacite();
		getJoueurLie().getMain().remove(this);
		mort();
	}

	@Override
	public String toString() {
		return "Apocalypse " + super.toString();
	}
}
