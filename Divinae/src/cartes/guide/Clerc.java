package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Clerc extends GuideSpirituel {
  
  public Clerc (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Origine origine = Capacite.choisirOrigine();
		Capacite.donnerPointAction(this.getCroyantLie().size(), origine, this.getJoueurLie());
	}
	
	  
}
