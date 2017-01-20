package fr.utt.divinae.api.cartes.guide;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;

public class Devin extends GuideSpirituel {

  public Devin () {
	 	super("Devin", Origine.Neant, "Oblige une Divinitï¿½ ayant le Dogme Nature ou Mystique ï¿½ sacrifier"
	 			+ " l'un de ses Guides Spirituels.", new Dogme [] {Dogme.Nature,Dogme.Mystique}, 1, 53);

		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Divinite divinite = getJoueurLie().choisirDiviniteOuDogme(Dogme.Nature, Dogme.Mystique);
		Capacite.imposerSacrificeGuideSpirituel(divinite, getJoueurLie().getPartie());
	}

}
