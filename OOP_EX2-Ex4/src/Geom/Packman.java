package Geom;

import Algorithm.Path;

public class Packman {
	
	private int id;
	private double Radius;
	private double Speed;
	private double Orientation;
	public Point3D location;
	Path myPath;

	
	
	public Packman(Point3D location, String id, String Radius, String Speed) {
		this.id=Integer.parseInt(id);
		this.location=location;
		this.Radius=Integer.parseInt(Radius);
		this.Speed=Integer.parseInt(Speed);
		this.myPath=new Path();
		
	}
	public Packman(Packman p) {
		this.id=p.id;
		this.Radius=p.Radius;
		this.Speed=p.Speed;
		this.location=p.location;
		setPath(new Path());
	}



	public Packman(Point3D point_return, double radius2, double speed2) {
		this.location=point_return;
		this.Radius=radius2;
		this.Speed=speed2;
	}
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	public double getOrientation() {
		return this.Orientation;
	}
	



	public double getRadius() {
		return Radius;
	}



	public void setRadius(double input_radius) {
		Radius = input_radius;
	}



	public double getSpeed() {
		return Speed;
	}



	public void setSpeed(double input_speed) {
		Speed = input_speed;
	}



	public Point3D getLocation() {
		return this.location;
	}



	public void setLocation(Point3D location) {
		this.location = location;
	}
	
	public void setPath(Path myPath) {
		this.myPath=myPath;
	}
	public Path getPath() {
		return this.myPath;
	}
	
	
	/**
	 * Methods
	 */
	
	
	public void Move() {
		
		
		
		
		
	}
	public String tostring() {
		String s="Packman ID ="+this.id+" Speed is : "+this.Speed+" Eat Radius is : "+this.Radius+" And the location is : "+this.location;
		return s;
		
		
	}
	
	
}
