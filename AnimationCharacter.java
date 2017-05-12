import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

public class AnimationCharacter extends Animation {
	CharacterView charView;
	public AnimationCharacter(CharacterView charView) {
		this.count=3;
		this.lastIndex=0;
		this.charView=charView;
		setCycleDuration(Duration.millis(400));
		this.setCycleCount(INDEFINITE);

	}

	@Override	
	protected void interpolate(double k) {
		int index = Math.min((int) (k * count), count - 1);
		if (index != lastIndex) {
			if(charView.getCharact().getMoveY()!=0 ||charView.getCharact().getMoveX()!=0){
				int x = (int) ((index % charView.getCharact().width) * charView.getCharact().width + charView.getCharact().offsetX);
				int y = (int) ((index / charView.getCharact().height) * charView.getCharact().height + charView.getCharact().offsetY);
				charView.getView().setViewport(new Rectangle2D(x, y, charView.getCharact().width, charView.getCharact().height));
				lastIndex = index;
			}
			else{
				 charView.getView().setViewport((new Rectangle2D(charView.getCharact().offsetX, charView.getCharact().offsetY, charView.getCharact().width, charView.getCharact().height)));
			 }
		}
		
	}
	
}
