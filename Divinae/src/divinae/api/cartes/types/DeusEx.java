package divinae.api.cartes.types;

public abstract class DeusEx extends CarteAction {

	public DeusEx(String nom, Origine origine, String capacite, int id) {
		super(nom, origine, capacite, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void poserCarteAction() {
		// TODO Auto-generated method stub
		activerCapacite();
		this.getJoueurLie().getPartie().getDefausse().ajoutCarte(this);

	}
}
