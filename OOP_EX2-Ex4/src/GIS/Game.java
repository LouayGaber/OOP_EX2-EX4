package GIS;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Coords.MyCoords;
import File_format.Csv2kml;
import GUI.ConvertLatLong2XY;
import GUI.Map;
import Geom.Box;
import Geom.Fruit;
import Geom.Geom_element;
import Geom.Packman;
import Geom.Point3D;
import Robot.Play;
public class Game {
	
	
	private String Type;
	private  int id;
	private String Lat;
	private String Lon;
	private String Alt;
	private int Spewe;//Speed or weight
	private int Radius;
	public String path="";
	public Map map=new Map();
	public Play play;
	
	public Player Player_user;
	public   ArrayList<Packman> Packmans = new ArrayList<>();
	public   ArrayList<Fruit> Fruits = new ArrayList<>();
	public   ArrayList<Box> Boxs_arr=new ArrayList<>();
	public   ArrayList<Ghost> Ghost_arr=new ArrayList<>();
	
	public Game(ArrayList<Packman> Packmans,ArrayList<Fruit> Fruits) {
	this.Packmans=Packmans;
	this.Fruits=Fruits;
		
	}
	public Game() throws IOException {
	initGameusecsv(path);
	
		
		
	}
	
	public Game(ArrayList<Packman> packman_arr, ArrayList<Fruit> fruits_arr, ArrayList<Box> boxs_arr,
			ArrayList<Ghost> ghost_arr) {
		// TODO Auto-generated constructor stub
	}
	public Game(Play play) throws IOException {
		this.play = play;
		CreateGame(play.getBoard());
	}
	
	public void initGameusecsv(String path) throws IOException{
		this.path=path;
		List<String[]> s=Csv2kml.readCsvGame(path);
		
		for (int i = 1; i < s.size(); i++) {
			String[] line=s.get(i);
			
			if(line[0].equals("P")) {
				Double x = Double.parseDouble(line[2]);
				Double y = Double.parseDouble(line[3]);
				Double z = Double.parseDouble(line[4]);
				Point3D pgps=new Point3D(x,y,z);
				pgps=map.GPS2Pixel(pgps);
				Packman p=new Packman(pgps,line[1],line[6],line[5]);
				
				System.out.println(p.tostring());
				Packmans.add(p);
				
			}
			if(line[0].equals("F")) {
				Double x = Double.parseDouble(line[2]);
				Double y = Double.parseDouble(line[3]);
				Double z = Double.parseDouble(line[4]);
				Point3D pgps=new Point3D(x,y,z);
				pgps=map.GPS2Pixel(pgps);
				
				Fruit fu=new Fruit(pgps, line[5],line[1]);
				System.out.println(fu.tostring());
				Fruits.add(fu);
				
				
			}
			
			
			
		}
		
		
		
		
	}
	public void saveGame() throws FileNotFoundException {
		Csv2kml cv=new Csv2kml();
		cv.writeCsv(this.Packmans,this.Fruits);
		
		
		
		
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		Game g=new Game();
		g.saveGame();
		
		
	}

	public void setPath(String s) {
		this.path=s;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public void CreateGame(ArrayList<String> board) throws IOException{		

		ArrayList<String> s = board;

		Packmans=new ArrayList<>();
		Fruits=new  ArrayList<>();
		Ghost_arr=new ArrayList<>();
		for(int i=0;i<s.size();i++) {
			String line = s.get(i);
			String[] row = line.split(",");

			if(row[0].equals("P")) {
				Point3D p = new Point3D(Double.parseDouble(row[2]),Double.parseDouble(row[3]),Double.parseDouble(row[4]));
				p = map.GPS2Pixel(p);
			
				double speed = Double.parseDouble(row[5]);
				double radius = Double.parseDouble(row[6]);
				Packmans.add(new Packman(p, speed, radius));	
			}
			if(row[0].equals("F")) {
				Point3D p = new Point3D(Double.parseDouble(row[2]),Double.parseDouble(row[3]),Double.parseDouble(row[4]));
				p = map.GPS2Pixel(p);
				double Weight = Double.parseDouble(row[5]);
				Fruits.add(new Fruit(p, Weight));
			}
			if(row[0].equals("B")) {
				Point3D p1 = new Point3D(Double.parseDouble(row[2]),Double.parseDouble(row[3]),Double.parseDouble(row[4]));
				Point3D p2 = new Point3D(Double.parseDouble(row[5]),Double.parseDouble(row[6]),Double.parseDouble(row[7]));
				p1 = map.GPS2Pixel(p1);
				p2 = map.GPS2Pixel(p2);
				Boxs_arr.add(new Box(p1,p2));

			}
			if(row[0].equals("G")) {
				Point3D p = new Point3D(Double.parseDouble(row[2]),Double.parseDouble(row[3]),Double.parseDouble(row[4]));
				p = map.GPS2Pixel(p);

				double speed = Double.parseDouble(row[5]);
				double radius = Double.parseDouble(row[6]);
				Ghost_arr.add(new Ghost(p, speed, radius));	
			}
			if(row[0].equals("M")) {
				Point3D p = new Point3D(Double.parseDouble(row[2]),Double.parseDouble(row[3]),Double.parseDouble(row[4]));
				p = map.GPS2Pixel(p);
				double speed = Double.parseDouble(row[5]);
				double radius = Double.parseDouble(row[6]);
				Player_user = new Player(p, speed, radius);

			}

		}


	}
	public void save_to_kml() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
