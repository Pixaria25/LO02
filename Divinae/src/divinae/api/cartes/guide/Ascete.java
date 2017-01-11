package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Ascete extends GuideSpirituel {

  public Ascete () {

	 	super("Ascete", Origine.Nuit, "Sacrifie 2 cartes Croyants d'une Divinitï¿½ ayant le Dogme Humain ou Symboles."
	 			+ " Les capacitï¿½s spï¿½ciales sont jouï¿½es normalement.",
	 			new Dogme [] {Dogme.Humain,Dogme.Symboles}, 1, 52);

		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
    
		Divinite divinite = getJoueurLie().choisirDiviniteOuDogme(Dogme.Humain, Dogme.Symboles);
		

		for (int nb = 0; nb < 2 ; nb++){
			Croyant croyantCible = getJoueurLie().choisirCroyant();
			croyantCible.activerCapacite();
			Capacite.defausser(croyantCible, this.getJoueurLie().getPartie());
		}
	}
}
