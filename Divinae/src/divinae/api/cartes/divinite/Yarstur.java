package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Yarstur extends Divinite {

	public Yarstur() {
		super("Yarstur", Origine.Jour, "Peut détruire toutes les cartes de Croyants "
				+ "au centre de la table d'Origine Néant.", 
				new Dogme[]{Dogme.Chaos, Dogme.Symboles, Dogme.Mystique}, 
				"Dernier pur du jour, Yarstur combat le Néant sous toutes ses formes.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		super.activerCapacite();
		
	}

}
