package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Lycanthropes extends Croyant {

	public Lycanthropes() {
		super("Lycanthropes", Origine.Nuit, "Retirez tous les Croyants attachés à l'un des Guides Spirituels d'une autre Divinité. "
				+ "Les Croyants reviennent au milieu de la table, le Guide Spirituel est défaussé.",
				new Dogme [] {Dogme.Humain, Dogme.Nature, Dogme.Chaos}, 4, 24);
		// TODO Auto-generated constructor stub
	}

	 public void activerCapacite() {
		  	Capacite.retirerTousCroyantLies(this.getJoueurLie().getPartie());
	  }
}
