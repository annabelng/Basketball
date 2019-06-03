
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Basketball extends JPanel{

	private int x=300,y=440;
	private int width, height;
	private Image img;
	private double dx=0, dy=0;
	
	
	private int court_floor = 410;
	private int court_wall = 1173;
	
	int radius = 30;//size
	
	double gravity =4;
	
	double energyloss = .65;
	double dt = .2;
	
	Music music;
	Clip c;
	public Basketball(String filename) {
		//sets up board image to be drawn
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + filename);
		img = getImage(filename);
		music = new Music("BOUNCE+1.wav", c);
	}

	//method that allows board to be painted in the paint method
	// as opposed to simply being added into the frame
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);
		
	}
	
	
	public void move() {
		
		x+=dx;
		
		y+=dy;
		dy+=gravity;
		
		
	}
	

	//getter for the image
	private Image getImage(String path) {

		img = Toolkit.getDefaultToolkit().getImage("basketball.png");
		return img;
	}
	
	public void update(){
		if (x  > court_wall - radius - 1) {
			x = court_wall - radius - 1;
			dx = -dx;
		} else if (x  < 100) {
			x = 100;
			dx = -dx;
		} else {
			//x += dx;
		}

		if (y >= court_floor - radius) {
			dx *= .90;
			if (Math.abs(dx) < .1) {
				dx = 0;
			}
		}

		if (y > court_floor - radius) {
			y = court_floor - radius;
			music.playSong();
			dy *= energyloss;
			if(dy>0) {
				dy= -dy;
			}
			
		} else {
			dy += gravity * dt;
			y += dy * dt + .5 * gravity * dt * dt;
		}

		if (y >= court_floor - radius && Math.abs(dy) < 5) {
			dy = 0;
			y = court_floor - radius;
		}

	//	ball.setX(x);
//		ball.setY(y);
		
		
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x =x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y =y;
	}
	
	public int getW(){
		return img.getWidth(null);
	}
	
	public int getH(){
		return img.getHeight(null);
	}
	
	public void setdx(double d){
		this.dx = d;
	}
	
	public void setdy(double d){
		this.dy = d;
	}
	
	public void shoot(int hoopX, int hoopY, int courtLength, int ballX, int ballY){
		double dis = Math.abs(ballX - hoopX);
		//double successProb = (double)(1-(double)(dis)/(courtLength));
		
		//velocity = distance/time
		//set time it takes for the ball to reach the hoop
		
		//if(Math.random()<=successProb){
			dx = 20;
			dy =((double)(hoopY)/((double)(dis)/dx)) - (double)(0.5*(gravity)*((double)(dis)/dx));
		//}else{
			//dx = 20;
			//dy= ((double)(hoopY)/((double)(dis)/dx)) - (double)(0.5*(gravity)*((double)(dis)/dx))+(double)(Math.random()*5+5+1));
		//}
		//
	}
	
}
