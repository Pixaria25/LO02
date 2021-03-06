package fr.utt.divinae.api.cartes.croyant;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public  class Diplomates extends Croyant {

	public Diplomates() {
		super("Diplomates", Origine.Jour, "Relancer le d� de Cosmogonie."
				+ "Le tour se finit normalement sous la nouvelle influence.", new Dogme[]{Dogme.Humain,Dogme.Symboles,Dogme.Chaos}, 4, 13);
	}

	public void activerCapacite() {
		Capacite.relancerDe(this.getJoueurLie().getPartie());
	}
}
