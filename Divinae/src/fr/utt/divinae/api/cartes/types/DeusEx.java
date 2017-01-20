package fr.utt.divinae.api.cartes.types;

/**
 * La classe Apocalypse modélise les cartes Deus Ex
 * @author Thomas, Abraham
 *
 */
public abstract class DeusEx extends CarteAction {

	public DeusEx(String nom, Origine origine, String capacite, int id) {
		super(nom, "Deus Ex", origine, capacite, id);
	}

	@Override
	public void poserCarteAction() {
		if (isCapaciteBloque()) {
			System.out.println(getNom() + " a été bloqué !");
			
		} else {
			getJoueurLie().messageRecap(getJoueurLie().getNom() + " joue " + getNom());
			activerCapacite();
			getJoueurLie().getMain().remove(this);
			getJoueurLie().tuerCarte(this);
		}
	}
	
	public String toString() {
		return "Deus Ex"+super.toString();
	}
}
