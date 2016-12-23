package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;
import cartes.divinite.Divinite;

public class Devin extends GuideSpirituel {
  
  public Devin (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super("Devin", Origine.Neant, "Oblige une Divinité ayant le Dogme Nature ou Mystique à sacrifier"
	 			+ " l'un de ses Guides Spirituels.", new Dogme [] {Dogme.Nature,Dogme.Mystique}, 1);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Divinite divinite = Capacite.choisirDiviniteOuDogme(Dogme.Nature, Dogme.Mystique, this.getJoueurLie().getPartie());
		Capacite.imposerSacrifice(divinite,"GuideSpirituel", this.getJoueurLie().getPartie());
	}
	  
}
