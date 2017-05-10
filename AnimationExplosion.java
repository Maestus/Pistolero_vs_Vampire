import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AnimationExplosion extends Animation{
	ImageView explose;
	Pane p;
	int count;
	boolean isFinished;
	public AnimationExplosion(double x,double y,Pane p){
		explose = new ImageView(new Image("file:src/explos1.png"));
		explose.setViewport(new Rectangle2D(0, 0, 25,25));

		explose.relocate(x-(25/2), y-(25/2));
		isFinished = false;
		this.setCycleDuration(Duration.millis(200));
		this.p= p ;
		this.setOnFinished(new EventHandler<ActionEvent>() {
	        @Override 
	        public void handle(ActionEvent actionEvent) {
	        	p.getChildren().remove(explose);
	        	isFinished = true;
				System.out.println("on est la");
	        }
	    });
        this.count =12;
        p.getChildren().add(explose);
	}
		protected void interpolate(double k) {
			int index = Math.min((int) (k * count), count - 1);
			if (index != lastIndex) {
				
				int x = (int) ((index % 25) * 25 );
				int y = 0;
					lastIndex = index;
					System.out.println(x+" "+y);
					explose.setViewport(new Rectangle2D(x, y,25,25));

			}
		}

}
