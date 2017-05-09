import java.util.ArrayList;


import javafx.scene.layout.Pane;

public class ContainerView {
	public Container container;
	private PistoleroView player;
	private ArrayList<BulletView> bullets;
	private ArrayList<VampireView> vamps;
	private ArrayList<ObstacleView>  obstacles;
	private ArrayList<AnimationExplosion>  animation;

	Pane p;

	public ContainerView(Container container,Pane p){
		this.p = p;
		this.container = container;
		player = new PistoleroView(container.pist,p);
		vamps=new ArrayList<VampireView>();
		bullets =new ArrayList<BulletView>();
		obstacles =new ArrayList<ObstacleView>();

		player.add();
		for(int i=0;i<container.vampList.size();i++){
			vamps.add(new VampireView(p,container.vampList.get(i)));
			vamps.get(i).add();
		}
		System.out.println("connt"+container.obstacles.size());
		for(int i=0;i<container.obstacles.size();i++){
			obstacles.add(new ObstacleView(p,container.obstacles.get(i)));
			obstacles.get(i).add();
			System.out.println("add");
		}
		animation = new ArrayList<AnimationExplosion>();
	}

	public void update(){
		relocateAll();
		removingDeath();
	}

	public void relocateAll(){
		player.update();
		if(((Pistoleros)player.getCharact()).getHurt)
			player.imageView.setOpacity(0.4);
		else
			player.imageView.setOpacity(1);
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
		
		if(!player.getCharact().isAlive())
			player.remove();
	}
}
