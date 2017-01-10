package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Messie extends GuideSpirituel {

	

	public Messie() {
		super("Messie", Origine.Jour, "Le joueur pose le dé de Cosmogonie sur la face qu'il désire et commence"
				+ " un nouveau tour de jeu.", new Dogme [] {Dogme.Humain,Dogme.Mystique}, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		getJoueurLie().choisirFaceDe(this);
	}

}

