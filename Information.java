import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Information extends VBox{
	public final static double WIDTH = 1270;
	public final static double HEIGHT = 100;
	Slider slidspeedVamp ;
	Slider slidspeedPist;
	public Information(SimpleListProperty<VampireView> vamps,Pistoleros pist,SimpleIntegerProperty timer){
		this.setSpacing(15);
		this.setAlignment(Pos.CENTER);
		
		HBox vampire = new HBox(5);
		vampire.setAlignment(Pos.CENTER);
		
		HBox vampireNumber = new HBox(5);
		Text vamp = new Text("vampire :");
		Text vampSuite = new Text();
		vamp.setFont(Font.loadFont("file:src/discoduckchromeital.ttf", 15));
		vampSuite.setFont(Font.loadFont("file:src/discoduckchromeital.ttf", 15));
		vampSuite.textProperty().bind(vamps.sizeProperty().asString());
		vampireNumber.getChildren().addAll(vamp,vampSuite);
		
		HBox vampireSpeed= new HBox(5);

		Text vspeed = new Text("vampire speed :");
		vspeed.setFont(Font.loadFont("file:src/discoduckchromeital.ttf", 15));
		slidspeedVamp = new Slider(0.1,2,1);
		slidspeedVamp.setShowTickLabels(true);
		slidspeedVamp.setShowTickMarks(true);
		slidspeedVamp.setMajorTickUnit(0.5);
		slidspeedVamp.setBlockIncrement(0.1);
		vampireSpeed.getChildren().addAll(vspeed,slidspeedVamp);
		
		HBox time = new HBox(5);
		time.setPrefWidth(100);
		Text timeT = new Text("time :");
		Text timeS = new Text();
		timeT.setFont(Font.loadFont("file:src/discoduckchromeital.ttf", 15));
		timeS.setFont(Font.loadFont("file:src/discoduckchromeital.ttf", 15));
		timeS.textProperty().bind(timer.asString());
		time.getChildren().addAll(timeT,timeS);
		
		vampire.getChildren().addAll(vampireNumber,vampireSpeed,time);
		
		
		HBox pistEmpl  = new HBox(5);
		pistEmpl.setAlignment(Pos.CENTER);

		HBox pistolerosBullet = new HBox(5);
		Text pistText= new Text("pistoleros balle:");
		pistText.setFont(Font.loadFont("file:src/discoduckchromeital.ttf", 15));
		Text pistSuite = new Text();
		pistSuite.setFont(Font.loadFont("file:src/discoduckchromeital.ttf", 15));
		pistSuite.textProperty().bind(pist.gun.nbBullet.asString());
		pistolerosBullet.getChildren().addAll(pistText,pistSuite);
		
		HBox pistolerosSpeed = new HBox(5);
		Text pistSpeed = new Text("pistoleros speed:");
		pistSpeed.setFont(Font.loadFont("file:src/discoduckchromeital.ttf", 15));
		slidspeedPist= new Slider(0.1,2,1);
		slidspeedPist.setShowTickLabels(true);
		slidspeedPist.setShowTickMarks(true);
		slidspeedPist.setMajorTickUnit(0.5);
		slidspeedPist.setBlockIncrement(0.1);
		pistolerosSpeed.getChildren().addAll(pistSpeed,slidspeedPist);
		
		HBox pistolerosLife = new HBox(5);
		Text pistLife = new Text("pistoleros life :");
		pistLife.setFont(Font.loadFont("file:src/discoduckchromeital.ttf", 15));
		ProgressBar pb  = new ProgressBar();
		pb.progressProperty().bind(pist.life.divide(3.0));
		pistolerosLife.getChildren().addAll(pistLife,pb);
		pistEmpl.getChildren().addAll(pistolerosBullet,pistolerosSpeed,pistolerosLife);
		this.getChildren().addAll(vampire,pistEmpl);
		
	}
}
