package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class XmlEncoder {
	
	private XmlEncoder() {
		
	}
	
	public static void createXML(LayersGroup layersGroup, ArrayList<CustomShape> drawnShape, File file) throws FileNotFoundException, XMLStreamException {
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
		/*
		for(int layerIndex=0; layerIndex<length; layerIndex++) {
			int numberOfShape = layersGroup.getLayers().get(layerIndex).getPane().getChildren().size();
			for(int shapeIndex=0; shapeIndex<numberOfShape; shapeIndex++) {
				Shape sh = (Shape) layersGroup.getLayers().get(layerIndex).getPane().getChildren().get(shapeIndex);
				writer.writeStartElement("Shape");
				writer.writeAttribute("shapeType",((CustomShape) sh).getType());
				writer.writeAttribute("layer", layersGroup.getLayers().get(layerIndex).getId());
				writer.writeStartElement("xPos");
				writer.writeCharacters(String.valueOf(((CustomShape) sh).getXPos()));
				writer.writeEndElement();
				writer.writeStartElement("yPos");
				writer.writeCharacters(String.valueOf(((CustomShape) sh).getYPos()));
				writer.writeEndElement();
				writer.writeStartElement("width");
				writer.writeCharacters(String.valueOf(((CustomShape) sh).getWidth()));
				writer.writeEndElement();
				writer.writeStartElement("height");
				writer.writeCharacters(String.valueOf(((CustomShape) sh).getHeight()));
				writer.writeEndElement();
				writer.writeStartElement("radius");
				writer.writeCharacters(String.valueOf(((CustomShape) sh).getRadius()));
				writer.writeEndElement();
				writer.writeStartElement("length");
				writer.writeCharacters(String.valueOf(((CustomShape) sh).getLength()));
				writer.writeEndElement();
				writer.writeStartElement("rotation");
				writer.writeCharacters(String.valueOf(((CustomShape) sh).getRotation()));
				writer.writeEndElement();
				writer.writeStartElement("fill");
				writer.writeCharacters(sh.getAccessibleText());
				writer.writeEndElement();
				writer.writeStartElement("stroke");
				writer.writeCharacters(sh.getStroke().toString());
				writer.writeEndElement();
				writer.writeStartElement("strokeWidth");
				writer.writeCharacters(String.valueOf(sh.getStrokeWidth()));
				writer.writeEndElement();
				writer.writeEndElement();
			}	
		}*/
		writer.writeEndElement();
		writer.writeEndDocument();
		writer.close();
	}

}
