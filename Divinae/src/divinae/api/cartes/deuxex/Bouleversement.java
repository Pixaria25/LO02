package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class Bouleversement extends DeusEx {

	public Bouleversement() {
		super("Bouleversement", Origine.Aucune, "Relancez le dé de Cosmogonie. "
				+ "Le tour de jeu se terminera normalement, mais sous la nouvelle influence.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Capacite.getActionSuivante().relancerDe(this.getJoueurLie().getPartie());

	}

}
