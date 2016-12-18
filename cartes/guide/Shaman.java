package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;
import cartes.divinite.Divinite;

public class Shaman extends GuideSpirituel {
  
  public Shaman (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Divinite divinite = Capacite.choisirDiviniteOuDogme(Dogme.Humain, Dogme.Humain, this.getJoueurLie().getPartie());
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
