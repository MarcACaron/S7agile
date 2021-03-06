package models;

import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import adraw4us.MainApp;
import adraw4us.ShapeFactory;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CustomUnionShape extends CustomShape {
	ArrayList<CustomShape> listOfShape;
	private boolean grouped = false;
	public CustomUnionShape(ArrayList<CustomShape> listOfShape) {
		this.boundingBox = new Rectangle();
		this.listOfShape = listOfShape;
		scale = false;
		this.listOfShape.forEach(customShape->{
			if(customShape.isScale()) {
				this.scale=true;
			}
		});
		this.type = "CustomUnionShape";
	}

	public CustomUnionShape() {
		this.boundingBox = new Rectangle();
		this.listOfShape = new ArrayList<>();
		this.listOfShape.forEach(customShape->{
			if(customShape.isScale()) {
				this.scale=true;
			}
		});
		this.type = "CustomUnionShape";
	}

	@Override
	public void setXPos(double value) {
		clearTransfrom();

		double deplacement= value-this.boundingBox.getX();
		listOfShape.forEach(sh -> {
			sh.setXPos(deplacement+sh.getXPos());
		});
		this.boundingBox.setX(value);

		
		listOfShape.forEach(sh->{
			sh.loadTransform();
		});


	}

	@Override
	public void setYPos(double value) {
		clearTransfrom();
		
		double deplacement= value-this.boundingBox.getY();

		listOfShape.forEach(sh -> {
			sh.setYPos(deplacement+sh.getYPos());
		});
		this.boundingBox.setY(value);
		
		listOfShape.forEach(sh->{
			sh.loadTransform();
		});
	}

	@Override
	public void setWidth(double value) {
		if(value<0.5)
			value=0.5;
		double rapportWidth = value/this.boundingBox.getWidth();
		for(int i=0; i<listOfShape.size(); i++) {
			double rapportDeplacement = (listOfShape.get(i).getXPos()-this.boundingBox.getX())/this.boundingBox.getWidth();
			listOfShape.get(i).setXPos(this.boundingBox.getX() + rapportDeplacement*value);
			listOfShape.get(i).setWidth(rapportWidth*listOfShape.get(i).getWidth());
		}/*
		listOfShape.forEach(sh -> {
			double rapportDeplacement = (sh.getXPos()-this.boundingBox.getX())/this.boundingBox.getWidth();
			sh.setXPos(this.boundingBox.getX() + rapportDeplacement*value);
			sh.setWidth(rapportWidth*sh.getWidth());

		});*/
		if(scale) {
			setHeight(value * getHeight()/getWidth(), true);
		}
		this.boundingBox.setWidth(value);
	}

	private void setWidth(double value, boolean stop) {
		double rapportWidth = value/this.boundingBox.getWidth();
		listOfShape.forEach(sh -> {
			double rapportDeplacement = (sh.getXPos()-this.boundingBox.getX())/this.boundingBox.getWidth();
			if(!sh.isScale()){
				sh.setWidth(rapportWidth*sh.getWidth());
			}
			sh.setXPos(this.boundingBox.getX() + rapportDeplacement*value);
		});
		this.boundingBox.setWidth(value);
	}

	@Override
	public void setHeight(double value) {
		if(value<0.5)
			value=0.5;
		double rapportWidth= value/this.boundingBox.getHeight();
		for(int i=0; i<listOfShape.size(); i++) {
			double rapportDeplacement = (listOfShape.get(i).getYPos()-this.boundingBox.getY())/this.boundingBox.getHeight();
			listOfShape.get(i).setYPos(this.boundingBox.getY() + rapportDeplacement*value);
			listOfShape.get(i).setHeight(rapportWidth*listOfShape.get(i).getHeight());
		}/*
		listOfShape.forEach(sh -> {
			double rapportDeplacement = (sh.getYPos()-this.boundingBox.getY())/this.boundingBox.getHeight();
			sh.setYPos(this.boundingBox.getY() + rapportDeplacement*value);
			sh.setHeight(rapportWidth*sh.getHeight());
		});*/
		if(scale) {
			setWidth(value * getWidth()/getHeight(), true);
		}
		this.boundingBox.setHeight(value);
	}

	public void setHeight(double value, boolean stop) {
		double rapportWidth= value/this.boundingBox.getHeight();
		listOfShape.forEach(sh -> {
			double rapportDeplacement = (sh.getYPos()-this.boundingBox.getY())/this.boundingBox.getHeight();
			if(!sh.isScale()){
				sh.setHeight(rapportWidth*sh.getHeight());
			}
			sh.setYPos(this.boundingBox.getY() + rapportDeplacement*value);
		});
		this.boundingBox.setHeight(value);
	}

	@Override
	public void setFill(Paint value, String fillName) {
		listOfShape.forEach(sh -> 
		sh.setFill(value, fillName)
				);
	}

	@Override
	public void setRotate(double value) {
		listOfShape.forEach(sh -> {
			double diff =  value- this.getRotate();
			double xCenter = this.getXPos()+this.getWidth()/2;
			double yCenter = this.getYPos()+this.getHeight()/2;
			double x = sh.getXPos() + sh.getWidth()/2 - xCenter;
			double y = sh.getYPos() + sh.getHeight()/2 - yCenter;
			double xTrans = x*Math.cos(Math.toRadians(diff)) + y*Math.sin(Math.toRadians(diff)) + xCenter;
			double yTrans = -x*Math.sin(Math.toRadians(diff)) + y*Math.cos(Math.toRadians(diff)) + yCenter;
			sh.setXPos(xTrans-sh.getWidth()/2);
			sh.setYPos(yTrans-sh.getHeight()/2);
			sh.setRotate(sh.getRotate()+diff);

		}
				);
		this.boundingBox.setRotate(value);
	}

	@Override
	public void setStroke(Paint value) {
		listOfShape.forEach(sh -> 
		sh.setStroke(value)
				);
	}

	@Override
	public void setStrokeWidth(double strokeWidth) {
		listOfShape.forEach(sh -> 
		sh.setStrokeWidth(strokeWidth)
				);
	} 

	@Override
	public CustomShape duplicate(int offsetX, int offsetY, MainApp mainApp) {
		CustomUnionShape newUnion = new CustomUnionShape();
		for(int i=0; i<listOfShape.size(); i++) {
			CustomShape sh = listOfShape.get(i).duplicate(offsetX, offsetY, mainApp);
			sh.setOnMouseClicked(newUnion, mainApp);
			newUnion.add(sh);
		}
		newUnion.type=this.type;
		newUnion.updateBoudingBox();
		return newUnion;
	}

	public void add(CustomShape customShape) {
		this.listOfShape.add(customShape);
		if(customShape.isScale()) {
			scale=true;
		}
	}

	public boolean updateBoudingBox() {
		double xMin = 0;
		double yMin = 0;
		double xMax = 0;
		double yMax = 0;
		boolean ok = !listOfShape.isEmpty();
		if(ok) {
			xMin = this.listOfShape.get(0).getXPos();
			yMin = this.listOfShape.get(0).getYPos();
			xMax = this.listOfShape.get(0).getXPos()+this.listOfShape.get(0).getWidth();
			yMax = this.listOfShape.get(0).getYPos()+this.listOfShape.get(0).getHeight();
		}
		for(int i=0; i<listOfShape.size();i++) {
			xMin=Math.min(xMin, this.listOfShape.get(i).getXPos());	
			yMin=Math.min(yMin, this.listOfShape.get(i).getYPos());	
			xMax=Math.max(xMax, this.listOfShape.get(i).getXPos()+this.listOfShape.get(i).getWidth());	
			yMax=Math.max(yMax, this.listOfShape.get(i).getYPos()+this.listOfShape.get(i).getHeight());	
		}
		this.boundingBox.setX(xMin);
		this.boundingBox.setY(yMin);
		this.boundingBox.setWidth(xMax-xMin);
		this.boundingBox.setHeight(yMax-yMin);
		return ok;
	}

	public void group(String name, MainApp mainApp) {
		grouped=true;
		listOfShape.forEach(sh->{
			int index = LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().indexOf(sh);
			LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().remove(index);
			sh.remove(LayersGroup.getLayersGroup().getCurrentLayer().getPane());
			sh.draw(LayersGroup.getLayersGroup().getCurrentLayer().getPane());
			CustomShape thisGroup = this;
			sh.setOnMouseClicked(thisGroup, mainApp);


		});
		LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().add(this);
		setLayer(LayersGroup.getLayersGroup().getCurrentLayer().getId());
	}
	@Override
	public void remove(Pane p) {
		listOfShape.forEach(sh->{
			sh.remove(p);
		});
	}

	@Override
	public void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		if(!scale) {
			ajustOnDragFromCornerNotScalable(posXStart, posYStart, posXEnd, posYEnd);
		}else
			ajustOnDragFromCornerScalable(posXStart, posYStart, posXEnd, posYEnd);

	}
	private void ajustOnDragFromCornerNotScalable(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double startX;
		double startY;
		double width;
		double height;
		if(posXStart<posXEnd) {
			startX=posXStart;
			width = posXEnd-posXStart;
		}else {
			startX=posXEnd;
			width = posXStart-posXEnd;
		}
		if(posYStart<posYEnd) {
			startY=posYStart;
			height = posYEnd-posYStart;
		}else {
			startY=posYEnd;
			height = posYStart-posYEnd;
		}
		this.setWidth(width);
		this.setHeight(height);
		this.setXPos(startX);
		this.setYPos(startY);

	}
	private void ajustOnDragFromCornerScalable(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double width = Math.abs(posXStart-posXEnd);
		double height = Math.abs(posYStart-posYEnd);
		double startX=posXStart;
		double startY=posYStart;
		double rapport =this.getWidth()/this.getHeight();
		if(height*this.getWidth()/this.getHeight()<width) {
			width=height*rapport; 
		}else {
			height=width/rapport; 
		}
		if(posXStart>posXEnd) {
			startX=posXStart-width;
		}
		if(posYStart>posYEnd) {
			startY=posYStart-height;
		}
		this.setWidth(width);
		this.setHeight(height);
		this.setXPos(startX);
		this.setYPos(startY);
		
	}

	@Override
	public void ajustOnDragFromCenter(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		if(!scale) {
			ajustOnDragFromCenterNotScalable(posXStart, posYStart, posXEnd, posYEnd);
		}else
			ajustOnDragFromCenterScalable(posXStart, posYStart, posXEnd, posYEnd);

	}
	private void ajustOnDragFromCenterNotScalable(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double startX;
		double startY;
		double width;
		double height;
		if(posXStart<posXEnd) {
			width = posXEnd-posXStart;
			startX=posXStart-width;
		}else {
			startX=posXEnd;
			width = posXStart-posXEnd;
		}
		if(posYStart<posYEnd) {
			height = posYEnd-posYStart;
			startY=posYStart-height;
		}else {
			startY=posYEnd;
			height = posYStart-posYEnd;
		}
		this.setXPos(startX);
		this.setYPos(startY);
		this.setWidth(width*2);
		this.setHeight(height*2);

	}
	private void ajustOnDragFromCenterScalable(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double width = Math.abs(posXStart-posXEnd);
		double height = Math.abs(posYStart-posYEnd);
		double rapport =this.getWidth()/this.getHeight();
		if(height*this.getWidth()/this.getHeight()<width) {
			width=height*rapport; 
		}else {
			height=width/rapport; 
		}
		double startX=posXStart-width;
		double startY=posYStart-height;
		this.setWidth(width*2);
		this.setHeight(height*2);
		this.setXPos(startX);
		this.setYPos(startY);
		
	}

	@Override
	public void setType(String type, MainApp mainApp) {
		super.setType(type, mainApp);
		if(!grouped)
			group(type, mainApp);
	}

	@Override
	public int size() {
		return this.listOfShape.size();
	}
	@Override
	public void up(int collectionLength2, int index1, ObservableList<Node> a) {
		for(int i=0; i<size(); i++) {
			listOfShape.get(i).up(collectionLength2, index1+listOfShape.size()-1, a);
		}	
	}

	@Override
	public void down(int collectionLength2, int index1, ObservableList<Node> a) {
		for(int i=0; i<size(); i++) {
			listOfShape.get(i).down(collectionLength2, index1+i, a);
		}	
	}

	@Override
	public boolean isSelected(double xStart, double yStart, double xEnd, double yEnd) {
		boolean isSelected = listOfShape.size()>0;
		for(int i=0; i<listOfShape.size(); i++) {
			isSelected = listOfShape.get(i).isSelected(xStart, yStart, xEnd, yEnd) && isSelected;
		}
		return super.isSelected(xStart, yStart, xEnd, yEnd);
	}

	@Override
	public void draw(Pane p) {
		listOfShape.forEach(sg->{
			sg.draw(p);
		});
	}

	@Override
	public void setOnMouseClicked(CustomShape shapeToSelect, MainApp mainApp) {
		listOfShape.forEach(sh->{
			sh.setOnMouseClicked(shapeToSelect, mainApp);
		});

	}
	@Override
	protected void write(XMLStreamWriter writer) throws XMLStreamException {
		for(int i=0; i<listOfShape.size(); i++) {
			writer.writeStartElement("Shape");
			writer.writeAttribute("shapeType", listOfShape.get(i).getType());
			writer.writeAttribute("shapeConstructor", listOfShape.get(i).getContructorName());
			listOfShape.get(i).write(writer);
			writer.writeEndElement();
		}
		writer.writeStartElement("hFlip");
		writer.writeCharacters(String.valueOf(hFlip));
		writer.writeEndElement();
		writer.writeStartElement("vFlip");
		writer.writeCharacters(String.valueOf(vFlip));
		writer.writeEndElement();
	}
	@Override
	public void read(XMLEventReader reader, MainApp mainApp) throws XMLStreamException {
		XMLEvent event;
		StartElement se = null;
		CustomShape sh = null;
		boolean search = true;
		while (reader.hasNext() && search) {

			event = reader.nextEvent();
			if (event.isCharacters()) {
				search=false;
				break;
			}
			if (!event.isStartElement()) {
				continue;
			}
			se = event.asStartElement();
			if(se.getName().getLocalPart().equals("Shape")) {
				sh = ShapeFactory.build(se.getAttributeByName(new QName("shapeConstructor")).getValue());
				if(sh != null) {
					grouped=true;
					sh.setType(se.getAttributeByName(new QName("shapeType")).getValue(), mainApp);
					reader.nextEvent();
					sh.read(reader, mainApp);
					add(sh);
					sh.setOnMouseClicked(this, mainApp);
				}
			}
		}
		updateBoudingBox();
		event = reader.nextEvent();
		while(!event.isCharacters() && reader.hasNext()) {
			event = reader.nextEvent();
		}
		this.flipShape(0, !Boolean.valueOf(event.asCharacters().getData()));//1 is VFlip, 0 is HFlip
		while(!event.isCharacters() && reader.hasNext()) {
			event = reader.nextEvent();
		}
		this.flipShape(1, !Boolean.valueOf(event.asCharacters().getData()));//1 is VFlip, 0 is HFlip

	}

	@Override
	public void setLayer(String id) {
		super.setLayer(id);
		listOfShape.forEach(sh->{
			sh.setLayer(id);
		});
	}
	@Override
	protected String getContructorName() {
		return "CustomUnionShape";
	}
	@Override
	public void clearTransfrom() {
		listOfShape.forEach(sh->{
			sh.clearTransfrom();
		});
	}
	@Override
	public void loadTransform() {
		listOfShape.forEach(sh->{
			sh.loadTransform();
		});
		if (this.getHFlip())
			this.flipShape(0, true);
		if (this.getVFlip())
			this.flipShape(1, true);
	}
	@Override
	public void transform(Point2D p1, int flipVorH) {
		clearTransfrom();
		if (flipVorH == 1) {
			if(hFlip) {
				listOfShape.forEach(sh->{
					sh.transform(p1, 0);
				});
			}
			if(!vFlip) {
				listOfShape.forEach(sh->{
					sh.transform(p1, 1);
				});
			}
		}else if (flipVorH == 0){
			if(!hFlip) {
				listOfShape.forEach(sh->{
					sh.transform(p1, 0);
				});
			}
			if(vFlip) {
				listOfShape.forEach(sh->{
					sh.transform(p1, 1);
				});
			}
		}
		listOfShape.forEach(sh->{
			sh.loadTransform();
		});


	}
	@Override
	public void flipShape(int flipVorH, boolean cleared) {

		this.transform(this.getCenterCoord(), flipVorH);
		//flipVorH = 1 is VFlip, 0 is HFlip
		if (flipVorH == 1 && !cleared) {
			setVFlip(!getVFlip());
		}else if (flipVorH == 0 && !cleared){
			setHFlip(!getHFlip());
		}
		//this.getDraw().getTransforms().add(transformIntoReflection(this.getCenterCoord(), flipVorH));
	}
}