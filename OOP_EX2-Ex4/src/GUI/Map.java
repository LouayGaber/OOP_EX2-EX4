package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Coords.MyCoords;
import Geom.Point3D;


//Source :- https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
public class Map {
	private Point3D leftUp;
	private Point3D RightDown;
	private double X;
	private double Y;
	private String path;
	public Map() {
		this.leftUp = new Point3D(32.105770,  35.202469);
		this.RightDown = new Point3D(32.101899, 35.211588);
		this.X = this.RightDown.y()-this.leftUp.y();
		this.Y = this.RightDown.x()-this.leftUp.x();
		this.path="C:\\Users\\Louay\\git\\OOP_EX2-EX4\\data\\Ariel1.png";

	}
	/**
	 * Contractor of Map : Receiv 3 Parameters
	 * @param LeftUpPointGps Point3D in the Lef tUp Coordinates 
	 * @param RightDownGps Point3D in the Right Down Coordinates 
	 * @param MapPath The Directory of Pictures 
	 */
	public Map(Point3D LeftUpPointGps , Point3D RightDownGps) {
		this.leftUp = LeftUpPointGps;
		this.RightDown = RightDownGps;
		this.X = this.RightDown.y()-this.leftUp.y();
		this.X = this.RightDown.x()-this.leftUp.x();
		

	}

	/**
	 * Convert a Pixel Coordinate to GPS Coordinates.
	 * @param Dx Wight of Picture
	 * @param Dy Hight Of Picture
	 * @return The Point3D in the Coordinate GPS corresponding to the pixel
	 */
	public  Point3D Pixel2GPS(double Diff_x , double Diff_y) {
		
		
		double LonX = Diff_x * this.X+leftUp.y();
		double LatY = Diff_y * this.X+leftUp.x(); // or RightdDown.x()
		
		Point3D GPSP = new Point3D(LatY,LonX);
		
		return GPSP;
	}
	
	/**
	 * Convert a Gps Coordinates to Pixel Coordinates
	 * @param p Receiv Point3D in GPS Corrdinates 
	 * @return Point3D in Pixel Coordinates
	 */
	public Point3D GPS2Pixel(Point3D p) {
		
		double Diff_x = (p.y()-leftUp.y())/this.X;
		double Diff_y = (p.x()-leftUp.x())/this.Y;
		Point3D PIXELP=new Point3D(Diff_x,Diff_y);
	return PIXELP;
			
	}
	/**
	 * Calculate the Distance Between 2 Points Pixels
	 * @param p1 Receiv the First Pixel Point 
	 * @param p2 Receiv the Secnd Pixel Point
	 * @return Distance Between the Points 
	 */
	public double Distance_in_Pixel(Point3D p1, Point3D p2) {
		Point3D X =  Pixel2GPS(p1.x(),p1.y());
		Point3D Y =  Pixel2GPS(p2.x(),p2.y());
		MyCoords m = new MyCoords();
		double result = m.distance3d(X, Y);
		return result;

	}
	
	
}