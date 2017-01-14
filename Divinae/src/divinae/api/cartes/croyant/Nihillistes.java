package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Nihillistes extends Croyant {
  
  public Nihillistes () {
	 	super("Nihillistes", Origine.Neant, "Jusqu'� la fin du tour, plus aucune Divinit� ne re�oit de points d'Action.",
	 			new Dogme [] {Dogme.Symboles, Dogme.Mystique, Dogme.Chaos}, 4, 37);
	}
  
  public void activerCapacite() {
	  Capacite.bloquerPointAction(this.getJoueurLie().getPartie());
	}
}