import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class SpriteView {
	protected ImageView imageView;
	Pane p;
	public SpriteView(String imagePath,Pane p){
		this.imageView = new ImageView(new Image(imagePath));
        this.imageView.setClip(new ImageView(imagePath));
        imageView.setCache(true);
		this.p = p;
	}
	
	public ImageView getView(){
		return imageView;
	}
	public void add(){
		p.getChildren().add(this.imageView);
	}
	public void remove(){
		p.getChildren().remove(this.imageView);
	}
	
	
	
}
