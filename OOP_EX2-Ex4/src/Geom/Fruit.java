package Geom;

public class Fruit {
	
	private Point3D Flocation;
	private double weight;
	private int id;
	
	public Fruit(Point3D Flocation,String weight,String id) {
		
		this.Flocation=Flocation;
		this.weight=Integer.parseInt(weight);
		this.id=Integer.parseInt(id);
	}
	public Fruit(Fruit f) {
		this.Flocation=f.Flocation;
		this.weight=f.weight;
		
	}
	
public Fruit(Point3D p, double Weight) {
		
		this.Flocation = p;
		this.weight = Weight;
	}
	public Point3D getFlocation() {
		return this.Flocation;
	}
	public void setFlocation(Point3D Flocation) {
		this.Flocation=Flocation;
		
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String tostring() {
		String s="Fruit ID ="+this.id+" Weight is : "+this.weight+" And the location is : "+this.Flocation;
		return s;
	
	}

}
