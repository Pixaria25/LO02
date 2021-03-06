package fr.utt.divinae.api.cartes.deuxex;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.CarteAction;
import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.Origine;

public class InfluenceJour extends DeusEx {

	public InfluenceJour() {
		super("Influence Jour", Origine.Jour, "Annule la capacit� sp�ciale d'une carte d'Action d'Origine Nuit ou N�ant.", 67);
		// TODO Auto-generated constructor stub
	}

	public enum ClasseName {
	    Croyant,
	    GuideSpirituel,
	    DeusEx,
		Apocalypse;
	}

	@Override
	public void activerCapacite() {
		Origine [] origineCible = {Origine.Nuit,Origine.Neant};
		CarteAction carteInterupt = Capacite.getCarteInterupt();
	    Capacite.annulerEffetCarte(carteInterupt, origineCible, this.getJoueurLie().getPartie());
	    }

}


