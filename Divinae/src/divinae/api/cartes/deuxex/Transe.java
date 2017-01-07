package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Transe extends DeusEx {

	public Transe() {
		super("Transe", Origine.Aucune, "Permet de récupérer les effets bénéfiques d'une carte d'Action posée par une autre Divinité. "
				+ "S'il s'agit d'une carte Croyant ou Guide Spirituel, vous posez la carte devant vous.");
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
		Carte cartePosee = partie.getTable(partie.getTable().size()-1);
		Joueur joueur = this.getJoueurLie();
		Capacite.getActionSuivante().recupererEffetBenef(cartePosee, joueur, partie);
	}



}
