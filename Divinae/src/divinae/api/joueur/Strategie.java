package divinae.api.joueur;

import java.util.List;

import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.Partie;

public interface Strategie {
	
	public abstract int jouer();
	
	public abstract List<CarteAction> defausser(List<CarteAction> main);

	public abstract int choixCarteAction(List<CarteAction> main);
	
	public abstract CarteAction choixSacrifice(List<CarteAction> cartesValides);
	
	public abstract Joueur choisirJoueurCible(List<Joueur> liste);
	
	public abstract GuideSpirituel choisirGsp (Partie partie);
	
	public abstract Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie);
	
	public abstract GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie);
	
	public abstract Croyant choisirCroyant (Joueur joueur, Partie partie);
	
	public abstract Origine choisirOrigine ();
	
	public abstract GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie);
	
	public abstract void choisirFaceDe (Carte carte,Partie partie);
	
	public abstract boolean choixMultiples (String cible);
	
	public abstract int gspOuCroyant ();
	
	public abstract void messageRecap (String message);

	public abstract Croyant choisirTasCroyant(Joueur joueur, Partie partie);
	
	public abstract GuideSpirituel choisirGspRenvoye (List <GuideSpirituel> gspCiblable);

	void demanderInterruption();

}
