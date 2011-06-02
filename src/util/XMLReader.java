package util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import obj.Edge;
import obj.Node;
import obj.Path;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * @author peedarpk
 * 
 */
public class XMLReader {

	File filename;
	Map nodesMap;

	public XMLReader(ArrayList<Node> nodesList, ArrayList<Path> pathList, ArrayList<Edge> edgesList, File fname) {
		this.filename = fname;
		nodesMap = new HashMap<String, Node>();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(filename);

			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			// read nodes
			NodeList nList = doc.getElementsByTagName("node");
			Node graphNode;
			for (int i = 0; i < nList.getLength(); i++) {

				org.w3c.dom.Node n = nList.item(i);

				if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

					String nodeName = n.getAttributes().getNamedItem("name").getTextContent();

					Element childElements = (Element) n;
					int xPoint = Integer.parseInt(childElements.getElementsByTagName("xCordinate").item(0)
							.getTextContent());
					int yPoint = Integer.parseInt(childElements.getElementsByTagName("yCordinate").item(0)
							.getTextContent());

					graphNode = new Node(xPoint, yPoint, nodeName);
					nodesMap.put(nodeName, graphNode);
					nodesList.add(graphNode);
				}
			}

			// read edges
			NodeList edges = doc.getElementsByTagName("edges");

			for (int y = 0; y < edges.getLength(); y++) {
				org.w3c.dom.Node e = edges.item(y);

				Element edgesElement = (Element) e;
				NodeList eList = edgesElement.getElementsByTagName("edge");
				Edge graphEdge;
				for (int i = 0; i < eList.getLength(); i++) {
					org.w3c.dom.Node n = eList.item(i);
					if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

						String edgeName = n.getAttributes().getNamedItem("name").getTextContent();
						String startNode = n.getAttributes().getNamedItem("startNode").getTextContent();
						String endNode = n.getAttributes().getNamedItem("endNode").getTextContent();

						graphEdge = new Edge(edgeName, (Node) nodesMap.get(startNode), (Node) nodesMap.get(endNode));
						edgesList.add(graphEdge);
					}
				}
			}

			// read paths

			NodeList paths = doc.getElementsByTagName("paths");
			for (int y = 0; y < paths.getLength(); y++) {
				org.w3c.dom.Node p = paths.item(y);
				Element pathsElement = (Element) p;
				NodeList pList = pathsElement.getElementsByTagName("Path");
				Path graphPath;
				for (int i = 0; i < pList.getLength(); i++) {

					org.w3c.dom.Node n = pList.item(i);
					String pathName = n.getAttributes().getNamedItem("name").getTextContent();
					Element pathElements = (Element) n;
					int maxSpeed = Integer.parseInt(pathElements.getElementsByTagName("maxSpeed").item(0)
							.getTextContent());
					int vehicleFreq = Integer.parseInt(pathElements.getElementsByTagName("vehicleFreq").item(0)
							.getTextContent());

					NodeList eList = pathElements.getElementsByTagName("edge");
					Edge graphEdge;

					ArrayList<Edge> localEdgeList = new ArrayList<Edge>();
					for (int x = 0; x < eList.getLength(); x++) {
						org.w3c.dom.Node eNode = eList.item(x);
						if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

							String edgeName = eNode.getAttributes().getNamedItem("name").getTextContent();
							String startNode = eNode.getAttributes().getNamedItem("startNode").getTextContent();
							String endNode = eNode.getAttributes().getNamedItem("endNode").getTextContent();

							graphEdge = new Edge(edgeName, (Node) nodesMap.get(startNode), (Node) nodesMap.get(endNode));
							localEdgeList.add(graphEdge);
						}
					}

					graphPath = new Path(pathName, maxSpeed, vehicleFreq, localEdgeList);
					pathList.add(graphPath);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
