package divinae.api.cartes.types;

public abstract class DeusEx extends CarteAction {

	public DeusEx(String nom, Origine origine, String capacite, int id) {
		super(nom, "Deus Ex", origine, capacite, id);
	}

	@Override
	public void poserCarteAction() {

		activerCapacite();
		getJoueurLie().tuerCarte(this);
	}
	
	public String toString() {
		return "Deus Ex"+super.toString();
	}
}
