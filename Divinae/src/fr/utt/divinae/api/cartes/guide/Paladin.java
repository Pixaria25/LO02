package fr.utt.divinae.api.cartes.guide;

import java.util.List;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.cartes.types.Utilitaire;

public class Paladin extends GuideSpirituel {

  public Paladin () {
	 	super("Paladin", Origine.Jour, "Tous les Croyants, d'Origine Nuit ou Nï¿½ant et ayant le Dogme Nature, actuellement"
	 			+ " sur la table sont dï¿½faussï¿½s. Les capacitï¿½s spï¿½ciales ne sont pas jouï¿½es.",
	 			new Dogme [] {Dogme.Humain,Dogme.Symboles}, 3, 51);

	}


	public void activerCapacite() {
		List<Croyant> croyantASuppr = Utilitaire.trierCroyantDogme(Origine.Nuit, Origine.Neant, Dogme.Nature, getJoueurLie().getPartie());
		
		for(int i=0; i < croyantASuppr.size(); i++){
			Capacite.defausser(croyantASuppr.get(i), getJoueurLie().getPartie());
		}
	}


}
