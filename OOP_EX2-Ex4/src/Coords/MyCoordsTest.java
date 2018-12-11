package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Coords.MyCoords;

class MyCoordsTest extends MyCoords {
	
	MyCoords mycords;
	Point3D gps0;
	Point3D gps1;
	double expectedValue;
	double acutalValue;

	@BeforeEach
	void setUp() throws Exception {
		gps0=new Point3D(32.103315,35.209039,670);
		gps1=new Point3D(32.106352,35.205225,650);
		expectedValue = 0;
		acutalValue = 0;

		mycords = new MyCoords();
	}


	@Test
	void testAdd() {
		Point3D local_vector_in_Meter=new Point3D(10,20,30);
		Point3D res=new Point3D(mycords.add(gps0, local_vector_in_Meter));
		assertEquals("32.10741383602047,35.209151263133634,702.632644386962", res.toString());
	}

	@Test
	void testDistance3d() {
		double d=mycords.distance3d(gps0, gps1);
		assertEquals(493.504487890462,d);
	}

	@Test
	void testVector3D() {
		Point3D p0=mycords.gpstoxyz(gps0);
		Point3D p1=mycords.gpstoxyz(gps1);
		Point3D expctedPoint = new Point3D(p1.x()-p0.x(),p1.y()-p0.y(),p1.z()-p0.z());
		Point3D acutalPoint  = vector3D(gps0, gps1);
		if(!expctedPoint.equals(acutalPoint))
			fail("You Got Worng with the verktor3D function");

	}

	@Test
	void testAzimuth_elevation_dist() {
		double[] acutal_Azimuth_elevation_dist  = azimuth_elevation_dist(gps0, gps1);
		System.out.println(acutal_Azimuth_elevation_dist[0]);
		System.out.println(acutal_Azimuth_elevation_dist[1]);
		System.out.println(acutal_Azimuth_elevation_dist[2]);
		if(acutal_Azimuth_elevation_dist[0] != 321.47050096915774) 
			fail("You got worng with the north_angle function in point ");

		if(acutal_Azimuth_elevation_dist[1] != 2.3226323668828446) 
			fail("You got worng with the elevation calcaulte");

		if(acutal_Azimuth_elevation_dist[2] != 493.504487890462) 
			fail("You got worng with the distance3d at pointe");

	}

	@Test
	void testIsValid_GPS_Point() {
		assertTrue(isValid_GPS_Point(gps0));
	}

}
