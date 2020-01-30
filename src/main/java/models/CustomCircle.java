package models;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class CustomCircle extends Circle implements Transformable, Identifiable {

	public CustomCircle() {
		// TODO Auto-generated constructor stub
	}

	public CustomCircle(double radius) {
		super(radius);
	}

	public CustomCircle(double radius, Paint fill) {
		super(radius, fill);
		// TODO Auto-generated constructor stub
	}

	public CustomCircle(double centerX, double centerY, double radius) {
		super(centerX, centerY, radius);
		// TODO Auto-generated constructor stub
	}

	public CustomCircle(double centerX, double centerY, double radius, Paint fill) {
		super(centerX, centerY, radius, fill);
		// TODO Auto-generated constructor stub
	}
	
	public void setXPosTool(double value) {
		this.setCenterX(value);
	}

	public void setYPosTool(double value) {
		this.setCenterY(value);
	}

	public void setWidthTool(double value) {
	}

	public void setHeightTool(double value) {
	}

	public void setRadiusTool(double value) {
		this.setRadius(value);
	}

	public void setLengthTool(double value) {
	}

	@Override
	public void setRotationTool(double value) {
		this.setRotate(value);
		
	}
	
	@Override
	public ShapeType getShapeType() {
		return ShapeType.CIRCLE;
	}
	
}
