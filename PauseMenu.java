import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class PauseMenu extends MenuPrincipal{

	protected ImageView background;
    protected Pane game;
    protected VBox menuBox = new VBox();
    protected Line line;
    protected static SettingMenu setting;
    double lineX;
    double lineY;
    boolean menuBox_already_add_in_plat = false;
    KeyController kc;
    
    protected ArrayList<String> menuData = new ArrayList<>();
 
    
    protected void addBackground() {
        ImageView background = new ImageView(new Image("file:src/bg_menu.png"));
        background.setFitWidth(game.getPrefWidth());
        background.setFitHeight(game.getPrefHeight());

        game.getChildren().add(background);
    }


    protected void addLine(double x, double y) {
        line = new Line(x, y, x, y + 400);
        line.setStrokeWidth(1);
        line.setStroke(Color.WHITE);
        line.setScaleY(0);

        game.getChildren().add(line);
    }

    protected void startAnimation() {
		line.setEndY(line.getEndY() - 250);
    	ParallelTransition deploy = new ParallelTransition();
        TranslateTransition menuitems[] = new TranslateTransition[menuBox.getChildren().size()];
        ScaleTransition barre = new ScaleTransition(Duration.seconds(1), line);
        barre.setToY(1);
        
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            Node n = menuBox.getChildren().get(i);
            menuitems[i] = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
            menuitems[i].setToX(0);
        }
        
        deploy.getChildren().add(barre);
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            deploy.getChildren().add(menuitems[i]);
        }
        deploy.play();
    }

    protected void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);

        menuData.forEach(data -> {
            MenuPrincipalItem item = new MenuPrincipalItem(data);
            if(data.equals("Game Options")){
                item.setOnMouseClicked(e -> setting.init());
            } else if(data.equals("Back To Menu")){
                item.setOnMouseClicked(e -> {
                	game.getChildren().clear();
                	new MenuPrincipal();
                	
                });
            } else if(data.equals("Resume")){
            	item.setOnMouseClicked(e -> {
            		game.getChildren().remove(background);
            		game.getChildren().remove(line);
            		game.getChildren().remove(menuBox);
            	});
            }

            item.setTranslateX(200);
            
            VBox.setMargin(item, new Insets(10,1,10,1));
            
            menuBox.getChildren().addAll(item);
        });
		if(!menuBox_already_add_in_plat){
        	game.getChildren().add(menuBox);
        	menuBox_already_add_in_plat = true;
		}
        startAnimation();
    }

    public void start(Pane p) {
    	
    	game = p;
    	
      	lineX = game.getPrefWidth() / 2 - 200;
        lineY = game.getPrefHeight() / 4 + 70;
    	
    	menuData.add("Resume");
        menuData.add("Game Options");
        menuData.add("Back To Menu");
        
    	addBackground();

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        setting = new SettingMenu(this);
        
        kc = new KeyController(game.getScene(), setting.configuration);
    }
	
}
