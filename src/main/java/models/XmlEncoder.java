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
import javafx.scene.paint.Color;


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
					
					rectangle.setAttribute(posX, String.valueOf(rectShape.getX()));
					rectangle.setAttribute(posY, String.valueOf(rectShape.getY()));
					rectangle.setAttribute(width, String.valueOf(rectShape.getWidth()));
					rectangle.setAttribute(height, String.valueOf(rectShape.getHeight()));
					rectangle.setAttribute(strokeWidth, String.valueOf(rectShape.getStrokeWidth()));
					rectangle.setAttribute(strokeColor, String.valueOf(rectShape.getStroke()));
					rectangle.setAttribute(fill, rectShape.getAccessibleText());
					
					root.appendChild(rectangle);

				} 
				else if (a.getShapeType() == ShapeType.CIRCLE) {

					CustomCircle circShape = (CustomCircle)(shapeList.get(i));

					Element circle = doc.createElement("circle");
					
					circle.setAttribute(posX, String.valueOf(circShape.getCenterX()));
					circle.setAttribute(posY, String.valueOf(circShape.getCenterY()));
					circle.setAttribute(radius, String.valueOf(circShape.getRadius()));
					circle.setAttribute(strokeWidth, String.valueOf(circShape.getStrokeWidth()));
					circle.setAttribute(strokeColor, String.valueOf(circShape.getStroke()));
					circle.setAttribute(fill, circShape.getAccessibleText());
					
					root.appendChild(circle);


				} else if (a.getShapeType() == ShapeType.LINE) {

					CustomLine lineShape = (CustomLine)(shapeList.get(i));
					

					Element line = doc.createElement("line");
					
					line.setAttribute(startPosX, String.valueOf(lineShape.getStartX()));
					line.setAttribute(startPosY, String.valueOf(lineShape.getStartY()));
					line.setAttribute(endPosX, String.valueOf(lineShape.getEndX()));
					line.setAttribute(endPosY, String.valueOf(lineShape.getEndY()));
					line.setAttribute(strokeWidth, String.valueOf(lineShape.getStrokeWidth()));
					line.setAttribute(strokeColor, String.valueOf(lineShape.getStroke()));
					
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
