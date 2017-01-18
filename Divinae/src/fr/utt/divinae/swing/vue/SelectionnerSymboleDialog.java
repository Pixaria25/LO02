package fr.utt.divinae.swing.vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.utt.divinae.api.cartes.types.Carte;

public class SelectionnerSymboleDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel fenetre;
	private int choix;
	
	public SelectionnerSymboleDialog(JFrame parent, String titre, String symbolePrefixe, int max) {
		super(parent, titre, true);
		this.setMinimumSize(new Dimension(320, 240));
		this.setMaximumSize(new Dimension(320, 240));
		this.setPreferredSize(new Dimension(320, 240));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		init(symbolePrefixe, max);
	}
	private void init(String symbolePrefixe, int max) {
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel pane = new JPanel();

		for (int id=1 ; id <= max ; id++) {
			VueSymbole vue = new VueSymbole(symbolePrefixe, id);
			vue.addActionListener(this);
			fenetre.add(vue);
		}
		
		pane.add(fenetre);
		this.getContentPane().add(pane);
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object  source=e.getSource();

		if (source instanceof VueSymbole)
		{
			choix = ((VueSymbole)source).getId();
			setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Aucune action prévue pour " + source);
		}
	}
	
	public int getChoix() {
		return choix;
	}
}
