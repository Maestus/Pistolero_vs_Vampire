import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

public class ObstacleView extends SpriteView{
	public final static String image = "file:src/caisse.png";
	Obstacle caisse;
	public ObstacleView( Pane p,Obstacle t) {
		super(image, p);
		this.caisse = t;
		imageView.setViewport(new Rectangle2D(caisse.offsetX,caisse.offsetY,caisse.height,caisse.width));
		imageView.relocate(caisse.posX, caisse.posY);
	}

}
