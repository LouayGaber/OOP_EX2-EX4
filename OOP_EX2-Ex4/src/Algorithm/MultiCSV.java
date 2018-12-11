package Algorithm;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import File_format.Csv2kml;
import GIS.GIS_layer_class;
import GIS.GIS_project_class;

/**
 * 
 * This class can scan for a Csv file in a given directory (Directory function) , then the user choose if he want to Complete convert 
 * all the csv files to a  one kml or every csv to kml . 
 * 
 *
 */
public class MultiCSV {
	
	
	/**
	 * 
	 * This function search and scan for a csv files by given an path like : User\\\\git\\OOP_EX2-EX4\\data
	 * This is the source for the scanning code : 
	 * https://stackoverflow.com/questions/2102952/listing-files-in-a-directory-matching-a-pattern-in-java
	 * @return ArrayList that contain a string of the founded csv files .
	 */
	public  ArrayList<String> Directory(String path) {
		ArrayList<String> dirfiles=new ArrayList<String>();
		File [] files;
		File dir = new File(path);
		files = dir.listFiles(new FilenameFilter()
		{
		    @Override
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".csv");
		    }
		});

		for (File csvfile : files) {
		    dirfiles.add(csvfile.getAbsolutePath());
		}
		return dirfiles;
	
	}
	
		/**
	 * This function can create an one kml file by given more than one csv file 
	 * @param dirfiles : ArrayList of string that contain path of founded csv file by using DirectoryFunction
	 * @param path : where the kml file will be built . (String path  like : User\\\\git\\OOP_EX2-EX4\\data).
	 * @throws ParseException 
	 */
	public static void multicsvtoKml(ArrayList<String> dirfiles,String path)throws IOException, ParseException
	{
		
		GIS_project_class multicsvtokml = new GIS_project_class();
		for (int i = 0; i < dirfiles.size(); i++) {

			File file=new File(dirfiles.get(i));
			GIS_layer_class Layer= Csv2kml.ReadCsv_Layer(Csv2kml.readFile(file));
			multicsvtokml.add(Layer);
			
		}
		multicsvtokml.to_KML(path+"/AllinOnekml.kml");
		
	}
	/**
	 * This function can create one kml file to one csv file , by given an arraylist of a csv founded file in a directory 
	 * @param dirfiles : ArrayList of csv founded files in a directory by using the function Directory
	 * 
	 */
	public static void oneCsvoneKml(ArrayList<String> dirfiles) throws IOException
	{

		for (int i = 0; i < dirfiles.size(); i++) {

			File file=new File(dirfiles.get(i));
			Csv2kml.to_KML(Csv2kml.readFile(file.getAbsoluteFile()),dirfiles.get(i).substring(0,dirfiles.get(i).length()-4)+".kml"); 
			System.out.println("The csv file "+dirfiles.get(i)+" converted Successfully to KML");
		}
		
		System.out.println("Command completed :  The kml file can be found in the same path the user give ");
	}

}