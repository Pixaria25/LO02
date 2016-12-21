package joueur;

public interface Strategie {

	public abstract void poserCarteAction();
	
	public abstract void activerCapacite();
	
	public abstract void defausser();
	
	public abstract void finirTour();
}
