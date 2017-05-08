
public  abstract class Character extends Sprite{
	int life;
	int sens;

	public Character(double posX, double posY,
			double speed, double maxX, double maxY, double width, double height,double offsetX,double offsetY,int life) {
		super(posX, posY, speed, maxX, maxY, width, height,offsetX,offsetY);
		this.life = life;
	}
	
	public  abstract boolean getHurt(int dammage);
	
	
	public boolean isAlive(){
		if(life>0)
			return true;
		else
			return false;
	}
}
