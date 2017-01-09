package divinae.api.joueur;

import divinae.api.partie.Partie;

public class JoueurVirtuel extends Joueur {

	private Strategie strategie;
	
	public JoueurVirtuel(String nom, Partie partie,Strategie strategie) {
		super(nom, partie);
		this.strategie = strategie;
	}
	
	@Override
	public void jouer() {
		
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
