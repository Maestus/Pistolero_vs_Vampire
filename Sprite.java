
public abstract class Sprite {
	protected double posX;
	protected double posY;
	protected double moveX;
	protected double moveY;
	protected double speed;
	protected double maxX;
	protected double maxY;
	protected double width;
	protected double height;
	protected double offsetX;
	protected double offsetY;
	public Sprite(double posX, double posY,
			double speed, double maxX, double maxY, double width, double height,double offsetX,double offsetY){
		this.posX = posX;
		this.posY = posY;
		this.moveX = 0;
		this.moveY = 0;
		this.speed = speed;
		this.maxX = maxX;
		this.maxY = maxY;
		this.width = width;
		this.height = height;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public void moveRight(){
		moveX = speed;
	}
	public void moveLeft(){
		moveX = -speed;
	}
	public void moveUp(){
		moveY = -speed;
	}
	
	public void moveDown(){
		moveY = speed;
	}
	public void stopVert(){
		moveX=0;
	}
	public void stopHor(){
		moveY=0;
	}
	public void move(double time){
		double deplX = moveX*time;
		double deplY = moveY*time;
		if(posX+deplX >=maxX-width)
			posX =maxX-width;
		else if(posX+deplX <=0)
			posX =0;
		else 
			posX += deplX;
		if(posY+deplY >=maxY-height)
			posY =maxY-height;
		else if(deplY+posY <=0)
			posY =0;
		else 
			posY += deplY;
		
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public double getMoveX() {
		return moveX;
	}

	public double getMoveY() {
		return moveY;
	}


	public double getSpeed() {
		return speed;
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMaxY() {
		return maxY;
	}
	public boolean collides(Sprite s){

		if((s.posX<=posX && posX <= s.posX+s.width && s.posY <= posY && posY <= s.posY+s.height) || (s.posX <= posX +width && posX+width <= s.posX+ s.width && s.posY <= posY && posY <= s.posY+s.height ) 
				|| (s.posX<=posX && posX <= s.posX+s.width && s.posY <= posY+height && posY+height <= s.posY+s.height) && (s.posX <= posX +width && posX+width <= s.posX+ s.width && s.posY <= posY+height && posY+height <= s.posY+s.height) ) {
			System.out.println("premier cas");
			return true;
		}
		/*else
			System.out.println("deuxieme cas")*/
		else return false;
			
	}
	public boolean inBorder(){
		if(posX==0 || posY==0 || posX+width==maxX || posX+height==maxY)
			return true;
		else
			return false;
					
	}
}
