package fr.utt.divinae.api.cartes.divinite;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public class Shingua extends Divinite {

	public Shingua() {
		super("Shingua", Origine.Aube, "Récupère autant de points d'Action supplémentaires d'Origine Néant "
				+ "que le nombre de Guides Spirituels que la Divinité possède.", 
				new Dogme[]{Dogme.Humain, Dogme.Mystique, Dogme.Chaos}, 
				"Perverse et retorse, Shingva est une Divinité que toutes les autres détestent.", 88);
	}

	@Override
	public void activerCapacite() {
		Capacite.donnerPointAction(this.getJoueurLie().getGuides().size(), Origine.Neant, getJoueurLie());	

	}

}
