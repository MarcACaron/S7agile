package models;


import adraw4us.MainApp;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CustomLine extends CustomShape{

	public CustomLine() {
		this.shape = new Line();
		this.boundingBox = new Rectangle();
		this.scale=false;
		this.type = "line";
	}
	public CustomLine(double startX, double startY, double endX, double endY) {
		this.shape = new Line();
		this.boundingBox = new Rectangle();
		this.setXPos(startX);
		this.setYPos(startY);
		this.setWidth(endX - startX);
		this.setHeight(endY - startY);
		this.scale=false;
		this.type = "line";
	}
	
	@Override
	public void setXPos(double value) {
    	this.getDraw().getTransforms().clear();
		this.boundingBox.setX(value);
		double decalageStart = ((Line)this.shape).getStartX()-this.boundingBox.getX();
		double decalageEnd = ((Line)this.shape).getEndX()-this.boundingBox.getX();
		((Line)this.shape).setStartX(value+decalageStart);
		((Line)this.shape).setEndX(value+decalageEnd);
		if (this.getHFlip())
			this.flipShape(0, true);
    	if (this.getVFlip())
    		this.flipShape(1, true);
	}
	@Override
	public void setYPos(double value) {
    	this.getDraw().getTransforms().clear();
		this.boundingBox.setY(value);
		double decalageStart = ((Line)this.shape).getStartY()-this.boundingBox.getY();
		double decalageEnd = ((Line)this.shape).getEndY()-this.boundingBox.getY();
		((Line)this.shape).setStartY(value+decalageStart);
		((Line)this.shape).setEndY(value+decalageEnd);
		if (this.getHFlip())
			this.flipShape(0, true);
    	if (this.getVFlip())
    		this.flipShape(1, true);
	}

	@Override
	public void setWidth(double value) {
		this.boundingBox.setWidth(value);
		if(((Line)this.shape).getStartX()>((Line)this.shape).getEndX())
			((Line)this.shape).setStartX(value + ((Line)this.shape).getEndX());
		else
			((Line)this.shape).setEndX(value + ((Line)this.shape).getStartX());
	}

	@Override
	public void setHeight(double value) {
		this.boundingBox.setHeight(value);
		if(((Line)this.shape).getStartY()>((Line)this.shape).getEndY())
			((Line)this.shape).setStartY(value + ((Line)this.shape).getEndY());
		else
			((Line)this.shape).setEndY(value + ((Line)this.shape).getStartY());
	}

	public void updateLineLength() {
		
		// Intended to be like this
	}
	

	
	public CustomShape duplicate(int offsetX, int offsetY, MainApp mainApp) {
		CustomLine newLine = new CustomLine();
		applyValues(newLine, offsetX, offsetY, mainApp);
		return newLine;
	}
	
	@Override
	public void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double startX=Math.min(posXStart, posXEnd);
		double startY=Math.min(posYStart, posYEnd);
		double width = Math.abs(posXStart - posXEnd);
		double height = Math.abs(posYStart - posYEnd);
		this.boundingBox.setX(startX);
		this.boundingBox.setY(startY);
		this.boundingBox.setWidth(width);
		this.boundingBox.setHeight(height);
		((Line)shape).setStartX(posXStart);
		((Line)shape).setStartY(posYStart);
		((Line)shape).setEndX(posXEnd);
		((Line)shape).setEndY(posYEnd);
	}
	@Override
	public void ajustOnDragFromCenter(double posXStart, double posYStart, double posXEnd, double posYEnd) {
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
		((Line)shape).setStartX(2*posXStart-posXEnd);
		((Line)shape).setStartY(2*posYStart-posYEnd);
		((Line)shape).setEndX(posXEnd);
		((Line)shape).setEndY(posYEnd);
	}
	@Override
	protected String getContructorName() {
		return "CustomLine";
	}
}
