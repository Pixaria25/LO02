package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.De;

public class Inquisition extends DeusEx {

	public Inquisition() {
		super("Inquisition", Origine.Aucune, "Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. "
				+ "Lancez le dé de Cosmogonie. Sur Jour, le Guide adverse est sacrifié, sur Nuit le votre est sacrifié, sur Néant rien ne se passe.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		GuideSpirituel MonGp = Capacite.getActionSuivante().choisirSonGsp(this.getJoueurLie(),this.getJoueurLie().getPartie());
		GuideSpirituel SonGp = Capacite.getActionSuivante().choisirGsp(this.getJoueurLie().getPartie());
		while (SonGp.getJoueurLie()==this.getJoueurLie()){
			SonGp = Capacite.getActionSuivante().choisirGsp(this.getJoueurLie().getPartie());
		}
		
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
