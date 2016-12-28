package cartes;

public abstract class Croyant extends Carte {
	
	private Dogme [] dogme;
	private int nombreCroyant;
	private GuideSpirituel guideLie;
	
	
	public Croyant(String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		// TODO Auto-generated constructor stub
		super(nom, origine, capacite);
	    this.dogme = dogme;
		nombreCroyant = this.nombreCroyant;
		System.out.println("carte" + nom + "créée");
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

	
}
