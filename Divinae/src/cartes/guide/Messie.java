package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Messie extends GuideSpirituel {

	

	public Messie(String nom, Origine origine, String capacite, Dogme[] dogme,int nombreCroyantLiable) {
		super("Messie", Origine.Jour, "Le joueur pose le d� de Cosmogonie sur la face qu'il d�sire et commence"
				+ " un nouveau tour de jeu.", new Dogme [] {Dogme.Humain,Dogme.Mystique}, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Capacite.choisirFaceDe(this, this.getJoueurLie().getPartie());
	}

}

