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
import javafx.scene.transform.Affine;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

public abstract class CustomShape {
	public static final int XCOPYOFFSET = 50;
	public static final int YCOPYOFFSET = 50;
	public static final double SELECTIONSHAPEOFFSET = 10.0;
	protected Rectangle boundingBox;
	protected Shape shape;
	protected String layer;
	protected boolean hFlip = false;
	protected boolean vFlip = false;
	protected boolean scale;
	protected String type = "Shape";
	
	public boolean getHFlip() {
		return hFlip;
	}
	public void setHFlip(boolean hFlip) {
		this.hFlip = hFlip;
	}
	public boolean getVFlip() {
		return vFlip;
	}
	public void setVFlip(boolean vFlip) {
		this.vFlip = vFlip;
	}
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
	public String getFillName() {
		return this.shape.getAccessibleText();
	}
	public Shape getDraw() {
		return this.shape;
	}
	public void setType(String type) {
		if(type!=null) {
			this.type = type;
		}
	}
	public String getType() {
		return this.type;
	}
	public String getLayer() {
		return layer;
	}
	public abstract CustomShape duplicate(int offsetX, int offsetY);
	public boolean isSelected(double xStart, double yStart, double xEnd, double yEnd) {

		if(this.boundingBox.getY()+this.getHeight()>yEnd || this.boundingBox.getX()+this.getWidth()>xEnd
				|| this.boundingBox.getY()<yStart || this.boundingBox.getX()<xStart)
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
        double[] array = {0,0,0,0};
        
        array[0] = this.getXPos() - SELECTIONSHAPEOFFSET;
        array[1] = this.getYPos() - SELECTIONSHAPEOFFSET;
        array[2] = this.getXPos() + this.getWidth() + SELECTIONSHAPEOFFSET;
        array[3] = this.getYPos() + this.getHeight() + SELECTIONSHAPEOFFSET;
        
        return array;
    }
    
    public final Point2D getCenterCoord() {
        return (new Point2D(this.getXPos()+this.getWidth()/2, this.getYPos()+this.getHeight()/2));
    }
    
    public void setXPosition(double value) {
    	this.getDraw().getTransforms().clear();
		this.boundingBox.setX(value);
		this.setXPos(value);
		if (this.getHFlip())
			this.flipShape(0, true);
    	if (this.getVFlip())
    		this.flipShape(1, true);
    }
	
    public void setYPosition(double value) {
    	this.getDraw().getTransforms().clear();
		this.boundingBox.setY(value);
		this.setYPos(value);
		if (this.getHFlip())
			this.flipShape(0, true);
    	if (this.getVFlip())
    		this.flipShape(1, true);
    }
    
    private Transform transformIntoReflection(Point2D p1, int flipXorY) {
		//flipXorY = 1 is Y-Flip, 0 is X-Flip
	    Translate translation = new Translate(-p1.getX(), -p1.getY());
	    Scale scale;
	    if (flipXorY == 1) {
	    	scale = new Scale(1, -1);
	    }else{
	    	scale = new Scale(-1, 1);
	    }
	    Affine reflection = new Affine();
	    reflection.append(translation.createInverse());
	    reflection.append(scale);
	    reflection.append(translation);
	    return reflection ;
	}
    
    public void flipShape(int flipVorH, boolean cleared) {
		//flipVorH = 1 is VFlip, 0 is HFlip
		if (flipVorH == 1 && !cleared) {
			setVFlip(!getVFlip());
		}else if (flipVorH == 0 && !cleared){
			setHFlip(!getHFlip());
		}
		this.getDraw().getTransforms().add(transformIntoReflection(this.getCenterCoord(), flipVorH));
	}
	public boolean isScale() {
		return scale;
	}
	public void setScale(boolean scale) {
		this.scale = scale;
	}
}