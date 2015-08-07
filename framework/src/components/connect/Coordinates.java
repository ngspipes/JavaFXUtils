package components.connect;

public class Coordinates {

	private double x;
	public double getX(){ return x; }
	public void setX(double x){ this.x = x; }
	
	private double y;
	public double getY(){ return y; }
	public void setY(double y){ this.y = y; }
	
	public Coordinates(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinates() {
		this(0, 0);
	}
	
}
