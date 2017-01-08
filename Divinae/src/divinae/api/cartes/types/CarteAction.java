package divinae.api.cartes.types;

public abstract class CarteAction extends Carte{

	public CarteAction(String nom, Origine origine, String capacite) {
		super(nom, origine, capacite);
		// TODO Auto-generated constructor stub
	}

	abstract public void poserCarteAction();
	
}
