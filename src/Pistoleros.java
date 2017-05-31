import javafx.beans.property.SimpleIntegerProperty;

public class Pistoleros extends Character{
	Gun gun;
	KeyController kc;
	int reloadTime;
	boolean getHurt;
	int hurtTime;
	SimpleIntegerProperty score;
	public Pistoleros(double posX, double posY,
			double speed, double maxX, double maxY, double width, double height,double offsetX,double offsetY, int life,	Gun gun,KeyController kc) {
		super(posX, posY, speed, maxX, maxY,width, height,offsetX,offsetY, life);
		this.gun = gun;
		this.kc =kc;
		sens=0;
		getHurt=false;
		reloadTime = 20;
		hurtTime = 50;
		score = new SimpleIntegerProperty();
		score.set(0);
	}
	
	public void KeyControllerchanged(KeyController kc){
		this.kc = kc;
	}
	
	
	public boolean shoot(){
		if(reloadTime==20 && gun.nbBullet.getValue() !=0){
			reloadTime = 0;
			gun.nbBullet.setValue(gun.nbBullet.getValue()-1);
			return true;
		}
		else
			return false;
	}
	
	public int getDammage(){
		return gun.damage;
	}
	public boolean getHurt(int dam){
		if(!getHurt){
			getHurt = true;
			hurtTime=0;
			life.setValue(life.getValue()-dam);
			return true;
		}
		else
			return false;
	}

	public void up_kill_scoring() {
		score.set(score.get()+10);
	}

	public void up_time_score(SimpleIntegerProperty time) {
		score.set(score.get()+time.get());
	}

	
}
