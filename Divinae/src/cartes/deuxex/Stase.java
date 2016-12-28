package cartes.deuxex;

import cartes.Capacite;
import cartes.DeusEx;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Stase extends DeusEx {

	public Stase() {
		super("Stase", Origine.Jour, "Protège un Guide Spirituel et ses Croyants jusqu'à ce que cette carte soit annulée ou jusqu'à la prochaine tentative d'Apocalypse.");
		// TODO Auto-generated constructor stub
	}


	@Override
	public void activerCapacite() {
		GuideSpirituel Gp = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		Gp.setProtectionCiblage(true);
		for (int i = 0; i < Gp.getCroyantLie().size(); i++) {
			Gp.getCroyantLie(i).setProtectionCiblage(true);
		}
	}

}
