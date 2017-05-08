
public class Gun {
	int damage;
	String name;
	int nbBullet;
	int nbMaxBullet;
	public Gun(int damage, String name, int nbMaxBullet) {
		super();
		this.damage = damage;
		this.name = name;
		this.nbBullet = nbMaxBullet;
		this.nbMaxBullet = nbMaxBullet;
	}
	
	public boolean shoot(){
		if(nbBullet==0)
			return false;
		else
			return true;
	}
	
	public void reload(){
		this.nbBullet=nbMaxBullet;
	}
}
