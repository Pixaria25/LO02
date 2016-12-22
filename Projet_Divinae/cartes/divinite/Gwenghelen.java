package cartes.divinite;

import cartes.Dogme;
import cartes.Origine;

public class Gwenghelen extends Divinite {

	public Gwenghelen() {
		super("Gwenghelen", Origine.Aube, "Récupère autant de points d'Action supplémentaires "
				+ "d'Origine Néant que le nombre de Guides Spirituels que la Divinité possède.", 
				new Dogme[]{Dogme.Humain, Dogme.Mystique, Dogme.Symboles}, 
				"Première Divinité à recevoir l'influence du Néant, "
				+ "Gwenghelen est celle qui en a reçu le plus de puissance.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		super.activerCapacite();
		getJoueurLie().ajoutPointsAction(getJoueurLie().getGuides().size(), Origine.Neant);
		System.out.println(getJoueurLie().getNom()+" recoit "+getJoueurLie().getGuides().size()+" points de Neant.");
	}

}
