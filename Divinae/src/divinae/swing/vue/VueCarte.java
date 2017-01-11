package divinae.swing.vue;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import divinae.api.cartes.types.Carte;

public class VueCarte extends JButton
{
	private Carte carte;
	public VueCarte(Carte carte) {
		this.carte = carte;
		this.setMinimumSize(new Dimension(132, 200));
		this.setMaximumSize(new Dimension(132, 200));
		this.setPreferredSize(new Dimension(132, 200));
		//this.setText(carte.getNom());
		try
		{
			this.setIcon(new ImageIcon(scale("/images/" + carte.getId() +".png", 132, 200)));
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
}
