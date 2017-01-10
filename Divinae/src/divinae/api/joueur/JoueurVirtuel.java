package divinae.api.joueur;

import java.util.List;

import divinae.api.cartes.types.CarteAction;
import divinae.api.partie.Partie;

public class JoueurVirtuel extends Joueur {

	private Strategie strategie;
	
	public JoueurVirtuel(String nom, Partie partie,Strategie strategie) {
		super(nom, partie);
		this.strategie = strategie;
	}
	
	@Override
	public void jouer() {
		defausser(strategie.defausser(getMain()));
		boolean tourJoueurFini = false;
		do{
			int choix = strategie.jouer();
			switch(choix) {
				case 0:
					poserCarteAction();
					break;
				case 1:
					sacrifier();
					break;
				case 2:
					getDivinite().activerCapacite();
					break;
				case 3:
					tourJoueurFini = true;
					break;
				default:
			}
		} while(!tourJoueurFini);
	}

	public void poserCarteAction() {
		strategie.choixCarteAction(getMain());
	}
	
	public void sacrifier() {
		List<CarteAction> cartesSacrifiables = recupererCartesSacrifiables();
		strategie.choixSacrifice(cartesSacrifiables);
	}
}
