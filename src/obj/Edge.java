package obj;

public class Edge {

	String name;
	Node startNode;
	Node endNode;

	public Edge(String n, Node n1, Node n2) {
		name = n;
		startNode = n1;
		endNode = n2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}
}
