import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Editeur extends Application {
	
	ImageView creation;
	Image draw;
	int WIDTH = 40; // largeur du plateau
	int HEIGHT = 24; // longueur du plateau
	int position = 0; // position dans le gridPane des images chargees
	int posx = 0, posy = 0; // position dans le plateau de jeu
	GridPane images, images_fix, images_mouvable; // Toutes les assets dans le fichier floor 
	GridPane plateau_view; // Le plateau en creation
	ListView<String> creations = new ListView<String>();
	public static final ObservableList<String> names = 
	        FXCollections.observableArrayList();
	BorderPane root;
	ArrayList<ObstacleModel> obstacles = new ArrayList<>();
	File dir_creations = new File("res/creations");
	File dir_floors = new File("res/floors/");
	File dir_obstacles_mouvable = new File("res/obstacles/mouvable");
	File dir_obstacles_fix = new File("res/obstacles/fix");
	File obstacles_file_storage = new File("res/creations");
	private String obstacle_name;
	private String creation_to_edit;
	BorderPane paint;
	 VBox menu ;
	private boolean obstacle_type;

	FilenameFilter extension_png = new FilenameFilter() {
		
		@Override
		public boolean accept(File arg0, String arg1) {
			return arg1.endsWith(".png");
		}
	};

    private void init_from_res_dir_assets() {
		
    	if (dir_floors.isDirectory()) {
    		System.err.println("la");
            for (final File f : dir_floors.listFiles(extension_png)) {
                Image image = new Image("file:res/floors/"+f.getName());
				
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
    
    
    private void init_from_obstacle_dir_assets() {
    	if (dir_obstacles_mouvable.isDirectory()) {

            for (final File f : dir_obstacles_mouvable.listFiles(extension_png)) {
                Image image = new Image("file:res/obstacles/mouvable/"+f.getName());
				
				ImageView source = new ImageView();
				source.setImage(image);
				images_mouvable.add(source, position++, 1);
				GridPane.setMargin(source, new Insets(0, 10, 0, 0));
			        
				source.setOnDragDetected( event -> {
					draw = source.getImage();
		            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
		            ClipboardContent content = new ClipboardContent();
		            content.putImage(source.getImage());
		            content.putString(f.getPath());
		            obstacle_type = true;
		            db.setContent(content);
		            event.consume();
		        });
				
				source.setOnDragDone((DragEvent event) -> {             
					/* if the data was successfully moved, clear it */
					if (event.getTransferMode() == TransferMode.MOVE) {
			            obstacle_type = true;
			        }
					event.consume();
				});	
				
				source.setOnMouseClicked( e -> {
					draw = source.getImage();
					obstacle_name = f.getPath();
		            obstacle_type = true;
				});
            }
        }
	}
    
    
    private void init_from_obstacle_fix_dir_assets() {
    	
    	position = 0;
    	
    	if (dir_obstacles_fix.isDirectory()) {

            for (final File f : dir_obstacles_fix.listFiles(extension_png)) {
                Image image = new Image("file:res/obstacles/fix/"+f.getName());
				
				ImageView source = new ImageView();
				source.setImage(image);
				images_fix.add(source, 1, position++);
				GridPane.setMargin(source, new Insets(0, 10, 0, 0));
			        
				source.setOnDragDetected( event -> {
					draw = source.getImage();
		            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
		            ClipboardContent content = new ClipboardContent();
		            content.putImage(source.getImage());
		            content.putString(f.getPath());
		            obstacle_type = false;
		            db.setContent(content);
		            event.consume();
		        });
				
				source.setOnDragDone((DragEvent event) -> {             
					if (event.getTransferMode() == TransferMode.MOVE) {
			            obstacle_type = false;
			        }
					event.consume();
				});	
				
				source.setOnMouseClicked( e -> {
					draw = source.getImage();
					obstacle_name = f.getPath();
		            obstacle_type = false;
				});
            }
        }
	}
    
    
    public void init_from_creation_assets() {
    	if (dir_creations.isDirectory()) {

            for (final File f : dir_creations.listFiles(extension_png)) {
                Image image = new Image("file:res/creations/"+f.getName());
				
				System.out.println("image: " + f.getName());
				System.out.println("width : " + image.getWidth());
				System.out.println("height: " + image.getHeight());
				System.out.println("size  : " + f.length());
				
				names.add(f.getName());
				
            }
        }
    }
    
    
	private void add_obstacles() {
		creations.setPrefWidth(100);
		creations.setPrefHeight(70);	
		
		ScrollPane mouvable = new ScrollPane();
		mouvable.setVbarPolicy(ScrollBarPolicy.NEVER);
		mouvable.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		 
	    images_mouvable = new GridPane();
	    
		init_from_obstacle_dir_assets();
		
		mouvable.setContent(images_mouvable);
	    
		ScrollPane inmouvable = new ScrollPane();
		inmouvable.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		inmouvable.setHbarPolicy(ScrollBarPolicy.NEVER);
		 
		images_fix = new GridPane();
	    
		init_from_obstacle_fix_dir_assets();
		
		inmouvable.setContent(images_fix);
		
		
		init_from_creation_assets();
		
		creations.setItems(names);
		StackPane stack = new StackPane();
        stack.getChildren().add(creations);

        paint = new BorderPane();
		
		
		
        
		creations.setOnMouseClicked( e -> {	      
	            System.out.println("clicked on " + creations.getSelectionModel().getSelectedItem());
	            
	            Image image = new Image("file:res/creations/"+creations.getSelectionModel().getSelectedItem());
	            
	            creation_to_edit = creations.getSelectionModel().getSelectedItem();
	            
	            ImageView source = new ImageView();
				source.setImage(image);
				
				paint.setMaxWidth(source.getFitWidth());
				paint.setMaxHeight(source.getFitHeight());
				paint.setCenter(source);

				paint.setOnDragOver( event -> {               
	                 if (event.getGestureSource() != source
	                         && event.getDragboard().hasImage()) {
	                     event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	                 }               
	                 event.consume();
	             });;
	             
	             paint.setOnDragEntered( event -> {               
	                 if (event.getGestureSource() != source
	                         && event.getDragboard().hasImage()) {
	                 }               
	                 event.consume();
	             });
	
	            
	             paint.setOnDragExited(
	                     event -> {
	                         event.consume();
	             });
	                 
	             paint.setOnDragDropped((DragEvent event) ->{
	             	
	             	Dragboard db = event.getDragboard();
	             	
	             	boolean success = false;
	             	if (db.hasImage()) {
	             		ImageView obs = new ImageView(db.getImage());
	             		obs.setX(event.getX()-(db.getImage().getWidth()/2));
	            		obs.setY(event.getY()-(db.getImage().getHeight()/2));
	            		success = true;
	            		paint.getChildren().add(obs);
	            		obstacles.add(new ObstacleModel(db.getString(), event.getX(), event.getY(), obstacle_type, draw.getWidth(), draw.getHeight()));
	             	}
	                         
	             	event.setDropCompleted(success);
	             	event.consume();
	             });
	             
	             paint.setOnMouseClicked( event -> {
	            	 if(draw != null && obstacle_name != null){
	            		ImageView obs = new ImageView(draw);
		             	obs.setX(event.getX()-(draw.getWidth()/2));
		             	obs.setY(event.getY()-(draw.getHeight()/2));
	            		paint.getChildren().add(obs);
	            		obstacles.add(new ObstacleModel(obstacle_name, event.getX(), event.getY(), obstacle_type, draw.getWidth(), draw.getHeight()));
	              	 }
	             });
	                         
				
				root.setCenter(paint);
	    });
		
        HBox boutton = new HBox(5);
        boutton.setAlignment(Pos.BOTTOM_CENTER);
		Button valider = new Button("Valider");
        Button retour = new Button("retour");
        boutton.getChildren().addAll(valider,retour);
		
		 retour.setOnAction(act->{
	        	root.getChildren().clear();
	        	root.getChildren().add(menu);
	        });
		 
		valider.setOnAction( e -> {
			FileWriter fstream;
			BufferedWriter out = null;
	        WritableImage snapshot = paint.snapshot(new SnapshotParameters(), null);
	        creation = new ImageView();
	        creation.setImage(snapshot);
	            
	        try {
	            File outputFile = null;
	        	File dest = null;
	        	for(int i = 0; i < 100; i++){
	        		dest = new File("res/creations/"+creation_to_edit.split(".png")[0]+"_"+i+".md");
	        		outputFile = new File("res/creations/"+creation_to_edit.split(".png")[0]+"_"+i+".png"); 
	        		if(!dest.exists() && !outputFile.exists())
	        			break;
	            }
				BufferedImage bImage = SwingFXUtils.fromFXImage(creation.getImage(), null);
                ImageIO.write(bImage, "png", outputFile);
				System.err.println(dest.getPath());
				fstream = new FileWriter(dest, true);
				out = new BufferedWriter(fstream);
				for(ObstacleModel o : obstacles){
					if(o.mouvable)
						out.write("mouvable\n");
					else
						out.write("inmouvable\n");
					out.write(o.path + "\n");
					out.write("" + o.posx + "\n");
					out.write("" + o.posy + "\n");
					out.write("" + o.width + "\n");
					out.write("" + o.height + "\n");
				}
		        out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				
			}
		});
		
		
		root.setBottom(boutton);
  
        root.setTop(mouvable);
        root.setRight(inmouvable);
        root.setLeft(stack);
	}
    
    
	private void init_board_game() {
		Image white = new Image("file:res/default/"+"white.png");
		
		for(int i = 0; i < HEIGHT; i++){
	        for(int j = 0; j < WIDTH; j++){
	        	 
	        	ImageView target = new ImageView();
	    		target.setImage(white);;
	            target.setFitHeight(32);
	            target.setFitWidth(32);
	             
	             plateau_view.add(target, posx++, posy);
	
	             target.setOnDragDetected(e -> {
	            	 target.startFullDrag();
	             });
	             
	             target.setOnMouseDragEntered(e -> {
	            	 if(draw != null){
	            		 target.setImage(draw);
	            	 }
	             });
	             
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
	                 }               
	                 event.consume();
	             });
	
	            
	             target.setOnDragExited(
	                     event -> {
	                         //target.setFill(targetColor);
	                         event.consume();
	             });
	                 
	             target.setOnDragDropped((DragEvent event) ->{
	             	
	             	Dragboard db = event.getDragboard();
	             	
	             	boolean success = false;
	             	if (db.hasImage()) {
	             		target.setImage(db.getImage());
	             		success = true;
	             	}
	                         
	             	event.setDropCompleted(success);
	             	event.consume();
	             });
	             
	             target.setOnMouseClicked( e -> {
	            	 if(draw != null){
	            		target.setImage(draw);
	            	 }
	             });
	                         
	        }
	        posy++;
	        posx = 0;
		} 
    
	}
	
	
	public void draw_map(){
		creation = new ImageView();
        
        HBox complet = new HBox();
        
        Button retour = new Button("retour");
        TextField name = new TextField("Give a name");
        
        Button create_map = new Button("Create !");
		create_map.setDisable(true);
        
        complet.getChildren().addAll(name, create_map,retour);
        complet.setAlignment(Pos.BOTTOM_CENTER);
        
        create_map.setOnAction(e -> {
            plateau_view.setStyle("-fx-grid-lines-visible: false");
            WritableImage snapshot = plateau_view.snapshot(new SnapshotParameters(), null);
            creation.setImage(snapshot);
            
            try {
            	File outputFile =new File("res/creations/"+name.getText()+".png"); 
                BufferedImage bImage = SwingFXUtils.fromFXImage(creation.getImage(), null);
                ImageIO.write(bImage, "png", outputFile);
            } catch (IOException ex) {
              throw new RuntimeException(ex);
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
        
        retour.setOnAction(act->{
        	root.getChildren().clear();
        	root.getChildren().add(menu);
        });
        
        plateau_view = new GridPane(); 
        plateau_view.setMaxHeight(32*HEIGHT);
        plateau_view.setMaxWidth(32*WIDTH);
        
        plateau_view.setStyle("-fx-grid-lines-visible: true");
        plateau_view.setAlignment(Pos.CENTER);
        
        plateau_view.setBorder(new Border(new BorderStroke(Color.BLACK, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
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
	}
	
	
    @Override
    public void start(Stage stage) {
        stage.setTitle("Editeur de niveau");

        root = new BorderPane();
        Scene scene = new Scene(root, 500, 500);
        stage.setMaximized(true);
        
        menu = new VBox();
        
        Button image_editor = new Button("Construire votre map!");
        Button obstacle_editor = new Button("  Creer des variantes  ");
        Button quitter =new Button("            quitter             ");
        menu.getChildren().addAll(image_editor, obstacle_editor,quitter);
        
        menu.setAlignment(Pos.CENTER);    
        
        quitter.setOnAction(quit->Platform.exit());
        image_editor.setOnAction( e -> {
        	root.getChildren().remove(menu);
        	draw_map();
        });
        
        obstacle_editor.setOnAction( e -> {
        	root.getChildren().remove(menu);
        	add_obstacles();
        });
        
        root.setCenter(menu);
        stage.setScene(scene);
        stage.show();
    }

	public static void main(String[] args) {
        Application.launch(args);
    }
}
