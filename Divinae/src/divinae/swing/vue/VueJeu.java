package divinae.swing.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import divinae.api.cartes.types.Carte;
import divinae.swing.modele.ModeleJeu;

public class VueJeu extends JFrame implements ActionListener, Observer {
	private ModeleJeu modeleJeu;
	private JTextArea log;
	private JPanel panelTable = new JPanel();
	private JPanel panelJoueur = new JPanel();
	private JPanel panelJoueurDivinite = new JPanel();
	private JPanel panelBoutons = new JPanel();
    private JButton boutonFinDeTour = new JButton("Fin de tour");

	public VueJeu(ModeleJeu modeleJeu) {
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

		// Panel du joueur
		panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.X_AXIS));
		panelJoueur.setBackground(Color.BLACK);
		panelJoueur.setMinimumSize(new Dimension(946, 200));
		panelJoueur.setMaximumSize(new Dimension(946, 200));
		panelJoueur.setPreferredSize(new Dimension(946, 200));
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(panelJoueur);

		// Panel de divinite du joueur
		panelJoueurDivinite.setLayout(new BorderLayout());
		panelJoueurDivinite.setBackground(Color.BLACK);
		panelJoueurDivinite.setMinimumSize(new Dimension(142, 200));
		panelJoueurDivinite.setMaximumSize(new Dimension(142, 200));
		panelJoueurDivinite.setPreferredSize(new Dimension(142, 200));


		// Panel des boutons
        boutonFinDeTour.addActionListener(this);
		panelBoutons.setBackground(Color.BLACK);
		panelBoutons.setMinimumSize(new Dimension(200, 200));
		panelBoutons.setMaximumSize(new Dimension(200, 200));
		panelBoutons.setPreferredSize(new Dimension(200, 200));
		boutonFinDeTour.setMinimumSize(new Dimension(200, 200));
		boutonFinDeTour.setMaximumSize(new Dimension(200, 200));
		boutonFinDeTour.setPreferredSize(new Dimension(200, 200));
		panelBoutons.add(boutonFinDeTour);

		// Panel du joueur
		JPanel panelJoueurBouton = new JPanel();
		panelJoueurBouton.setLayout(new BorderLayout());
		panelJoueurBouton.setMinimumSize(new Dimension(1080, 200));
		panelJoueurBouton.setMaximumSize(new Dimension(1080, 200));
		panelJoueurBouton.setPreferredSize(new Dimension(1080, 200));
		panelJoueurBouton.add(panelJoueurDivinite, BorderLayout.WEST);
		panelJoueurBouton.add(panelJoueur, BorderLayout.CENTER);
		panelJoueurBouton.add(boutonFinDeTour, BorderLayout.EAST);

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

	@Override
	public void update(Observable o, Object arg) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public void afficherJoueurDivinite(Carte carte) {
		panelJoueurDivinite.add(new VueCarte(carte), BorderLayout.WEST);
		afficherMessage(carte.toString());
		panelJoueur.updateUI();
	}

	public void afficherMain(List<Carte> cartes) {
		for (Carte carte:cartes)
		{
			panelJoueur.add(new VueCarte(carte));
			afficherMessage(carte.toString());
		}
		panelJoueur.updateUI();
	}
}
