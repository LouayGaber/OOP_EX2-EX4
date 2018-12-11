package GIS;

import java.io.BufferedWriter;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * 
 * @author Louay
 *This class implement the GIS_Project
 *
 */

public class GIS_project_class implements GIS_project {

	private Set<GIS_layer> layers = new HashSet<GIS_layer>();
	private LayerMetaData LayerMetaData;
	
	public boolean add(GIS_layer e) {
		return layers.add(e);
	}

	public boolean addAll(Collection<? extends GIS_layer> c) {
		return layers.addAll(c);
	}

	public void clear() {
		layers.clear();
	}

	public boolean contains(Object o) {
		return layers.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return layers.containsAll(c);
	}

	public boolean equals(Object o) {
		return layers.equals(o);
	}

	public int hashCode() {
		return layers.hashCode();
	}

	public boolean isEmpty() {
		return layers.isEmpty();
	}

	public Iterator<GIS_layer> iterator() {
		return layers.iterator();
	}


	public boolean remove(Object o) {
		return layers.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return layers.removeAll(c);
	}


	public boolean retainAll(Collection<?> c) {
		return layers.retainAll(c);
	}

	public int size() {
		return layers.size();
	}

	public Object[] toArray() {
		return layers.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return layers.toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {
		return LayerMetaData;
	}
	/**
	 * This is the main function it can recive an a output , make the conversion from a csv file to kml file 
	 * This function goal is to be implemented by more than one csv file . 
	 * @param output of the builded final kml file "Project".
	 * I used and helped with the csv to kml function for a separted csv file . that can be found in Csv2kml class . 
	 */
	public void to_KML(String output) {
		String[]name= {"MAC", "SSID", "AuthMode", "Channel", "RSSI", "Lat", "Lon", "AltitudeMeters", "AccuracyMeters", "type","UTC","Orientation ","Color"};

		ArrayList<String> content = new ArrayList<String>();
		String start = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
						"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\">\r\n" + 
						"<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"<Folder><name>Wifi Networks</name>\n\n";
		content.add(start);
		String end = "</Folder>\n" + 
				"</Document>\n</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(GIS_layer current_layer: this){


				Iterator<GIS_element> myelm = current_layer.iterator();

				while(myelm.hasNext()) {

					String s = myelm.next().toString();
					
					String[] data=s.split(",");

					String kmlelement ="<Placemark>\n" +
							"<name><![CDATA["+data[1]+"]]></name>\n" +
							"<description>"+
							"<![CDATA[B"
							+name[0]+": <b>"+data[0]+" </b><br/>"
							+name[2]+": <b>"+data[2]+" </b><br/>"
							+name[3]+": <b>"+data[3]+" </b><br/>"
							+name[4]+": <b>"+data[4]+" </b><br/>" 
							+name[5]+": <b>"+data[5]+" </b><br/>" 
							+name[6]+": <b>"+data[6]+" </b><br/>" 
							+name[7]+": <b>"+data[7]+" </b><br/>" 
							+name[8]+": <b>"+data[8]+" </b><br/>"
							+name[9]+": <b>"+data[9]+" </b><br/>"
							+name[10]+": <b>"+data[10]+" </b><br/>" 
							+name[11]+": <b>"+data[11]+" </b><br/>" 
							+name[12]+": <b>"+data[12]+" </b><br/>" 



							+"]]></description>\n" +"<styleUrl>#"+data[12]+"</styleUrl>"+
							"<Point>\n" +
							"<coordinates>"+data[6]+","+data[5]+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";

					content.add(kmlelement);

				}
			}

			content.add(end);
			bw.write(String.join("\n", content));
			System.out.println("Operation Complete");
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	
	
	
	
	

}