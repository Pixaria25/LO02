package cartes.guide;

import java.util.List;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;
import cartes.croyant.Croyant;

public class Paladin extends GuideSpirituel {
  
  public Paladin () {
	 	super("Paladin", Origine.Jour, "Tous les Croyants, d'Origine Nuit ou Néant et ayant le Dogme Nature, actuellement"
	 			+ " sur la table sont défaussés. Les capacités spéciales ne sont pas jouées.",
	 			new Dogme [] {Dogme.Humain,Dogme.Symboles}, 3);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		List<Croyant> croyantASuppr = Capacite.trierCroyantDogme(Origine.Nuit, Origine.Neant, Dogme.Nature, this.getJoueurLie().getPartie());
		for(int i=0; i < croyantASuppr.size(); i++){
			Capacite.defausser(croyantASuppr.get(i), this.getJoueurLie().getPartie());
		}
	}
	
	  
}
