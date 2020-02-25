package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class XmlEncoder {
	
	private XmlEncoder() {
		
	}
	
	public static void createXML(LayersGroup layersGroup, List<CustomShape> drawnShape, File file) throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(file));
		writer.writeStartDocument();
		int length = drawnShape.size();
		writer.writeStartElement("Save");
		for(int shapeIndex=0; shapeIndex<length; shapeIndex++) {
			writer.writeStartElement("Shape");
			writer.writeAttribute("shapeType", drawnShape.get(shapeIndex).getType());
			writer.writeAttribute("layer", drawnShape.get(shapeIndex).getLayer());
			drawnShape.get(shapeIndex).write(writer);
			writer.writeEndElement();
		}
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.close();
	}

}
