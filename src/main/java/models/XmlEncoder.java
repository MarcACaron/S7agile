package models;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
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
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		String FEATURE = null; //OWASP
		try {
			// This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all 
		    // XML entity attacks are prevented
		    // Xerces 2 only - http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl
		    FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
		    dbf.setFeature(FEATURE, true);

		    // If you can't completely disable DTDs, then at least do the following:
		    // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
		    // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities
		    // JDK7+ - http://xml.org/sax/features/external-general-entities
		    FEATURE = "http://xml.org/sax/features/external-general-entities";
		    dbf.setFeature(FEATURE, false);

		    // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
		    // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
		    // JDK7+ - http://xml.org/sax/features/external-parameter-entities
		    FEATURE = "http://xml.org/sax/features/external-parameter-entities";
		    dbf.setFeature(FEATURE, false);

		    // Disable external DTDs as well
		    FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
		    dbf.setFeature(FEATURE, false);

		    // and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks"
		    dbf.setXIncludeAware(false);
		    dbf.setExpandEntityReferences(false);
			///
			
			
			
			
			
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

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
