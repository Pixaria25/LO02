package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Vampire extends Croyant {

 
  public Vampire (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		super(nom, Origine.Nuit, "Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le "
				+ "sacrifié. La capacité spéciale du sacrifice est jouée.", dogme, 1);

		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("Croyant", this.getJoueurLie().getPartie());
  } 
}
