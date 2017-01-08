package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class PuiTara extends Divinite {

	public PuiTara() {
		super("Pui-Tara", Origine.Nuit, "Peut détruire toutes les cartes de Croyants "
				+ "au centre de la table d'Origine Jour.",
				new Dogme[]{Dogme.Nature, Dogme.Mystique, Dogme.Symboles}, 
				"Pui-Tara est la Divinité "
				+ "sur laquelle l'influence de la Nuit s'est faite la plus forte.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
