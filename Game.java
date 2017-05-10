import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Game extends VBox{
	ContainerView cont;
	public final  static double WIDTH =1270;
	public final  static double Height =ContainerView.HEIGHT+Information.HEIGHT;
	Information hb ;
	public Game(KeyController kc){
		System.out.println(this.isFocused());
        Pane plat = new Pane();
		kc.addListeners();
		
		Pistoleros pisto = new Pistoleros(0,0,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,0,0,5000,new Gun(50,"name",5),kc);
		ArrayList<Vampire> vamp = new ArrayList<Vampire>();
		for(int i=0;i<4;i++)
		vamp.add(new Vampire(i*32,ContainerView.HEIGHT-32,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,32*3,36*3,3,50,true));
		ArrayList<Obstacle> Obstacle = new ArrayList<Obstacle>();
		Obstacle.add(new Obstacle(150,150,ContainerView.WIDTH,ContainerView.HEIGHT,35,35,0,0));
		
		Container c = new Container(vamp,pisto,Obstacle);
		cont = new ContainerView(c,plat);
		hb = new Information(cont.getVamps(),(Pistoleros) cont.getPlayer().charact);
		cont.container.gameSpeedVampire.bind(hb.slidspeedVamp.valueProperty());
		this.getChildren().add(plat);
		this.getChildren().add(hb);
		this.resize(WIDTH, 100+Height);
		
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
