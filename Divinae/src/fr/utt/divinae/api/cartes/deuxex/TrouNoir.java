package fr.utt.divinae.api.cartes.deuxex;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.Origine;

public class TrouNoir extends DeusEx {

	public TrouNoir() {
		super("Trou Noir", Origine.Neant, "Aucun autre joueur ne gagne de points d'Action durant ce tour.", 65);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Capacite.bloquerPointAction(this.getJoueurLie().getPartie());
	}

}
