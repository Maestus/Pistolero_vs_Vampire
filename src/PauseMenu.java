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
    protected Main game;
    protected Pane pause;
    protected VBox menuBox = new VBox();
    protected Line line;
    double lineX;
    double lineY;
    boolean menuBox_already_add_in_plat = false;    
    protected ArrayList<String> menuData = new ArrayList<>();
	private boolean running = false;
 
    
    protected void addBackground() {
        ImageView background = new ImageView(new Image("file:res/bg_menu.png"));
        background.setFitWidth(pause.getPrefWidth());
        background.setFitHeight(pause.getPrefHeight());
        
        pause.getChildren().add(background);
    }


    protected void addLine(double x, double y) {
        line = new Line(x, y, x, y + 350);
        line.setStrokeWidth(1);
        line.setStroke(Color.WHITE);
        line.setScaleY(0);
        
        pause.getChildren().add(line);
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
            if(data.equals("Back To Menu")){
                item.setOnMouseClicked(e -> {
            		running = false;
            		game.pane.getChildren().clear();
                	game.boot_menu();                	
                });
            } else if(data.equals("Resume")){
            	item.setOnMouseClicked(e -> {
            		running = false;
            		game.pane.getChildren().removeAll(pause);
            		game.cont.container.pause = false;
            	});
            }

            item.setTranslateX(200);
            
            VBox.setMargin(item, new Insets(10,1,10,1));
            
            menuBox.getChildren().addAll(item);
        });
        pause.getChildren().add(menuBox);
        startAnimation();
    }

    public void start(Main m) {
    	    	
    	game = m;
    	    	
    	running = true;
    	
    	pause = new Pane();
    	pause.setPrefHeight(game.pane.getPrefHeight());
    	pause.setPrefWidth(game.pane.getPrefWidth());
    	
      	lineX = pause.getPrefWidth() / 2 - 200;
        lineY = pause.getPrefHeight() / 4 + 70;
    	
    	menuData.add("Resume");
        menuData.add("Back To Menu");
        
    	addBackground();

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        game.pane.getChildren().add(pause);
    }


	public boolean displayed() {
		return running;
	}
	
	public void close(){
		running = false;
		game.pane.getChildren().removeAll(pause);
	}
}
