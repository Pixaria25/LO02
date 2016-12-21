package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Messie extends GuideSpirituel {

	

	public Messie(String nom, Origine origine, String capacite, Dogme[] dogme,int nombreCroyantLiable) {
		super(nom, origine, capacite, dogme, nombreCroyantLiable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Capacite.choisirFaceDe(this, this.getJoueurLie().getPartie());
	}

}

