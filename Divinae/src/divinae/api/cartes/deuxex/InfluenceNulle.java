package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class InfluenceNulle extends DeusEx {

	public InfluenceNulle() {
		super("Influence Nulle", Origine.Aucune, "Annule la capacit� sp�ciale d'une autre carte d'Action.", 70);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Origine [] origineCible = {Origine.Nuit,Origine.Neant,Origine.Jour,Origine.Aube, Origine.Aucune,Origine.Crepuscule};
		CarteAction carteInterupt = Capacite.getCarteInterupt();
	    Capacite.annulerEffetCarte(carteInterupt, origineCible, this.getJoueurLie().getPartie());
	}

}
