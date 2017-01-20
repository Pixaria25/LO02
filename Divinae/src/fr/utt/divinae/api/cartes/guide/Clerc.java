package fr.utt.divinae.api.cartes.guide;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;

public class Clerc extends GuideSpirituel {
  
  public Clerc (Origine origine, Dogme[] dogme, int id) {
	 	super("Clerc", origine, "Fait gagner un nombre de points d'Action égal au nombre de cartes de Croyants rattachées."
	 			+ " L'Origine des points d'Action est au choix du joueur.", dogme, 2, id);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Origine origine = getJoueurLie().choisirOrigine();

		Capacite.donnerPointAction(this.getCroyantLie().size(), origine, this.getJoueurLie());

	}
}
