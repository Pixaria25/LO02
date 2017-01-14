package divinae.api.joueur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.cartes.types.Utilitaire;
import divinae.api.partie.Partie;
import divinae.console.InterfacePartie;

public class StrategieAleatoire implements Strategie {

	private Random random = new Random();
	
	
	@Override
	public int jouer() {

		int choix = random.nextInt(4);
		return choix;
	}
	
	@Override
	public void demanderInterruption() {
		interruption();
	}

	public void interruption() {
		Partie partie = InterfacePartie.getPartie();
		HashSet<Integer> actionsValides = new HashSet<Integer>();
		Joueur joueurCourant = partie.getTable(partie.getTable().size()-1).getJoueurLie();

		if(joueurCourant.aDesCartesSansOrigine()) {
			actionsValides.add(1);
		}
		
		if(!joueurCourant.getDivinite().capaciteActivee()) {
			actionsValides.add(2);
		}
		
		int choixInterruption = random.nextInt(8);
		int choixAction = -1;
		
		if (actionsValides.isEmpty()){
			actionsValides.add(0);
		} else if (choixInterruption == 0) {
				choixAction = random.nextInt(actionsValides.size());
		}
		
		switch(choixAction) {
			case 0:
				HashSet<Integer> cartesValides = new HashSet<Integer>();
				for(int i = 0; i < joueurCourant.getMain().size(); i++) {
					if(joueurCourant.getMain().get(i).getOrigine() == Origine.Aucune) {
						cartesValides.add(i);
					}
				}
				int carteChoisie = random.nextInt(cartesValides.size());
				joueurCourant.poserCarteAction(carteChoisie);
			case 1:
				joueurCourant.getDivinite().activerCapacite();
			default:
		}
	}

	@Override
	public List<CarteAction> defausser(List<CarteAction> main) {

		int nombreCartes = random.nextInt(main.size()+1);
		List<CarteAction> cartesADefausser = new ArrayList<CarteAction>();
		for(int i = 0; i < nombreCartes; i++) {
			int numCarte = random.nextInt(main.size());
			cartesADefausser.add(main.get(numCarte));
		}
		return cartesADefausser;

	}

	@Override
	public int choixCarteAction(List<CarteAction> main) {
		int numCarte = random.nextInt(main.size());
		return numCarte-1;
	}

	@Override
	public CarteAction choixSacrifice(List<CarteAction> cartesValides) {
		int numCarte = random.nextInt(cartesValides.size());
		return cartesValides.get(numCarte);

	}

	public Joueur choisirJoueurCible(List<Joueur> liste) {
		int choix = random.nextInt(liste.size());
		return liste.get(choix);
	}
	
	public GuideSpirituel choisirGsp (Partie partie){
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		int indexGsp = 0;
		int indexJoueur = 0;
		while (indexJoueur < partie.getJoueurs().size()) {
			while (indexGsp < partie.getJoueurs().get(indexJoueur).getGuides().size()) {
				gspCiblable.add(partie.getJoueurs().get(indexJoueur).getGuide(indexGsp));
				indexGsp++;
			}
			indexGsp = 0;
			indexJoueur++;
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}
		
		int choix = random.nextInt(gspCiblable.size());
		return gspCiblable.get(choix);
	}
	
	public Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie){
		int choixDivinite = 0;
		List<Divinite> diviniteCiblable = new ArrayList<Divinite>();
		while (choixDivinite < partie.getJoueurs().size()) {
			if (Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme1, partie)
					|| Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme2,
							partie)) {
				diviniteCiblable.add(partie.getJoueurs().get(choixDivinite).getDivinite());
			}
			choixDivinite++;
		}
		int choix = random.nextInt(diviniteCiblable.size());
		return diviniteCiblable.get(choix);
	}
	
	public GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie){
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		int indexJoueur = partie.getJoueurs().indexOf(joueur);

		for (int i = 0; i < partie.getJoueurs().get(indexJoueur).getGuides().size(); i++) {
			gspCiblable.add(partie.getJoueurs().get(indexJoueur).getGuide(i));
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}
		int choix = random.nextInt(gspCiblable.size()+1);
		return gspCiblable.get(choix);
	}
	
	public Croyant choisirCroyant (Joueur joueur, Partie partie){
		List<Croyant> croyantCiblable = new ArrayList<Croyant>();
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for (int j = 0; j < joueur.getGuide(i).getCroyantLie().size(); j++) {
				croyantCiblable.add(joueur.getGuide(i).getCroyantLie(j));
			}
		}
		int choix = random.nextInt(croyantCiblable.size()+1);
		return croyantCiblable.get(choix);
	}
	
	public Origine choisirOrigine (){
		int choix = random.nextInt(3);
		return Origine.values()[choix];
	}
	
	public GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie){
		int choixDivinite = 0;
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();

		while (choixDivinite < partie.getJoueurs().size()) {
			if (!(Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme,
					partie))) {
				gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());
			} else {
				for (int choixGuide = 0; choixGuide < partie.getJoueurs().get(choixDivinite).getGuides()
						.size(); choixGuide++) {
					if (!(Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme,
							partie))) {
						gspCiblable.add(partie.getJoueurs().get(choixDivinite).getGuide(choixGuide));
					}
				}
			}
			choixDivinite++;
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}
		int choix = random.nextInt(gspCiblable.size()+1);
		return gspCiblable.get(choix);
	}
	
	public void choisirFaceDe (Carte carte,Partie partie){
		int choix = random.nextInt(3);
		partie.getDe().setInfluence(Origine.values()[choix]);
	}
	
	public boolean choixMultiples (String cible){
		boolean choix = random.nextBoolean();
		return choix;
	}
	
	public int gspOuCroyant (){
		int choix = random.nextInt(2);
		return choix;
	}
	
	public void messageRecap (String message){
		System.out.println(message);
	}

	public Croyant choisirTasCroyant(Joueur joueur, Partie partie){
		int choix = random.nextInt(partie.getTasDeCroyants().size()+1);
		return partie.getTasDeCroyants(choix);
	}
	
	public GuideSpirituel choisirGspRenvoye (List <GuideSpirituel> gspCiblable){
		int choix = random.nextInt(gspCiblable.size()+1);
		return gspCiblable.get(choix);
	}
}
