
import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuPrincipalItem extends Pane {
    protected Text text;

    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 5);

    public MenuPrincipalItem(String name) {

        text = new Text(name);
        text.setTranslateX(5);
        text.setTranslateY(20);
        text.setFont(Font.loadFont("file:src/discoduck3d.ttf", 24));
        text.setFill(Color.rgb(255,200,100));

        text.effectProperty().bind(Bindings.when(hoverProperty()).then(shadow).otherwise(blur));

        getChildren().addAll(text);
    }

}