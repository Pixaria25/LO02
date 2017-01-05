package divinae.api.cartes.guide;

import java.util.List;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Exorciste extends GuideSpirituel {
  
  public Exorciste () {
	 	super("Exorciste", Origine.Jour, "Une Divinité d'Origine Nuit ou ayant les Dogmes Mystique et Chaos "
	 			+ "reprend dans sa main l'un de ses Guides Spirituels. Les Croyants qui y étaient attachés sont défaussés.",
	 			new Dogme [] {Dogme.Chaos,Dogme.Mystique}, 1);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		List <GuideSpirituel> gspCiblable = Capacite.choisirDiviniteOrigine(Origine.Nuit, this.getJoueurLie().getPartie());
		gspCiblable.addAll(Capacite.choisirDiviniteEtDogme(Dogme.Mystique, Dogme.Chaos, this.getJoueurLie().getPartie()));
		Capacite.renvoyerGsp(gspCiblable, this.getJoueurLie().getPartie());
	}
	
	  
}
