package divinae.api.cartes.types;

public abstract class DeusEx extends CarteAction {

	public DeusEx(String nom, Origine origine, String capacite, int id) {
		super(nom, origine, capacite, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void poserCarteAction() {

		// TODO Auto-generated method stub
		boolean validite = Capacite.retirerPointAction(this, this.getOrigine());
		if(validite) {
			activerCapacite();
			getJoueurLie().tuerCarte(this);
		}
	}
	
	public String toString() {
		return "Deus Ex"+super.toString();
	}
}
