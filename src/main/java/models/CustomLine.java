package models;


import javafx.scene.shape.Line;

public class CustomLine extends Line implements Transformable{

	public CustomLine() {
	}

	public CustomLine(double startX, double startY, double endX, double endY) {
		super(startX, startY, endX, endY);
	}
	
	@Override
	public void setXPosTool(double value) {
		double xL = this.getEndX() - this.getStartX();
		this.setStartX(value);
		this.setEndX(value + xL);
	}

	@Override
	public void setYPosTool(double value) {
		double yL = this.getEndY() - this.getStartY();
		this.setStartY(value);
		this.setEndY(value + yL);
	}

	@Override
	public void setWidthTool(double value) {
		//Don't have width
	}

	@Override
	public void setHeightTool(double value) {
		//Don't have height
	}

	@Override
	public void setRadiusTool(double value) {
		//Don't have radius
	}

	@Override
	public void setLengthTool(double value) {
		double length = this.getLength();
		this.setEndX((this.getEndX()-this.getStartX())*value/length+this.getStartX());
		this.setEndY((this.getEndY()-this.getStartY())*value/length+this.getStartY());
	}
	
	@Override
	public void setRotationTool(double value) {
		this.setRotate(value);
		
	}

	@Override
	public double getXPos() {
		return this.getStartX();
	}

	@Override
	public double getYPos() {
		return this.getStartY();
	}

	@Override
	public double getWidth() {
		return 0;
	}

	@Override
	public double getHeight() {
		return 0;
	}

	@Override
	public double getRadius() {
		return 0;
	}

	@Override
	public double getLength() {
		return Math.sqrt(Math.pow(this.getStartX() - this.getEndX(), 2) + Math.pow(this.getStartY() - this.getEndY(), 2));
	}

	@Override
	public double getRotation() {
		return this.getRotate();
	}

	@Override
	public boolean widthToolisNeeded() {
		return false;
	}

	@Override
	public boolean heightToolisNeeded() {
		return false;
	}

	@Override
	public boolean radiusToolisNeeded() {
		return false;
	}

	@Override
	public boolean lengthToolisNeeded() {
		return true;
	}

	@Override
	public String getType() {
		return "line";
	}
}
