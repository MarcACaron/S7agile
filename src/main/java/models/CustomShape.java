package models;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class CustomShape {
	public final int XCOPYOFFSET = 50;
	public final int YCOPYOFFSET = 50;
	public final double SELECTIONSHAPEOFFSET = 10.0;
	protected Rectangle boundingBox;
	protected Shape shape;
	protected String layer;
	
	public abstract void setXPos(double value);
	public abstract void setYPos(double value);
	public abstract void setWidth(double value);
	public abstract void setHeight(double value);
	public void setStroke(Paint value) {
		this.shape.setStroke(value);
	}
	public void setStrokeWidth(double strokeWidth) {
		this.shape.setStrokeWidth(strokeWidth);
		
	}
	public void setFill(Paint value, String fillName) {
		this.shape.setFill(value);
		this.shape.setAccessibleText(fillName);
	}
	public void setRotate(double value) {
		this.shape.setRotate(value);
		this.boundingBox.setRotate(value);
	}
	
	public double getXPos() {
		return this.boundingBox.getX();
	}
	public double getYPos() {
		return this.boundingBox.getY();
	}
	public double getWidth() {
		return this.boundingBox.getWidth();
	}
	public double getHeight() {
		return this.boundingBox.getHeight();
	}
	public double getRotate() {
		return this.boundingBox.getRotate();
	}
	public Paint getStroke() {
		return this.shape.getStroke();
	}
	public double getStrokeWidth() {
		return this.shape.getStrokeWidth();
	}
	public Paint getFill() {
		return this.shape.getFill();
	}
	public Shape getDraw() {
		return this.shape;
	}
	public abstract String getType();
	public String getLayer() {
		return layer;
	}
	public abstract CustomShape duplicateAndOffset();
	public abstract CustomShape duplicate();
	public boolean isSelected(double xStart, double yStart, double xEnd, double yEnd) {
		if(this.boundingBox.getX()<xStart)
			return false;
		if(this.boundingBox.getY()<yStart)
			return false;
		if(this.boundingBox.getX()+this.getWidth()>xEnd)
			return false;
		if(this.boundingBox.getY()+this.getHeight()>yEnd)
			return false;
		return true;
	}
	protected void write(XMLStreamWriter writer) throws XMLStreamException {
		writer.writeStartElement("xPos");
		writer.writeCharacters(String.valueOf(getXPos()));
		writer.writeEndElement();
		writer.writeStartElement("yPos");
		writer.writeCharacters(String.valueOf(getYPos()));
		writer.writeEndElement();
		writer.writeStartElement("width");
		writer.writeCharacters(String.valueOf(getWidth()));
		writer.writeEndElement();
		writer.writeStartElement("height");
		writer.writeCharacters(String.valueOf(getHeight()));
		writer.writeEndElement();
		writer.writeStartElement("rotation");
		writer.writeCharacters(String.valueOf(getRotate()));
		writer.writeEndElement();
		writer.writeStartElement("fill");
		writer.writeCharacters(shape.getAccessibleText());
		writer.writeEndElement();
		writer.writeStartElement("stroke");
		writer.writeCharacters(getStroke().toString());
		writer.writeEndElement();
		writer.writeStartElement("strokeWidth");
		writer.writeCharacters(String.valueOf(getStrokeWidth()));
		writer.writeEndElement();
	}
	public void read(XMLEventReader reader) throws XMLStreamException {
		PatternApplier patternApplier = new PatternApplier();
		XMLEvent event;
		//reader.nextEvent();
		//reader.nextEvent();
		event = reader.nextEvent();
		this.setXPos(Double.valueOf(event.asCharacters().getData()));
		reader.nextEvent();
		reader.nextEvent();
		event = reader.nextEvent();
		this.setYPos(Double.valueOf(event.asCharacters().getData()));
		reader.nextEvent();
		reader.nextEvent();
		event = reader.nextEvent();
		this.setWidth(Double.valueOf(event.asCharacters().getData()));
		reader.nextEvent();
		reader.nextEvent();
		event = reader.nextEvent();
		this.setHeight(Double.valueOf(event.asCharacters().getData()));
		reader.nextEvent();
		reader.nextEvent();
		event = reader.nextEvent();
		this.setRotate(Double.valueOf(event.asCharacters().getData()));
		reader.nextEvent();
		reader.nextEvent();
		event = reader.nextEvent();
		patternApplier.fillShape(this.getDraw(), event.asCharacters().getData());
		reader.nextEvent();
		reader.nextEvent();
		event = reader.nextEvent();
		this.setStroke(Color.valueOf(event.asCharacters().getData()));
		reader.nextEvent();
		reader.nextEvent();
		event = reader.nextEvent();
		this.setStrokeWidth(Double.valueOf(event.asCharacters().getData()));
	}
	public abstract void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd);
	public abstract void ajustOnDragFromCenter(double posXStart, double posYStart, double posXEnd, double posYEnd);
	public void setLayer(String id) {
		this.layer = id;
	}
    public final double[] getOutlineCoords() {
        double array[] = {0,0,0,0};
        
        array[0] = this.getXPos() - SELECTIONSHAPEOFFSET;
        array[1] = this.getYPos() - SELECTIONSHAPEOFFSET;
        array[2] = this.getXPos() + this.getWidth() + SELECTIONSHAPEOFFSET;
        array[3] = this.getYPos() + this.getHeight() + SELECTIONSHAPEOFFSET;
        
        return array;
    }
    
    public final Point2D getCenterCoord() {
        return (new Point2D(this.getXPos()+this.getWidth()/2, this.getYPos()+this.getHeight()/2));
    }
	    
	
}