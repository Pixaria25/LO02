package cartes.divinite;

import cartes.Divinite;
import cartes.Dogme;
import cartes.Origine;

public class Gorpa extends Divinite {

	public Gorpa() {
		super("Gorpa", Origine.Crepuscule, "Peut r�cup�rer les points d'Action d'une autre Divinit� en plus des siens. "
				+ "L'autre Divinit� ne re�oit aucun point d'Action ce tour-ci.", 
				new Dogme[]{Dogme.Humain, Dogme.Symboles, Dogme.Chaos}, 
				"Divinit� joueuse et espi�gle, "
				+ "Gorpa aime g�ner ses cons�urs dans leur recherche de puissance.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
