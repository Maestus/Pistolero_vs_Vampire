import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	ArrayList<Obstacle> Obstacle = new ArrayList<Obstacle>();
	ImageView background;
	
	public Game(KeyController kc, ImageView background){
		pisto = new Pistoleros(0,0,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,0,0,30,new Gun(50,"name",100),kc);
		vamp = new ArrayList<Vampire>();
		for(int i=0;i<20;i++)
			vamp.add(new Vampire(i*32,ContainerView.HEIGHT-32,100,ContainerView.WIDTH,ContainerView.HEIGHT,32,32,32*3,36*3,3,1,true));

		pause_menu = new PauseMenu();
		this.prefHeight(HEIGHT);
		this.prefWidth(WIDTH);
		plat = this;
		this.background = background;
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

	public void loadObstacles(String string) {
        String line = null;
        BufferedReader out;
		FileReader fstream;
		// TODO Auto-generated method stub
		File md_files = new File(getClass().getClassLoader().getResource("res/creations").getFile());
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
	        				Obstacle.add(new Obstacle(obstacle, posx,posy,ContainerView.WIDTH,ContainerView.HEIGHT,35,35,0,0,10));
	    	            }              		    	
	            		   
	            	}
	            }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.init();
	}

	protected void init() {
		// TODO Auto-generated method stub
		Container c = new Container(vamp,pisto,Obstacle);
		cont = new ContainerView(c, background, this);
		hb = new Information(cont.getVamps(),(Pistoleros) cont.getPlayer().charact,c.timer);
		ho = new InGameOptions(cont.getVamps(),(Pistoleros) cont.getPlayer().charact,c.timer);
		cont.container.gameSpeedVampire.bind(ho.slidspeedVamp.valueProperty());
		cont.container.gameSpeedPistolero.bind(ho.slidspeedPist.valueProperty());
		this.getChildren().add(hb);
		this.getChildren().add(ho);
		this.start();
	}
}
