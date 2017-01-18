package fr.utt.divinae.swing.vue;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.utt.divinae.api.cartes.types.Carte;
import fr.utt.divinae.api.cartes.types.CarteAction;

public class VueCarte extends JButton
{
	public static int CARTE_LARGEUR = 132;
	public static int CARTE_LONGUEUR = 200;

	private int id;
	private CarteAction carteAction = null;
	private Carte carte = null;
	private VueSelection vueSelection;

	public VueCarte(int id, CarteAction carteAction, final VueSelection vueSelection)
	{
		this(id, (Carte)carteAction, vueSelection);
		this.carteAction = carteAction;
	}

	public VueCarte(int id, Carte carte, final VueSelection vueSelection)
	{
		this.id = id;
		this.carte = carte;
		this.vueSelection = vueSelection;
		this.setMinimumSize(new Dimension(CARTE_LARGEUR, CARTE_LONGUEUR));
		this.setMaximumSize(new Dimension(CARTE_LARGEUR, CARTE_LONGUEUR));
		this.setPreferredSize(new Dimension(CARTE_LARGEUR, CARTE_LONGUEUR));
		try
		{
			this.setIcon(new ImageIcon(scale("/images/" + carte.getId() +".png", CARTE_LARGEUR, CARTE_LONGUEUR)));
		} catch (IOException e) {
			// Icon not set
		}
	}

	public BufferedImage scale(String imageFileName, int targetWidth, int targetHeight) throws IOException
	{
		BufferedImage img = ImageIO.read(VueCarte.class.getResourceAsStream(imageFileName));
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = img;
		BufferedImage scratchImage = null;
		Graphics2D g2 = null;

		int w = img.getWidth();
		int h = img.getHeight();

		int prevW = w;
		int prevH = h;

		do {
			if (w > targetWidth) {
				w /= 2;
				w = (w < targetWidth) ? targetWidth : w;
			}

			if (h > targetHeight) {
				h /= 2;
				h = (h < targetHeight) ? targetHeight : h;
			}

			if (scratchImage == null) {
				scratchImage = new BufferedImage(w, h, type);
				g2 = scratchImage.createGraphics();
			}

			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

			prevW = w;
			prevH = h;
			ret = scratchImage;
		} while (w != targetWidth || h != targetHeight);

		if (g2 != null) {
			g2.dispose();
		}

		if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
			scratchImage = new BufferedImage(targetWidth, targetHeight, type);
			g2 = scratchImage.createGraphics();
			g2.drawImage(ret, 0, 0, null);
			g2.dispose();
			ret = scratchImage;
		}

		return ret;
	}

	public int getId() {
		return id;
	}
	
	public void setSelected(boolean selected) {
		if (vueSelection != null) {
			vueSelection.setSelected(selected);
		}
	}
	
	public boolean isSelected() {
		if (vueSelection == null)
		{
			return false;
		}
		return vueSelection.isSelected();
	}

	public CarteAction getCarteAction() {
		return carteAction;
	}
}
