package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

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
		Carte carte = Capacite.getCarteInterupt ();
		ClasseName z = ClasseName.valueOf(carte.getClass().getSimpleName());
		Origine [] origineCible = {Origine.Nuit,Origine.Neant};
	    switch (z) {
	    case Croyant :
	    case GuideSpirituel :
	    case DeusEx :
	    case Apocalypse :
	    	Capacite.annulerEffetCarte(carte.getOrigine(), origineCible, this.getJoueurLie().getPartie());
	    	break;
	    }

	}

}
