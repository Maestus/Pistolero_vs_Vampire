import javafx.beans.property.SimpleIntegerProperty;

public  abstract class Character extends Sprite{
	SimpleIntegerProperty life;
	int sens;

	public Character(double posX, double posY,
			double speed, double maxX, double maxY, double width, double height,double offsetX,double offsetY,int life) {
		super(posX, posY, speed, maxX, maxY, width, height,offsetX,offsetY);
		this.life = new SimpleIntegerProperty(life);
	}
	
	public  abstract boolean getHurt(int dammage);
	
	
	public boolean isAlive(){
		if(life.getValue()>0)
			return true;
		else
			return false;
	}
}
