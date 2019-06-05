import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Scoreboard extends JPanel{
	private int x, y;
	private Image img;
	
	public Scoreboard(String filename) {
		//sets up board image to be drawn
			String src = new File("").getAbsolutePath() + "/src/";
			ImageIcon ast = new ImageIcon(src + filename);
			this.x = 575;
			this.y = 0;				
			img = getImage(filename);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, x, y, this);
		
		
		
	}

	//getter for the image
	private Image getImage(String path) {

		img = Toolkit.getDefaultToolkit().getImage("scoreboard.jpg");
		return img;
	}
	
	public void update(){
		
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
	
	
}

