package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class XmlEncoder {
	
	private XmlEncoder() {
		
	}
	
	public static void createXML(File file) throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(file));
		writer.writeStartDocument();
		int length = LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().size();
		writer.writeStartElement("Save");
		for(int shapeIndex=0; shapeIndex<length; shapeIndex++) {
			writer.writeStartElement("Shape");
			writer.writeAttribute("shapeType", LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().get(shapeIndex).getType());
			writer.writeAttribute("layer", LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().get(shapeIndex).getLayer());
			writer.writeAttribute("shapeConstructor", LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().get(shapeIndex).getContructorName());
			System.out.println(LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().get(shapeIndex).getContructorName());
			LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().get(shapeIndex).write(writer);
			writer.writeEndElement();
		}
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.close();
	}
	
	public static void customShapeEncoder(CustomShape sh, String fileName) throws FileNotFoundException, XMLStreamException {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		File file = new File(fileName);
		System.out.println(file.getAbsolutePath());
		XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(file));
		writer.writeStartDocument();
		writer.writeStartElement("Save");
		
		writer.writeStartElement("Shape");
		writer.writeAttribute("shapeType", sh.getType());
		writer.writeAttribute("shapeConstructor", sh.getContructorName());
		sh.write(writer);
		
		writer.writeEndElement();
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.close();
	}

}
