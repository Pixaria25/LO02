package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Fourberie extends DeusEx {

	public Fourberie() {
		super("Fouberie", Origine.Nuit, "Sacrifiez 2 cartes Croyants "
				+ "d'une autre Divinité. Les capacités spéciales ne sont pas jouées.", 62);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {

		Partie partie = this.getJoueurLie().getPartie();
    
		Joueur joueur =  getJoueurLie().choisirJoueurCible();


		int nbCroyantCiblable = 0;
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for(int j =0; j < joueur.getGuide(i).getCroyantLie().size(); j++){
				if (joueur.getGuide(i).getCroyantLie(j).isProtectionCiblage()) {
					nbCroyantCiblable++;
				}
			}
		}

		if (nbCroyantCiblable < 2) {

			joueur =  getJoueurLie().choisirJoueurCible();
      
		}

		for (int k =0; k < 2; k++) {

			Capacite.defausser( getJoueurLie().choisirCroyant(joueur), partie);
		}


	}

}
