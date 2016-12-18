package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Lycanthropes extends Croyant {

	public Lycanthropes(String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		super(nom, origine, capacite, dogme, nombreCroyant);
		// TODO Auto-generated constructor stub
	}

	 public void activerCapacite() {
		  	Capacite.retirerCroyant(this.getJoueurLie().getPartie());
	  }
}
