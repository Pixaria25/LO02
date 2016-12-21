package cartes.guide;

import java.util.List;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Exorciste extends GuideSpirituel {
  
  public Exorciste (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		List <GuideSpirituel> gspCiblable = Capacite.choisirDiviniteOrigine(Origine.Nuit, this.getJoueurLie().getPartie());
		gspCiblable.addAll(Capacite.choisirDiviniteEtDogme(Dogme.Mystique, Dogme.Chaos, this.getJoueurLie().getPartie()));
		Capacite.renvoyerGsp(gspCiblable, this.getJoueurLie().getPartie());
	}
	
	  
}
