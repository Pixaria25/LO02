package divinae.api.cartes.guide;

import java.util.List;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Paladin extends GuideSpirituel {

  public Paladin () {
	 	super("Paladin", Origine.Jour, "Tous les Croyants, d'Origine Nuit ou N�ant et ayant le Dogme Nature, actuellement"
	 			+ " sur la table sont d�fauss�s. Les capacit�s sp�ciales ne sont pas jou�es.",
	 			new Dogme [] {Dogme.Humain,Dogme.Symboles}, 3, 51);

		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		List<Croyant> croyantASuppr = Capacite.trierCroyantDogme(Origine.Nuit, Origine.Neant, Dogme.Nature, this.getJoueurLie().getPartie());
		for(int i=0; i < croyantASuppr.size(); i++){
			Capacite.defausser(croyantASuppr.get(i), this.getJoueurLie().getPartie());
		}
	}


}
