package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Fouberie extends DeusEx {

	public Fouberie() {
		super("Fouberie", Origine.Nuit, "Sacrifiez 2 cartes Croyants "
				+ "d'une autre Divinit�. Les capacit�s sp�ciales ne sont pas jou�es.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Partie partie = this.getJoueurLie().getPartie();
		Joueur joueur = Capacite.choisirJoueurCible(this.getJoueurLie().getPartie());
		int nbCroyantCiblable = 0;
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for(int j =0; j < joueur.getGuide(i).getCroyantLie().size(); j++){
				if (joueur.getGuide(i).getCroyantLie(j).isProtectionCiblage()) {
					nbCroyantCiblable++;
				} 
			} 
		}

		if (nbCroyantCiblable < 2) {
			System.out.println("Veuillez choisir un autre joueur, celui-ci n'a pas assez de croyants ciblables.");
			joueur = Capacite.choisirJoueurCible(this.getJoueurLie().getPartie());
		}
		
		for (int k =0; k < 2; k++) {
			Capacite.defausser(Capacite.choisirCroyant(joueur, partie), partie);
		}
	}

}