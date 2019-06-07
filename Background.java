/**
 * Background of the game
 * Prevents images from glitching out on the screen
 * 
 * Inherits the paint method, getters, and setters, from parent imageIcon class
 * @author annabelng, jaidenSmith
 *
 */
public class Background extends imageObject{

	/**
	 * Takes in the filename of the object so the 
	 * image and imageIcon can be initialized
	 * 
	 * Uses super to construct the image and initialize x,y,width,height values
	 * @param filename
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Background(String filename, int x, int y, int width, int height) {
		//sets up board image to be drawn
		super(x,y,width,height,filename);
	}
	
}