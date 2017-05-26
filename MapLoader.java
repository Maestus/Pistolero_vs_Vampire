import java.io.File;
import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MapLoader {

	Rectangle display;
	VBox menuBox;
	Line line;
	Pane pane;
	File dir = new File(getClass().getClassLoader().getResource("res/creations").getFile());
	ArrayList <ImageView> maps = new ArrayList<>();
	int position = 0;
	Button next, previous;
	MenuPrincipalItem select;
	
	public MapLoader(MenuPrincipal m){
		this.menuBox = m.menuBox;
		this.line = m.line;
		this.pane = m.game.pane;
		display = new Rectangle(400, 400);
		
    	load_from_dir_maps();
		
		next = new Button(">");
		next.setStyle("-fx-background-radius : 30px; -fx-background-color : firebrick;");
		
		next.setOnAction( e -> {
			position = (position + 1)%maps.size();
			renderMap();
		});
		
		previous = new Button("<");
		previous.setStyle("-fx-background-radius : 30px; -fx-background-color : firebrick;");
		
		previous.setOnAction( e -> {
			if(position > 0){
				position--;
			} else {
				position = maps.size() - 1;
			}
			renderMap();
		});
		
		select = new MenuPrincipalItem("Selectionner");		
		
		select.setOnMousePressed(e -> {
			m.game.loadGame(m.kc, maps.get(position));
		});
	}
	
	public void init() {
		ScaleTransition barre = new ScaleTransition(Duration.seconds(1), line);
	    barre.setToY(0);
		
		ParallelTransition deploy = new ParallelTransition();
		TranslateTransition menuitems[] = new TranslateTransition[menuBox.getChildren().size()];
        for (int i = menuBox.getChildren().size() - 1; i >= 0; i--) {
            Node n = menuBox.getChildren().get(i);
            menuitems[i] = new TranslateTransition(Duration.seconds(1), n);
            menuitems[i].setToY(500);
            menuitems[i].setOnFinished(e2 -> menuBox.getChildren().remove(n));
        }
        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            deploy.getChildren().add(menuitems[i]);
        }
        deploy.getChildren().add(barre);
        deploy.play();
        deploy.setOnFinished(e -> {
        	pane.getChildren().remove(menuBox);
    		pane.getChildren().remove(line);
    		addLoaderMap((pane.getPrefWidth()/2)-200, line.getStartY());
        });
	}

	private void addLoaderMap(double x, double y) {
		display.setStrokeWidth(1);
	    display.setStroke(Color.WHITE);
		display.setScaleX(0);
		
		display.setTranslateX(x);
		display.setTranslateY(y);
		
		next.setTranslateX(x + 225);
		next.setTranslateY(y + (display.getHeight()/2) - 15);
		
		previous.setTranslateX(x + 125);
		previous.setTranslateY(y + (display.getHeight()/2) - 15);
		
		pane.getChildren().add(display);
		pane.getChildren().add(previous);
		pane.getChildren().add(next);

    	ParallelTransition deploy = new ParallelTransition();
        ScaleTransition rectangle = new ScaleTransition(Duration.seconds(1), display);
        rectangle.setToX(1);
        TranslateTransition trans_next = new TranslateTransition(Duration.seconds(1), next);
        trans_next.setToX(x + 450);
        TranslateTransition trans_pervious = new TranslateTransition(Duration.seconds(1), previous);
        trans_pervious.setToX(x - 75);
        
        deploy.getChildren().add(rectangle);
        deploy.getChildren().add(trans_next);
        deploy.getChildren().add(trans_pervious);
        deploy.play();
        
        deploy.setOnFinished( e -> {
        	renderMap();
    		select.setTranslateX((pane.getPrefWidth()/2)-100);
    		select.setTranslateY(pane.getPrefHeight()-50);
    		pane.getChildren().add(select);
        });
		
	}

	private void renderMap() {
		display.setFill(new ImagePattern(maps.get(position).getImage()));
	}

	private void load_from_dir_maps() {
		if (dir.isDirectory()) {
            for (final File f : dir.listFiles()) {
                Image image = new Image("file:src/res/creations/"+f.getName());
				System.out.println("image: " + f.toString());
				maps.add(new ImageView(image));
            }
        }
	}
	
	

}
