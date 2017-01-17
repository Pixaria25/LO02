package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Transe extends DeusEx {

	public Transe() {
		super("Transe", Origine.Aucune, "Permet de r�cup�rer les effets b�n�fiques d'une carte d'Action pos�e par une autre Divinit�. "
				+ "S'il s'agit d'une carte Croyant ou Guide Spirituel, vous posez la carte devant vous.", 72);
		// TODO Auto-generated constructor stub
	}


	public enum ClasseName {
	    Croyant,
	    GuideSpirituel,
	    DeusEx;
	}
	@Override
	public void activerCapacite() {
		Partie partie = this.getJoueurLie().getPartie();
		if (partie.getTable().size() > 0)
		{
			Carte cartePosee = partie.getTable().get(partie.getTable().size()-1);
			Capacite.recupererEffetBenef(cartePosee, this.getJoueurLie(), partie);
		}
	}



}
