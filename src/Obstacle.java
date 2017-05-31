
public abstract class Obstacle extends Sprite{
	int poids;
	String name;
	public Obstacle(String name, double posX, double posY,double maxX, double maxY, double width, double height,
			double offsetX, double offsetY,int poids) {
		super(posX, posY, 0, maxX, maxY, width, height, offsetX, offsetY);
		this.poids =poids;
		this.name = name;
	}

}
