package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Romtec extends Divinite {

	public Romtec() {
		super("Romtec", Origine.Crepuscule, "Peut emp�cher un jour de cr�er un Guide Spirituel. "
				+ "La carte est d�fauss�e.", new Dogme[]{Dogme.Nature, Dogme.Humain, Dogme.Chaos}, 
				"Romtec est une Divinit� individualiste, pour qui chaque �tre vivant doit garder son libre arbitre.", 90);
	}

	@Override
	public void activerCapacite() {
		super.activerCapacite();
	}

}
