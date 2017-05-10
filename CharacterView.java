import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

public class CharacterView extends SpriteView{
	protected Character charact;
	protected AnimationCharacter walking;
	public CharacterView(String imagePath, Pane p,Character charact) {
		super(imagePath, p);
		this.charact = charact;
		imageView.relocate(charact.posX, charact.posY);
		imageView.setViewport(new Rectangle2D(charact.offsetX,charact.offsetY,charact.height,charact.width));
		this.walking = new AnimationCharacter(this);
		this.walking.play();

	}
	
	public void update(){
		imageView.relocate(getCharact().getPosX(),getCharact().getPosY());

	}

	public Character getCharact() {
		return charact;
	}
	

	
	
}
