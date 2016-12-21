package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;
import cartes.croyant.Croyant;

public  class Pillards extends Croyant {
  
  public Pillards (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.recupererPointAction(this, this.getJoueurLie().getPartie());;
	}
}
