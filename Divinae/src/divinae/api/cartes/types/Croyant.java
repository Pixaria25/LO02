package divinae.api.cartes.types;

public abstract class Croyant extends Carte implements Action {
	
	private Dogme [] dogme;
	private int nombreCroyant;
	private GuideSpirituel guideLie;
	
	
	public Croyant(String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		// TODO Auto-generated constructor stub
		super(nom, origine, capacite);
	    this.dogme = dogme;
		this.nombreCroyant = nombreCroyant;
		this.guideLie = null;
	}

	@Override
	public void poserCarteAction() {
		// TODO Auto-generated method stub
		getJoueurLie().getPartie().getTasDeCroyants().add(this);
		setJoueurLie(null);
	}
	
	public GuideSpirituel getGuideLie() {
		return guideLie;
	}
	
	public void setGuideLie(GuideSpirituel guideLie) {
		this.guideLie = guideLie;
	}

	public Dogme[] getDogme() {
		return dogme;
	}

	public int getNombreCroyant() {
		return nombreCroyant;
	}
	
	@Override
	public String toString() {
		return "Croyant" + getNom() + "\n " + getOrigine() + "\n " + dogme + "\n " + getCapacite();
	}

}
