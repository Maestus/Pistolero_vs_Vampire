import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InGameOptions extends Pane{
	public final static double WIDTH = 1280;
	public final static double HEIGHT = 50;
	Slider slidspeedVamp ;
	Slider slidspeedPist;
	public InGameOptions(SimpleListProperty<VampireView> vamps,Pistoleros pist,SimpleIntegerProperty timer){
		
		this.setPrefHeight(HEIGHT);
		this.setPrefWidth(WIDTH);
		
		HBox sliders = new HBox(5);
		sliders.setAlignment(Pos.CENTER);
		
		HBox vampireSpeed= new HBox(5);

		Text vspeed = new Text("vampire speed ");
		vspeed.setFont(Font.loadFont("file:src/Disko.ttf", 20));
		vspeed.setFill(Color.rgb(255,150,50));
		slidspeedVamp = new Slider(0.1,2,1);
		slidspeedVamp.setShowTickLabels(true);
		slidspeedVamp.setShowTickMarks(true);
		slidspeedVamp.setMajorTickUnit(0.5);
		slidspeedVamp.setBlockIncrement(0.1);
		vampireSpeed.getChildren().addAll(vspeed,slidspeedVamp);
		sliders.getChildren().add(vampireSpeed);

		
		HBox pistolerosSpeed = new HBox(5);
		Text pistSpeed = new Text("pistoleros speed ");
		pistSpeed.setFont(Font.loadFont("file:src/Disko.ttf", 20));
		pistSpeed.setFill(Color.rgb(255,150,50));
		slidspeedPist= new Slider(0.1,2,1);
		slidspeedPist.setShowTickLabels(true);
		slidspeedPist.setShowTickMarks(true);
		slidspeedPist.setMajorTickUnit(0.5);
		slidspeedPist.setBlockIncrement(0.1);
		pistolerosSpeed.getChildren().addAll(pistSpeed,slidspeedPist);	
		sliders.getChildren().add(pistolerosSpeed);
		
	
		sliders.setSpacing(50);
		sliders.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: firebrick");
		sliders.setTranslateX(WIDTH/4);
		this.getChildren().add(sliders);
		this.setTranslateY(ContainerView.HEIGHT);

		
	}
}
