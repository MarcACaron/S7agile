package models;


import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CustomLine extends CustomShape{

	public CustomLine() {
		this.shape = new Line();
		this.boundingBox = new Rectangle();
	}
	public void setXPos(double value) {
		double decalageStart = ((Line)this.shape).getStartX()-this.boundingBox.getX();
		double decalageEnd = ((Line)this.shape).getEndX()-this.boundingBox.getX();
		this.boundingBox.setX(value);
		((Line)this.shape).setStartX(value+decalageStart);
		((Line)this.shape).setEndX(value+decalageEnd);
		/*double xL = this.getEndX() - this.getStartX();
		this.setStartX(value);
		this.setEndX(value + xL);*/
	}

	public void setYPos(double value) {
		double decalageStart = ((Line)this.shape).getStartY()-this.boundingBox.getY();
		double decalageEnd = ((Line)this.shape).getEndY()-this.boundingBox.getY();
		this.boundingBox.setY(value);
		((Line)this.shape).setStartY(value+decalageStart);
		((Line)this.shape).setEndY(value+decalageEnd);
		/*
		double yL = this.getEndY() - this.getStartY();
		this.setStartY(value);
		this.setEndY(value + yL);*/
	}

	@Override
	public void setWidth(double value) {
		this.boundingBox.setWidth(value);
		if(((Line)this.shape).getStartX()>((Line)this.shape).getEndX())
			((Line)this.shape).setStartX(value + ((Line)this.shape).getEndX());
		else
			((Line)this.shape).setEndX(value + ((Line)this.shape).getStartX());
		//((Line)this.shape).setEndX(value);
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
		//double length = this.getLength();
		/*this.setEndX((this.getEndX()-this.getStartX())*value/length+this.getStartX());
		this.setEndY((this.getEndY()-this.getStartY())*value/length+this.getStartY());*/
	}
	
	public CustomShape duplicateAndOffset() {//TODO: Réparer
		CustomLine newLine = new CustomLine();
		/*newLine.setStroke(this.getStroke());
		newLine.setStrokeWidth(this.getStrokeWidth());
		newLine.setFill(this.getFill());
		newLine.setStartX(this.getStartX() + XCOPYOFFSET);
		newLine.setStartY(this.getStartY() + YCOPYOFFSET);
		newLine.setEndX(this.getEndX() + XCOPYOFFSET);
		newLine.setEndY(this.getEndY() + YCOPYOFFSET);
		newLine.setLengthTool(this.getLength());
		newLine.setRotationTool(this.getRotation());*/
		
		return newLine;
	}
	
	public CustomShape duplicate() {//TODO: Réparer
		CustomLine newLine = new CustomLine();
		/*newLine.setStroke(this.getStroke());
		newLine.setStrokeWidth(this.getStrokeWidth());
		newLine.setFill(this.getFill());
		newLine.setStartX(this.getStartX());
		newLine.setStartY(this.getStartY());
		newLine.setEndX(this.getEndX());
		newLine.setEndY(this.getEndY());
		newLine.setLengthTool(this.getLength());
		newLine.setRotationTool(this.getRotation());*/
		
		return newLine;
	}
	
	@Override
	public String getType() {
		return "line";
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
}
