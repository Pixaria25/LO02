package fr.utt.divinae.api.cartes.guide;

import fr.utt.divinae.api.cartes.types.CarteAction;
import fr.utt.divinae.api.cartes.types.Origine;

public abstract class CarteSacrifiable extends CarteAction {

	public CarteSacrifiable(String nom, String categorie, Origine origine, String capacite, int id) {
		super(nom, categorie, origine, capacite, id);
	}

	public void sacrifice() {
		activerCapacite();
		mort();
	}

}
