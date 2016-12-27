package cartes.croyant;

import cartes.Capacite;
import cartes.Croyant;
import cartes.Dogme;
import cartes.Origine;

public class Nihillistes extends Croyant {
  
  public Nihillistes () {
	 	super("Nihillistes", Origine.Neant, "Jusqu'à la fin du tour, plus aucune Divinité ne reçoit de points d'Action.",
	 			new Dogme [] {Dogme.Symboles, Dogme.Mystique, Dogme.Chaos}, 4);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.bloquerPointAction(this.getJoueurLie().getPartie());
	}
}