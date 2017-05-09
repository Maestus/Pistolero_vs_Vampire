
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SettingItem extends Pane{

	protected Label field;
	protected TextField value;
	protected Label validity;

	public SettingItem(String name) {

		field = new Label(name);
		
		field.setFont(Font.loadFont("file:src/discoduck3d.ttf", 24));
		field.setTextFill(Color.rgb(255,200,100));;
		
		field.setWrapText( true );
		field.setPrefWidth( 175 );
		
		validity = new Label();
		
		validity.setFont(Font.loadFont("file:src/discoduckchrome.ttf", 15));
		validity.setTextFill(Color.rgb(255,200,100));;
		
		validity.setPrefWidth(250);
		
		value = new TextField();
		value.setAlignment(Pos.CENTER);
		
	
		value.setStyle("-fx-background-color: firebrick; -fx-text-inner-color: rgb(255,200,100);");
		
		value.setEditable(false);
		
		value.setOnKeyPressed(e -> {
			
			value.positionCaret(value.getText().length());
				if(e.getCode().equals(KeyCode.BACK_SPACE)){
					value.setEditable(true);
					value.setText("");
				} else {
					if(value.getText().toString().length() >= 1) {
						value.setEditable(false);
					} else if((e.getCode().equals(KeyCode.SPACE)) || (e.getCode().equals(KeyCode.TAB))){
						if(Setting.type(e.getCode().getName()) == null){
							validity.setText("Invalid button");
							value.setText("");
						} else if(!e.getCode().isLetterKey()) {
							if (already_assigned(e.getCode().getName())){
								validity.setText("Button already assigned");
								value.setText("");
							} else {
								value.getText().toLowerCase();

								validity.setText("");
							
								value.setText("");
								value.setText(e.getCode().getName());
							}
						} else {
							value.getText().toUpperCase();
							validity.setText("");
						}
					}
				}

		});
		
		value.setOnKeyReleased(e -> {
			if(!(e.getCode().equals(KeyCode.SHIFT)) && !(e.getCode().equals(KeyCode.BACK_SPACE)) && !(e.getCode().equals(KeyCode.SPACE)) && !(e.getCode().equals(KeyCode.TAB))){
				if(Setting.type(e.getCode().getName()) == null){
					validity.setText("Invalid button");
					value.setText("");
				} else if(!e.getCode().isLetterKey()) {
					if (already_assigned(e.getCode().getName())){
						validity.setText("Button already assigned");
						value.setText("");
					} else {
						value.getText().toLowerCase();
						validity.setText("");

						value.setText("");
						value.setText(e.getCode().getName());
					}
				} else {
					if (already_assigned(e.getCode().getName().toUpperCase())){
						validity.setText("Button already assigned");
						value.setText("");
					} else {
						value.getText().toUpperCase();
						validity.setText("");
						
						value.setText("");
						value.setText(e.getCode().getName());
					}
				}
			}

			if(value.getText().length() > 0 && (e.getCode().equals(KeyCode.SPACE)) && value.getText().substring(0,1).equals(" ")){
				value.setText(value.getText().substring(1,value.getText().length()));
			}
		});
		
		
		
		HBox hb = new HBox();
		hb.getChildren().addAll(field, value, validity);
		hb.setSpacing(50);
	    	
		getChildren().addAll(hb);
	}

	private boolean already_assigned(String name) {
		for (int i = 0; i < SettingMenu.items.length; i++) {
			if(SettingMenu.items[i].value.getText().equals(name) && SettingMenu.items[i].value != value)
				return true;
		}
		return false;
	}
}
