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
		this.setStartX(value);
	}

	@Override
	public void setYPosTool(double value) {
		this.setStartY(value);
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
}
