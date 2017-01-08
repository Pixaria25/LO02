package divinae.api.cartes.types;

public abstract class DeusEx extends CarteAction {

	public DeusEx(String nom, Origine origine, String capacite) {
		super(nom, origine, capacite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void poserCarteAction() {
		// TODO Auto-generated method stub
		activerCapacite();
		
	}
}
