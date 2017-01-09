package divinae.api.cartes.types;

import java.util.ArrayList;
import java.util.List;

import divinae.console.InterfacePartie;

public abstract class GuideSpirituel extends Carte implements Action {

	private List<Croyant> croyantLie;
	private Dogme [] dogme;
	private int nombreCroyantLiable;
	
	
	public GuideSpirituel (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyantLiable) {
		super(nom, origine, capacite);
		this.croyantLie = new ArrayList<Croyant>();
		this.dogme = dogme;
		this.nombreCroyantLiable = nombreCroyantLiable;
	}
	

	public void poserCarteAction() {
		boolean validite = Capacite.retirerPointAction(this, this.getOrigine());
		if (validite == true) {	
			int indexCourant = InterfacePartie.getPartie().getIndexJoueur1();
			setJoueurLie(InterfacePartie.getPartie().getJoueurs().get(indexCourant));
			Capacite.getActionSuivante().convertirCroyant(InterfacePartie.getPartie(), this);
		}
	}

	public List<Croyant> getCroyantLie() {
		return croyantLie;
	}
	
	public Croyant getCroyantLie(int i) {
		return croyantLie.get(i);
	}

	public Dogme[] getDogme() {
		return dogme;
	}

	public int getNombreCroyantLiable() {
		return nombreCroyantLiable;
	}
	
	
	@Override
	public String toString() {
		return "Guide Spirituel " + getNom() + "\n " + getOrigine() + "\n " + dogme + "\n " + getCapacite();
	}
}
