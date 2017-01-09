package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Revenant extends Croyant {

	public Revenant() {
		super("Revenant", Origine.Neant, "Lancez le dé de Cosmogonie. Le tour se finit normalement, mais "
				+ "sous cette nouvelle influence.", new Dogme [] {Dogme.Mystique, Dogme.Humain, Dogme.Nature}, 1, 35);
		// TODO Auto-generated constructor stub
	}

	 public void activerCapacite() {
		  	Capacite.relancerDe(this.getJoueurLie().getPartie());
	  }
}
