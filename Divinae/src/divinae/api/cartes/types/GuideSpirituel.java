package divinae.api.cartes.types;

import divinae.api.partie.Partie;

import java.util.ArrayList;
import java.util.List;

public abstract class GuideSpirituel extends CarteAction {

	private List<Croyant> croyantLie;
	private Dogme [] dogme;
	private int nombreCroyantLiable;
	
	
	public GuideSpirituel (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyantLiable, int id) {
		super(nom, "Guide Spirituel", origine, capacite, id);
		this.croyantLie = new ArrayList<Croyant>();
		this.dogme = dogme;
		this.nombreCroyantLiable = nombreCroyantLiable;
	}
	

	public void convertirCroyant (Partie partie) {
		while (this.nombreCroyantLiable  >  croyantLie.size() || partie.getTasDeCroyants().size() > 0) { 
			Croyant croyant = getJoueurLie().choisirTasCroyant();
			croyant.setGuideLie(this);
			croyant.setJoueurLie(getJoueurLie());
			croyantLie.add(croyant);
			Utilitaire.majPointAction(croyant, +croyant.getValeurCroyant());
		}
	}
	

	public void poserCarteAction() {
			int indexCourant = getJoueurLie().getPartie().getIndexJoueur1();
			setJoueurLie(getJoueurLie().getPartie().getJoueurs().get(indexCourant));
			convertirCroyant(getJoueurLie().getPartie());
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
		return "Guide Spirituel " +super.toString()+ "\n Dogme: " + dogmeToString(dogme) + "\n Nombre de croyants: "+nombreCroyantLiable;
	}
}
