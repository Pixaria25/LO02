package cartes.deuxex;

import cartes.Capacite;
import cartes.DeusEx;
import cartes.Origine;

public class TrouNoir extends DeusEx {

	public TrouNoir(String nom, Origine origine, String capacite) {
		super("Trou Noir", Origine.Neant, "Aucun autre joueur ne gagne de points d'Action durant ce tour.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Capacite.bloquerPointAction(this.getJoueurLie().getPartie());
	}

}
