package divinae.swing.modele;

import java.util.Observable;

import divinae.api.partie.Partie;
import divinae.api.partie.TypeStrategie;

public class ModeleJeu extends Observable {
	private Partie partie;
	
	public ModeleJeu() {
		this.partie = Partie.getInstance();
	}
	
	public void initialiserPartie(String nomJoueurReel, int nombreJoueurVirtuel) {
		partie.ajouterUnJoueurReel(nomJoueurReel);
		for (int index=0 ; index<nombreJoueurVirtuel ; index++) {
			partie.ajouterUnJoueurVirtuel("MAC"+index, TypeStrategie.ALEATOIRE);
		}
		
		partie.distribuerLesDivinites();
		partie.remplirPioche();
		partie.distribuerCartes();
	}
	
	public Partie getPartie() {
		return this.partie;
	}
}
