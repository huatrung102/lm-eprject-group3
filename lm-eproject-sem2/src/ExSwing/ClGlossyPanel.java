package ExSwing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;
/**
 * 
 * @author dhilshuk
 * 
 */
@SuppressWarnings("serial")
public class ClGlossyPanel extends JPanel {

	private int theme = Theme.GLOSSY_BLUEGRAY_THEME;
	private String title = "";
	private BufferedImage panelImage;
	private Font FONT;
	private Paint bgColor;
	private GradientPaint fgColor;
	private Color textColor;
	@SuppressWarnings("rawtypes")
	private List colors;

	/**
	 * Default Constructor.
	 */
	public ClGlossyPanel() {
		init();
	}

	/**
	 * Constructor with the title of the panel.
	 * 
	 * @param title
	 *            text of the panel.
	 */
	public ClGlossyPanel(String title) {
		this.title = title;
		init();
	}

	/**
	 * Constructor with theme of the panel.
	 * 
	 * @param theme
	 *            theme of the panel
	 */
	public ClGlossyPanel(int theme) {
		this.theme = theme;
		init();
	}

	/**
	 * Constructor with title and theme.
	 * 
	 * @param title
	 *            title of the panel
	 * @param theme
	 *            theme of the panel
	 */
	public ClGlossyPanel(String title, int theme) {
		this.title = title;
		this.theme = theme;
		init();
	}

	/**
	 * Constructor with title,theme and text color
	 * 
	 * @param title
	 *            title of the panel
	 * @param theme
	 *            theme of the panel
	 * @param textColor
	 *            text color
	 */
	public ClGlossyPanel(String title, int theme, Color textColor) {
		this.title = title;
		this.theme = theme;
		this.textColor = textColor;
		init();
	}

	/**
	 * Initializes
	 */
	private void init() {
		colors = Colors.getInStance().getGlossyColor(theme, 0, 50);
		fgColor = (GradientPaint) colors.get(0);
		if (colors.get(1) instanceof GradientPaint) {
			bgColor = ((GradientPaint) colors.get(1)).getColor1();
		} else {
			bgColor = (Paint) colors.get(1);
		}
		if (textColor == null) {
			textColor = (Color) colors.get(2);
		}
		this.FONT = new Font("Thoma", 1, 16);

	}

	@Override
	public void paintComponent(Graphics g2) {
		Graphics2D g2d = (Graphics2D) g2;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
				java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		if (panelImage == null || panelImage.getWidth() != w
				|| panelImage.getHeight() != h) {
			createPanelImage();
		}
		g2d.drawImage(panelImage, null, 0, 0);
	}

	/**
	 * Creates Panel Image.
	 */
	private void createPanelImage() {
		panelImage = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = panelImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
				java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Shape shape = null;
		Paint paint = null;
		int width = getWidth();
		int height = getHeight();

		// Background Panel
		shape = new RoundRectangle2D.Double(0, 0, width, height, 26, 26);
		paint = new GradientPaint(0, 0, (Color) bgColor, 0, 2 * getHeight(),
				Color.WHITE);
		g2d.setPaint(paint);
		g2d.fill(shape);

		//Curved Shape
		shape = createShape(width);
		paint = new GradientPaint(0, 2, new Color(255, 255, 255, 200), 0, 50,
				fgColor.getColor2());
		g2d.setPaint(paint);
		g2d.fill(shape);

		// Outer Border
		g2d.setStroke(new BasicStroke(3));
		shape = new RoundRectangle2D.Double(1, 1, width - 3, height - 3, 18.0,
				18.0);
		g2d.setPaint(new Color(255, 255, 255,100));
		g2d.draw(shape);

		g2d.setFont(FONT);
		if (textColor == Color.WHITE) {
			g2d.setColor(new Color(0, 0, 0));
		} else {
			g2d.setColor(new Color(255, 255, 255));
		}
		g2d.drawString(title, width / 8, 26);

		g2d.setColor(textColor);
		g2d.drawString(title, width / 8, 25);

		g2d.dispose();

	}

	/**
	 * Creates Shape.
	 * 
	 * @param width
	 * @return
	 */
	private Shape createShape(int width) {
		Shape shape = new GeneralPath();
		((GeneralPath) shape).moveTo(15.1308, 1.8172914);
		((GeneralPath) shape).curveTo(6.820799, 1.8172914, 2.1307993, 4.507291,
				2.1307993, 13.817291);
		((GeneralPath) shape).lineTo(2.1307993, 31.78604);
		((GeneralPath) shape).curveTo(39.09004, 37.30802, 86.78241, 40.69229,
				width / 2, 40.69229);
		((GeneralPath) shape).curveTo(width / 2, 40.69229, width / 2 + 30,
				38.130333, width - 1, 33.84854);
		((GeneralPath) shape).lineTo(width - 1, 11.817291);
		((GeneralPath) shape).curveTo(width - 1, 6.507291, width - 5,
				2.8172914, width - 13, 1.8172914);
		((GeneralPath) shape).lineTo(18.1308, 1.8172914);
		((GeneralPath) shape).closePath();
		return shape;
	}

	/**
	 * Returns the title.
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns Font
	 */
	public Font getFONT() {
		return FONT;
	}
    /**
     * Sets Font
     * @param font
     */
	public void setFONT(Font font) {
		FONT = font;
	}
}