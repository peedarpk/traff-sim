package obj;

import java.util.ArrayList;

public class Path {
	String name;
	int maxSpeed;
	int vehiclePerMin;
	ArrayList<Edge> edgesList = new ArrayList<Edge>();

	public Path(String n, int s, int v, ArrayList<Edge> l) {
		name = n;
		maxSpeed = s;
		vehiclePerMin = v;
		edgesList = l;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getVehiclePerMin() {
		return vehiclePerMin;
	}

	public void setVehiclePerMin(int vehiclePerMin) {
		this.vehiclePerMin = vehiclePerMin;
	}

	public ArrayList<Edge> getEdgesList() {
		return edgesList;
	}

	public void setEdgesList(ArrayList<Edge> edgesList) {
		this.edgesList = edgesList;
	}
}
