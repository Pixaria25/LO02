package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Ascete extends GuideSpirituel {

  public Ascete () {
	 	super("Ascete", Origine.Nuit, "Sacrifie 2 cartes Croyants d'une Divinit� ayant le Dogme Humain ou Symboles."
	 			+ " Les capacit�s sp�ciales sont jou�es normalement.",
	 			new Dogme [] {Dogme.Humain,Dogme.Symboles}, 1, 52);

		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Divinite divinite = Capacite.getActionSuivante().choisirDiviniteOuDogme(Dogme.Humain, Dogme.Symboles, this.getJoueurLie().getPartie());

		for (int nb = 0; nb < 2 ; nb++){
			Croyant croyantCible = Capacite.getActionSuivante().choisirCroyant(divinite.getJoueurLie(), this.getJoueurLie().getPartie());
			croyantCible.activerCapacite();
			Capacite.defausser(croyantCible, this.getJoueurLie().getPartie());
		}
	}
}
