package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;
import cartes.divinite.Divinite;

public class Devin extends GuideSpirituel {
  
  public Devin (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Divinite divinite = Capacite.choisirDiviniteOuDogme(Dogme.Nature, Dogme.Mystique, this.getJoueurLie().getPartie());
		Capacite.imposerSacrifice(divinite,"GuideSpirituel", this.getJoueurLie().getPartie());
	}
	  
}
