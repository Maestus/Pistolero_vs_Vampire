import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WinPane extends Pane{

	static Label win = new Label();
	
	public WinPane(){
		this.setPrefHeight(Main.HEIGHT);
		this.setPrefWidth(Main.WIDTH);
		
		
		win.setText("WIN !");
		
		win.setAlignment(Pos.CENTER);
		
		ImageView background = new ImageView(new Image("file:src/bg_menu.png"));
        background.setFitWidth(this.getPrefWidth());
        background.setFitHeight(this.getPrefHeight());

        this.getChildren().add(background);
        this.getChildren().add(win);
        //Main.pane.getChildren().add(this);
        
	}
	
}
