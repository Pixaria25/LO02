package fr.utt.divinae.api.cartes.guide;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.cartes.types.Utilitaire;

public class Tyran extends GuideSpirituel {

  public Tyran () {
	 	super("Tyran", Origine.Neant, "D�fausse tous les Croyants ayant le Dogme Mystique actuellement"
	 			+ " au centre de la table.", new Dogme [] {Dogme.Chaos,Dogme.Symboles}, 3, 56);
	}


	public void activerCapacite() {

		for(int i = 0; i < getJoueurLie().getPartie().getTasDeCroyants().size(); i++) {
			Dogme [] dogme1 = getJoueurLie().getPartie().getTasDeCroyants().get(i).getDogme();
			
			if (Utilitaire.comparerDogme(dogme1, Dogme.Mystique, getJoueurLie().getPartie())) {
				Capacite.defausser(getJoueurLie().getPartie().getTasDeCroyants().get(i), getJoueurLie().getPartie());
			}
		}
	}


}
