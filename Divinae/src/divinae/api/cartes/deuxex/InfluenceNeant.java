package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class InfluenceNeant extends DeusEx {

	public InfluenceNeant() {
		super("Influence Neant", Origine.Neant, "Annule la capacit� sp�ciale d'une carte d'Action d'Origine Jour ou Nuit.", 69);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Origine [] origineCible = {Origine.Nuit,Origine.Jour};
		CarteAction carteInterupt = Capacite.getCarteInterupt();
	    Capacite.annulerEffetCarte(carteInterupt, origineCible, this.getJoueurLie().getPartie());


	}

}
