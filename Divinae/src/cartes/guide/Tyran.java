package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Tyran extends GuideSpirituel {
  
  public Tyran (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		
		for(int i = 0; i < this.getJoueurLie().getPartie().getTasDeCroyants().size(); i++) {
			Dogme [] dogme1 = this.getJoueurLie().getPartie().getTasDeCroyants().get(i).getDogme();
			if (Capacite.comparerDogme(dogme1, Dogme.Mystique, this.getJoueurLie().getPartie())) {
				Capacite.defausser(this.getJoueurLie().getPartie().getTasDeCroyants().get(i), this.getJoueurLie().getPartie());
			}
		}
	}
	
	  
}
