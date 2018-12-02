package Coords;

import Geom.Point3D;

public class Test {

	public static void main(String[] args) {
		
		MyCoords mc=new MyCoords();
		Point3D gps0=new Point3D(32.103315,35.209039,670);
		Point3D gps1=new Point3D(32.106352,35.205225,650);
		System.out.println(mc.gpstoxyz(gps1).toString());
		Point3D local_vector_in_meter=new Point3D(10,10,10);
		System.out.println(mc.distance3d(gps0, gps1));
		
		System.out.println(mc.add(gps1, local_vector_in_meter).toString());
		

	}

}
