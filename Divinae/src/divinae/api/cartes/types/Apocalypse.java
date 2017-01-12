package divinae.api.cartes.types;

public class Apocalypse extends  CarteAction {

	public Apocalypse(Origine origine, int id) {
		super("Apocalypse", "",origine, "Lance une apocalypse.", id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Capacite.lancerApocalypse(this.getJoueurLie().getPartie());
		this.getJoueurLie().getPartie().setIndexJoueur1(this.getJoueurLie().getPartie().getJoueurs().indexOf(this.getJoueurLie()));
	}

	@Override
	public void poserCarteAction() {
		activerCapacite();
		getJoueurLie().tuerCarte(this);
	}

	@Override
	public String toString() {
		return "Apocalypse " + super.toString();
	}
}
