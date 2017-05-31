import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	HashMap<String,ImageView> maps = new HashMap<>();
	ArrayList<String> md = new ArrayList<>();
	int position = 0;
	Button next, previous;
	MenuPrincipalItem select;
	
	FilenameFilter extension_png = new FilenameFilter() {
		
		@Override
		public boolean accept(File arg0, String arg1) {
			return arg1.endsWith(".png");
		}
	};
	
	FilenameFilter extension_md = new FilenameFilter() {
		
		@Override
		public boolean accept(File arg0, String arg1) {
			return arg1.endsWith(".md");
		}
	};
	
	public MapLoader(MenuPrincipal m){
		this.menuBox = m.menuBox;
		this.line = m.line;
		this.pane = m.game.pane;
		display = new Rectangle(642, 332);
		
    	load_from_dir_maps();
    	load_from_dir_md();
		
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
			
			boolean variation = false;
			
			Iterator<String> keys = maps.keySet().iterator();
			int r = 0;
			while(keys.hasNext()){
				if(position == r){
					String k = keys.next();
					String find = k.split(".png")[0]+".md";
					for(int i = 0; i < md.size(); i++){
						if(find.equals(md.get(i))){
							m.game.loadGame(m.kc, maps.get(k.split("_")[0]+".png"));
							m.game.loadObstacles(md.get(i));
							variation = true;
							break;
						}
					}
					r++;
				} else {
					r++;
					System.err.println(r);
					keys.next();
				}
				
				if(variation)
					break;
			}
			if(!variation){
				keys = maps.keySet().iterator();
				r = 0;
				while(keys.hasNext()){
					if(r == position){
						String clef = keys.next();
						m.game.loadGame(m.kc, maps.get(clef));
						m.game.initialize();
						System.out.println(maps.get(clef));
						variation = true;
						break;
					} else {
						r++;
						keys.next();
					}
				}
			}
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
    		addLoaderMap((pane.getPrefWidth()/2)-321, line.getStartY());
        });
	}

	private void addLoaderMap(double x, double y) {
		display.setStrokeWidth(1);
	    display.setStroke(Color.WHITE);
		display.setScaleX(0);
		
		display.setTranslateX(x);
		display.setTranslateY(y);
		
		next.setTranslateX(x + 350);
		next.setTranslateY(y + (display.getHeight()/2) - 15);
		
		previous.setTranslateX(x + 250);
		previous.setTranslateY(y + (display.getHeight()/2) - 15);
		
		pane.getChildren().add(display);
		pane.getChildren().add(previous);
		pane.getChildren().add(next);

    	ParallelTransition deploy = new ParallelTransition();
        ScaleTransition rectangle = new ScaleTransition(Duration.seconds(1), display);
        rectangle.setToX(1);
        TranslateTransition trans_next = new TranslateTransition(Duration.seconds(1), next);
        trans_next.setToX(x + 675);
        TranslateTransition trans_pervious = new TranslateTransition(Duration.seconds(1), previous);
        trans_pervious.setToX(x - 50);
        
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
		Iterator<String> keys = maps.keySet().iterator();
		int r = 0;
		while(keys.hasNext()){
			if(r == position){
				System.err.println(position);
				display.setFill(new ImagePattern(maps.get(keys.next()).getImage()));
				break;
			}else{
				r++;
				keys.next();
			}
		}
	}

	private void load_from_dir_maps() {
		if (dir.isDirectory()) {
            for (final File f : dir.listFiles(extension_png)) {
                Image image = new Image(getClass().getClassLoader().getResource("res/creations/").toString()+f.getName());
				System.out.println("image: " + f.getPath());
				maps.put(f.getName(), new ImageView(image));
            }
        }
	}
	
	private void load_from_dir_md() {
		if (dir.isDirectory()) {
            for (final File f : dir.listFiles(extension_md)) {
				System.out.println("image: " + f.getPath());
				md.add(f.getName());
            }
        }
	}
	
	

}
