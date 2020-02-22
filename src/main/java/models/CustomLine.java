package models;


import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CustomLine extends CustomShape{

	public CustomLine() {
		this.shape = new Line();
		this.boundingBox = new Rectangle();
	}
	public void setXPos(double value) {
		((Line)this.shape).setStartX(value);
		this.boundingBox.setX(value);
		/*double xL = this.getEndX() - this.getStartX();
		this.setStartX(value);
		this.setEndX(value + xL);*/
	}

	public void setYPos(double value) {
		((Line)this.shape).setStartY(value);
		this.boundingBox.setY(value);
		/*
		double yL = this.getEndY() - this.getStartY();
		this.setStartY(value);
		this.setEndY(value + yL);*/
	}

	@Override
	public void setWidth(double value) {
		((Line)this.shape).setStartX(this.boundingBox.getX()+value);
		this.boundingBox.setWidth(value);
		//((Line)this.shape).setEndX(value);
	}

	@Override
	public void setHeight(double value) {
		((Line)this.shape).setStartY(this.boundingBox.getY()+value);
		this.boundingBox.setHeight(value);
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
}
