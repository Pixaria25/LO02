package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Revolutionnaires extends Croyant {

 
  public Revolutionnaires () {
		super("Révolutionnaires", Origine.Neant, "Imposez le sacrifice d'une carte de Croyants à autant de Divinités"
				+ " que vous le voulez. Chaque Divinité choisit la carte à sacrifier. Les capacités spéciales sont jouées.",
				new Dogme [] {Dogme.Symboles, Dogme.Humain, Dogme.Chaos}, 2, 36);

		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  boolean stop = false;
	  do {
		Capacite.imposerSacrifice("Croyant", this.getJoueurLie().getPartie());
	  	stop = Capacite.getActionSuivante().choixMultiples("Divinité");
	  } while (!stop);
  }
}
