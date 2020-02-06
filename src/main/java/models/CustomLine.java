package models;


import javafx.scene.shape.Line;

public class CustomLine extends Line implements Transformable, Identifiable{

	public CustomLine() {
	}

	public CustomLine(double startX, double startY, double endX, double endY) {
		super(startX, startY, endX, endY);
	}
	
	@Override
	public void setXPosTool(double value) {
		double length = this.getLength();
		this.setStartX(value);
		this.setLengthTool(length);
	}

	@Override
	public void setYPosTool(double value) {
		double length = this.getLength();
		this.setStartY(value);
		this.setLengthTool(length);
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
		this.setEndX(this.getStartX()+Math.cos(this.getRotate())*value);
		this.setEndX(this.getStartX()+Math.cos(this.getRotate())*value);
	}
	
	@Override
	public void setRotationTool(double value) {
		this.setRotate(value);
		
	}
	
	@Override
	public ShapeType getShapeType() {
		return ShapeType.LINE;
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
}
