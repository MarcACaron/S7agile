package models;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CustomRectangle extends Rectangle implements Transformable, Identifiable {

	public CustomRectangle() {
		// TODO Auto-generated constructor stub
	}

	public CustomRectangle(double width, double height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	public CustomRectangle(double width, double height, Paint fill) {
		super(width, height, fill);
		// TODO Auto-generated constructor stub
	}

	public CustomRectangle(double x, double y, double width, double height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
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
	}

	@Override
	public void setLengthTool(double value) {
	}

	@Override
	public void setRotationTool(double value) {
		this.setRotate(value);
		
	}
	
	@Override
	public ShapeType getShapeType() {
		return ShapeType.RECTANGLE;
	}
	

}
