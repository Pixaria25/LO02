package divinae.api.cartes.types;

public class Apocalypse extends  CarteAction {

	public Apocalypse(Origine origine, int id) {
		super("Apocalypse", origine, "Lance une apocalypse.", id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		boolean validite = Capacite.retirerPointAction(this, this.getOrigine());
		if(validite) {
			Capacite.lancerApocalypse(this.getJoueurLie().getPartie());
			this.getJoueurLie().getPartie().setIndexJoueur1(this.getJoueurLie().getPartie().getJoueurs().indexOf(this.getJoueurLie()));
		}
	}

	@Override
	public void poserCarteAction() {
		// TODO Auto-generated method stub
		activerCapacite();
		this.getJoueurLie().getPartie().getDefausse().ajoutCarte(this);
	}

	@Override
	public String toString() {
		return "Carte " + getNom() + "\n " + getOrigine() + "\n " + getCapacite();
	}
}
