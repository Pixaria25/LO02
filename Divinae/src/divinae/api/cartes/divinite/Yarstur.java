package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.Partie;

public class Yarstur extends Divinite {

	public Yarstur() {
		super("Yarstur", Origine.Jour, "Peut d�truire toutes les cartes de Croyants "
				+ "au centre de la table d'Origine N�ant.", 
				new Dogme[]{Dogme.Chaos, Dogme.Symboles, Dogme.Mystique}, 
				"Dernier pur du jour, Yarstur combat le N�ant sous toutes ses formes.", 83);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		super.activerCapacite();
		Partie partie = this.getJoueurLie().getPartie();
		for (int i=0; i < partie.getTasDeCroyants().size(); i++) {
			if (partie.getTasDeCroyants(i).getOrigine() == Origine.Neant) {
				partie.getDefausse().ajoutCarte(partie.getTasDeCroyants(i));
			}
		}
		
	}

}
