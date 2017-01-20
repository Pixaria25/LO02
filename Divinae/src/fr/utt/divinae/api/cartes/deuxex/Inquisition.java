package fr.utt.divinae.api.cartes.deuxex;

import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.partie.De;

public class Inquisition extends DeusEx {

	public Inquisition() {
		super("Inquisition", Origine.Aucune, "Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. "
				+ "Lancez le d� de Cosmogonie. Sur Jour, le Guide adverse est sacrifi�, sur Nuit le votre est sacrifi�, sur N�ant rien ne se passe"
												 + ".", 75);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		GuideSpirituel monGp =  getJoueurLie().choisirSonGsp();
		GuideSpirituel sonGp =  getJoueurLie().choisirGsp();

		De de = this.getJoueurLie().getPartie().getDe();
		de.lancerDe();
		Origine influence = de.getInfluence();

		switch (influence) {
		case Jour :
			if (sonGp != null) {
				sonGp.getJoueurLie().sacrifierCarte(sonGp);
			}
		break;
		case Nuit :
			if (monGp != null) {
				monGp.getJoueurLie().sacrifierCarte(monGp);
			}
		break;
		default:
		break;
		}
	}
}
