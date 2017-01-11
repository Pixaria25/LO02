package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class InfluenceNuit extends DeusEx {

	public InfluenceNuit(String nom, Origine origine, String capacite) {
		super("Influence Nuit", Origine.Nuit, "Annule la capacit� sp�ciale d'une carte d'Action d'Origine Jour ou N�ant.", 68);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Origine [] origineCible = {Origine.Jour,Origine.Neant};
		CarteAction carteInterupt = Capacite.getCarteInterupt();
	    Capacite.annulerEffetCarte(carteInterupt, origineCible, this.getJoueurLie().getPartie());
	}

}
