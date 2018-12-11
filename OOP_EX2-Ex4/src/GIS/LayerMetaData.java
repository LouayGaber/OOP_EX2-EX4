package GIS;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import Geom.Point3D;

/**
 * 
 * This class implements the Meta_Data functions 
 *
 */
public class LayerMetaData implements Meta_data {
	private long UTC;
	private Point3D Orientation;
	private String color;
	
	public LayerMetaData() {
		
		this.UTC = getUTC();
		this.Orientation = get_Orientation();
		this.color = getColor();
	}


	public void setUTC(String DateAndTime) throws ParseException
	{
	
		long millis  = DateToMilis(DateAndTime);
		UTC = millis;
	}
	/**
	 * 
	 * @param dateAndTime
	 * @return datee in milis 
	 * @throws ParseException
	 * helped source for this code : https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
	 */
	private long DateToMilis(String dateAndTime) throws ParseException
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date date = format.parse(dateAndTime);
		long millis = date.getTime();
		return millis;
	}
	
	
	@Override
	public long getUTC()
	{
		return UTC;
	}
	private String longToDate(long millis)
	{
		SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date(millis));
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String Color) {
		this.color = Color;
	}

	@Override
	public String toString() {
		return ""+longToDate(UTC)+ "," + Orientation + "," + color+"";
	}

	
}