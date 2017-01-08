package divinae.api.cartes.types;

import divinae.api.partie.Partie;

import java.util.ArrayList;
import java.util.List;

public abstract class GuideSpirituel extends CarteAction {

	private List<Croyant> croyantLie;
	private Dogme [] dogme;
	private int nombreCroyantLiable;
	
	
	public GuideSpirituel (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyantLiable) {
		super(nom, origine, capacite);
		this.croyantLie = new ArrayList<Croyant>();
		this.dogme = dogme;
		this.nombreCroyantLiable = nombreCroyantLiable;
	}
	

	public void convertirCroyant (Partie partie) {
		while (this.nombreCroyantLiable  >  croyantLie.size() || partie.getTasDeCroyants().size() > 0) { 
			Croyant croyant = Capacite.getActionSuivante().choisirCroyant(getJoueurLie(), getJoueurLie().getPartie());
			croyant.setGuideLie(this);
			croyant.setJoueurLie(getJoueurLie());
			croyantLie.add(croyant);
		}
	}
	
	public void poserCarteAction() {
		boolean validite = Capacite.getActionSuivante().retirerPointAction(this, this.getOrigine());
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
