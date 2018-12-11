package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class GIS_element_class implements GIS_element {
	
	Point3D Current_Point;
	LayerMetaData MetaData;
	private String MAC;
	private String SSID;
	private String AuthMode;
	private double Lat;
	private double Lon;
	private double AltitudeMeters;
	private int Channel;
	private int RSSI; 
	private int AccuracyMeters;
	private String type;



	/**
	 * This is the main function (Constructor ) That recive a line of data (row ) 
	 * This row in the csv file  there colums separted by comma (,) this is the Format of the csv 
	 * 
	 * 
	 * @param MAC
	 * @param SSID
	 * @param AuthMode
	 * @param FirstSeen
	 * @param Channel
	 * @param RSSI
	 * @param Lat
	 * @param Lon
	 * @param AltitudeMeters
	 * @param AccuracyMeters
	 * @param type
	 */
	public GIS_element_class(String MAC , String SSID,String AuthMode,String Channel,
			String RSSI,String Lat,String Lon , String  AltitudeMeters,String AccuracyMeters, String type) {
		
		setMAC(MAC); 
		setSSID(SSID); 
		setAuthMode(AuthMode); 
		setType(type);; 
		setPoint(Lat,Lon,AltitudeMeters);
		setChannel(Channel);
		setRSSI(RSSI); 
		setAccuracyMeters(AccuracyMeters);		
		MetaData=new LayerMetaData();
	}

	

	public String getMAC() {
		return MAC;
	}

	public String getSSID() {
		return SSID;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public double getLat() {
		return Lat;
	}

	public double getLon() {
		return Lon;
	}

	public double getAltitudeMeters() {
		return AltitudeMeters;
	}

	public int getChannel() {
		return Channel;
	}

	public int getRSSI() {
		return RSSI;
	}

	public double getAccuracyMeters() {
		return AccuracyMeters;
	}

	public String getType() {
		return type;
	}
	
	public void setChannel(String Channel) {
		int theChannel = Integer.parseInt(Channel);
		this.Channel = theChannel;
	}
	public void setRSSI(String RSSI) {
		int theRSSI = Integer.parseInt(RSSI);
		this.RSSI = theRSSI;
	}
	public void setAccuracyMeters(String AccuracyMeters) {
		
		double theAccuracyMeters = Double.parseDouble(AccuracyMeters);
		this.AccuracyMeters =(int) theAccuracyMeters;
	}
	
	public void setPoint(String lat, String lon , String AltitudeMeters) {
		Double x = Double.parseDouble(lat);
		Double y = Double.parseDouble(lon);
		Double z = Double.parseDouble(AltitudeMeters);
	Current_Point = new Point3D(x,y,z);
	}	
	
	public void setType(String type) {
		this.type = type;
	}

	public void setAuthMode(String AuthMode) {
		this.AuthMode = AuthMode;
	}
	public void setSSID(String name) {
		this.SSID = name;
	}
	public void setMAC(String MAC) {
		this.MAC = MAC;
	}


	


	@Override
	public Geom_element getGeom() {
		return Current_Point;
	}

	@Override
	public Meta_data getData() {
		return this.MetaData;
	}

	@Override
	public void translate(Point3D vec) {
		MyCoords m = new MyCoords();


		Point3D ans = m.add(this.Current_Point, vec);
		Current_Point = new Point3D(ans.x(),ans.y(),ans.z());

	}



	/**
	 * String that include all the information
	 * 
	 */
	public String toString() {

		return ""+MAC +"," +SSID+","+AuthMode+","+Channel+","+RSSI+","+Current_Point.toString()+","+AccuracyMeters+","+type+","+getData().toString();
	}
}