import java.util.ArrayList;

public class Container {
	public ArrayList<Vampire> vampList;
	public ArrayList<Bullet> bullets;
	public ArrayList<Obstacle> obstacles;
	public Pistoleros pist;
	int change;
	int []choix;
	public Container(ArrayList<Vampire> vampList, Pistoleros pist,	ArrayList<Obstacle> obstacles) {
		this.vampList = vampList;
		bullets = new ArrayList<Bullet>();
		this.pist = pist;
		this.obstacles=obstacles;
		choix = new int[vampList.size()];
		change=0;
	}
	public void update(double speed){
		
		if(pist.kc.moveUp()){
			
			pist.offsetY=0;
			pist.moveUp();
			pist.sens=0;
		}
		else if(pist.kc.moveDown()){
			
				pist.offsetY=36*2;
			pist.moveDown();
			pist.sens=2;

		}	
		else if(pist.kc.moveRight()){
			
			pist.offsetY=36;
			pist.moveRight();
			pist.sens=1;

		}
		else if(pist.kc.moveLeft()){
			
			pist.offsetY=36*3;
			pist.moveLeft();
			pist.sens=3;
		}
		pist.move(speed);
		if(!pist.kc.moveLeft() && !pist.kc.moveRight())
			pist.stopVert();
		if(!pist.kc.moveDown() && !pist.kc.moveUp())
			pist.stopHor();
		
		for(int i=0;i<bullets.size();i++){
			bullets.get(i).update(speed);
		}
		if(pist.kc.moveSpace() && pist.shoot()){
			Bullet bulls = new Bullet(pist,400,10,10,0,0);
			bullets.add(bulls);
			System.out.println("click");
		}
		if(pist.reloadTime != 20)
			pist.reloadTime++;
		if(pist.hurtTime !=50){
			pist.hurtTime++;
			if(pist.hurtTime==50)
				pist.getHurt=false;
		}
		for(int i=0;i<vampList.size();i++){
			if(change== 10)
				change =0;
			if(change==0){
				choix [i]= (int) (Math.random()*4);
			}
			
			
			
			switch(choix[i]){
				case 0:
					vampList.get(i).moveRight();
					vampList.get(i).offsetY=36;
					vampList.get(i).moveY=0;
					vampList.get(i).sens=1;
					break;
				case 1:
					vampList.get(i).moveLeft();
					vampList.get(i).offsetY=3*36;
					vampList.get(i).moveY=0;
					vampList.get(i).sens=3;

					break;
				case 2:
					vampList.get(i).moveUp();
					vampList.get(i).offsetY=0;
					vampList.get(i).moveX=0;
					vampList.get(i).sens=0;
					break;
				case 3:
					vampList.get(i).moveDown();
					vampList.get(i).offsetY=2*36;
					vampList.get(i).moveX=0;
					vampList.get(i).sens = 2;
					break;	
			}
			vampList.get(i).move(speed);
		}
		change++;
		checkCollides(speed);
		

	}
	public void checkCollides(double speed){
		for(int i=0;i<vampList.size();i++){
			for(int j=i+1;j<vampList.size();j++){
				if(vampList.get(i).collides(vampList.get(j)) || vampList.get(j).collides(vampList.get(i))){
					vampList.get(i).life=0;
					break;
				}
			}
			for(int j=0;j<bullets.size();j++){
				if((vampList.get(i).collides(bullets.get(j))|| bullets.get(j).collides(vampList.get(i))) && vampList.get(i).isAlive() && !bullets.get(j).explose){
					vampList.get(i).life--;
					bullets.get(j).explose=true;
					break;
				}
			}
			if((vampList.get(i).collides(pist) || pist.collides(vampList.get(i))) && pist.isAlive() && vampList.get(i).isAlive()){
				System.out.println("collision");
				if(pist.getHurt(vampList.get(i).getDammage()))
					System.out.println("getHurt");
			}
			for(int j =0;j<obstacles.size();j++){
				if(vampList.get(i).collides(obstacles.get(j))|| obstacles.get(j).collides(vampList.get(i))){
					System.out.println("collission arbre");
					if(vampList.get(i).sens==0)
						vampList.get(i).posY+=vampList.get(i).speed*speed;
					else if(vampList.get(i).sens==1)
						vampList.get(i).posX-=vampList.get(i).speed*speed;
					else if(vampList.get(i).sens==2)
						vampList.get(i).posY-=vampList.get(i).speed*speed;
					else
						vampList.get(i).posX +=vampList.get(i).speed*speed;
						

				}
			}			
		}
		for(int i =0;i<obstacles.size();i++){
			if(pist.collides(obstacles.get(i))|| obstacles.get(i).collides(pist)){
				System.out.println("collission arbre");
				if(pist.sens==0)
					pist.posY+=pist.speed*speed;
				else if(pist.sens==1)
					pist.posX-=pist.speed*speed;
				else if(pist.sens==2)
					pist.posY-=pist.speed*speed;
				else
					pist.posX +=pist.speed*speed;
					

			}
		}
	}
	
	
	
}
