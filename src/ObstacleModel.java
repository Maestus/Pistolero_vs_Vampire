
public class ObstacleModel {

	String path;
	double posx;
	double posy;
	double height;
	double width;
	boolean mouvable;
	
	public ObstacleModel(String p, double x, double y, boolean mouvable, double w, double h){
		path = p;
		posx = x;
		posy = y;
		this.mouvable = mouvable;
		this.width = w;
		this.height = h;
	}
	
}
