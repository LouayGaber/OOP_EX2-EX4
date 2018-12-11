package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter {
	
	/**
	 * Author : Louay 
	 * This class implement coords_converter functions . 
	 * 
	 */
	
	public static final int RADUIS_EARTH = 6371000;//Earth Radius 


	/**
	 * This function can add or give the new point by givin an 3d point and distance or vector in meter 
	 * @param gps : The 3d point (gps ) that we want to add or change there place 
	 * @param local_vector_in_meter : a vector in meter 
	 * @return The new 3d point .
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D Ngps=gpstoxyz(gps);
		double Nx=Ngps.x()+local_vector_in_meter.x();
		double Ny=Ngps.y()+local_vector_in_meter.y();
		double Nz=Ngps.z()+local_vector_in_meter.z();
		Point3D p2=new Point3D(Nx,Ny,Nz);
		return toGps(p2);
		
	}

	/**
	 * This function calculate the 3d distance between 2 gps points . 
	 * by converting the 2 gps point to xyz coordinate then implement the distance3d of the POINT3D class 
	 */
	
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		
		Point3D Ngps0=gpstoxyz(gps0);
		Point3D Ngps1=gpstoxyz(gps1);
		return Math.abs(Ngps0.distance3D(Ngps1));

	}

	/**
	 * This function calculate the 3DVECTOR  by giving 2 Gps points 
	 * the 2 points are converted to an xyz coordinate 
	 * then calculate the difference betweeen every axis coordinate 
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		
		Point3D Ngps0=gpstoxyz(gps0);
		Point3D Ngps1=gpstoxyz(gps1);
		
		double dx=Ngps1.x()-Ngps0.x();
		double dy=Ngps1.y()-Ngps0.y();
		double dz=Ngps1.z()-Ngps0.z();
		Point3D v=new Point3D(dx, dy, dz);
		
		
		return v;
	}
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		
		double[] result=new double [3];
		result[0]=gps1.north_angle(gps0);
		result[1]= Math.toDegrees(Math.asin((gps0.z()-gps1.z())/(distance3d(gps0 , gps1))));
		double distance=distance3d(gps0,gps1);
		result[2]=distance;
		return result;
	}

	/**
	 * This basic function check the validate of the Gps point 
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		return (p.x()>-180&&p.x()<180 && p.y()>-90&&p.y()<90 && p.z()>-450&& p.z()<Double.POSITIVE_INFINITY);
		
	}
	
	
	
	//Convert Cartesian to GPS coordinates
	public Point3D toGps(Point3D p) {
		
		double x=Math.asin(p.z()/RADUIS_EARTH)*180/Math.PI;
		double y=Math.atan2(p.y(), p.x())*180/Math.PI;
		double r =Math.sqrt(Math.pow(p.x(), 2)+Math.pow(p.y(), 2)+Math.pow(p.z(), 2))-RADUIS_EARTH;
		Point3D p2=new Point3D(x,y,r);
		return p2;
	}
	
	//Convert GPS coordinates to Cartesian 
	public Point3D gpstoxyz(Point3D p) {
		double G_x = Math.cos(p.y()/180*Math.PI) * Math.cos(p.x()/180*Math.PI)*(RADUIS_EARTH+p.z());;
		double G_y = Math.sin(p.y()/180*Math.PI) * Math.cos(p.x()/180*Math.PI)*(RADUIS_EARTH+p.z());
		double G_z = (RADUIS_EARTH+p.z())*Math.sin(p.x()/180*Math.PI);
		Point3D p2=new Point3D(G_x,G_y,G_z);
		return p2;

		
	}

}
