import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyController {
	private BitSet keyboardBitSet = new BitSet();
	private Scene scene;

	
	public KeyController(Scene s){
		this.scene = s;
	}
	public void addListeners() {
		scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
	}

	public void removeListeners() {
		scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
		scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
	}

	private EventHandler<KeyEvent> keyPressedEventHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			// register key down
			keyboardBitSet.set(event.getCode().ordinal(), true);
		}
	};

	private EventHandler<KeyEvent> keyReleasedEventHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			// register key up
			keyboardBitSet.set(event.getCode().ordinal(), false);
		}
	};

	public boolean moveUp() {
		return !keyboardBitSet.get((KeyCode.DOWN).ordinal()) && keyboardBitSet.get((KeyCode.UP).ordinal())  && !keyboardBitSet.get((KeyCode.LEFT).ordinal()) && !keyboardBitSet.get((KeyCode.RIGHT).ordinal()) ;
	}

	public boolean moveDown() {
		return keyboardBitSet.get((KeyCode.DOWN).ordinal()) && !keyboardBitSet.get((KeyCode.UP).ordinal())  && !keyboardBitSet.get((KeyCode.LEFT).ordinal()) && !keyboardBitSet.get((KeyCode.RIGHT).ordinal()) ;
	}

	public boolean moveLeft() {
		return !keyboardBitSet.get((KeyCode.DOWN).ordinal()) && !keyboardBitSet.get((KeyCode.UP).ordinal())  && keyboardBitSet.get((KeyCode.LEFT).ordinal()) && !keyboardBitSet.get((KeyCode.RIGHT).ordinal()) ;
	}

	public boolean moveRight() {
		return !keyboardBitSet.get((KeyCode.DOWN).ordinal()) && !keyboardBitSet.get((KeyCode.UP).ordinal())  && !keyboardBitSet.get((KeyCode.LEFT).ordinal()) && keyboardBitSet.get((KeyCode.RIGHT).ordinal()) ;
	}
	
	public boolean moveSpace(){
		return keyboardBitSet.get((KeyCode.SPACE).ordinal());
	}
}
