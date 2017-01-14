package divinae.swing.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.swing.controleur.ControleurJeu;
import divinae.swing.modele.ModeleJeu;

public class VueJeu extends JFrame implements ActionListener, Observer {
	private ControleurJeu controleurJeu;
	private ModeleJeu modeleJeu;
	private JTextArea log;
	private JPanel panelTable = new JPanel();
	private JPanel panelSelection = new JPanel();
	private JPanel panelJoueur = new JPanel();
	private JPanel panelJoueurDivinite = new JPanel();
	private JPanel panelBoutons = new JPanel();
	private JButton boutonDefausser = new JButton("Défausser");
	private JButton boutonJouer = new JButton("Jouer");
	private JButton boutonSacrifier = new JButton("Sacrifier");
    private JButton boutonFinDeTour = new JButton("Fin de tour");
	private List<VueCarte> vueCartes = new ArrayList<>();

	public VueJeu(ControleurJeu controleurJeu, ModeleJeu modeleJeu) {
		this.controleurJeu = controleurJeu;
		this.modeleJeu = modeleJeu;
		modeleJeu.addObserver(this);

		this.setTitle("Pandocreon Divinae");
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(1280, 800));
		this.setMaximumSize(new Dimension(1280, 800));
		this.setPreferredSize(new Dimension(1280, 800));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panel de la table
		panelTable.setBackground(Color.LIGHT_GRAY);
		panelTable.setMinimumSize(new Dimension(1280, 600));
		panelTable.setMaximumSize(new Dimension(1280, 600));
		panelTable.setPreferredSize(new Dimension(1280, 600));

		// Zone non utilisée
		JPanel panelSeparateur = new JPanel();
		panelSeparateur.setBackground(Color.BLACK);
		panelSeparateur.setMinimumSize(new Dimension(142, 10));
		panelSeparateur.setMaximumSize(new Dimension(142, 10));
		panelSeparateur.setPreferredSize(new Dimension(142, 10));

		// Panel de sélection de carte joueur
		panelSelection.setLayout(new BoxLayout(panelSelection, BoxLayout.X_AXIS));
		panelSelection.setBackground(Color.BLACK);
		panelSelection.setMinimumSize(new Dimension(1136, 10));
		panelSelection.setMaximumSize(new Dimension(1136, 10));
		panelSelection.setPreferredSize(new Dimension(1136, 10));

		// Panel du groupe de sélection
		JPanel panelGroupeSelection = new JPanel();
		panelGroupeSelection.setLayout(new BorderLayout());
		panelGroupeSelection.setMinimumSize(new Dimension(1280, 10));
		panelGroupeSelection.setMaximumSize(new Dimension(1280, 10));
		panelGroupeSelection.setPreferredSize(new Dimension(1280, 10));
		panelGroupeSelection.add(panelSeparateur, BorderLayout.WEST);
		panelGroupeSelection.add(panelSelection, BorderLayout.EAST);

		// Panel du joueur
		panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.X_AXIS));
		panelJoueur.setBackground(Color.BLACK);
		panelJoueur.setMinimumSize(new Dimension(946, 200));
		panelJoueur.setMaximumSize(new Dimension(946, 200));
		panelJoueur.setPreferredSize(new Dimension(946, 200));

		// Panel de divinite du joueur
		panelJoueurDivinite.setLayout(new BorderLayout());
		panelJoueurDivinite.setBackground(Color.BLACK);
		panelJoueurDivinite.setMinimumSize(new Dimension(142, 200));
		panelJoueurDivinite.setMaximumSize(new Dimension(142, 200));
		panelJoueurDivinite.setPreferredSize(new Dimension(142, 200));


		// Panel des boutons
		boutonDefausser.addActionListener(this);
		boutonJouer.addActionListener(this);
        boutonFinDeTour.addActionListener(this);
		panelBoutons.setBackground(Color.BLACK);
		panelBoutons.setMinimumSize(new Dimension(200, 200));
		panelBoutons.setMaximumSize(new Dimension(200, 200));
		panelBoutons.setPreferredSize(new Dimension(200, 200));
		
		boutonDefausser.setMinimumSize(new Dimension(200, 40));
		boutonDefausser.setMaximumSize(new Dimension(200, 40));
		boutonDefausser.setPreferredSize(new Dimension(200, 40));
		
		boutonJouer.setEnabled(false);
		boutonJouer.setMinimumSize(new Dimension(200, 40));
		boutonJouer.setMaximumSize(new Dimension(200, 40));
		boutonJouer.setPreferredSize(new Dimension(200, 40));

		boutonSacrifier.setMinimumSize(new Dimension(200, 40));
		boutonSacrifier.setMaximumSize(new Dimension(200, 40));
		boutonSacrifier.setPreferredSize(new Dimension(200, 40));
		
		boutonFinDeTour.setMinimumSize(new Dimension(200, 40));
		boutonFinDeTour.setMaximumSize(new Dimension(200, 40));
		boutonFinDeTour.setPreferredSize(new Dimension(200, 40));

		panelBoutons.add(boutonDefausser);
		panelBoutons.add(boutonJouer);
		panelBoutons.add(boutonSacrifier);
		panelBoutons.add(boutonFinDeTour);

		// Panel du joueur : cartes + boutons
		JPanel panelJoueurBouton = new JPanel();
		panelJoueurBouton.setLayout(new BorderLayout());
		panelJoueurBouton.setMinimumSize(new Dimension(1080, 200));
		panelJoueurBouton.setMaximumSize(new Dimension(1080, 200));
		panelJoueurBouton.setPreferredSize(new Dimension(1080, 200));
		panelJoueurBouton.add(panelGroupeSelection, BorderLayout.NORTH);
		panelJoueurBouton.add(panelJoueurDivinite, BorderLayout.WEST);
		panelJoueurBouton.add(panelJoueur, BorderLayout.CENTER);
		panelJoueurBouton.add(panelBoutons, BorderLayout.EAST);

		//TextArea des logs
		log = new JTextArea();
		log.setEditable(false);
		log.setRows(5);
		JScrollPane scrollPane = new JScrollPane(log);
		scrollPane.setBackground(Color.LIGHT_GRAY);

		this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.NORTH);
        this.add(panelTable, BorderLayout.CENTER);
		this.add(panelJoueurBouton, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
	}

	public void afficherMessage(String message) {
		this.log.append(message + "\n");
		this.log.setCaretPosition(this.log.getDocument().getLength());
	}

	public void alert(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	@Override
	public void update(Observable o, Object arg) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object  source=e.getSource();

		if  (source==boutonDefausser)
		{
			List<CarteAction> cartes = getCartesSelectionnees();
			if (cartes.size() > 0) {
				Joueur joueur = cartes.get(0).getJoueurLie();
				joueur.defausser(cartes);
				afficherMain(joueur.getMain());
				int nombreCartes = 7-joueur.getMain().size();
				afficherMessage("Pioche de "+nombreCartes+" cartes.");
				joueur.completerMain();
				afficherMain(joueur.getMain());
			}
			boutonDefausser.setEnabled(false);
		}
		else if (source==boutonJouer)
		{
			List<VueCarte> vueCartes = getVueCartesSelectionnees();
			VueCarte vueCarte = vueCartes.get(0);
			Joueur joueur = vueCarte.getCarteAction().getJoueurLie();
			String message = carteJouable(vueCarte.getCarteAction(), joueur);
			if(message == null) {
				joueur.poserCarteAction(vueCarte.getId());
				//demanderInterruption();
				// Solution ?
				//Capacite.setCarteInterupt(vueCarte.getCarteAction());
				//CarteAction cartePosee = Capacite.getCarteInterupt();
				//if (cartePosee != null && cartePosee.isCapaciteBloqué() && ( !(cartePosee instanceof Croyant) ||  !(cartePosee instanceof GuideSpirituel) )) {
				//	alert(cartePosee.getNom() + " a été bloqué !");
				//} else {
					modeleJeu.getPartie().activerCartes();
				//}
				boutonDefausser.setEnabled(false);
				boutonJouer.setEnabled(false);
				modeleJeu.getPartie().setCroyantsRattachables();
				afficherTable();
				controleurJeu.jouerTourJoueursVirtuels();
			} else {
				alert(message);
				vueCarte.setSelected(false);
			}
		}
		else if (source==boutonSacrifier)
		{
			if (vueCartes.size() < 1)
			{
				throw new RuntimeException("Pas de carte dans la main, erreur inattendue.");
			}
			Joueur joueurCourant = vueCartes.get(0).getCarteAction().getJoueurLie();
			List<CarteAction> listeCartesSacrifiables = joueurCourant.recupererCartesSacrifiables();
			SelectionnerCarteDialog dialog = new SelectionnerCarteDialog(this, "Sacrifier une carte", listeCartesSacrifiables);
			dialog.setVisible(true);
			int choixSacrifice = dialog.getChoixCarte(); 
			if ((joueurCourant.isAutorisationgsp() == false
				&& listeCartesSacrifiables.get(choixSacrifice) instanceof GuideSpirituel) | (joueurCourant.isAutorisationcr() == false
				&& listeCartesSacrifiables.get(choixSacrifice) instanceof Croyant)) {
				alert("Vous ne pouvez pas sacrifier cette carte ce tour ci. (Utilisation d'une capacite contre vous)");
			} else {
				// Capacite.setCarteInterupt(listeCartesSacrifiables.get(choixSacrifice));
				// TODO demanderInterruption();
				// if (listeCartesSacrifiables.get(choixSacrifice).isCapaciteBloqué()) {
				//	System.out.println(joueurCourant.getMain().get(choixSacrifice).getNom() + " a été bloqué !");
				// } else {
					joueurCourant.sacrifierCarte(listeCartesSacrifiables.get(choixSacrifice));
				//	break;
				//}
				joueurCourant.tuerCarte(listeCartesSacrifiables.get(choixSacrifice));
				afficherTable();
			}
		}
		else if (source==boutonFinDeTour)
		{
			boutonFinDeTour.setEnabled(false);
			modeleJeu.getPartie().setCroyantsRattachables();
			controleurJeu.jouerTourJoueursVirtuels();
		}
		else if (source instanceof VueCarte)
		{
			((VueCarte)source).setSelected(!((VueCarte)source).isSelected());
			int nombre = compterCarteSelectionnee();
			if (nombre == 1) {
				boutonJouer.setEnabled(true);
			} else if (nombre > 1) {
				boutonJouer.setEnabled(false);
			}
		}
		else
		{
			alert("Aucune action prévue pour " + source);
		}
	}

	private String carteJouable(CarteAction carte, Joueur joueur) {
		String result = null;
		switch (carte.getOrigine()){
			
			case Jour :
				if (joueur.getPointsAction()[Origine.Jour.ordinal()] >= 1) {
					result = null;
				} else {
					result = "Pas de point d'origine jour.";
				}
				break;
				
			case Nuit :
				if (joueur.getPointsAction()[Origine.Nuit.ordinal()] >= 1) {
					result = null;
				} else {
					result =  "Pas de point d'origine Nuit.";
				}
				break;
				
			case Neant :
				if (joueur.getPointsAction()[Origine.Neant.ordinal()] >= 1) {
					result = null;
				} else {
					result = "Pas de point d'origine Neant.";
				}
				break;
				
			case Aube:
			case Crepuscule:
			case Aucune:
				result = null;
				
			default:
		}
		return result;
	}

	private List<CarteAction> getCartesSelectionnees() {
		List<CarteAction> cartes = new ArrayList<>();
		for (VueCarte vueCarte: vueCartes) {
			if (vueCarte.isSelected()) {
				cartes.add(vueCarte.getCarteAction());
			}
		}
		return cartes;
	}

	private List<VueCarte> getVueCartesSelectionnees() {
		List<VueCarte> cartes = new ArrayList<>();
		for (VueCarte vueCarte: vueCartes) {
			if (vueCarte.isSelected()) {
				cartes.add(vueCarte);
			}
		}
		return cartes;
	}
	
	private int compterCarteSelectionnee() {
		int nombreSelection = 0;
		for (VueCarte vueCarte: vueCartes)
		{
			if (vueCarte.isSelected()) {
				nombreSelection++;
			}
		}
		return nombreSelection;
	}

	public void afficherJoueurDivinite(Carte carte) {
		this.panelJoueurDivinite.add(new VueCarte(0, carte, null), BorderLayout.WEST);
		afficherMessage(carte.toString());
		this.panelJoueur.updateUI();
	}

	public void afficherTable() {
		panelTable.removeAll();
		int id = 0;
		for (CarteAction carte:modeleJeu.getPartie().getTable())
		{
			panelTable.add(new VueCarte(id++, carte, null));
		}
	}
	
	public void afficherMain(List<CarteAction> cartes) {
		effacerPanelJoueur();
		int id = 0;
		for (CarteAction carte:cartes)
		{
			final VueSelection vueSelection = new VueSelection(false);
			panelSelection.add(vueSelection);
			VueCarte vueCarte = new VueCarte(id++, carte, vueSelection);
			vueCarte.addActionListener(this);
			if (carteJouable(vueCarte.getCarteAction(), carte.getJoueurLie()) == null) {
				vueSelection.setBackground(Color.BLUE);
			}
			panelJoueur.add(vueCarte);
			vueCartes.add(vueCarte);
			afficherMessage(carte.toString());
		}
		Joueur joueurCourant = (cartes.size() > 0) ? cartes.get(0).getJoueurLie() : null;
		if(joueurCourant != null && ((joueurCourant.isAutorisationcr()
			&& joueurCourant.isAutorisationgsp()) || joueurCourant.getGuides().size() == 0)) {
			boutonSacrifier.setEnabled(false);
		}			
		panelJoueur.updateUI();
	}

	public void initialiserVueJoueur() {
		boutonDefausser.setEnabled(true);
		boutonFinDeTour.setEnabled(true);		
		boutonSacrifier.setEnabled(true);
	}
	
	private void effacerPanelJoueur() {
		panelJoueur.removeAll();
		panelSelection.removeAll();
		panelSelection.updateUI();
		vueCartes.clear();
	}
}
