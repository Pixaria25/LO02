package cartes.croyant;

import cartes.Capacite;
import cartes.Croyant;
import cartes.Dogme;
import cartes.Origine;

public class Revenant extends Croyant {

	public Revenant() {
		super("Revenant", Origine.Neant, "Lancez le dé de Cosmogonie. Le tour se finit normalement, mais "
				+ "sous cette nouvelle influence.", new Dogme [] {Dogme.Mystique, Dogme.Humain, Dogme.Nature}, 1);
		// TODO Auto-generated constructor stub
	}

	 public void activerCapacite() {
		  	Capacite.relancerDe(this.getJoueurLie().getPartie());
	  }
}
