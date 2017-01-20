package fr.utt.divinae.api.cartes.deuxex;

import java.util.List;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.cartes.types.Utilitaire;

public class ColereDivine extends DeusEx {

	public ColereDivine(Origine origine, int id) {
		super("Col�re Divine", origine, "", id);
		String capacite = "D�truit une carte Guide Spirituel d'Origine ";
		if(origine == Origine.Jour) {
			capacite += "Nuit";
		} else {
			capacite += "Jour";
		}
		capacite += " ou N�ant, dont la capacit� sp�ciale n'a pas effet. Les Croyants attach�s reviennent au centre de la table.";
		setCapacite(capacite);
	}

	@Override
	public void activerCapacite() {
		List<GuideSpirituel> gspCiblable = Utilitaire.getGspOrigine(Origine.Neant, this, getJoueurLie().getPartie());
		if(getOrigine() == Origine.Jour) {
			gspCiblable.addAll(Utilitaire.getGspOrigine(Origine.Nuit, this, getJoueurLie().getPartie()));
		} else {
			gspCiblable.addAll(Utilitaire.getGspOrigine(Origine.Jour, this, getJoueurLie().getPartie()));
		}
		Capacite.renvoyerCroyantsGsp(gspCiblable, this, getJoueurLie().getPartie());
		
	}

}
