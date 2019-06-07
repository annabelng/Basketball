/**
 * 
 * @author annabelng, jaidenSmith
 * Sets up the scoreboard image that will contain the different scores
 * Inherits from the imageObject class in order to get the
 * paint method, getters, and setters
 */
public class Scoreboard extends imageObject {

	/**
	 * Extends imageObject parent class, which supers 
	 * the x,y coordinates, width, height, and links the 
	 * filename to the imageIcon
	 * 
	 * Child class also contains paint method and getters + setters
	 * @param filename
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Scoreboard(String filename, int x, int y, int width, int height) {
		// sets up board image to be drawn
		super(x, y, width, height, filename);

	}

}