package fr.utt.divinae.api.cartes.types;

public abstract class DeusEx extends CarteAction {

	public DeusEx(String nom, Origine origine, String capacite, int id) {
		super(nom, "Deus Ex", origine, capacite, id);
	}

	@Override
	public void poserCarteAction() {
		if (isCapaciteBloque()) {
			System.out.println(getNom() + " a été bloqué !");
			
		} else {
			Capacite.getActionSuivante().messageRecap(getJoueurLie().getNom() + " joue " + getNom());
			activerCapacite();
			getJoueurLie().getMain().remove(this);
			getJoueurLie().tuerCarte(this);
		}
	}
	
	public String toString() {
		return "Deus Ex"+super.toString();
	}
}
