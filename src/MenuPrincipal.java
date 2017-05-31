import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.ScaleTransition;
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

import java.util.ArrayList;

public class MenuPrincipal extends Pane{

    protected ImageView background;
    protected Pane title;
    protected VBox menuBox = new VBox();
    protected Line line;
    protected static SettingMenu setting;
    protected static MapLoader loader;
    double lineX;
    double lineY;
    boolean menuBox_already_add_in_pane = false;
    KeyController kc;
    protected ArrayList<String> menuData = new ArrayList<>();
	protected Pane pane;
	protected Game game; 
 
	protected void boot_menu(){
		pane.getChildren().clear();
		addBackground();
        addTitle();

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);
	}
	
	
    protected void addBackground() {
        ImageView background = new ImageView(new Image("file:res/bg_menu.png"));
        background.setFitWidth(pane.getPrefWidth());
        background.setFitHeight(pane.getPrefHeight());

        this.getChildren().add(background);
    }

	protected void addTitle() {
        
		title = new Pane();
        
        Text text;
        
        String name = "FxBattle";
        
        String name_with_spacing = "";
        for (char c : name.toCharArray()) {
        	name_with_spacing += c + " ";
        }

        text = new Text(name_with_spacing);
        text.setFont(Font.loadFont("file:res/font/discoduckchromeital.ttf", 74));
        text.setFill(Color.rgb(255, 180, 67));
        
        title.getChildren().addAll(text);

        
        title.setTranslateX(pane.getPrefWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
        title.setTranslateY(pane.getPrefHeight() / 4);

        this.getChildren().add(title);
    }

    protected void addLine(double x, double y) {
        line = new Line(x, y, x, y + 400);
        line.setStrokeWidth(1);
        line.setStroke(Color.WHITE);
        line.setScaleY(0);

        this.getChildren().add(line);
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
            } else if(data.equals("Exit")){
                item.setOnMouseClicked(e -> Platform.exit());
            } else if(data.equals("Play")){
            	item.setOnMouseClicked(e -> {	
            		
            		loader.init();
            	
            	});
            }

            item.setTranslateX(200);
            
            VBox.setMargin(item, new Insets(10,1,10,1));
            
            menuBox.getChildren().addAll(item);
        });
		if(!menuBox_already_add_in_pane){
        	this.getChildren().add(menuBox);
        	menuBox_already_add_in_pane = true;
		}
        startAnimation();
    }

	public void start(Pane pane) {
		
		this.pane = pane;
		
		this.setPrefHeight(Main.HEIGHT);
		this.setPrefWidth(Main.WIDTH);
		
      	lineX = this.getPrefWidth() / 2 - 200;
        lineY = this.getPrefHeight() / 4 + 70;
    	
    	menuData.add("Play");
        menuData.add("Game Options");
        menuData.add("Exit");
        
    	addBackground();
        addTitle();

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);

        setting = new SettingMenu(this);
        kc = new KeyController(this.pane.getScene(), setting.configuration);	
        loader = new MapLoader(this);
        game = new Game(pane);
        
        this.pane.getChildren().add(this);
        
	}
	
}
