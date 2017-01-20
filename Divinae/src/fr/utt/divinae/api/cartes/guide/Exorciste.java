package fr.utt.divinae.api.cartes.guide;

import java.util.List;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.cartes.types.Utilitaire;

public class Exorciste extends GuideSpirituel {

  public Exorciste () {
	 	super("Exorciste", Origine.Jour, "Une Divinitï¿½ d'Origine Nuit ou ayant les Dogmes Mystique et Chaos "
	 			+ "reprend dans sa main l'un de ses Guides Spirituels. Les Croyants qui y ï¿½taient attachï¿½s sont dï¿½faussï¿½s.",
	 			new Dogme [] {Dogme.Chaos,Dogme.Mystique}, 1, 54);

		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		List <GuideSpirituel> gspCiblable = Utilitaire.choisirGuideLieADiviniteOrigine(Origine.Nuit, this.getJoueurLie().getPartie());
		gspCiblable.addAll(Utilitaire.choisirDiviniteEtDogme(Dogme.Mystique, Dogme.Chaos, this.getJoueurLie().getPartie()));
		Capacite.renvoyerCroyantsGsp(gspCiblable, this, this.getJoueurLie().getPartie());
	}


}
