package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Anarchiste extends GuideSpirituel {
  
  public Anarchiste (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		GuideSpirituel Gsp = Capacite.choisirDiviniteOuGspNonDogme(Dogme.Chaos, this.getJoueurLie().getPartie());
		Gsp.activerCapacite();
		Capacite.defausser(Gsp, this.getJoueurLie().getPartie());
	}
	
	  
}
