package Lab3;

public class State extends Government implements Comparable<State>{


	public int compareTo(State other) {
		return govName.compareTo(other.govName);
	}

	OrderedLinkedList<City> cityList;
	
	public State(String name) {
		super(name);
		cityList = new OrderedLinkedList<City>();
		// TODO Auto-generated constructor stub
	}
	
	public OrderedLinkedList<City> getCityList(){
		return cityList;
	}

}
