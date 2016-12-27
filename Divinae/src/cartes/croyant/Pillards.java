package cartes.croyant;

import cartes.Capacite;
import cartes.Croyant;
import cartes.Dogme;
import cartes.Origine;

public  class Pillards extends Croyant {
  
  public Pillards () {
	 	super("Pillards", Origine.Nuit, "R�cup�rez les points d'Action d'une Divinit� n'ayant pas encore jou� durant "
	 			+ "ce tour. Les points d'Action dardents leur Origine. La Divinit� perd ses points.", 
	 			new Dogme [] {Dogme.Symboles, Dogme.Nature, Dogme.Mystique}, 4);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.recupererPointAction(this, this.getJoueurLie().getPartie());;
	}
}
