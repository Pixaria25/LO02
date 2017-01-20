package fr.utt.divinae.api.cartes.divinite;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public class Drinded extends Divinite {

	public Drinded() {
		super("Drinded", Origine.Jour, "Peut emp�cher le sacrifice d'un des Guides Spirituels de n'importe quel joueur.", 
				new Dogme[]{Dogme.Nature, Dogme.Humain, Dogme.Symboles}, 
				"D�fenseur des hommes, quelles que soient leurs croyances, Drinded prot�ge les chefs religieux", 82);
	}
	
	@Override
	public void activerCapacite() {
		super.activerCapacite();
		Capacite.empecherSacrifice(null, null, "GuideSpirituel", this, getJoueurLie().getPartie());
	}

}
