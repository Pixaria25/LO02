package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Tyran extends GuideSpirituel {

  public Tyran () {
	 	super("Tyran", Origine.Neant, "D�fausse tous les Croyants ayant le Dogme Mystique actuellement"
	 			+ " au centre de la table.", new Dogme [] {Dogme.Chaos,Dogme.Symboles}, 3, 56);
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {

		for(int i = 0; i < this.getJoueurLie().getPartie().getTasDeCroyants().size(); i++) {
			Dogme [] dogme1 = this.getJoueurLie().getPartie().getTasDeCroyants().get(i).getDogme();
			if (Capacite.comparerDogme(dogme1, Dogme.Mystique, this.getJoueurLie().getPartie())) {
				Capacite.defausser(this.getJoueurLie().getPartie().getTasDeCroyants().get(i), this.getJoueurLie().getPartie());
			}
		}
	}


}
