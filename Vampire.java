
public class Vampire extends Character{
	private int dammage;
	private boolean sex;
	public Vampire(double posX, double posY, double speed, double maxX, double maxY, double width, double height, double offsetX,double offsetY,int life,int dammage,boolean sex) {
		super(posX, posY, speed, maxX, maxY, width, height,offsetX,offsetY, life);
		this.setDammage(dammage);
		this.setSex(sex);
	}
	public int getDammage() {
		return dammage;
	}
	public void setDammage(int dammage) {
		this.dammage = dammage;
	}
	public boolean getSex() {
		return sex;
	}
	public boolean getHurt(int dam){
		life.setValue(life.getValue()-dam);
		return true;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	
}
