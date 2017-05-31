import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Information extends Pane{
	public final static double WIDTH = 1280;
	public final static double HEIGHT = 70;
	Slider slidspeedVamp ;
	Slider slidspeedPist;
	public Information(SimpleListProperty<VampireView> vamps,Pistoleros pist,SimpleIntegerProperty timer){
		
		HBox informations = new HBox();
		informations.setPrefHeight(HEIGHT);
		informations.setPrefWidth(WIDTH);
		
		VBox status = new VBox(5);
		status.setPrefHeight(HEIGHT);
		status.setPrefWidth(150);
		//status.setStyle("-fx-background-color: firebrick");
		//status.setOpacity(0.7);
		status.setAlignment(Pos.CENTER);
		
		HBox vampireNumber = new HBox(5);
		Text vamp = new Text("Vampire ");
		Text vampSuite = new Text();
		vamp.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		vamp.setFill(Color.rgb(255,150,50));
		vampSuite.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		vampSuite.setFill(Color.rgb(255,150,50));
		vampSuite.textProperty().bind(vamps.sizeProperty().asString());
		vampireNumber.getChildren().addAll(vamp,vampSuite);
		
		HBox time = new HBox(5);
		time.setPrefWidth(255);
		Text timeT = new Text("Time ");
		Text timeS = new Text();
		timeT.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		timeT.setFill(Color.rgb(255,150,50));
		timeS.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		timeS.setFill(Color.rgb(255,150,50));
		timeS.textProperty().bind(timer.asString());
		time.getChildren().addAll(timeT,timeS);
		
		HBox pistolerosScore = new HBox(5);
		Text pistScore= new Text("Score ");
		pistScore.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		pistScore.setFill(Color.rgb(255,150,50));
		Text score = new Text();
		score.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		score.setFill(Color.rgb(255,150,50));
		score.textProperty().bind(pist.score.asString());
		pistolerosScore.getChildren().addAll(pistScore,score);
		
		status.getChildren().addAll(vampireNumber,time, pistolerosScore);
		
		VBox pistEmpl  = new VBox(5);
		pistEmpl.setPrefHeight(HEIGHT);
		pistEmpl.setPrefWidth(150);
		//pistEmpl.setStyle("-fx-background-color: firebrick");
		//pistEmpl.setOpacity(0.7);
		pistEmpl.setAlignment(Pos.CENTER);

		HBox pistolerosBullet = new HBox(5);
		Text pistText= new Text("Bullets ");
		pistText.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		pistText.setFill(Color.rgb(255,150,50));
		Text pistSuite = new Text();
		pistSuite.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		pistSuite.setFill(Color.rgb(255,150,50));
		pistSuite.textProperty().bind(pist.gun.nbBullet.asString());
		pistolerosBullet.getChildren().addAll(pistText,pistSuite);
		
		
		HBox pistolerosLife = new HBox(5);
		Text pistLife = new Text("Life ");
		pistLife.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		pistLife.setFill(Color.rgb(255,150,50));
		ProgressBar pb  = new ProgressBar();
		pb.progressProperty().bind(pist.life.divide(3.0));
		pistolerosLife.getChildren().addAll(pistLife,pb);
		
		pistEmpl.getChildren().addAll(pistolerosLife, pistolerosBullet);
		
		status.setTranslateX(WIDTH-125);
		informations.getChildren().addAll(status, pistEmpl);
	    	
		getChildren().addAll(informations);
		
		this.getChildren().addAll(status,pistEmpl);
		
	}
}
