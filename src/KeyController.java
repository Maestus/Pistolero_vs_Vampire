import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class KeyController {
	private BitSet keyboardBitSet = new BitSet();
	private Scene scene;
	private Setting config;
	
	public KeyController(Scene s, Setting config){
		this.scene = s;
		this.config = config;
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
		return !keyboardBitSet.get((Setting.type(config.bas.getAttribute("key"))).ordinal()) && keyboardBitSet.get((Setting.type(config.haut.getAttribute("key"))).ordinal())  && !keyboardBitSet.get((Setting.type(config.droite.getAttribute("key"))).ordinal())  && !keyboardBitSet.get((Setting.type(config.gauche.getAttribute("key"))).ordinal())  ;
	}

	public boolean moveDown() {
		return keyboardBitSet.get((Setting.type(config.bas.getAttribute("key"))).ordinal()) && !keyboardBitSet.get((Setting.type(config.haut.getAttribute("key"))).ordinal())  && !keyboardBitSet.get((Setting.type(config.droite.getAttribute("key"))).ordinal())  && !keyboardBitSet.get((Setting.type(config.gauche.getAttribute("key"))).ordinal())  ;
	}

	public boolean moveLeft() {	
		return !keyboardBitSet.get((Setting.type(config.bas.getAttribute("key"))).ordinal()) && !keyboardBitSet.get((Setting.type(config.haut.getAttribute("key"))).ordinal())  && !keyboardBitSet.get((Setting.type(config.droite.getAttribute("key"))).ordinal())  && keyboardBitSet.get((Setting.type(config.gauche.getAttribute("key"))).ordinal())  ;
	}

	public boolean moveRight() {
		return !keyboardBitSet.get((Setting.type(config.bas.getAttribute("key"))).ordinal()) && !keyboardBitSet.get((Setting.type(config.haut.getAttribute("key"))).ordinal())  && keyboardBitSet.get((Setting.type(config.droite.getAttribute("key"))).ordinal())  && !keyboardBitSet.get((Setting.type(config.gauche.getAttribute("key"))).ordinal())  ;	
	}
	
	public boolean moveSpace(){
		return keyboardBitSet.get((Setting.type(config.attaque.getAttribute("key"))).ordinal());
	}
	
	public boolean pause(){
		return keyboardBitSet.get((Setting.type(config.pause.getAttribute("key"))).ordinal());
	}
}
