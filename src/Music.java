import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	protected MediaPlayer med;
	protected boolean playing;
	public Music(int i){
		Media m ;
		if(i==0)
			 m = new Media(getClass().getResource("vampire.mp3").toString());
		else
			m = new Media(getClass().getResource("musique_menu_final.mp3").toString());
		med = new MediaPlayer(m);
		med.setCycleCount(Timeline.INDEFINITE);
		med.setVolume(0.4);
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
