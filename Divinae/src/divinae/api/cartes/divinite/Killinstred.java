package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Killinstred extends Divinite {

	public Killinstred() {
		super("Killinstred", Origine.Nuit, "Peut obliger un joueur � poser une carte Apocalypse "
				+ "s'il en poss�de une.", new Dogme[]{Dogme.Nature, Dogme.Mystique, Dogme.Chaos}, 
				"Divinit� machiav�lique et manipulatrice, "
				+ "Killinstred cherche � influencer et contr�ler ses ennemis.", 84);
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		super.activerCapacite();
	}

}
