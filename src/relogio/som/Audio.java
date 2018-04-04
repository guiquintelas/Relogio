package relogio.som;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	private Clip clip;
	public String nome;

	public Audio(String fileName) {
		
		URL url = getClass().getResource(fileName);

		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		//retira a terminação .wav ou .mp3
		this.nome = fileName.substring(0, fileName.length() - 4);

	}
	
	public String toString() {
		return nome;
	}

	public void play() {
		clip.stop();
		
		if (clip.isRunning() == false) {
			clip.loop(100);
		}							
	}
	
	public void stop() {
		clip.stop();
		clip.setFramePosition(0);
	}

}
