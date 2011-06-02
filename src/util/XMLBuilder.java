package util;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import obj.Edge;
import obj.Node;
import obj.Path;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XMLBuilder {

	ArrayList<Node> nodesList = new ArrayList<Node>();
	ArrayList<Path> pathList = new ArrayList<Path>();
	File filename;

	public XMLBuilder(ArrayList<Node> nodesList, ArrayList<Path> pathList, ArrayList<Edge> edgesList, File fName) {
		
		filename = fName;
		try {

			// Creating an empty XML Document

			// We need a Document
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			// Creating the XML tree

			// create the root element and add it to the document
			Element root = doc.createElement("roadMap");
			doc.appendChild(root);

			// set nodes
			Element nodesListElement = doc.createElement("nodesList");
			root.appendChild(nodesListElement);

			for (Node n : nodesList) {
				Element nodeElement = doc.createElement("node");
				nodeElement.setAttribute("name", n.getName());

				Element xCordinate = doc.createElement("xCordinate");
				nodeElement.appendChild(xCordinate);
				Text xValue = doc.createTextNode(new Integer(n.getxPoint()).toString());
				xCordinate.appendChild(xValue);
				Element yCordinate = doc.createElement("yCordinate");
				nodeElement.appendChild(yCordinate);
				Text yValue = doc.createTextNode(new Integer(n.getyPoint()).toString());
				yCordinate.appendChild(yValue);
				nodesListElement.appendChild(nodeElement);

			}

			//set edges
			Element edgesElement = doc.createElement("edges");
			root.appendChild(edgesElement);
			
			for(Edge e : edgesList){
				Element eElement = doc.createElement("edge");
				
				eElement.setAttribute("name", e.getName());
				eElement.setAttribute("startNode", e.getStartNode().getName());
				eElement.setAttribute("endNode", e.getEndNode().getName());
				
				edgesElement.appendChild(eElement);	
			}
			
			//set paths
			Element paths = doc.createElement("paths");
			root.appendChild(paths);
			
			for (Path p : pathList) {
				Element pathElement = doc.createElement("Path");
				pathElement.setAttribute("name", p.getName());
				Element maxSpeedElement = doc.createElement("maxSpeed");
				
				Text maxSpeedValue = doc.createTextNode(new Integer(p.getMaxSpeed()).toString());
				maxSpeedElement.appendChild(maxSpeedValue);
				
				Element vehicleFreqElement = doc.createElement("vehicleFreq");
				
				Text vehicleFreqValue = doc.createTextNode(new Integer(p.getVehiclePerMin()).toString());
				vehicleFreqElement.appendChild(vehicleFreqValue);
				
				Element edgeListElement = doc.createElement("edgeList");
				
				for(Edge e : p.getEdgesList()){
					Element edgeElement = doc.createElement("edge");
					
					edgeElement.setAttribute("name", e.getName());
					edgeElement.setAttribute("order", p.getName());
					edgeElement.setAttribute("startNode", e.getStartNode().getName());
					edgeElement.setAttribute("endNode", e.getEndNode().getName());
					
					edgeListElement.appendChild(edgeElement);	
				}
				
				pathElement.appendChild(maxSpeedElement);
				pathElement.appendChild(vehicleFreqElement);
				pathElement.appendChild(edgeListElement);
				paths.appendChild(pathElement);
			}

			// ///////////////
			// Output the XML

			// set up a transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			String xmlString = sw.toString();

			// print xml
			System.out.println("Here's the xml:\n\n" + xmlString);
			
			writeXmlFile(doc, filename);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void writeXmlFile(Document doc, File filename) {
	    try {
	        // Prepare the DOM document for writing
	        Source source = new DOMSource(doc);

	        // Prepare the output file
	        Result result = new StreamResult(filename);

	        // Write the DOM document to the file
	        Transformer xformer = TransformerFactory.newInstance().newTransformer();
	        xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	        xformer.transform(source, result);
	    } catch (TransformerConfigurationException e) {
	    } catch (TransformerException e) {
	    }
	}

}
