package fr.utt.divinae.api.cartes.types;

import java.util.ArrayList;
import java.util.List;

import fr.utt.divinae.api.cartes.guide.CarteSacrifiable;
import fr.utt.divinae.api.partie.Partie;

/**
 * La classe GuideSpirituel modelise les cartes Guide Spirituel.
 * @author Thomas, Abraham
 *
 */
public abstract class GuideSpirituel extends CarteSacrifiable {

	private List<Croyant> croyantLie;
	private Dogme [] dogme;
	private int nombreCroyantLiable;
	
	
	public GuideSpirituel (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyantLiable, int id) {
		super(nom, "Guide Spirituel", origine, capacite, id);
		this.croyantLie = new ArrayList<Croyant>();
		this.dogme = dogme;
		this.nombreCroyantLiable = nombreCroyantLiable;
	}
	
	@Override
	public void mort() {
		getJoueurLie().getPartie().getTasDeCroyants().addAll(croyantLie);
		croyantLie.clear();
		super.mort();
	}

	public void convertirCroyant (Partie partie) {
		while (this.nombreCroyantLiable  >  croyantLie.size() && partie.getTasDeCroyants().size() > 0) { 
			Croyant croyant = getJoueurLie().choisirTasCroyant();
			croyant.setGuideLie(this);
			croyant.setJoueurLie(getJoueurLie());
			croyantLie.add(croyant);
			Utilitaire.majPointsCroyant(croyant, +croyant.getNombrePrieres());
			getJoueurLie().messageRecap(getJoueurLie().getNom() + " lie " + croyant.getNom() + " à " + getNom());
		}
	}
	
	public void poserCarteAction() {
			int indexCourant = getJoueurLie().getPartie().getIndexJoueur1();
			Partie partie = getJoueurLie().getPartie();
			if (!partie.getTasDeCroyants().isEmpty()) {
				setJoueurLie(partie.getJoueurs().get(indexCourant));
				convertirCroyant(getJoueurLie().getPartie());
				getJoueurLie().messageRecap(getJoueurLie().getNom() + " joue " + getNom());
				getJoueurLie().getMain().remove(this);
			} else { 
				getJoueurLie().messageRecap("Pas de croyants disponible ! Reprise de la carte");
				Utilitaire.rendrePointActionEtCarte (this);
				
			}
			
	}

	public List<Croyant> getCroyantLie() {
		return croyantLie;
	}
	
	public Croyant getCroyantLie(int i) {
		return croyantLie.get(i);
	}

	public Dogme[] getDogme() {
		return dogme;
	}

	public int getNombreCroyantLiable() {
		return nombreCroyantLiable;
	}
	
	
	@Override
	public String toString() {
		return "Guide Spirituel " +super.toString()+ "\n Dogme: " + dogmeToString(dogme) + "\n Nombre de croyants: "+nombreCroyantLiable;
	}
}
