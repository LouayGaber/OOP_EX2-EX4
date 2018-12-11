package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GIS.GIS_element_class;
import GIS.GIS_layer_class;
import GIS.GIS_project_class;


/**

 *
 */
public class Csv2kml {


/**
 * This class convert an a csv file to a kml file by saving the csv file that text  separted by comma "," 
 * to a Kml file 
 * 
 * @param file : CSV file 
 * @return list of string of Data for the given file  
 * @throws IOException
 * Helped source : https://stackoverflow.com/questions/37941909/read-and-write-csv-file-using-java
 */
	public static List<String[]> readFile(File file) throws IOException {


		List<String[]> result = new ArrayList<String[]>();

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			result.add(line.split(","));

		}
		br.close();
		fr.close();
		return result;
	}

/**
 * Get a list with the read data from a List file, Send to Gis
 * @param result List<String[]> of Data for file 
 * @return GIS_Layer_using with the csv data
 * @throws ParseException 
 */
	public static GIS_layer_class ReadCsv_Layer(List<String[]> result) throws ParseException
	{
		GIS_layer_class myLayer = new GIS_layer_class();
		// Add color Random
		String Color []= {"yellow","red","black","green","blue"};
		int indx = new Random().nextInt(Color.length);
		String random = (Color[indx]);

		for (int i = 2; i < result.size(); i++) {
			String[] s = result.get(i);
			GIS_element_class row  =new GIS_element_class(s[0],s[1],s[2],s[4],s[5],s[6],s[7],s[8],s[9],s[10]);
			
			row.getData().setUTC(s[3]);
			row.getData().setColor(random);
			myLayer.add(row);
		}
		//Add color to the  Layer
		myLayer.get_Meta_data().setColor(random);
		return myLayer;

	}
	/**
	 * The main function to convert a csv file to an kml file 
	 * @param Data :the list of the data 
	 * @param output : the file name include the path of the file to be exported in .
	 * @return A kml file in the given output path . like : C:\\Users\\Louay\\Desktop\\test.kml.
	 */
	public static void to_KML(List<String[]> Data, String output) {


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
		String[] nameData = Data.get(1);
		String end = "</Folder>\n" + 
				"</Document>\n</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 2; i < Data.size(); i++) {
				String[] s = Data.get(i);

				String Element ="<Placemark>\n" +
						"<name><![CDATA["+s[1]+"]]></name>\n" +
						"<description>"+
						"<![CDATA[B"
						+nameData[0]+": <b>"+s[0]+" </b><br/>"
						+nameData[2]+": <b>"+s[2]+" </b><br/>"
						+nameData[3]+": <b>"+s[3]+" </b><br/>" 
						+nameData[4]+": <b>"+s[4]+" </b><br/>"
						+nameData[5]+": <b>"+s[5]+" </b><br/>" 
						+nameData[6]+": <b>"+s[6]+" </b><br/>"
						+nameData[7]+": <b>"+s[7]+" </b><br/>"
						+nameData[8]+": <b>"+s[8]+" </b><br/>" 
						+nameData[9]+": <b>"+s[9]+" </b><br/>" 
						+nameData[10]+": <b>"+s[10]+" </b><br/>" 

						+"]]></description>\n" +
						"<Point>\n" +
						"<coordinates>"+s[7]+","+s[6]+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";


				content.add(Element);


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
