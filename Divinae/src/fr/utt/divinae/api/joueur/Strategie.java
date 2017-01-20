package fr.utt.divinae.api.joueur;

import java.util.List;

import fr.utt.divinae.api.cartes.guide.CarteSacrifiable;
import fr.utt.divinae.api.cartes.types.CarteAction;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.partie.Partie;

public interface Strategie {
	
	public abstract int jouer(Joueur joueurCourant);
	
	public abstract List<CarteAction> defausser(List<CarteAction> main);

	public abstract int choixCarteAction(List<CarteAction> main);
	
	public abstract CarteSacrifiable choixSacrifice(List<CarteSacrifiable> cartesValides);
	
	public abstract Joueur choisirJoueurCible(List<Joueur> liste);
	
	public abstract GuideSpirituel choisirGsp (Partie partie);
	
	public abstract Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie);
	
	public abstract GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie);
	
	public abstract Croyant choisirCroyant (Joueur joueur, Partie partie);
	
	public abstract Origine choisirOrigine ();
	
	public abstract GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie);
	
	public abstract int choisirFaceDe (Joueur joueur);
	
	public abstract boolean choixMultiples (String cible);
	
	public abstract int gspOuCroyant ();
	
	public abstract void messageRecap (String message);

	public abstract Croyant choisirTasCroyant(Joueur joueur, Partie partie);
	
	public abstract GuideSpirituel choisirGspRenvoye (List <GuideSpirituel> gspCiblable);

	public abstract void demanderInterruption(Joueur joueurCourant);

}
