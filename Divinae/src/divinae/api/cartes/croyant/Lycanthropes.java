package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;
import divinae.api.cartes.types.Utilitaire;

public class Lycanthropes extends Croyant {

	public Lycanthropes() {
		super("Lycanthropes", Origine.Nuit, "Retirez tous les Croyants attach�s � l'un des Guides Spirituels d'une autre Divinit�. "
				+ "Les Croyants reviennent au milieu de la table, le Guide Spirituel est d�fauss�.",
				new Dogme [] {Dogme.Humain, Dogme.Nature, Dogme.Chaos}, 4, 24);
	}

	 public void activerCapacite() {
		 Utilitaire.retirerTousCroyantLies(this, this.getJoueurLie().getPartie());
	  }
}
