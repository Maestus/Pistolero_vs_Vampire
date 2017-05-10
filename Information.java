import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class Information extends HBox{
	public final static double WIDTH = 1270;
	public final static double HEIGHT = 100;
	Slider slidspeedVamp ;
	public Information(SimpleListProperty<VampireView> vamps,Pistoleros pist){
		Label Vamp = new Label("vampire :");
		Label VampSuite = new Label();
		VampSuite.textProperty().bind(vamps.sizeProperty().asString());
		slidspeedVamp = new Slider(0,2,0.1);
		slidspeedVamp.setValue(1);
		slidspeedVamp.setFocusTraversable(false);

		slidspeedVamp.setOnMouseReleased(e->{
			System.out.println("la non");

		});
	
		Label Pist= new Label("pistoleros balle:");
		Label PistSuite = new Label();
		PistSuite.textProperty().bind(pist.gun.nbBullet.asString());
		this.getChildren().addAll(Vamp,VampSuite,slidspeedVamp,Pist,PistSuite);
	}
}
