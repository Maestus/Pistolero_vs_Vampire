import java.util.ArrayList;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ContainerView {
	public static double WIDTH = 1270;
	public static double HEIGHT = 620;
	public Container container;
	private PistoleroView player;
	private ArrayList<BulletView> bullets;
	private SimpleListProperty<VampireView> vamps;
	private SimpleListProperty<ObstacleView>  obstacles;
	

	private ArrayList<AnimationExplosion>  animation;
	Label tv;
	Pane p;

	public ContainerView(Container container,Pane p){
		this.p = p;
		loadBackGround();
		this.container = container;
		setPlayer(new PistoleroView(container.pist,p));
		bullets =new ArrayList<BulletView>();
		getPlayer().add();
		loadVampire();
		loadObstacle();
		animation = new ArrayList<AnimationExplosion>();
		
		
	}

	public void update(){
		p.requestFocus();
		relocateAll();
		removingDeath();
	}

	public void relocateAll(){
		getPlayer().update();
		if(((Pistoleros)getPlayer().getCharact()).getHurt)
			getPlayer().imageView.setOpacity(0.4);
		else
			getPlayer().imageView.setOpacity(1);
		for(int i=0;i<vamps.size();i++){
			vamps.get(i).update();
		}
		for(int i=0;i<container.bullets.size();i++){
			if(i>=bullets.size()){
				bullets.add(new BulletView(container.bullets.get(i),p));
				bullets.get(i).add();
			}
			bullets.get(i).update();
		}
		for(int i=0;i<obstacles.size();i++){
			obstacles.get(i).imageView.relocate(obstacles.get(i).caisse.posX,obstacles.get(i).caisse.posY);
		}
	}
	
	public void removingDeath(){
		for(int i=0;i<animation.size();i++){
			if(animation.get(i).isFinished)
				animation.remove(i);
		}
		for(int i=0;i<container.bullets.size();i++){

			if(container.bullets.get(i).explose){
				if(container.bullets.get(i).moveX>0)
					animation.add(new AnimationExplosion(container.bullets.get(i).posX,container.bullets.get(i).posY, p));
				else
					animation.add(new AnimationExplosion(container.bullets.get(i).posX,container.bullets.get(i).posY, p));

				animation.get(animation.size()-1).play();
				container.bullets.remove(i);
				bullets.get(i).remove();
				bullets.remove(i);
				break;
			}
		}
		
		for(int i=0;i<vamps.size();i++){
			if(!vamps.get(i).charact.isAlive()){
				vamps.get(i).remove();
				vamps.remove(i);
				container.vampList.remove(i);
			}
			
			
		}
		
		if(!getPlayer().getCharact().isAlive())
			getPlayer().remove();
	}
	public SimpleListProperty<VampireView> getVamps() {
		return vamps;
	}

	public void setVamps(SimpleListProperty<VampireView> vamps) {
		this.vamps = vamps;
	}

	public PistoleroView getPlayer() {
		return player;
	}

	public void setPlayer(PistoleroView player) {
		this.player = player;
	}
	public void loadBackGround(){
		ImageView bg = new ImageView(new Image("file:src/bg2.png"));
		p.getChildren().add(bg);
		bg.setFitWidth(Game.WIDTH);
        bg.setFitHeight(Game.Height-100);
	}
	public void loadVampire(){
		vamps=new SimpleListProperty<VampireView>(FXCollections.observableArrayList());
		for(int i=0;i<container.vampList.size();i++){
			vamps.add(new VampireView(p,container.vampList.get(i)));
			vamps.get(i).add();
		}
	}
	public void loadObstacle(){
		obstacles =new SimpleListProperty<ObstacleView>(FXCollections.observableArrayList());

		for(int i=0;i<container.obstacles.size();i++){
			obstacles.add(new ObstacleView(p,container.obstacles.get(i)));
			obstacles.get(i).add();
		}
	}
}
