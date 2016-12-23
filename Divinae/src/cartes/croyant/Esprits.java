package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Esprits extends Croyant {
  
  public Esprits (String nom, Dogme[] dogme) {
	 	super(nom, Origine.Neant, "Donne un point d'Action d'Origine Néant.", dogme, 2);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.donnerPointAction(1, Origine.Neant, this.getJoueurLie());
	}
}
