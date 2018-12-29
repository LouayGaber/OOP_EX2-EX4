package Algorithm;

import java.util.ArrayList;

import GUI.Map;
import Geom.Fruit;
import Geom.Packman;
import Geom.Point3D;

public class Path {
	
	public double Time;
	Map map=new Map();
	
	private ArrayList<Fruit> Targetpath;
	private ArrayList<Packman> packmans;
	public Path() {
		this.Time=0;
		this.Targetpath=new ArrayList<>();
		
	}
	public void setTime(double Time) {
		this.Time=Time;
	}
	public double getTime() {
		return this.Time;
	}
	public ArrayList<Fruit> getmyTarget(){
		return this.Targetpath;
	}
	public ArrayList<Packman> getmyPackmans(){
		return this.packmans;
	}
	public Point3D nextTarget(Packman p,Fruit f) {
		double dt=p.getPath().getTime();
		double vx= p.getLocation().x()/dt+0.05;
		double vy=p.getLocation().y()/dt+0.05;
		double xt = p.getLocation().x()+vx*(f.getFlocation().x()-p.getLocation().x());
		double yt= p.getLocation().y()+vy*(f.getFlocation().y()-p.getLocation().y());
		Point3D TARGET=new Point3D(xt,yt);
		return TARGET;
		
	}
	
	public double Time2Target(Packman p,Fruit f) {
		if(map.Distance_in_Pixel(p.getLocation(), f.getFlocation())<p.getRadius()) return 0;
		else {
			return (map.Distance_in_Pixel(p.getLocation(),f.getFlocation())-p.getRadius()/p.getSpeed());
		}
	}
	public double TotalTime(Packman p) {
		double TotalTime=0;
		double Time2Target=0;
		Packman pack=new Packman(p);
		for (int i = 0; i <p.getPath().getmyTarget().size(); i++) {
			Time2Target=Time2Target(p,p.getPath().getmyTarget().get(i));
			TotalTime=TotalTime+Time2Target;
			p.setLocation(p.getPath().getmyTarget().get(i).getFlocation());
			
		}
		p.getPath().setTime(TotalTime);
		p.setLocation(pack.getLocation());
		return TotalTime;
		
		
	}
	public void setPath(ArrayList<Fruit> f) {
		this.Targetpath=f;
	}
	
	
	
	
	
}
