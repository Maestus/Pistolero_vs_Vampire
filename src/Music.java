import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	protected MediaPlayer med;
	protected boolean playing;
	public Music(){
		Media m = new Media(getClass().getResource("vampire.mp3").toString());
		med = new MediaPlayer(m);
		med.setCycleCount(Timeline.INDEFINITE);
		med.setVolume(5);
	}
	
	public void play(){
		playing = true;
		med.play();
	}
	public void pause(){
		playing = false;
		med.pause();
	}
	public void stop(){
		playing = false;
		med.stop();
	}
}
