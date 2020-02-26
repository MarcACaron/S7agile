package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class XmlEncoder {
	
	private XmlEncoder() {
		
	}
	
	public static void createXML(LayersGroup layersGroup, File file) throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(file));
		writer.writeStartDocument();
		int length = DrawnShapes.getDrawnShapes().size();
		writer.writeStartElement("Save");
		for(int shapeIndex=0; shapeIndex<length; shapeIndex++) {
			writer.writeStartElement("Shape");
			writer.writeAttribute("shapeType", DrawnShapes.getDrawnShapes().get(shapeIndex).getType());
			writer.writeAttribute("layer", DrawnShapes.getDrawnShapes().get(shapeIndex).getLayer());
			DrawnShapes.getDrawnShapes().get(shapeIndex).write(writer);
			writer.writeEndElement();
		}
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.close();
	}

}
