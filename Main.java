import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	protected static final int WIDTH = 1280;
    protected static final int HEIGHT = 760;
	Pane pane;
	Game game;
	MenuPrincipal menu;
	Setting configurations;
	
	public void loadGame(KeyController k, ImageView imageView){
		k.addListeners();
		pane.getChildren().clear();
		game = new Game(k, imageView);
		pane.getChildren().add(game);
		game.start();
	}
	
	public void start(Stage primaryStage) throws Exception {
		pane = new Pane();
		pane.setPrefWidth(WIDTH);
		pane.setPrefHeight(HEIGHT);
		
		menu = new MenuPrincipal();
		
		Scene scene = new Scene(pane);
        primaryStage.setTitle("FxBattle");
        primaryStage.setScene(scene);
        
		menu.start(this);
		
        primaryStage.show();
	}
	
    public static void main(String[] args) {
        launch(args);
    }

}
