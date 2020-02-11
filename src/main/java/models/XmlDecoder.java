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
import adraw4us.shapeFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class XmlDecoder extends XmlStrings {
	
	private static LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	private static PatternApplier patternApplier = new PatternApplier();
	
	public static void readXML(File file, MainApp mainApp) throws FileNotFoundException, XMLStreamException {
		layersGroup.clear();
		XMLInputFactory xif = XMLInputFactory.newInstance();
		xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
		XMLEventReader  reader = xif.createXMLEventReader(new FileInputStream(file));
	    XMLEvent event;
		ArrayList<String> layerNames = new ArrayList<String>();
	    while (reader.hasNext()) {
	    	Shape sh = null;
			event = reader.nextEvent();
			if (event.isStartElement()) {
				StartElement se = event.asStartElement();
				if(se.getName().getLocalPart().equals("Shape")) {
					sh = shapeFactory.build(se.getAttributeByName(new QName("shapeType")).getValue());
					if(sh != null) {
						sh.setAccessibleText(se.getAttributeByName(new QName("shapeType")).getValue());
						String name = se.getAttributeByName(new QName("layer")).getValue();
						if(!layerNames.contains(name)) {
							Layer a = new GridLayer(name);
							System.out.println(layersGroup.getLayers().size() + "allo");
							layerNames.add(name);
							layersGroup.createNewLayer(a);
						}
						layersGroup.getLayers().get(layerNames.indexOf(name)).getPane().getChildren().add(sh);
						event = reader.nextEvent();
						event = reader.nextEvent();
						((Transformable)sh).setXPosTool(Double.valueOf(event.asCharacters().getData()));
						event = reader.nextEvent();
						event = reader.nextEvent();
						event = reader.nextEvent();
						((Transformable)sh).setYPosTool(Double.valueOf(event.asCharacters().getData()));
						event = reader.nextEvent();
						event = reader.nextEvent();
						event = reader.nextEvent();
						((Transformable)sh).setWidthTool(Double.valueOf(event.asCharacters().getData()));
						event = reader.nextEvent();
						event = reader.nextEvent();
						event = reader.nextEvent();
						((Transformable)sh).setHeightTool(Double.valueOf(event.asCharacters().getData()));
						event = reader.nextEvent();
						event = reader.nextEvent();
						event = reader.nextEvent();
						((Transformable)sh).setRadiusTool(Double.valueOf(event.asCharacters().getData()));
						event = reader.nextEvent();
						event = reader.nextEvent();
						event = reader.nextEvent();
						((Transformable)sh).setLengthTool(Double.valueOf(event.asCharacters().getData()));
						event = reader.nextEvent();
						event = reader.nextEvent();
						event = reader.nextEvent();
						((Transformable)sh).setRotationTool(Double.valueOf(event.asCharacters().getData()));
						event = reader.nextEvent();
						event = reader.nextEvent();
						event = reader.nextEvent();
						patternApplier.fillShape(sh, event.asCharacters().getData());
						event = reader.nextEvent();
						event = reader.nextEvent();
						event = reader.nextEvent();
						sh.setStroke(Color.valueOf(event.asCharacters().getData()));
						event = reader.nextEvent();
						event = reader.nextEvent();
						event = reader.nextEvent();
						sh.setStrokeWidth(Double.valueOf(event.asCharacters().getData()));
						Shape sh2 = sh;
						sh.setOnMouseClicked(t2 -> {
							mainApp.getTool().setShape(sh2);
							mainApp.getPaletteCouleurController().setLineWidth(sh2.getStrokeWidth());
							mainApp.getPaletteCouleurController().setStroke((Color) (sh2.getStroke()));
							
							mainApp.getTool().fillDetails(mainApp.getPaletteDetailController(), sh2).apply(null);
						});
					}				
				}
			} else if (event.isCharacters()) {
			} else if (event.isEndElement()) {
			}
		}
	    System.out.println(layersGroup.size() + " Size in encoder");
	    mainApp.getDrawingZoneController().updateLayers();
	}

}
