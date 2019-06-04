import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Basketball extends JPanel {

	private int x = 300, y = 0;
	private int width, height;
	private Image img;
	private double dx = 10, dy = 10;

	int court_floor = 350;
	int court_wall = 1200;

	int radius = 30;// size

	double gravity = 15;
	double energyloss = .65;
	double dt = .2;

	public Basketball(String filename) {
		// sets up board image to be drawn
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + filename);
		this.x = 0;
		this.y = 0;

		img = getImage(filename);

	}

	// method that allows board to be painted in the paint method
	// as opposed to simply being added into the frame
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);

	}

	// getter for the image
	private Image getImage(String path) {

		img = Toolkit.getDefaultToolkit().getImage("basketball.png");
		return img;
	}

	public void move() {

		x += dx;

		y += dy;
		dy += gravity;

	}

	public void update() {
		if (x > court_wall - radius - 1) {
			x = court_wall - radius - 1;
			dx = -dx;
		} else if (x < 100) {
			x = 100;
			dx = -dx;
		} else {
			// x += dx;
		}

		if (y >= court_floor - radius) {
			dx *= .90;
			if (Math.abs(dx) < .1) {
				dx = 0;
			}
		}

		if (y > court_floor - radius) {
			y = court_floor - radius;
			// music.playSong();
			dy *= energyloss;
			if (dy > 0) {
				dy = -dy;
			}

		} else {
			dy += gravity * dt;
			y += dy * dt + .5 * gravity * dt * dt;
		}

		if (y >= court_floor - radius && Math.abs(dy) < 5) {
			dy = 0;
			y = court_floor - radius;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return img.getWidth(null);
	}

	public int getH() {
		return img.getHeight(null);
	}

	public void bounce() {
		if (x + dx > court_wall) {
			x = court_wall;
			dx = -dx;
		} else if (x + dx < 100) {
			x = 100;
			dx = -dx;
		} else {
			x += dx;
		}

		if (y == court_floor - radius - 1) {
			dx *= .95;
			if (Math.abs(dx) < .1) {
				dx = 0;
			}
		}

		if (y > court_floor - radius - 1) {
			y = court_floor - radius - 21;
			// music.playSong();
			dy *= energyloss;
			dy = -dy;
		} else {
			dy += gravity * dt;
			y += dy * dt + .5 * gravity * dt * dt;
		}

		if (y == court_floor - radius - 21 && Math.abs(dy) < 5) {
			dy = 0;
			y = court_floor - radius - 21;
		}
		this.setX(x);
		this.setY(y);
	}

	public void dribbleLeft(int bottom) {
		this.setdy(10);
		if (y < (bottom + 10)) {
			y += dy * .9 + .8 * gravity * 4;
		} else {
			y -= 5;
		}

		this.setX(x);
		this.setY(y);
	}

	public void dribbleRight(int bottom) {

		if (y < (bottom + 10)) {
			y += dy * .9 + .8 * gravity * 4;
		} else {
			y -= 5;
		}
		x += 25;
		this.setX(x);
		this.setY(y);
	}

	public void setdx(double d) {
		this.dx = d;
	}

	public void setdy(double d) {
		this.dy = d;
	}
	
	public void shoot(int dx){
		
		this.setdx(dx);
		
		this.setdy(-55);
	}


}
