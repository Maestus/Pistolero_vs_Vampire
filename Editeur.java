import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Editeur extends Application {
	
	ImageView creation;
	Image draw;
	int WIDTH = 20; // largeur du plateau
	int HEIGHT = 10; // longueur du plateau
	int position = 0; // position dans le gridPane des images chargees
	int posx = 0, posy = 0; // position dans le plateau de jeu
	GridPane images; // Toutes les assets dans le fichier floor 
	GridPane plateau_view; // Le plateau en creation
	SimpleIntegerProperty to_create; // check si la grille est rempli avant de proposer de la creer

    	
    private void init_from_res_dir_assets() {
    	File dir = new File(getClass().getClassLoader().getResource("res/floors").getFile());
    	if (dir.isDirectory()) {

            for (final File f : dir.listFiles()) {
                Image image = null;

                image = new Image("file:src/res/floors/"+f.getName());
				
				System.out.println("image: " + f.toString());
				System.out.println(" width : " + image.getWidth());
				System.out.println(" height: " + image.getHeight());
				System.out.println(" size  : " + f.length());
				
				ImageView source = new ImageView();
				source.setImage(image);
				images.add(source, position++, 1);
				GridPane.setMargin(source, new Insets(0, 10, 0, 0));
			        
				source.setOnDragDetected( event -> {
		            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
		            ClipboardContent content = new ClipboardContent();
		            content.putImage(source.getImage());
		            db.setContent(content);
		            event.consume();
		        });
				
				source.setOnDragDone((DragEvent event) -> {             
					/* if the data was successfully moved, clear it */
					if (event.getTransferMode() == TransferMode.MOVE) {
						//source.setFill(Color.GREY);
			        }
					event.consume();
				});	
				
				source.setOnMouseClicked( e -> {
					draw = source.getImage();
				});
            }
        }
	}
    
    
	private void init_board_game() {
        
		for(int i = 0; i < HEIGHT; i++){
	        for(int j = 0; j < WIDTH; j++){
	        	 ImageView target = new ImageView();
	             Image image = new Image("file:src/res/default/white.png");
	             target.setImage(image);
	             target.setFitHeight(32);
	             target.setFitWidth(32);
	             
	             plateau_view.add(target, posx++, posy);
	
	             target.setOnDragOver( event -> {               
	                 if (event.getGestureSource() != target
	                         && event.getDragboard().hasImage()) {
	                     event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                 }               
	                 event.consume();
	             });;
	             
	             target.setOnDragEntered( event -> {               
	                 if (event.getGestureSource() != target
	                         && event.getDragboard().hasImage()) {
	                	 System.out.println("yabon");
	                 }               
	                 event.consume();
	             });
	
	            
	             target.setOnDragExited(
	                     event -> {
	                         //target.setFill(targetColor);
	                         event.consume();
	                         System.out.println("exited");
	             });
	                 
	             target.setOnDragDropped((DragEvent event) ->{
	             	
	             	Dragboard db = event.getDragboard();
	             	
	             	boolean success = false;
	             	if (db.hasImage()) {
	             		target.setImage(db.getImage());
	             		to_create.set(to_create.get()-1);
	             		System.out.println(to_create.get());
	             		success = true;
	             	}
	                         
	             	event.setDropCompleted(success);
	             	event.consume();
	             });
	             
	             target.setOnMouseClicked( e -> {
	            	 if(draw != null){
	            		target.setImage(draw);
		             	to_create.set(to_create.get()-1);
	            	 }
	             });
	                         
	        }
	        posy++;
	        posx = 0;
		} 
    
	}
	
    @Override
    public void start(Stage stage) {
        stage.setTitle("Editeur de niveau");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 640, 400);
        
        creation = new ImageView();
        
        HBox complet = new HBox();
        
        TextField name = new TextField("Give a name");
        name.setDisable(true);
        
        Button create_map = new Button("Create !");
		create_map.setDisable(true);
        
        complet.getChildren().addAll(name, create_map);
        complet.setAlignment(Pos.BOTTOM_CENTER);
        
        create_map.setOnAction(e -> {
            plateau_view.setStyle("-fx-grid-lines-visible: false");
            WritableImage snapshot = plateau_view.snapshot(new SnapshotParameters(), null);
            creation.setImage(snapshot);
            
            try {
            	File outputFile = new File("C:/Users/Bensa/workspace/Editeur/src/res/creations/"+name.getText()+".png");  
                BufferedImage bImage = SwingFXUtils.fromFXImage(creation.getImage(), null);
                ImageIO.write(bImage, "png", outputFile);
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }            
        });
        
        to_create = new SimpleIntegerProperty(WIDTH*HEIGHT);
        
        to_create.addListener( e -> {
        	if(to_create.get() == 0){
        		name.setDisable(false);
        	}
        });
        
        name.setOnMousePressed(e -> {
        	if(create_map.isDisable()){
        		name.setText("");
        	}
        });
        
        name.setOnKeyReleased( e -> {
        	System.out.println(name.getText().length());
        	if(name.getText().isEmpty()){
        		create_map.setDisable(true);
        	} else {
        		create_map.setDisable(false);
        	}
        });
        
        
        plateau_view = new GridPane(); 
        plateau_view.setStyle("-fx-grid-lines-visible: true");
        plateau_view.setAlignment(Pos.CENTER);
        
        ScrollPane sp = new ScrollPane();
        sp.setVbarPolicy(ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
          
        images = new GridPane();
        
        init_from_res_dir_assets();
                        
        sp.setContent(images);
        
        init_board_game();
                        
        root.setTop(sp);
        root.setCenter(plateau_view);
        root.setBottom(complet);
        stage.setScene(scene);
        stage.show();
    }

	public static void main(String[] args) {
        Application.launch(args);
    }
}
