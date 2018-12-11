package File_format;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import Algorithm.MultiCSV;
import Coords.MyCoords;
import Geom.Point3D;

public class Test {
	

	public static void main(String[] args) throws IOException, ParseException {
/*		Csv2kml cv=new Csv2kml();
		String pathfile="C:\\Users\\Louay\\Desktop\\WigleWifi_20171201110209.csv";
		File file=new File(pathfile);
		Csv2kml.readFile(file);
		Csv2kml test=new Csv2kml();
		Csv2kml.to_KML(Csv2kml.readFile(file),"C:\\Users\\Louay\\Desktop\\test.kml");
		*/
		/**
		
		String dir="C:\\Users\\Louay\\git\\OOP_EX2-EX4\\data";
		MultiCSV.oneCsvoneKml(mu.Directory(dir));
		MultiCSV.multicsvtoKml(mu.Directory(dir), dir);
		*/
		MyCoords mycords=new MyCoords();
		Point3D gps0=new Point3D(32.103315,35.209039,670);
		Point3D local_vector_in_Meter=new Point3D(10,20,30);
		Point3D res=new Point3D(mycords.add(gps0, local_vector_in_Meter));
		System.out.println(res.toString());



}
}
