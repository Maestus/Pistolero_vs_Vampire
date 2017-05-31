import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AnimationBleed extends Animation{
	PistoleroView p;
	double brighteness = 0;
	int times = 0;
	boolean isFinished;
	ColorAdjust monochrome;
    Blend bleed;
	
	public AnimationBleed(PistoleroView _p){
		p = _p;
		isFinished = false;
		monochrome = new ColorAdjust();
		monochrome.setBrightness(brighteness);
	    monochrome.setSaturation(-1.0);
		this.setCycleDuration(Duration.seconds(1));
		this.setOnFinished(new EventHandler<ActionEvent>() {
	        @Override 
	        public void handle(ActionEvent actionEvent) {
	        	p.imageView.setEffect(null);
	        	isFinished = true;
	        }
	    });
	}

	@Override
	protected void interpolate(double k) {
		if(k <= 0.2 || (k > 0.4 && k <= 0.6) || (k > 0.8 && k <= 1)){
			bleed = new Blend(
		            BlendMode.OVERLAY,
		            monochrome,
		            new ColorInput(0, 0, p.charact.width, p.charact.height, Color.RED)
		    );
			p.imageView.setEffect(bleed);
		} else if(k > 0.2 || k > 0.6){
			p.imageView.setEffect(null);
		}
	}

}
