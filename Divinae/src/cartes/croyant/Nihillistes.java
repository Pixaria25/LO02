package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Nihillistes extends Croyant {
  
  public Nihillistes (String nom, Origine origine, String capacite, Dogme [] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.bloquerPointAction(this.getJoueurLie().getPartie());
	}
}