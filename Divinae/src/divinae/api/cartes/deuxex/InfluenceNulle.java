package divinae.api.cartes.deuxex;

import divinae.api.cartes.deuxex.InfluenceJour.ClasseName;
import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class InfluenceNulle extends DeusEx {

	public InfluenceNulle() {
		super("Influence Nulle", Origine.Aucune, "Annule la capacité spéciale d'une autre carte d'Action.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Carte carte = Capacite.getCarteInterupt ();
		ClasseName z = ClasseName.valueOf(carte.getClass().getSimpleName());
		Origine [] origineCible = {Origine.Nuit,Origine.Neant,Origine.Jour,Origine.Aube, Origine.Aucune,Origine.Crepuscule};
	    switch (z) {
	    case Croyant: 
	    case GuideSpirituel :
	    case DeusEx :
	    case Apocalypse : 
	    	Capacite.annulerEffetCarte(carte.getOrigine(), origineCible, this.getJoueurLie().getPartie());
	    	break;
	    }
	    
	}

}
