package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.Partie;

public class Phoenix extends DeusEx {

	public Phoenix() {
		super("Phoenix", Origine.Neant, "Permet de b�n�ficier de la capacit� sp�ciale "
				+ "de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.", 66);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Partie partie = this.getJoueurLie().getPartie();
		int choix = Capacite.getActionSuivante().gspOuCroyant();
		if (choix == 1) {
			Croyant croyantCapa = Capacite.getActionSuivante().choisirCroyant(this.getJoueurLie(), partie);
			Capacite.copierCapacite(croyantCapa, partie);
		} else {
			GuideSpirituel GpCapa = Capacite.getActionSuivante().choisirSonGsp(this.getJoueurLie(), partie);
			Capacite.copierCapacite(GpCapa, partie);
		}

		Capacite.defausser(this, partie);
	}

}
