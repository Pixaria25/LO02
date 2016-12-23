package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Lycanthropes extends Croyant {

	public Lycanthropes(String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		super("Lycanthropes", Origine.Nuit, "Retirez tous les Croyants attachés à l'un des Guides Spirituels d'une autre Divinité. "
				+ "Les Croyants reviennent au milieu de la table, le Guide Spirituel est défaussé.",
				new Dogme [] {Dogme.Humain, Dogme.Nature, Dogme.Chaos}, 4);
		// TODO Auto-generated constructor stub
	}

	 public void activerCapacite() {
		  	Capacite.retirerCroyant(this.getJoueurLie().getPartie());
	  }
}
