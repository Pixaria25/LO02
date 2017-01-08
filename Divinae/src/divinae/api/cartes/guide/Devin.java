package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Devin extends GuideSpirituel {
  
  public Devin () {
	 	super("Devin", Origine.Neant, "Oblige une Divinité ayant le Dogme Nature ou Mystique à sacrifier"
	 			+ " l'un de ses Guides Spirituels.", new Dogme [] {Dogme.Nature,Dogme.Mystique}, 1);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Divinite divinite = Capacite.getActionSuivante().choisirDiviniteOuDogme(Dogme.Nature, Dogme.Mystique, this.getJoueurLie().getPartie());
		Capacite.imposerSacrifice(divinite,"GuideSpirituel", this.getJoueurLie().getPartie());
	}
	  
}
