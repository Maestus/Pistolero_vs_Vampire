import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	public InGameOptions(SimpleListProperty<VampireView> vamps,Pistoleros pist,SimpleIntegerProperty timer,Music music){
		
		this.setPrefHeight(HEIGHT);
		this.setPrefWidth(WIDTH);
		HBox generals = new HBox(20);
		HBox sliders = new HBox(5);
		sliders.setAlignment(Pos.CENTER);
		HBox musicBox = new HBox(5);
		 ImageView unmute= new ImageView(new Image("file:res/unmute.png"));

		unmute.setOnMouseClicked(clicked->{music.pause();});
	       musicBox.getChildren().add(unmute);
			unmute.setOnMouseClicked(clicked->{
				if(music.playing){
					music.pause();
					unmute.setImage(new Image("file:res/mute.png"));
				}
				else{
					music.play();
					unmute.setImage(new Image("file:res/unmute.png"));
				}
				
			});
			

		HBox vampireSpeed= new HBox(5);
	

		Text vspeed = new Text("vampire speed ");
		vspeed.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
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
		pistSpeed.setFont(Font.loadFont("file:res/font/Disko.ttf", 20));
		pistSpeed.setFill(Color.rgb(255,150,50));
		slidspeedPist= new Slider(0.1,2,1);
		slidspeedPist.setShowTickLabels(true);
		slidspeedPist.setShowTickMarks(true);
		slidspeedPist.setMajorTickUnit(0.5);
		slidspeedPist.setBlockIncrement(0.1);
		pistolerosSpeed.getChildren().addAll(pistSpeed,slidspeedPist);	
		sliders.getChildren().add(pistolerosSpeed);
		generals.getChildren().addAll(musicBox,sliders);
		 
		sliders.setSpacing(50);
		sliders.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: firebrick");
		generals.setTranslateX(WIDTH/4);
		this.getChildren().add(generals);
		this.setTranslateY(ContainerView.HEIGHT);

      
        
	}
}
