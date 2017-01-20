package fr.utt.divinae.api.cartes.divinite;

import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.partie.Partie;

public class PuiTara extends Divinite {

	public PuiTara() {
		super("Pui-Tara", Origine.Nuit, "Peut d�truire toutes les cartes de Croyants "
				+ "au centre de la table d'Origine Jour.",
				new Dogme[]{Dogme.Nature, Dogme.Mystique, Dogme.Symboles}, 
				"Pui-Tara est la Divinit� "
				+ "sur laquelle l'influence de la Nuit s'est faite la plus forte.", 86);
	}

	@Override
	public void activerCapacite() {
		super.activerCapacite();
		Partie partie = this.getJoueurLie().getPartie();
		for (int i=0; i < partie.getTasDeCroyants().size(); i++) {
			if (partie.getTasDeCroyants(i).getOrigine() == Origine.Jour
				&& partie.getTasDeCroyants(i).getJoueurLie()!= null) {
				partie.getTasDeCroyants(i).getJoueurLie().tuerCarte(partie.getTasDeCroyants(i));
			}
		}
		
	}

}
