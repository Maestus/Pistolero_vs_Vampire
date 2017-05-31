import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	public final  static double WIDTH = 1280;
	public final  static double HEIGHT = ContainerView.HEIGHT + InGameOptions.HEIGHT;
	Pane pane;
	MenuPrincipal menu;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		pane = new Pane();
		pane.setPrefWidth(WIDTH);
		pane.setPrefHeight(HEIGHT);

		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		
		menu = new MenuPrincipal();
		menu.start(pane);
		
		primaryStage.show();
	}

	public static void main(String [] args){
		launch(args);
	}
	
}