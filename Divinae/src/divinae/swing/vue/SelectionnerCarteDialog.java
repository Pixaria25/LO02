package divinae.swing.vue;

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

import divinae.api.cartes.types.CarteAction;

public class SelectionnerCarteDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel fenetre;
	private JButton ok;
	private JButton annuler;
	private int choixCarte;
	
	public SelectionnerCarteDialog(JFrame parent, String titre, List<? extends CarteAction> listeCartes) {
		super(parent, titre, true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		init(listeCartes);
	}
	private void init(List<? extends CarteAction> listeCartes) {
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel pane = new JPanel();
		
		ok = new JButton("Ok");
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				setVisible(false);
			}
		});

		annuler = new JButton("Annuler");
		annuler.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				setVisible(false);
			}
		});

		int id = 0;
		for (CarteAction carte: listeCartes) {
			VueCarte vueCarte = new VueCarte(id++, carte, null);
			vueCarte.addActionListener(this);
			fenetre.add(vueCarte);
		}
		
		fenetre.add(ok);
		fenetre.add(annuler);
		
		pane.add(fenetre);
		
		
		this.getContentPane().add(pane);
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object  source=e.getSource();

		if (source==annuler)
		{
			setVisible(false);
		}
		else if (source instanceof VueCarte)
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
