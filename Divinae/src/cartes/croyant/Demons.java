package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public  class Demons extends Croyant {
  
  public Demons (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super(nom, Origine.Nuit, "Donne un point d'Action d'Origine Nuit.", dogme, 2);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.donnerPointAction(1, Origine.Nuit, this.getJoueurLie());
	}
}
