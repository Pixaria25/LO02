package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Nihillistes extends Croyant {
  
  public Nihillistes (String nom, Origine origine, String capacite, Dogme [] dogme, int nombreCroyant) {
	 	super("Nihillistes", Origine.Neant, "Jusqu'� la fin du tour, plus aucune Divinit� ne re�oit de points d'Action.",
	 			new Dogme [] {Dogme.Symboles, Dogme.Mystique, Dogme.Chaos}, 4);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.bloquerPointAction(this.getJoueurLie().getPartie());
	}
}