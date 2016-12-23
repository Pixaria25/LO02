package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public  class Diplomates extends Croyant {

	public Diplomates() {
		super("Diplomates", Origine.Jour, "Relancer le dé de Cosmogonie."
				+ "Le tour se finit normalement sous la nouvelle influence.", new Dogme[]{Dogme.Humain,Dogme.Symboles,Dogme.Chaos}, 4);
		// TODO Auto-generated constructor stub
	}

	public void activerCapacite() {
		Capacite.relancerDe(this.getJoueurLie().getPartie());;
	}
}
