
public class Bullet extends Sprite{
	boolean explose;
	public Bullet(Pistoleros pist,double speed, double width, double height,double offsetX,double offsetY) {
		super(0,0,speed,pist.maxX,pist.maxY,width,height,offsetX,offsetY);
		switch(pist.sens){
		case 0:
			moveY=-speed;
			posX=(2*pist.posX+pist.width)/2-width/2;
			posY=pist.posY;
			break;
		case 1:
			moveX=speed;
			posX=pist.posX+pist.width;
			posY=(2*pist.posY+pist.height)/2;
			break;
		case 2:
			moveY=speed;
			posX=(2*pist.posX+pist.width)/2-width/2;
			posY=pist.posY+pist.height;
			break;
		case 3:
			moveX=-speed;
			posX=pist.posX;
			posY=(2*pist.posY+pist.height)/2;
			break;
		}
		explose = false;
		// TODO Auto-generated constructor stub
	}
	public void update(double speed){
		move(speed);
		if(posX >= maxX-width  || posY>=maxY-height || posX==0 || posY==0 )
			explose = true;
		
	}

	
}
