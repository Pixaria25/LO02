package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Shaman extends GuideSpirituel {

  public Shaman () {
	 	super("Shaman", Origine.Nuit, "Sacrifie tous les Croyants d'Origine N�ant d'une Divinit� ayant le Dogme Humain."
	 			+ "Les capacit�s sp�ciales sont jou�es normalement.", new Dogme [] {Dogme.Nature,Dogme.Symboles}, 3, 49);
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Divinite divinite = Capacite.getActionSuivante().choisirDiviniteOuDogme(Dogme.Humain, Dogme.Humain, this.getJoueurLie().getPartie());
		for (int balayageGuide = 0; balayageGuide < divinite.getJoueurLie().getGuides().size(); balayageGuide++) {
			for (int balayageCroyant = 0; balayageCroyant < divinite.getJoueurLie().getGuide(balayageGuide).getCroyantLie().size(); balayageCroyant++){
				if (divinite.getJoueurLie().getGuide(balayageGuide).getCroyantLie(balayageCroyant).getOrigine() == Origine.Neant){
					divinite.getJoueurLie().getGuide(balayageGuide).getCroyantLie(balayageCroyant).activerCapacite();
					Capacite.defausser(divinite.getJoueurLie().getGuide(balayageGuide).getCroyantLie(balayageCroyant), this.getJoueurLie().getPartie());
				}
			}
		}
	}


}
