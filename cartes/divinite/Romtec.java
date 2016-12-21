package cartes.divinite;

import cartes.Dogme;
import cartes.Origine;

public class Romtec extends Divinite {

	public Romtec() {
		super("Romtec", Origine.Crepuscule, "Peut emp�cher un jour de cr�er un Guide Spirituel. "
				+ "La carte est d�fauss�e.", new Dogme[]{Dogme.Nature, Dogme.Humain, Dogme.Chaos}, 
				"Romtec est une Divinit� individualiste, pour qui chaque �tre vivant doit garder son libre arbitre.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
