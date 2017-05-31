import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EndGame extends Pane{

	static Pane message;
	static Pane score;
    protected VBox menuBox = new VBox();
    protected Line line;
    double lineX;
    double lineY;
    protected ArrayList<String> menuData = new ArrayList<>();
    Game game;
    
    
    
    public EndGame(Game g){
    	this.game = g;
        menuData.add("Back To Menu");
    	menuData.add("Exit");
    }
    
    
    public void launch(String s) {
        game.cont.container.endpartie = true;

    	this.setPrefHeight(Main.HEIGHT);
    	this.setPrefWidth(Main.WIDTH);
    	
    	message = new Pane();
		
        Text text;
        String name = s;
        String name_with_spacing = "";
        for (char c : name.toCharArray()) {
        	name_with_spacing += c + " ";
        }
        text = new Text(name_with_spacing);
        text.setFont(Font.loadFont("file:res/font/discoduckchromeital.ttf", 74));
        text.setFill(Color.rgb(255, 180, 67));
        message.getChildren().add(text);        
        message.setTranslateX(game.pane.getPrefWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
        message.setTranslateY(game.pane.getPrefHeight() / 4);
        
        score = new Pane();
        
        Text sc = new Text("SCORE : "+String.valueOf(game.cont.container.pist.score.get()));
        sc.setFont(Font.loadFont("file:res/font/discoduckchromeital.ttf", 32));
        sc.setFill(Color.rgb(255, 180, 67));
        score.getChildren().add(sc); 
        score.setTranslateX(game.pane.getPrefWidth() / 2 - sc.getLayoutBounds().getWidth() / 2);
        score.setTranslateY(game.pane.getPrefHeight() / 3);
        
        addBackground();
        
        this.getChildren().add(message);
        this.getChildren().add(score);
        
        game.pane.getChildren().clear();
        game.cont.container.pause = true;
        game.pane.getChildren().add(this);
        
     	lineX = this.getPrefWidth() / 2 - 200;
        lineY = this.getPrefHeight() / 4 + 200;
        
        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);
    }
    

	 protected void addBackground() {
		 ImageView background = new ImageView(new Image("file:res/bg_menu.png"));
		 background.setFitWidth(this.getPrefWidth());
		 background.setFitHeight(this.getPrefHeight());

		 this.getChildren().add(background);
	 }
	 
	 protected void addLine(double x, double y) {
	        line = new Line(x, y, x, y + 350);
	        line.setStrokeWidth(1);
	        line.setStroke(Color.WHITE);
	        line.setScaleY(0);
	        
	        game.pane.getChildren().add(line);
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
	        menuBox.getChildren().clear();
	        menuData.forEach(data -> {
	            MenuPrincipalItem item = new MenuPrincipalItem(data);
	            if(data.equals("Back To Menu")){
	                item.setOnMouseClicked(e -> {
	            		message.getChildren().clear();
	            		score.getChildren().clear();
	            		game.cont.container.pist.kc.removeListeners();
	            		game.pane.getChildren().clear();
	            		if(game.mus.playing)
	            			game.mus.stop();
	                	new MenuPrincipal().start(game.pane);                	
	                });
	            } else if(data.equals("Exit")){
	            	item.setOnMouseClicked(e -> {
	            		Platform.exit();
	            	});
	            }

	            item.setTranslateX(200);
	            
	            VBox.setMargin(item, new Insets(10,1,10,1));
	            
	            menuBox.getChildren().addAll(item);
	        });
	   
	        
	        game.pane.getChildren().add(menuBox);
	        startAnimation();
	    }
}