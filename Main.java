

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main {
	Pane p;
	Setting configurations;
	
	public Main(Pane _p, Setting settings){
		p = _p;
		configurations = settings;
	}

	public void start(Stage primaryStage) throws Exception {
		Scene scene = primaryStage.getScene();
		ImageView bg = new ImageView(new Image("file:src/bg.png"));
		bg.setFitWidth(scene.getWidth());
        bg.setFitHeight(scene.getHeight());
		p.getChildren().add(bg);
		KeyController kc = new KeyController(scene, configurations);
		kc.addListeners();
		Pistoleros pisto = new Pistoleros(0,0,100,scene.getWidth(),scene.getHeight(),32,32,0,0,5000,new Gun(10,"name",10),kc);
		ArrayList<Vampire> vamp = new ArrayList<Vampire>();
		for(int i=0;i<4;i++)
		vamp.add(new Vampire(i*32,scene.getWidth(),100,scene.getHeight(),scene.getHeight(),32,32,32*3,36*3,3,50,true));
		ArrayList<Obstacle> Obstacle = new ArrayList<Obstacle>();
		Obstacle.add(new Obstacle(150,150,Game.WIDTH,Game.Height,35,35,0,0));

		System.out.println("Obstacle"+Obstacle.size());
		Container c = new Container(vamp,pisto,Obstacle);
		ContainerView cv = new ContainerView(c,p);
		Game game = new Game(cv);
		game.start();
	}
}
