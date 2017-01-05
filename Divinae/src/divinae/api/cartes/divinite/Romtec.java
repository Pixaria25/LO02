package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Romtec extends Divinite {

	public Romtec() {
		super("Romtec", Origine.Crepuscule, "Peut empêcher un jour de créer un Guide Spirituel. "
				+ "La carte est défaussée.", new Dogme[]{Dogme.Nature, Dogme.Humain, Dogme.Chaos}, 
				"Romtec est une Divinité individualiste, pour qui chaque être vivant doit garder son libre arbitre.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
