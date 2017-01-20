package fr.utt.divinae.api.cartes.divinite;

import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public class Gwenghelen extends Divinite {

	public Gwenghelen() {
		super("Gwenghelen", Origine.Aube, "R�cup�re autant de points d'Action suppl�mentaires "
				+ "d'Origine N�ant que le nombre de Guides Spirituels que la Divinit� poss�de.", 
				new Dogme[]{Dogme.Humain, Dogme.Mystique, Dogme.Symboles}, 
				"Premi�re Divinit� � recevoir l'influence du N�ant, "
				+ "Gwenghelen est celle qui en a re�u le plus de puissance.", 87);
	}

	@Override
	public void activerCapacite() {
		super.activerCapacite();
		getJoueurLie().ajoutPointsAction(getJoueurLie().getGuides().size(), Origine.Neant);
//		System.out.println(getJoueurLie().getNom()+" recoit "+getJoueurLie().getGuides().size()+" points de Neant.");
	}

}
