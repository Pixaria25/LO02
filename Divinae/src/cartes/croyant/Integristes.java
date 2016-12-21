package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Integristes extends Croyant {
	
  public Integristes (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		super(nom, origine, capacite, dogme, nombreCroyant);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("Croyant", this.getJoueurLie().getPartie());
  }
}
