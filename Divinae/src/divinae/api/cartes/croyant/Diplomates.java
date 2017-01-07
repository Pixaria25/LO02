package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public  class Diplomates extends Croyant {

	public Diplomates() {
		super("Diplomates", Origine.Jour, "Relancer le dé de Cosmogonie."
				+ "Le tour se finit normalement sous la nouvelle influence.", new Dogme[]{Dogme.Humain,Dogme.Symboles,Dogme.Chaos}, 4);
		// TODO Auto-generated constructor stub
	}

	public void activerCapacite() {
		Capacite.getActionSuivante().relancerDe(this.getJoueurLie().getPartie());
	}
}
