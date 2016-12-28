package cartes.deuxex;

import cartes.Capacite;
import cartes.Carte;
import cartes.Croyant;
import cartes.DeusEx;
import cartes.GuideSpirituel;
import cartes.Origine;
import joueur.Joueur;
import partie.Partie;

public class Transe extends DeusEx {

	public Transe() {
		super("Transe", Origine.Aucune, "Permet de récupérer les effets bénéfiques d'une carte d'Action posée par une autre Divinité. "
				+ "S'il s'agit d'une carte Croyant ou Guide Spirituel, vous posez la carte devant vous.");
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public void activerCapacite() {
		Partie partie = this.getJoueurLie().getPartie();
		Carte cartePosee = partie.getTable(partie.getTable().size()-1);
	    Classe z = Classe.valueOf(cartePosee.getClass().getSimpleName());
	    
	    Joueur joueur = this.getJoueurLie();

	    switch (z) {
	    case Croyant:
		    int croyantLiable = 0;
		    for (int i = 0; i < joueur.getGuides().size(); i++) {
				for(int j =0; j < joueur.getGuide(i).getCroyantLie().size(); j++){
						croyantLiable++;
				} 
			}
	    	if (joueur.getNombreCroyant() < croyantLiable) {
	    		for (int i = 0; i < joueur.getGuides().size(); i++) {
	    			if (joueur.getGuide(i).getCroyantLie().size() < joueur.getGuide(i).getNombreCroyantLiable()) {
	    				((Croyant) cartePosee).setGuideLie(joueur.getGuide(i));
	    			} 
	    		}
	    	} else { System.out.println("Pas de place disponiblr pour lier ce croyant"); }
	    break;
	    
	    case GuideSpirituel:
	        cartePosee.setJoueurLie(joueur);
	        ((GuideSpirituel) cartePosee).convertirCroyant(partie);
	    break;
	    
	    case DeusEx:
	        switch (cartePosee.getNom()){
	        case "Stase" : 
	        case "Ordre Celeste" :
	        case "Diversion" :
	        case "Concentration" :
	        case "Trou Noir" :
	        case "Phoenix" : Capacite.copierCapacite(cartePosee, partie);
	     	break;
	    
	        default : System.out.println("Cette carte n'a aucun effet bénéfique dire pour vous.");
	       	break;
	        }
	    break;
	    
	    default : System.out.println("Aucun effet bénéfique pour vous.");
	    break;
	    }
	}


	enum Classe {
	    Croyant,
	    GuideSpirituel,
	    DeusEx;
	}
		
}
