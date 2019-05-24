import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Music {
	Clip sound;
	private File musicLocation;
	private AudioInputStream audio;
	
	public Music(String music, Clip clip){ //pass in a string (filename of sound to be played) and a clip
		musicLocation = new File(music);
		 clip = sound; //clips have a start and stop method
	}
	
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
	
	public Clip getClip(){ //use this to stop/pause a sound -- musicObject.getClip.stop();
		return sound;
	}
	

}
