package divinae.api.cartes.types;

public abstract class CarteAction extends Carte{
	private boolean capaciteBloqué = false;
	public CarteAction(String nom, Origine origine, String capacite, int id) {
		super(nom, origine, capacite, id);
		// TODO Auto-generated constructor stub
	}

	abstract public void poserCarteAction();
	
	

	public boolean isCapaciteBloqué() {
		return capaciteBloqué;
	}

	public void setCapaciteBloqué(boolean capaciteBloqué) {
		this.capaciteBloqué = capaciteBloqué;
	}
	
}
