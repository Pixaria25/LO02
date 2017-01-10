package divinae.api.joueur;

import java.util.List;
import java.util.Random;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.Partie;

public class JoueurVirtuel extends Joueur {

	private Strategie strategie;
	private Random random = new Random();
	
	public JoueurVirtuel(String nom, Partie partie,Strategie strategie) {
		super(nom, partie);
		this.strategie = strategie;
	}
	
	@Override
	public void jouer() {
		defausser(strategie.defausser(getMain()));
		boolean tourJoueurFini = false;
		do{
			int choix = strategie.jouer();
			switch(choix) {
				case 0:
					poserCarteAction();
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
		} while(!tourJoueurFini);
	}

	public void poserCarteAction() {
		strategie.choixCarteAction(getMain());
	}
	
	public void sacrifier() {
		List<CarteAction> cartesSacrifiables = recupererCartesSacrifiables();
		strategie.choixSacrifice(cartesSacrifiables);
	}
	
	
	//Appel des methodes de ActionSuivante
	public Joueur choisirJoueurCible() {
		int choixJoueur = random.nextInt(getPartie().getJoueurs().size());
		return getPartie().getJoueurs().get(choixJoueur);
	}
	
	public GuideSpirituel choisirGsp() {
		int choixJoueur = random.nextInt(getPartie().getJoueurs().size());
		return Capacite.getActionSuivante().choisirGsp(getPartie());
	}
	
	public void renvoyerGsp (List <GuideSpirituel> gspCiblable, Partie partie) {
		
	}
	
	public Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie) {
		return Capacite.getActionSuivante().choisirDiviniteOuDogme(dogme1, dogme2, partie);
	}
	
	public GuideSpirituel choisirSonGsp () {
		return Capacite.getActionSuivante().choisirSonGsp(this, getPartie());
	}
	
	public Croyant choisirCroyant () {
		return Capacite.getActionSuivante().choisirCroyant(this, getPartie());
	}
	
	public Origine choisirOrigine () {
		return Capacite.getActionSuivante().choisirOrigine();
	}
	
	public GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme) {
		return Capacite.getActionSuivante().choisirDiviniteOuGspNonDogme(dogme, getPartie());
	}
	
	public void choisirFaceDe (Carte carte,Partie partie) {
		Capacite.getActionSuivante().choisirFaceDe(carte, partie);
	}
	
	public boolean choixMultiples (String cible) {
		return Capacite.getActionSuivante().choixMultiples(cible);
	}
	
	public int gspOuCroyant () {
		return Capacite.getActionSuivante().gspOuCroyant();
	}

}
