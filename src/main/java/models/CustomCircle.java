package models;


import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CustomCircle extends Circle implements Transformable {
	
	private final static int selectionShapeOffset = 10;

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
	public boolean heightToolisNeeded() {
		return false;
	}
	
	@Override
	public boolean widthToolisNeeded() {
		return false;
	}

	@Override
	public boolean lengthToolisNeeded() {
		return false;
	}

	@Override
	public boolean radiusToolisNeeded() {
		return true;
	}

	
	
	@Override
	public Shape duplicateAndOffset() {
		CustomCircle newCircle = new CustomCircle();
		newCircle.setStroke(this.getStroke());
		newCircle.setStrokeWidth(this.getStrokeWidth());
		newCircle.setFill(this.getFill());
		newCircle.setCenterX(this.getCenterX() + XCOPYOFFSET);
		newCircle.setCenterY(this.getCenterY() + YCOPYOFFSET);
		newCircle.setRadiusTool(this.getRadius());
		newCircle.setRotationTool(this.getRotation());
		
		return newCircle;
	}
	
	@Override
	public Shape duplicate() {
		CustomCircle newCircle = new CustomCircle();
		newCircle.setStroke(this.getStroke());
		newCircle.setStrokeWidth(this.getStrokeWidth());
		newCircle.setFill(this.getFill());
		newCircle.setCenterX(this.getCenterX());
		newCircle.setCenterY(this.getCenterY());
		newCircle.setRadiusTool(this.getRadius());
		newCircle.setRotationTool(this.getRotation());
		
		return newCircle;
	}

	@Override
	public String getType() {
		return "circle";
	}

	@Override
	public boolean isSelected(double xStart, double yStart, double xEnd, double yEnd) {
		if(this.getXPos()<xStart)
			return false;
		if(this.getXPos()<yStart)
			return false;
		if(this.getXPos()>xEnd)
			return false;
		if(this.getXPos()>yEnd)
			return false;
		if(Math.sqrt(Math.pow(xStart-this.getXPos(), 2)+Math.pow(yStart-this.getYPos(), 2))<this.getRadius())
			return false;
		if(Math.sqrt(Math.pow(xEnd-this.getXPos(), 2)+Math.pow(yEnd-this.getYPos(), 2))<this.getRadius())
			return false;
		return true;
	}
	
	@Override
	public double[] getOutlineCoords() {
		double array[] = {0,0,0,0};
		
		array[0] = this.getXPos() - this.getRadius() - selectionShapeOffset;
		array[1] = this.getYPos() - this.getRadius() - selectionShapeOffset;
		array[2] = this.getXPos() + this.getRadius() + selectionShapeOffset;
		array[3] = this.getYPos() + this.getRadius() + selectionShapeOffset;
		
		return array;
	}
	
	@Override
	public Point2D getCenterCoord() {
		return (new Point2D(this.getXPos(), this.getYPos()));
	}
}
