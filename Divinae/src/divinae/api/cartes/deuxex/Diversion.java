package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class Diversion extends DeusEx {

	public Diversion() {
		super("Diversion", Origine.Nuit, "Prenez 3 cartes dans la main d'un autre joueur "
				+ "et incluez-les à votre main");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Capacite.prendreCartes(this, 3, this.getJoueurLie().getPartie());		
	}

}
