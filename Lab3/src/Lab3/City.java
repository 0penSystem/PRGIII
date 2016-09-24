package Lab3;

/**
 * An object used to represent a city.
 * @author Evan Diver
 */


public class City extends Government implements Comparable<City>{
	// fields
	
	double latitude, longitude;
	int timezone;
	boolean yesDaylight;
	int zipcode;

	// constructor
	/**
	 * An object to represent a city.
	 * @param zipcode Integer zipcode of the city.
	 * @param cityName Name of the city.
	 * @param latitude Latitude with North being considered positive.
	 * @param longitude Longitude with East being considered positive.
	 * @param timezone Timezone as integer hours difference from GMT.
	 * @param yesDaylight True if the city follows daylight savings time.
	 */
	public City(int zipcode, String cityName, double latitude, double longitude, int timezone,
			boolean yesDaylight) {
		super(cityName);
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timezone = timezone;
		this.yesDaylight = yesDaylight;
		
		
	}

	// methods

	/**
	 * Compares the latitudes of two cities.
	 * @param otherCity City to compare to.
	 * @return 
	 */
	public int compareTo(City other) {
		return super.compareTo(other);
	}	
	
	public int getZip(){
		return zipcode;
	}
	
	
	public String getTimezone(){
		String temp = "";
		switch(timezone){
		case (-4): temp = "AST"; break;
		case (-5): temp = "EST"; break;
		case (-6): temp = "CST"; break;
		case (-7): temp = "MST"; break;
		case (-8): temp = "PST"; break;
		case (-9): temp = "AKST"; break;
		case (-10): temp = "HST"; break;
		default: temp = "???"; break;
		}
		if(yesDaylight){
			temp = temp.replace('S', 'D'); // i should really use regex but I'm lazy and this works for now
		}
		return temp;
		
	}
	

	
}