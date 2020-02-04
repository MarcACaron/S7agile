package models;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.collections.ObservableList;
import javafx.scene.Node;


public class XmlEncoder extends XmlStrings {
	
	public static Boolean createXML(ObservableList<Node> shapeList, File file) {
		
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document doc = documentBuilder.newDocument();

			// root element
			Element root = doc.createElement("drawing");
			doc.appendChild(root);

			for (int i = 0; i < shapeList.size(); ++i) {

				Identifiable a = (Identifiable)(shapeList.get(i));

				if (a.getShapeType() == ShapeType.RECTANGLE) {
					
					CustomRectangle rectShape = (CustomRectangle)(shapeList.get(i));

					Element rectangle = doc.createElement("rectangle");
					
					rectangle.setAttribute(POSX, String.valueOf(rectShape.getX()));
					rectangle.setAttribute(POSY, String.valueOf(rectShape.getY()));
					rectangle.setAttribute(WIDTH, String.valueOf(rectShape.getWidth()));
					rectangle.setAttribute(HEIGHT, String.valueOf(rectShape.getHeight()));
					rectangle.setAttribute(STROKEWIDTH, String.valueOf(rectShape.getStrokeWidth()));
					rectangle.setAttribute(STROKECOLOR, String.valueOf(rectShape.getStroke()));
					rectangle.setAttribute(FILL, rectShape.getAccessibleText());
					
					root.appendChild(rectangle);

				} 
				else if (a.getShapeType() == ShapeType.CIRCLE) {

					CustomCircle circShape = (CustomCircle)(shapeList.get(i));

					Element circle = doc.createElement("circle");
					
					circle.setAttribute(POSX, String.valueOf(circShape.getCenterX()));
					circle.setAttribute(POSY, String.valueOf(circShape.getCenterY()));
					circle.setAttribute(RADIUS, String.valueOf(circShape.getRadius()));
					circle.setAttribute(STROKEWIDTH, String.valueOf(circShape.getStrokeWidth()));
					circle.setAttribute(STROKECOLOR, String.valueOf(circShape.getStroke()));
					circle.setAttribute(FILL, circShape.getAccessibleText());
					
					root.appendChild(circle);


				} else if (a.getShapeType() == ShapeType.LINE) {

					CustomLine lineShape = (CustomLine)(shapeList.get(i));
					

					Element line = doc.createElement("line");
					
					line.setAttribute(STARTPOSX, String.valueOf(lineShape.getStartX()));
					line.setAttribute(STARTPOSY, String.valueOf(lineShape.getStartY()));
					line.setAttribute(ENDPOSX, String.valueOf(lineShape.getEndX()));
					line.setAttribute(ENDPOSY, String.valueOf(lineShape.getEndY()));
					line.setAttribute(STROKEWIDTH, String.valueOf(lineShape.getStrokeWidth()));
					line.setAttribute(STROKECOLOR, String.valueOf(lineShape.getStroke()));
					
					root.appendChild(line);

				}
			}
			
			// create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
            
            return true;

		} catch (ParserConfigurationException pce) {

		} catch (TransformerException tfe) {
			
        }
		return null;
	}

}
