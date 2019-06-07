import javax.sound.sampled.Clip;

/**
 * Class for the basketball object
 * 
 * Inherits drawing, getter, and setter methods from imageObject class
 * 
 * Contains methods to simulate physics including drribling, bouncing, and
 * shooting
 * 
 * @author annabelNg, jaidenSmith, scottyKrysl, minjunKim
 *
 */
public class Basketball extends imageObject {

	// change in x and y variables
	private double dx = 10, dy = 10;

	// boundaries for the ball to bounce in
	private int court_floor = 350;
	private int court_wall = 1200;

	// size of the ball
	private int radius = 30;

	// different variables that affect physics of the ball
	double gravity = 15;
	double energyloss = .65;
	double dt = .2;

	Music music;
	Clip c;

	/**
	 * supers the different params and sets up the image and basic variables
	 * 
	 * @param filename
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Basketball(String filename, int x, int y, int width, int height) {
		// sets up board image to be drawn
		super(x, y, width, height, filename);
		music = new Music("BOUNCE+1.wav", c);
	}

	/**
	 * Bounce method that checks if the ball is within boundaries and changes
	 * the x and y variables accordingly
	 * 
	 * After all the checks have been executed, the x and y values are set using
	 * x and y setters
	 */
	public void bounce() {

		// When the ball hits either side of the court's edges,
		// it will bounce the opposite way
		if (x + dx > court_wall) {
			x = court_wall;
			dx = -dx;
		} else if (x + dx < 100) {
			x = 100;
			dx = -dx;
		} else {
			x += dx;
		}

		// Every time the ball hits the floor its x velocity goes down a little
		if (y == court_floor - radius - 1) {
			dx *= .6;
			if (Math.abs(dx) < .1) {
				dx = 0;
			}
		}

		// The ball will bounce back up after hitting the floor with less energy
		// The music object will play a noise after every bounce
		if (y > court_floor - radius - 1) {
			y = court_floor - radius - 21;
			music.playSong();
			dy *= energyloss;
			dy = -dy;
		} else {
			dy += gravity * dt;
			y += dy * dt + .5 * gravity * dt * dt;
		}

		//When the ball's y velocity is below 5, it will stop bouncing
		if (y == court_floor - radius - 21 && Math.abs(dy) < 5) {
			dy = 0;
			y = court_floor - radius - 21;
		}
		
		//If the x velocity is lower than 0.5, the ball will stop bouncing
		if (Math.abs(dx) <= 0.5) {
			dy *= 0.1;
		}
		this.setX(x);
		this.setY(y);
	}

	/**
	 * If driver calls the dribble method, the ball's y changes according to the
	 * equation
	 * 
	 * It checks for the bottom or the point at which it should stop bouncing
	 * 
	 * @param bottom
	 */
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

	/**
	 * If driver calls the dribble method, the ball's y changes according to the
	 * equation
	 * 
	 * It checks for the bottom or the point at which it should stop bouncing
	 * 
	 * Adds a change in x to account for change in direction
	 * 
	 * @param bottom
	 */
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

	/**
	 * When ball is shot, the change in y remains at a constant of -55 and dx
	 * depends on parameter
	 * 
	 * Simulates an arc
	 * 
	 * @param dx
	 */
	public void shoot(int dx) {
		this.setdx(dx);
		this.setdy(-55);
	}

	/**
	 * setter for the changes in x
	 * 
	 * @param d
	 */
	public void setdx(double d) {
		this.dx = d;
	}

	/**
	 * setter for the change in y
	 * 
	 * @param d
	 */
	public void setdy(double d) {
		this.dy = d;
	}

}