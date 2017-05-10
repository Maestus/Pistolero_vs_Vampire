

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
		
		KeyController kc = new KeyController(scene, configurations);
		kc.addListeners();
		Game game = new Game(kc);
        p.getChildren().add(game);
		game.start();
	}
}
