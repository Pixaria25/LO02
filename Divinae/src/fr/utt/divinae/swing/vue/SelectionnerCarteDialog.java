package fr.utt.divinae.swing.vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.utt.divinae.api.cartes.types.Carte;

public class SelectionnerCarteDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel fenetre;
	private int choixCarte;
	
	public SelectionnerCarteDialog(JFrame parent, String titre, List<? extends Carte> listeCartes) {
		super(parent, titre, true);
		this.setMinimumSize(new Dimension(800, 240));
		this.setMaximumSize(new Dimension(800, 240));
		this.setPreferredSize(new Dimension(800, 240));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		init(listeCartes);
	}
	private void init(List<? extends Carte> listeCartes) {
		fenetre = new JPanel();
		fenetre.setLayout(new BoxLayout(fenetre, BoxLayout.X_AXIS));
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(fenetre);
		
		JPanel pane = new JPanel();

		int id = 0;
		for (Carte carte: listeCartes) {
			VueCarte vueCarte = new VueCarte(id++, carte, null);
			vueCarte.addActionListener(this);
			fenetre.add(vueCarte);
		}
		
		pane.add(scroll);
		this.getContentPane().add(pane);
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object  source=e.getSource();

		if (source instanceof VueCarte)
		{
			choixCarte = ((VueCarte)source).getId();
			setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Aucune action prévue pour " + source);
		}
	}
	
	public int getChoixCarte() {
		return choixCarte;
	}
}
