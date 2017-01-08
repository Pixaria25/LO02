package divinae.api.cartes.deuxex;

import divinae.api.cartes.deuxex.InfluenceJour.ClasseName;
import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class InfluenceNeant extends DeusEx {

	public InfluenceNeant() {
		super("Influence Neant", Origine.Neant, "Annule la capacité spéciale d'une carte d'Action d'Origine Jour ou Nuit.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Carte carte = Capacite.getCarteInterupt ();
		ClasseName z = ClasseName.valueOf(carte.getClass().getSimpleName());
		Origine [] origineCible = {Origine.Nuit,Origine.Jour};
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
