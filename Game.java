import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Game extends VBox{
	ContainerView cont;
	public final  static double WIDTH =1270;
	public final  static double Height =ContainerView.HEIGHT+Information.HEIGHT;
	Information hb ;
	public Game(KeyController kc){
        Pane plat = new Pane();
		kc.addListeners();
		
		Pistoleros pisto = new Pistoleros(0,0,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,0,0,3,new Gun(50,"name",5),kc);
		ArrayList<Vampire> vamp = new ArrayList<Vampire>();
		for(int i=0;i<20;i++)
		vamp.add(new Vampire(i*32,ContainerView.HEIGHT-32,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,32*3,36*3,3,1,true));
		ArrayList<Obstacle> Obstacle = new ArrayList<Obstacle>();
		Obstacle.add(new Obstacle(150,150,ContainerView.WIDTH,ContainerView.HEIGHT,35,35,0,0,10));
		
		Container c = new Container(vamp,pisto,Obstacle);
		cont = new ContainerView(c,plat);
		hb = new Information(cont.getVamps(),(Pistoleros) cont.getPlayer().charact,c.timer);
		cont.container.gameSpeedVampire.bind(hb.slidspeedVamp.valueProperty());
		cont.container.gameSpeedPistolero.bind(hb.slidspeedPist.valueProperty());
		this.getChildren().add(plat);
		this.getChildren().add(hb);
		this.resize(WIDTH, 100+Height);
		
	}
	public void start(){
		AnimationTimer timer  = new AnimationTimer() {
			long lastNanoTime = System.nanoTime();
			@SuppressWarnings("unused")
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
