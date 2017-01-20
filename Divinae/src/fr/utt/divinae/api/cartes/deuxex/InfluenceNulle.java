package fr.utt.divinae.api.cartes.deuxex;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.CarteAction;
import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.Origine;

public class InfluenceNulle extends DeusEx {

	public InfluenceNulle(int id) {
		super("Influence Nulle", Origine.Aucune, "Annule la capacit� sp�ciale d'une autre carte d'Action.", id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Origine [] origineCible = {Origine.Nuit,Origine.Neant,Origine.Jour,Origine.Aube, Origine.Aucune,Origine.Crepuscule};
		CarteAction carteInterupt = Capacite.getCarteInterupt();
		if (carteInterupt != null) {
			Capacite.annulerEffetCarte(carteInterupt, origineCible, this.getJoueurLie().getPartie());
		}
	}

}
