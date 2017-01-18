package fr.utt.divinae.api.cartes.deuxex;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.partie.Partie;

public class Phoenix extends DeusEx {

	public Phoenix() {
		super("Phoenix", Origine.Neant, "Permet de b�n�ficier de la capacit� sp�ciale "
				+ "de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.", 66);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Partie partie = getJoueurLie().getPartie();
		int choix =  getJoueurLie().gspOuCroyant();
		if (choix == 1) {
			Croyant croyantCapa =  getJoueurLie().choisirCroyant(getJoueurLie());
			if (croyantCapa != null) {
				Capacite.copierCapacite(getJoueurLie(), croyantCapa, partie);
			}
		} else {
			GuideSpirituel gpCapa =  getJoueurLie().choisirSonGsp();
			if (gpCapa != null) {
				Capacite.copierCapacite(getJoueurLie(), gpCapa, partie);
			}
		}

		Capacite.defausser(this, partie);
	}

}
