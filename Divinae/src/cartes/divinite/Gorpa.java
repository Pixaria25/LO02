package cartes.divinite;

import cartes.Divinite;
import cartes.Dogme;
import cartes.Origine;

public class Gorpa extends Divinite {

	public Gorpa() {
		super("Gorpa", Origine.Crepuscule, "Peut récupérer les points d'Action d'une autre Divinité en plus des siens. "
				+ "L'autre Divinité ne reçoit aucun point d'Action ce tour-ci.", 
				new Dogme[]{Dogme.Humain, Dogme.Symboles, Dogme.Chaos}, 
				"Divinité joueuse et espiègle, "
				+ "Gorpa aime gêner ses consœurs dans leur recherche de puissance.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
