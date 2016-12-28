package cartes;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import partie.Partie;

public abstract class GuideSpirituel extends Carte {

	private List<Croyant> croyantLie;
	private Dogme [] dogme;
	private int nombreCroyantLiable;
	
	
	public GuideSpirituel (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyantLiable) {
		super(nom, origine, capacite);
		croyantLie = new ArrayList<Croyant>();
		dogme = this.dogme;
		nombreCroyantLiable = this.nombreCroyantLiable;
		System.out.println("carte" + nom + "crée");
		}
	
	public void convertirCroyant (Partie partie) {
		//A modifier !
		while (this.nombreCroyantLiable  >  croyantLie.size()) { 
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
	
	
	
}
