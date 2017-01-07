package divinae.api.cartes.types;

public abstract class Croyant extends Carte implements Action {
	
	private Dogme [] dogme;
	private int valeurCroyant;
	private GuideSpirituel guideLie;
	
	
	public Croyant(String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		// TODO Auto-generated constructor stub
		super(nom, origine, capacite);
	    this.dogme = dogme;
		this.valeurCroyant = nombreCroyant;
		this.guideLie = null;
	}

	@Override
	public void poserCarteAction() {
		// TODO Auto-generated method stub
		switch (joueurCourant.getMain().get(choixCarte).getOrigine()){
		case Jour :
			if (joueurCourant.getPointsAction()[Origine.Jour.ordinal()] >= 1) {
				joueurCourant.getPointsAction()[Origine.Jour.ordinal()]--;
				joueurCourant.setNombreCroyant(joueurCourant.getNombreCroyant()-((Croyant) joueurCourant.getMain().get(choixCarte)).getValeurCroyant());
			} else {
				System.out.println("Pas de point d'origine jour.");
			}
			break;
		case Nuit :
			if (joueurCourant.getPointsAction()[Origine.Nuit.ordinal()] >= 1) {
				joueurCourant.getPointsAction()[Origine.Nuit.ordinal()]--;
				joueurCourant.setNombreCroyant(joueurCourant.getNombreCroyant()-((Croyant) joueurCourant.getMain().get(choixCarte)).getValeurCroyant());
			} else {
				System.out.println("Pas de point d'origine Nuit.");
			}
			break;
		case Neant :
			if (joueurCourant.getPointsAction()[Origine.Neant.ordinal()] >= 1) {
				joueurCourant.getPointsAction()[Origine.Neant.ordinal()]--;
				
			} else {
				System.out.println("Pas de point d'origine Neant.");
			}
			break;
			default : 
		}
		getJoueurLie().getPartie().getTasDeCroyants().add(this);
		setJoueurLie(null);
	}
	
	public GuideSpirituel getGuideLie() {
		return guideLie;
	}
	
	public void setGuideLie(GuideSpirituel guideLie) {
		this.guideLie = guideLie;
	}

	public Dogme[] getDogme() {
		return dogme;
	}

	public int getValeurCroyant() {
		return valeurCroyant;
	}
	
	@Override
	public String toString() {
		return "Croyant" + getNom() + "\n " + getOrigine() + "\n " + dogme + "\n " + getCapacite();
	}

}
