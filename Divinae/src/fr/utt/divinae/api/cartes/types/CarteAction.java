package fr.utt.divinae.api.cartes.types;

/**
 * La classe CarteAction represente les cartes Actions, c'est-a-dire les cartes qui peuvent etre posees durant une partie.
 * @author Thomas, Abraham
 *
 */
public abstract class CarteAction extends Carte{
	private boolean capaciteBloqué = false;

  public CarteAction(String nom, String categorie, Origine origine, String capacite, int id) {
		super(nom, categorie, origine, capacite, id);
	}

	abstract public void poserCarteAction();
	
	public void mort() {
		setJoueurLie(null);
		getJoueurLie().getPartie().getDefausse().ajoutCarte(this);
	}

	public boolean isCapaciteBloque() {
		return capaciteBloqué;
	}

	public void setCapaciteBloqué(boolean capaciteBloqué) {
		this.capaciteBloqué = capaciteBloqué;
	}
	
}
