package fr.utt.divinae.api.cartes.guide;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;

public class Messie extends GuideSpirituel {



	public Messie() {
		super("Messie", Origine.Jour, "Le joueur pose le d� de Cosmogonie sur la face qu'il d�sire et commence"
				+ " un nouveau tour de jeu.", new Dogme [] {Dogme.Humain,Dogme.Mystique}, 3, 57);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Capacite.changerFaceDe(getJoueurLie(), getJoueurLie().getPartie());
	}

}

