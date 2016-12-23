package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Clerc extends GuideSpirituel {
  
  public Clerc (String nom, Origine origine, Dogme[] dogme) {
	 	super(nom, origine, "Fait gagner un nombre de points d'Action �gal au nombre de cartes de Croyants rattach�es."
	 			+ " L'Origine des points d'Action est au choix du joueur.", dogme, 2);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Origine origine = Capacite.choisirOrigine();
		Capacite.donnerPointAction(this.getCroyantLie().size(), origine, this.getJoueurLie());
	}
	
	  
}
