package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Martyr extends GuideSpirituel {

	

	public Martyr(String nom, Origine origine, String capacite, Dogme[] dogme,int nombreCroyantLiable) {
		super(nom, origine, capacite, dogme, nombreCroyantLiable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Capacite.lancerApocalypse(this.getJoueurLie().getPartie());
		this.getJoueurLie().getPartie().setIndexJoueur1(this.getJoueurLie().getPartie().getJoueurs().indexOf(this.getJoueurLie()));
	}

}

