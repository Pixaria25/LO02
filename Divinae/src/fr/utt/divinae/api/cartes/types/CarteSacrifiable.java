package fr.utt.divinae.api.cartes.types;

/**
 * La classe CarteSacrifiable modelise les cartes pouvant etre sacrifiees
 * c'est-a-dire les Croyants et les Guides Spirituel.
 * @author Thomas, Abraham
 *
 */
public abstract class CarteSacrifiable extends CarteAction {

	public CarteSacrifiable(String nom, String categorie, Origine origine, String capacite, int id) {
		super(nom, categorie, origine, capacite, id);
	}

	/**
	 * Sacrifie
	 */
	public void sacrifice() {
		activerCapacite();
		mort();
	}

}
