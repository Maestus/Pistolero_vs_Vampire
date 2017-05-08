import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AnimationExplosion extends Animation{
	BulletView bulletView;
	ImageView explose;
	Pane p;
	int count;
		protected void interpolate(double k) {
			int index = Math.min((int) (k * count), count - 1);
			if (index != lastIndex) {
				
					int x = (int) ((index % 52) * 52 +29 );
					int y = 23;
					bulletView.getView().setViewport(new Rectangle2D(x, y, bulletView.bullet.width, bulletView.bullet.height));
					lastIndex = index;
			}
		}

}
