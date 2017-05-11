
public class Obstacle extends Sprite{
	int poids;
	public Obstacle(double posX, double posY,double maxX, double maxY, double width, double height,
			double offsetX, double offsetY,int poids) {
		super(posX, posY, 0, maxX, maxY, width, height, offsetX, offsetY);
		this.poids =poids;
	}

}
