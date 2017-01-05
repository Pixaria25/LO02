package divinae.api.cartes.types;

public class Apocalypse extends Carte implements Action {

	public Apocalypse(Origine origine, String capacite) {
		super("Apocalypse", origine, "Lance une apocalypse.");
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
		// TODO Auto-generated method stub
		activerCapacite();
	}

	@Override
	public String toString() {
		return "Carte " + getNom() + "\n " + getOrigine() + "\n " + getCapacite();
	}
}
