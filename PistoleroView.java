import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PistoleroView extends CharacterView{
	public final static String image = "file:src/pixelart.png";
	Pane p;

    
	public PistoleroView(Pistoleros pist,Pane p){
		super(image,p,pist);
		
	}
	
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
}
