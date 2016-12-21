package cartes.guide;

import java.util.List;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;
import cartes.croyant.Croyant;

public class Paladin extends GuideSpirituel {
  
  public Paladin (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		List<Croyant> croyantASuppr = Capacite.trierCroyantDogme(Origine.Nuit, Origine.Neant, Dogme.Nature, this.getJoueurLie().getPartie());
		for(int i=0; i < croyantASuppr.size(); i++){
			Capacite.defausser(croyantASuppr.get(i), this.getJoueurLie().getPartie());
		}
	}
	
	  
}
