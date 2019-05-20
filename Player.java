import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends JPanel {

	// x and y location for board
	private int x, y;

	// img variable that will contain board image
	private Image img;

	public Player(String filename) {
		// sets up board image to be drawn
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + filename);

		this.x = 600;
		this.y = 300;
		img = getImg(filename);

	}

	// method that allows board to be painted in the paint method
	// as opposed to simply being added into the frame
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);
	}

	// getter for the image
	private Image getImg(String path) {

		img = Toolkit.getDefaultToolkit().getImage(path);
		return img;
	}

}
