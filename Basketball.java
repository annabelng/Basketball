import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Basketball extends JPanel{

	private int x,y;
	private int width, height;
	private Image img;
	private double dx=10, dy=10;
	
	int court_floor = 410;
	int court_wall = 1090;
	
	int radius = 50;//size
	
	double gravity =15;
	double energyloss = .65;
	double dt = .2;
	
	public Basketball(String filename) {
		//sets up board image to be drawn
		String src = new File("").getAbsolutePath() + "/src/";
		ImageIcon ast = new ImageIcon(src + filename);
		this.x = 0;
		this.y = 0;

		img = getImage(filename);
		
	}

	//method that allows board to be painted in the paint method
	// as opposed to simply being added into the frame
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);
		
		
		
	}

	//getter for the image
	private Image getImage(String path) {

		img = Toolkit.getDefaultToolkit().getImage("basketball.jpg");
		return img;
	}
	
	public void update(){
		if(x+dx > court_wall - radius-1){
			x = court_wall-radius-1;
			dx = -dx;
		}else if(x+dx < 100){
			x = 100;
			dx = -dx;
		}else{
			x+=dx;
		}
		
		if(y == court_floor - radius -21){
			dx *= .95;
			if(Math.abs(dx) < .1){
				dx =0;
			}
		}
		
		if(y > court_floor - radius -21){
			y = court_floor - radius-21;
			dy *= energyloss;
			dy = -dy;
		}else{
			dy+= gravity*dt;
			y += dy*dt + .5*gravity*dt*dt;
		}
		
		if(y == court_floor - radius -21 && Math.abs(dy) < 5){
			dy = 0;
			y = court_floor - radius - 21;
		}
		
		if(y != court_floor - radius-1){
			repaint();
		}
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
		this.dx = d;
	}
	
	public void shoot(int hoopX, int hoopY, int courtLength, int ballX, int ballY){
		double dis = Math.abs(ballX - hoopX);
		double successProb = (double)(1-(double)(dis)/(courtLength));
		
		//velocity = distance/time
		//set time it takes for the ball to reach the hoop
		
		if(Math.random()<=successProb){
			setdx(20);
			setdy(((double)(hoopY)/((double)(dis)/dx)) - (double)(0.5*(gravity)*((double)(dis)/dx)));
		}else{
			setdx(20);
			setdy(((double)(hoopY)/((double)(dis)/dx)) - (double)(0.5*(gravity)*((double)(dis)/dx))+(double)(Math.random()*5+5+1));
		}
		
	}
	
}
