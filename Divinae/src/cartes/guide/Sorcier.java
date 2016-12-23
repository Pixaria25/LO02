package cartes.guide;

import cartes.Capacite;
import cartes.Carte;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;
import joueur.Joueur;

public class Sorcier extends GuideSpirituel {

	

	public Sorcier() {
		super("Sorcier", Origine.Nuit, "Echangez l'un de vos Guides Spirituels avec un d'une autre Divinité. "
				+ "Vous choisissez les deus Guides Spirituels en question. Les Croyants restent attachés aux mêmes cartes.",
				new Dogme [] {Dogme.Mystique,Dogme.Symboles}, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Carte carte1 = Capacite.choisirSonGsp(this, this.getJoueurLie().getPartie());
		Carte carte2 = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		Joueur joueur1 = carte1.getJoueurLie();
		carte1.setJoueurLie(carte2.getJoueurLie());
		carte2.setJoueurLie(joueur1);
	}
	
}

