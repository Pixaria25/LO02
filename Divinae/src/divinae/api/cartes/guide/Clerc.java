package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Clerc extends GuideSpirituel {
  
  public Clerc (Origine origine, Dogme[] dogme) {
	 	super("Clerc", origine, "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées."
	 			+ " L'Origine des points d'Action est au choix du joueur.", dogme, 2);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Origine origine = Capacite.choisirOrigine();
		Capacite.donnerPointAction(this.getCroyantLie().size(), origine, this.getJoueurLie());
	}
	
	  
}
