import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Hoops extends JPanel {

	//x and y location for board
	private int x, y;

	//img variable that will contain board image
	private Image img;
	
	public Hoops(String filename, String side) {
		//sets up board image to be drawn
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + filename);
		
		if(side.equals("right")) {
			this.x = 930;
			this.y = 110;
		}
		
		if(side.equals("left")) {
			this.x = 120;
			this.y = 110;
		}
		img = getImg(filename);
		
	}

	//method that allows board to be painted in the paint method
	// as opposed to simply being added into the frame
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);
	}

	//getter for the image
	private Image getImg(String path) {

		img = Toolkit.getDefaultToolkit().getImage(path);
		return img;
	}
	
	//getter for the image
	private Image getLeftHoop(String path) {

		img = Toolkit.getDefaultToolkit().getImage("LeftHoop.png");
		return img;
	}

}
