package divinae.api.cartes.types;

import java.util.Scanner;

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
		//A modifier !
		while (this.nombreCroyantLiable  >  croyantLie.size() || partie.getTasDeCroyants().size() > 0) { 
			System.out.println("Veuillez choisir une carte à prendre du tas (écrire le nom)");
			Scanner sc = new Scanner(System.in);
			String nom = sc.next();
			int i = 0;
			do { 
				i++;
			} while (!(((partie.getTasDeCroyants(i).getNom())) == nom));
			
			croyantLie.add(partie.getTasDeCroyants(i));
			sc.close();
		}
	}
	
	public void poserCarteAction() {
		getJoueurLie().getPartie();
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
