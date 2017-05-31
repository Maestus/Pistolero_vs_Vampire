import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Game {
	Container c;
	ContainerView cont;
	Pistoleros pisto;
	ArrayList<Vampire> vamp;
	Information hb ;
	InGameOptions ho;
	boolean on_pause = false;
	Music mus = new Music(0);
	
    PauseMenu pause_menu = new PauseMenu(this);
	ArrayList<Obstacle> Obstacle = new ArrayList<Obstacle>();
	ImageView background;
	AnimationTimer gameloop;

	Pane pane;
	
	public Game(Pane pane){
		this.pane = pane;
	}
	
	public void loadGame(KeyController kc, ImageView background, Pane pane){
		kc.addListeners();
		pisto = new Pistoleros(0,0,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,0,0,30,new Gun(50,"name",100),kc);
		vamp = new ArrayList<Vampire>();
		for(int i=0;i<20;i++)
			vamp.add(new Vampire(i*32,ContainerView.HEIGHT-32,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,32*3,36*3,3,1,true));

		this.background = background;
		mus.play();

	}
	
	public void start(){
		gameloop  = new AnimationTimer() {
			long lastNanoTime = System.nanoTime();
			@Override
			public void handle(long now) {
				if(now-lastNanoTime>10000000.0){						
					if(cont.container.pause && !on_pause){
						pause_menu.restart();
						on_pause = true;
					} else {
						double elapsedTime = (now - lastNanoTime) / 1000000000.0;
						lastNanoTime = now;
						cont.container.update(elapsedTime);
						cont.update();
					}
					
					if(!cont.container.pause && on_pause){
						if(pause_menu.displayed()){
							pause_menu.close();
							pause_menu.getChildren().clear();
						}
						
						on_pause = false;
					}
				}
			}
		};
		gameloop.start();
	}
	
	public void KeyControllerchanged(KeyController kc){
		kc.addListeners();
		pisto.KeyControllerchanged(kc);
	}

	public void loadObstacles(String string) {
        String line = null;
        BufferedReader out;
		FileReader fstream;
		File md_files = new File("res/creations");
		if (md_files.isDirectory()) {
			try {
	            for (final File f : md_files.listFiles()) {
	            	if(f.getName().equals(string)){
						fstream = new FileReader(f);
	    				out = new BufferedReader(fstream);
	    				while((line = out.readLine()) != null) {
	    					String obstacle = "file:"+line;
	    					System.err.println("loading : "+ obstacle);
	        		    	double posx = Double.parseDouble(out.readLine());
	        		    	double posy = Double.parseDouble(out.readLine());
	        				Obstacle.add(new Obstacle(obstacle, posx,posy,ContainerView.WIDTH,ContainerView.HEIGHT,35,35,0,0,1));
	    	            }              		    	
	            		   
	            	}
	            }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.initialize();
	}

	public void initialize() {
		c = new Container(vamp,pisto,Obstacle);
		cont = new ContainerView(c, background, pane);
		hb = new Information(cont.getVamps(),(Pistoleros) cont.getPlayer().charact,c.timer);
		ho = new InGameOptions(cont.getVamps(),(Pistoleros) cont.getPlayer().charact,c.timer,mus);
		cont.container.gameSpeedVampire.bind(ho.slidspeedVamp.valueProperty());
		cont.container.gameSpeedPistolero.bind(ho.slidspeedPist.valueProperty());
		pane.getChildren().add(hb);
		pane.getChildren().add(ho);
		this.start();
	}
	
}
