package divinae.api.cartes.types;

public abstract class CarteAction extends Carte{

	public CarteAction(String nom, Origine origine, String capacite, int id) {
		super(nom, origine, capacite, id);
		// TODO Auto-generated constructor stub
	}

	abstract public void poserCarteAction();
	
}
