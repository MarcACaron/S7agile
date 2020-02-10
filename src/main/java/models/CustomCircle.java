package models;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CustomCircle extends Circle implements Transformable, Identifiable {

	public CustomCircle() {
	}

	public CustomCircle(double radius) {
		super(radius);
	}

	public CustomCircle(double radius, Paint fill) {
		super(radius, fill);
	}

	public CustomCircle(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius);
	}

	public CustomCircle(double centerX, double centerY, double radius, Paint fill) {
		super(centerX, centerY, radius, fill);
	}
	
	public void setXPosTool(double value) {
		this.setCenterX(value);
	}

	public void setYPosTool(double value) {
		this.setCenterY(value);
	}

	public void setWidthTool(double value) {
		//Don't have width
	}

	public void setHeightTool(double value) {
		//Don't have height
	}

	public void setRadiusTool(double value) {
		this.setRadius(value);
	}

	public void setLengthTool(double value) {
		//Don't have length
	}

	@Override
	public void setRotationTool(double value) {
		this.setRotate(value);
		
	}
	
	@Override
	public ShapeType getShapeType() {
		return ShapeType.CIRCLE;
	}

	@Override
	public double getXPos() {
		return this.getCenterX();
	}

	@Override
	public double getYPos() {
		return this.getCenterY();
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
	public double getLength() {
		return 0;
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
		return true;
	}

	@Override
	public boolean lengthToolisNeeded() {
		return false;
	}
	
	@Override
	public Shape duplicate() {
		CustomCircle newCircle = new CustomCircle();
		newCircle.setStroke(this.getStroke());
		newCircle.setStrokeWidth(this.getStrokeWidth());
		newCircle.setFill(this.getFill());
		newCircle.setCenterX(this.getCenterX() + xCopyOffset);
		newCircle.setCenterY(this.getCenterY() + yCopyOffset);
		newCircle.setRadiusTool(this.getRadius());
		newCircle.setRotationTool(this.getRotation());
		
		return newCircle;
	}

	@Override
	public String getType() {
		return "circle";
	}
}
