import javafx.animation.AnimationTimer;

public class Game {
	ContainerView cont;
	public final  static double WIDTH =400;
	public final  static double Height =400;
	
	public Game(ContainerView c){
		this.cont = c;
	}
	public void start(){
		AnimationTimer timer  = new AnimationTimer() {
			long lastNanoTime = System.nanoTime();
			long lastUpdate=0;
			@Override
			public void handle(long now) {
				if(now-lastNanoTime>10000000.0){
				double elapsedTime = (now - lastNanoTime) / 1000000000.0;
				lastNanoTime = now;
					cont.container.update(elapsedTime);
					cont.update();
					lastUpdate = now;
				}
			}
		};
		timer.start();
	}
	
}
