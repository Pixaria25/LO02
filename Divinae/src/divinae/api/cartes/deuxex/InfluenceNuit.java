package divinae.api.cartes.deuxex;

import divinae.api.cartes.deuxex.InfluenceJour.ClasseName;
import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class InfluenceNuit extends DeusEx {

	public InfluenceNuit(String nom, Origine origine, String capacite) {
		super("Influence Nuit", Origine.Nuit, "Annule la capacité spéciale d'une carte d'Action d'Origine Jour ou Néant.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Carte carte = Capacite.getCarteInterupt ();
		ClasseName z = ClasseName.valueOf(carte.getClass().getSimpleName());
		Origine [] origineCible = {Origine.Jour,Origine.Neant};
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
