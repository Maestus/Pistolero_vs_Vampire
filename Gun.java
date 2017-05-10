import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class Gun {
	int damage;
	String name;
	SimpleIntegerProperty nbBullet;
	int nbMaxBullet;
	int reloadTime;
	public Gun(int damage, String name, int nbMaxBullet) {
		super();
		this.damage = damage;
		this.name = name;
		this.nbBullet = new SimpleIntegerProperty(nbMaxBullet);
		this.nbMaxBullet = nbMaxBullet;
		reloadTime=0;
	}
	
	public boolean shoot(){
		if(nbBullet.getValue()==0)
			return false;
		else
			return true;
	}
	
	public boolean reload(){
		if(reloadTime==300){
			this.nbBullet.setValue(nbMaxBullet);
			reloadTime =0;
			return true;
		}
		else
			return false;
			
	}
}
