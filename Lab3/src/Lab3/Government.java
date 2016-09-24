package Lab3;

public class Government {

	String govName;

	public Government(String name) {
		govName = name;
	}

	
	public int compareTo(Government other) {
		return govName.compareTo(other.govName);
	}

	@Override
	public String toString() {
		return govName;
	}
}
