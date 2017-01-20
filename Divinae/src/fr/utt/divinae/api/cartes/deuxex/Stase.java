package fr.utt.divinae.api.cartes.deuxex;

import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;

public class Stase extends DeusEx {

	public Stase() {
		super("Stase", Origine.Jour, "Prot�ge un Guide Spirituel et ses Croyants jusqu'� ce que cette carte soit annul�e ou jusqu'� la prochaine "
										 + "tentative d'Apocalypse.", 60);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void activerCapacite() {
		GuideSpirituel Gp = getJoueurLie().choisirGsp();
		Gp.setProtectionCiblage(true);
		for (int i = 0; i < Gp.getCroyantLie().size(); i++) {
			Gp.getCroyantLie(i).setProtectionCiblage(true);
		}
	}

}
