package fr.utt.divinae.api.cartes.guide;

import fr.utt.divinae.api.cartes.types.Carte;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.joueur.Joueur;

public class Sorcier extends GuideSpirituel {



	public Sorcier() {
		super("Sorcier", Origine.Nuit, "Echangez l'un de vos Guides Spirituels avec un d'une autre Divinit�. "
				+ "Vous choisissez les deus Guides Spirituels en question. Les Croyants restent attach�s aux m�mes cartes.",
				new Dogme [] {Dogme.Mystique,Dogme.Symboles}, 3, 55);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Carte carte1 = getJoueurLie().choisirSonGsp();
		Carte carte2 = getJoueurLie().choisirGsp();
		Joueur joueur1 = carte1.getJoueurLie();
		carte1.setJoueurLie(carte2.getJoueurLie());
		carte2.setJoueurLie(joueur1);
	}

}

