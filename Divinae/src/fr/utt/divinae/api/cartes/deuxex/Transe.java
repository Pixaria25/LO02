package fr.utt.divinae.api.cartes.deuxex;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Carte;
import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.partie.Partie;

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
