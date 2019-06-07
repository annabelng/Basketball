
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 * @author jaidenSmith
 * Class that deals with the background music in the intro
 * and also with the ball bouncing
 */
public class Music {
	Clip sound;
	private File musicLocation;
	private AudioInputStream audio;
	
	/**
	 * Pass in a string (filename of sound to be played) and a clip
	 * Instantiate the music files
	 * 
	 * The clips have a start and stop method
	 * @param music
	 * @param clip
	 */
	public Music(String music, Clip clip){ 
		//initializes the variables
		musicLocation = new File(music);
		clip = sound; 
	}
	
	/**
	 * Method used to play the song
	 * Opens the audio file and plays it through a try and catch exception
	 */
	public void playSong(){
		try{
			if(musicLocation.exists()){
				audio = AudioSystem.getAudioInputStream(musicLocation);
				sound = AudioSystem.getClip();
				
				sound.open(audio);
				sound.start();
			}
		}
		catch(Exception e){
			
		}
	}
	
	/**
	 * Method used to return the clip of a sound
	 * @return
	 */
	public Clip getClip(){ //use this to stop/pause a sound -- musicObject.getClip.stop();
		return sound;
	}
	

}