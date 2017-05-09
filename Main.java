import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Pane p = new Pane();
		p.setPrefSize(Game.WIDTH,Game.Height);
		Scene scene = new Scene(p);
		KeyController kc = new KeyController(scene);
		kc.addListeners();
		ImageView bg = new ImageView(new Image("file:src/bg.png"));
		p.getChildren().add(bg);
		Pistoleros pisto = new Pistoleros(0,0,100,Game.WIDTH,Game.Height,32,32,0,0,5000,new Gun(10,"name",10),kc);
		ArrayList<Vampire> vamp = new ArrayList<Vampire>();
		for(int i=0;i<4;i++)
		vamp.add(new Vampire(i*32,Game.Height-32,100,Game.WIDTH-32,Game.Height-32,32,32,32*3,36*3,3,50,true));
		ArrayList<Obstacle> Obstacle = new ArrayList<Obstacle>();
		Obstacle.add(new Obstacle(150,150,Game.WIDTH,Game.Height,35,35,0,0));
		System.out.println("Obstacle"+Obstacle.size());
		Container c = new Container(vamp,pisto,Obstacle);
		ContainerView cv = new ContainerView(c,p);
		Game game = new Game(cv);
		game.start();
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
