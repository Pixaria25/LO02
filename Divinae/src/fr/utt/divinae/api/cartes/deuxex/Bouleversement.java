package fr.utt.divinae.api.cartes.deuxex;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.Origine;

public class Bouleversement extends DeusEx {

	public Bouleversement() {
		super("Bouleversement", Origine.Aucune, "Relancez le d� de Cosmogonie. "
				+ "Le tour de jeu se terminera normalement, mais sous la nouvelle influence.", 74);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Capacite.relancerDe(this.getJoueurLie().getPartie());

	}

}
