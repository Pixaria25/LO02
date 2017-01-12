package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.De;

public class Inquisition extends DeusEx {

	public Inquisition() {
		super("Inquisition", Origine.Aucune, "Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. "
				+ "Lancez le d� de Cosmogonie. Sur Jour, le Guide adverse est sacrifi�, sur Nuit le votre est sacrifi�, sur N�ant rien ne se passe"
												 + ".", 75);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		GuideSpirituel MonGp =  getJoueurLie().choisirSonGsp();
		GuideSpirituel SonGp =  getJoueurLie().choisirGsp();

		De de = this.getJoueurLie().getPartie().getDe();
		de.lancerDe();
		Origine influence = de.getInfluence();

		switch (influence) {
		case Jour : SonGp.getJoueurLie().sacrifierCarte(SonGp);
		break;
		case Nuit : MonGp.getJoueurLie().sacrifierCarte(MonGp);
		break;
		default:
		break;
		}
	}
}
