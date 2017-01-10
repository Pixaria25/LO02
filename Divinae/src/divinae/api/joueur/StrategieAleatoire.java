package divinae.api.joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import divinae.api.cartes.types.CarteAction;

public class StrategieAleatoire implements Strategie {

	private Random random = new Random();
	
	@Override
	public int jouer() {

		int choix = random.nextInt(4);
		return choix;
	}

	@Override
	public List<CarteAction> defausser(List<CarteAction> main) {

		int nombreCartes = random.nextInt(main.size()+1);
		List<CarteAction> cartesADefausser = new ArrayList<CarteAction>();
		for(int i = 0; i < nombreCartes; i++) {
			int numCarte = random.nextInt(main.size());
			cartesADefausser.add(main.get(numCarte));
		}
		return cartesADefausser;

	}

	@Override
	public int choixCarteAction(List<CarteAction> main) {
		int numCarte = random.nextInt(main.size());
		return numCarte;
		// TODO Auto-generated method stub

	}

	@Override
	public CarteAction choixSacrifice(List<CarteAction> cartesValides) {
		int numCarte = random.nextInt(cartesValides.size());
		return cartesValides.get(numCarte);

	}

}
