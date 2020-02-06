package models;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CustomRectangle extends Rectangle implements Transformable, Identifiable {

	public CustomRectangle() {
	}

	public CustomRectangle(double width, double height) {
		super(width, height);
	}

	public CustomRectangle(double width, double height, Paint fill) {
		super(width, height, fill);
	}

	public CustomRectangle(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void setXPosTool(double value) {
		this.setX(value);
	}

	@Override
	public void setYPosTool(double value) {
		this.setY(value);
	}

	@Override
	public void setWidthTool(double value) {
		this.setWidth(value);
	}

	@Override
	public void setHeightTool(double value) {
		this.setHeight(value);
	}

	@Override
	public void setRadiusTool(double value) {
		//Don't have radius
	}

	@Override
	public void setLengthTool(double value) {
		//Don't have length
	}

	@Override
	public void setRotationTool(double value) {
		this.setRotate(value);
		
	}
	
	@Override
	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}

	@Override
	public double getXPos() {
		return this.getX();
	}

	@Override
	public double getYPos() {
		return this.getY();
	}

	@Override
	public double getRadius() {
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
		return true;
	}

	@Override
	public boolean heightToolisNeeded() {
		return true;
	}

	@Override
	public boolean radiusToolisNeeded() {
		return false;
	}

	@Override
	public boolean lengthToolisNeeded() {
		return false;
	}
}
