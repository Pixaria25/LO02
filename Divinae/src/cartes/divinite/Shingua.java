package cartes.divinite;

import java.util.List;

import cartes.Capacite;
import cartes.Divinite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Shingua extends Divinite {

	public Shingua() {
		super("Shingua", Origine.Aube, "Récupère autant de points d'Action supplémentaires d'Origine Néant "
				+ "que le nombre de Guides Spirituels que la Divinité possède.", 
				new Dogme[]{Dogme.Humain, Dogme.Mystique, Dogme.Chaos}, 
				"Perverse et retorse, Shingva est une Divinité que toutes les autres détestent.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		List<GuideSpirituel> guidesCiblable = Capacite.choisirDiviniteEtDogme(Dogme.Mystique, Dogme.Nature, getJoueurLie().getPartie());
		
	}

}
