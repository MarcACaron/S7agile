package models;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class XmlDecoder extends XmlStrings {
	
	private static PatternApplier patternApplier = new PatternApplier();
	
	public static ObservableList<Node> readXML(File file, Pane pane) {
		
		
		try {

			 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		     DocumentBuilder builder = factory.newDocumentBuilder();
		       
		     Document doc = builder.parse(file);
		     
		     doc.getDocumentElement().normalize();
		     Element root = doc.getDocumentElement();
		     
		     NodeList a = root.getChildNodes();
		     
		     for (int i = 0; i<a.getLength(); ++i) {
		    	 
		    	 if (a.item(i).getNodeName().equals("rectangle")) {
		    		 
		    		 //// RECTANGLE
		    		 
		    		 CustomRectangle rectangle = new CustomRectangle();
		    		 
		    		 NamedNodeMap nodeMap = a.item(i).getAttributes();

		    		 for (int j = 0; j < nodeMap.getLength(); ++j) {
		    			 
		    			 if (nodeMap.item(j).getNodeName() == FILL) {
		    				 
		    				 rectangle.setAccessibleText(nodeMap.item(j).getNodeValue());
		    				 rectangle = (CustomRectangle)(patternApplier.setFill(rectangle));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == HEIGHT) {
		    				 
		    				 rectangle.setHeight(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == WIDTH) {
		    				 
		    				 rectangle.setWidth(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == POSX) {
		    				 
		    				 rectangle.setX(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == POSY) {
		    				 
		    				 rectangle.setY(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == STROKECOLOR) {
		    				 
		    				 rectangle.setStroke(Color.web(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == STROKEWIDTH) {
		    				 
		    				 rectangle.setStrokeWidth(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 }
	
		    		 }
		    		 
		    		 pane.getChildren().add(rectangle);
		    	 } // end rectangle
		    	 
		    	 else if (a.item(i).getNodeName().equals("circle")) {
		    		 
		    		 //// CIRCLE
		    		 
		    		 CustomCircle circle = new CustomCircle();
		    		 
		    		 NamedNodeMap nodeMap = a.item(i).getAttributes();

		    		 for (int j = 0; j < nodeMap.getLength(); ++j) {
		    			 
		    			 if (nodeMap.item(j).getNodeName() == FILL) {
		    				 
		    				 circle.setAccessibleText(nodeMap.item(j).getNodeValue());
		    				 circle = (CustomCircle)(patternApplier.setFill(circle));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == RADIUS) {
		    				 
		    				 circle.setRadius(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == POSX) {
		    				 
		    				 circle.setCenterX(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == POSY) {
		    				 
		    				 circle.setCenterY(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == STROKECOLOR) {
		    				 
		    				 circle.setStroke(Color.web(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == STROKEWIDTH) {
		    				 
		    				 circle.setStrokeWidth(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 }
	
		    		 }
		    		 
		    		 pane.getChildren().add(circle);
		    	 } // end circle
		    	 
		    	 else if (a.item(i).getNodeName().equals("line")) {
		    		 
		    		 //// LINE
		    		 
		    		 CustomLine line = new CustomLine();
		    		 
		    		 NamedNodeMap nodeMap = a.item(i).getAttributes();

		    		 for (int j = 0; j < nodeMap.getLength(); ++j) {
		    			 
		    			 if (nodeMap.item(j).getNodeName() == STARTPOSX) {
		    				 
		    				 line.setStartX(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == STARTPOSY) {
		    				 
		    				 line.setStartY(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == ENDPOSX) {
		    				 
		    				 line.setEndX(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == ENDPOSY) {
		    				 
		    				 line.setEndY(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == STROKECOLOR) {
		    				 
		    				 line.setStroke(Color.web(nodeMap.item(j).getNodeValue()));
		    				 
		    			 } else if (nodeMap.item(j).getNodeName() == STROKEWIDTH) {
		    				 
		    				 line.setStrokeWidth(Double.valueOf(nodeMap.item(j).getNodeValue()));
		    				 
		    			 }
	
		    		 }
		    		 
		    		 pane.getChildren().add(line);
		    	 } // end LINE
		    		 
		     }

		} catch (ParserConfigurationException pce) {

		} catch (SAXException e) {

		} catch (IOException e) {

		}
		return null;
		
	}

}
