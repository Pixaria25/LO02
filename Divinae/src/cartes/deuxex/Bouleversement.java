package cartes.deuxex;

import cartes.Capacite;
import cartes.DeusEx;
import cartes.Origine;

public class Bouleversement extends DeusEx {

	public Bouleversement() {
		super("Bouleversement", Origine.Aucune, "Relancez le dé de Cosmogonie. "
				+ "Le tour de jeu se terminera normalement, mais sous la nouvelle influence.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Capacite.relancerDe(this.getJoueurLie().getPartie());

	}

}
