package divinae.api.joueur;

import java.util.List;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.Partie;

public class JoueurVirtuel extends Joueur {

	private Strategie strategie;
	
	
	public JoueurVirtuel(String nom, Partie partie,Strategie strategie) {
		super(nom, partie);
		this.strategie = strategie;
	}
	
	@Override
	public void jouer () {
		defausser(strategie.defausser(getMain()));
		boolean tourJoueurFini = false;
		do{
			try{
				int choix = strategie.jouer(this);
				switch(choix) {
					case 0:
						poserCarteAction();
						demanderInterruption(this);
						getPartie().activerCartes();
						break;
					case 1:
						sacrifier();
						break;
					case 2:
						getDivinite().activerCapacite();
						break;
					case 3:
						tourJoueurFini = true;
						break;
					default:
				} 
			}catch(IllegalArgumentException e) {
					
			}catch(IndexOutOfBoundsException e) {
				
			}
			
		} while(!tourJoueurFini);
	}

	public void poserCarteAction() {

		int choix = strategie.choixCarteAction(getMain());
		CarteAction cartePosee = getMain().get(choix);
		boolean poserCarte = poserCarteAction(choix);
		if(poserCarte) {

			Capacite.setCarteInterupt(cartePosee);
			for (int i = 0; i < getPartie().getJoueurs().size(); i++) {
				if (!(getPartie().getJoueurs().get(i).getNom() == getNom())) {
					getPartie().getJoueurs().get(i).demanderInterruption(getPartie().getJoueurs().get(i));
				}
			}

			

		}
	}
	
	public void sacrifier() {
		List<CarteAction> cartesSacrifiables = recupererCartesSacrifiables();
		if(!cartesSacrifiables.isEmpty()) {
			sacrifierCarte(strategie.choixSacrifice(cartesSacrifiables));
		}
	}
	
	//Appel des methodes de ActionSuivante

	public void demanderInterruption(Joueur joueurCourant) {
		strategie.demanderInterruption(joueurCourant);
	}
	
	public Joueur choisirJoueurCible(List<Joueur> liste) {
		return strategie.choisirJoueurCible(liste);
	}
	
	@Override
	public GuideSpirituel choisirGsp() {
		return strategie.choisirGsp(getPartie());
	}

	@Override
	public Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2) {
		return strategie.choisirDiviniteOuDogme(dogme1, dogme2, getPartie());
	}

	@Override
	public GuideSpirituel choisirSonGsp () {
		return strategie.choisirSonGsp(this, getPartie());
	}

	@Override
	public Croyant choisirCroyant (Joueur joueur) {
		return strategie.choisirCroyant(joueur, getPartie());
	}

	@Override
	public Origine choisirOrigine () {
		return strategie.choisirOrigine();
	}

	@Override
	public GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme) {
		return strategie.choisirDiviniteOuGspNonDogme(dogme, getPartie());
	}
	
	@Override
	public int choisirFaceDe (Joueur joueur) {
		return strategie.choisirFaceDe(joueur);
	}

	@Override
	public boolean choixMultiples (String cible) {
		return strategie.choixMultiples(cible);
	}

	@Override
	public int gspOuCroyant () {
		return strategie.gspOuCroyant();
	}


  @Override
	public Croyant choisirTasCroyant() {
		return strategie.choisirTasCroyant(this, getPartie());
	}
	
  @Override
	public GuideSpirituel choisirGspRetire(List<GuideSpirituel> gspCiblable) {
		return strategie.choisirGspRenvoye(gspCiblable);
	}

	public void messageRecap (String message) {

	}
	
	public Strategie getStrategie() {
		return strategie;
	}
	

}
