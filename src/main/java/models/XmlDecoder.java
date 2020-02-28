package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import adraw4us.MainApp;
import adraw4us.ShapeFactory;

public class XmlDecoder {
	
	private XmlDecoder() {
		
	}
	
	private static LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	//private static PatternApplier patternApplier = new PatternApplier();
	
	public static void readXML(File file, MainApp mainApp) throws FileNotFoundException, XMLStreamException {
		layersGroup.clear();
		XMLInputFactory xif = XMLInputFactory.newInstance();
		xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
		XMLEventReader  reader = xif.createXMLEventReader(new FileInputStream(file));
	    XMLEvent event;
		ArrayList<String> layerNames = new ArrayList<>();
		ArrayList<CustomShape> drawnShape = new ArrayList<>();
	    while (reader.hasNext()) {
	    	CustomShape sh = null;
			event = reader.nextEvent();
			if (!event.isStartElement()) {
				continue;
			}
			StartElement se = event.asStartElement();
			if(!se.getName().getLocalPart().equals("Shape")) {
				continue;
			}
			sh = ShapeFactory.build(se.getAttributeByName(new QName("shapeType")).getValue());
			if(sh != null) {
				sh.getDraw().setAccessibleText(se.getAttributeByName(new QName("shapeType")).getValue());
				String name = se.getAttributeByName(new QName("layer")).getValue();
				if(!layerNames.contains(name)) {
					Layer a = new GridLayer(name);
					layerNames.add(name);
					layersGroup.createNewLayer(a);
				}
				layersGroup.getLayers().get(layerNames.indexOf(name)).getPane().getChildren().add(sh.getDraw());
				LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().add(sh);
				reader.nextEvent();
				drawnShape.add(sh);
				sh.read(reader);
				CustomShape sh2 = sh;
				sh.setOnMouseClicked(sh, mainApp);
			}				
		}
	    mainApp.getDrawingZoneController().updateLayers(true);
	}

}
