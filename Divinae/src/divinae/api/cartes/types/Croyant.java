package divinae.api.cartes.types;

import divinae.console.InterfacePartie;

public abstract class Croyant extends Carte implements Action {
	
	private Dogme [] dogme;
	private int valeurCroyant;
	private GuideSpirituel guideLie;
	
	
	public Croyant(String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		// TODO Auto-generated constructor stub
		super(nom, origine, capacite);
	    this.dogme = dogme;
		this.valeurCroyant = nombreCroyant;
		this.guideLie = null;
	}

	public void poserCarteAction() {
		// TODO Auto-generated method stub
		boolean validite = Capacite.retirerPointAction(this, this.getOrigine());
		if (validite == true) {	
			InterfacePartie.getPartie().getTasDeCroyants().add(this);
			setJoueurLie(null);
		}
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
	
	@Override
	public String toString() {
		return "Croyant" + getNom() + "\n " + getOrigine() + "\n " + dogme + "\n " + getCapacite();
	}

}
