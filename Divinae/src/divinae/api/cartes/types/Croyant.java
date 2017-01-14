package divinae.api.cartes.types;

public abstract class Croyant extends  CarteAction {
	
	private Dogme [] dogme;
	private int valeurCroyant;
	private GuideSpirituel guideLie;
	private boolean rattachable;
	
	public Croyant(String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant, int id) {
		super(nom, "Croyant", origine, capacite, id);
	    this.dogme = dogme;
		this.valeurCroyant = nombreCroyant;
		this.guideLie = null;
		this.rattachable = false;
	}

	@Override
	public void poserCarteAction() {
		Capacite.getActionSuivante().messageRecap(getJoueurLie().getNom() + " joue " + getNom());
		getJoueurLie().getMain().remove(this);
		getJoueurLie().getPartie().ajoutTasDCroyants(this);
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

	public int getValeurCroyant() {
		return valeurCroyant;
	}
	
	public boolean isRattachable() {
		return rattachable;
	}

	public void setRattachable(boolean rattachable) {
		this.rattachable = rattachable;
	}

	@Override
	public String toString() {
		return "Croyant " +super.toString()+ "\n Dogme: " + dogmeToString(dogme) + "\n Prieres: "+valeurCroyant;
	}
	
}
