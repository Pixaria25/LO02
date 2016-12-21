package joueur;

import partie.Partie;

public class JoueurVirtuel extends Joueur {

	private Strategie strategie;
	
	public JoueurVirtuel(String nom, Partie partie,Strategie strategie) {
		super(nom, partie);
		this.strategie = strategie;
	}

	public void poserCarteAction() {
		strategie.poserCarteAction();
	}
	
	public void activerCapacite() {
		strategie.activerCapacite();
	}
	
	public void defausser(int nombreCartes) {
		strategie.defausser();
	}
		
	public void finirTour() {
		strategie.finirTour();
	}
}
