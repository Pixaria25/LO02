package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.Partie;

public class Phoenix extends DeusEx {

	public Phoenix() {
		super("Phoenix", Origine.Neant, "Permet de bénéficier de la capacité spéciale "
				+ "de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Partie partie = this.getJoueurLie().getPartie();
		int choix =  getJoueurLie().gspOuCroyant();
		if (choix == 1) {
			Croyant croyantCapa =  getJoueurLie().choisirCroyant();
			Capacite.copierCapacite(croyantCapa, partie);
		} else {
			GuideSpirituel GpCapa =  getJoueurLie().choisirSonGsp();
			Capacite.copierCapacite(GpCapa, partie);
		}
		
		Capacite.defausser(this, partie);
	}

}
