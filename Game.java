import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Game extends Pane{
	ContainerView cont;
	Pistoleros pisto;
	Pane plat;
	ArrayList<Vampire> vamp;
	public final  static double WIDTH =1280;
	public final  static double HEIGHT =ContainerView.HEIGHT + InGameOptions.HEIGHT;
	Information hb ;
	InGameOptions ho;
	boolean on_pause = false;
    PauseMenu pause_menu;

	public Game(KeyController kc, ImageView background){
		pisto = new Pistoleros(0,0,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,0,0,30,new Gun(50,"name",100),kc);
		vamp = new ArrayList<Vampire>();
		for(int i=0;i<20;i++)
			vamp.add(new Vampire(i*32,ContainerView.HEIGHT-32,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,32*3,36*3,3,1,true));
		ArrayList<Obstacle> Obstacle = new ArrayList<Obstacle>();
		Obstacle.add(new Obstacle(150,150,ContainerView.WIDTH,ContainerView.HEIGHT,35,35,0,0,10));
		pause_menu = new PauseMenu();
		Container c = new Container(vamp,pisto,Obstacle);
		cont = new ContainerView(c, background, this);
		hb = new Information(cont.getVamps(),(Pistoleros) cont.getPlayer().charact,c.timer);
		ho = new InGameOptions(cont.getVamps(),(Pistoleros) cont.getPlayer().charact,c.timer);
		cont.container.gameSpeedVampire.bind(ho.slidspeedVamp.valueProperty());
		cont.container.gameSpeedPistolero.bind(ho.slidspeedPist.valueProperty());
		this.getChildren().add(hb);
		this.getChildren().add(ho);
		this.prefHeight(HEIGHT);
		this.prefWidth(WIDTH);
		plat = this;
	}
	
	public void start(){
		AnimationTimer timer  = new AnimationTimer() {
			long lastNanoTime = System.nanoTime();
			@SuppressWarnings("unused")
			long lastUpdate=0;
			@Override
			public void handle(long now) {
				if(now-lastNanoTime>10000000.0){						
					if(cont.container.pause && !on_pause){
					//	pause_menu.start(plat);
						on_pause = true;
					} else {
						double elapsedTime = (now - lastNanoTime) / 1000000000.0;
						lastNanoTime = now;
						cont.container.update(elapsedTime);
						cont.update();
						lastUpdate = now;
					} 
					if(!cont.container.pause){
						on_pause = false;
						plat.getChildren().remove(pause_menu.background);
						plat.getChildren().remove(pause_menu.menuBox);
						plat.getChildren().remove(pause_menu.line);
					}
				}
			}
		};
		timer.start();
	}
	
	public void KeyControllerchanged(KeyController kc){
		kc.addListeners();
		pisto.KeyControllerchanged(kc);
	}
	
	
	
}
