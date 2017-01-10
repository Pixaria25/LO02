package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class TrouNoir extends DeusEx {

	public TrouNoir(String nom, Origine origine, String capacite) {
		super("Trou Noir", Origine.Neant, "Aucun autre joueur ne gagne de points d'Action durant ce tour.", 65);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Capacite.bloquerPointAction(this.getJoueurLie().getPartie());
	}

}
