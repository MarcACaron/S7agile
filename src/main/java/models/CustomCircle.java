package models;


import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CustomCircle extends CustomShape {

	public CustomCircle() {
		this.shape = new Circle();
		this.boundingBox = new Rectangle();
	}
	
	public void setXPos(double value) {
		this.boundingBox.setX(value);
		((Circle)this.shape).setCenterX(value + this.boundingBox.getWidth()/2);
	}

	public void setYPos(double value) {
		this.boundingBox.setY(value);
		((Circle)this.shape).setCenterY(value + this.boundingBox.getHeight()/2);
	}

	public void setWidth(double value) {
		this.boundingBox.setWidth(value);
		this.boundingBox.setHeight(value);
		((Circle)this.shape).setRadius(value/2);
	}

	public void setHeight(double value) {
		this.boundingBox.setWidth(value);
		this.boundingBox.setHeight(value);
		((Circle)this.shape).setRadius(value/2);
	}
	@Override
	public CustomShape duplicateAndOffset() {
		CustomCircle newCircle = new CustomCircle();
		/*newCircle.setStroke(this.getStroke());
		newCircle.setStrokeWidth(this.getStrokeWidth());
		newCircle.setFill(this.getFill());
		newCircle.setCenterX(this.getCenterX() + XCOPYOFFSET);
		newCircle.setCenterY(this.getCenterY() + YCOPYOFFSET);
		newCircle.setRadiusTool(this.getRadius());
		newCircle.setRotationTool(this.getRotation());*/
		
		return newCircle;
	}
	
	@Override
	public CustomShape duplicate() {
		CustomCircle newCircle = new CustomCircle();
		/*newCircle.setStroke(this.getStroke());
		newCircle.setStrokeWidth(this.getStrokeWidth());
		newCircle.setFill(this.getFill());
		newCircle.setCenterX(this.getCenterX());
		newCircle.setCenterY(this.getCenterY());
		newCircle.setRadiusTool(this.getRadius());
		newCircle.setRotationTool(this.getRotation());*/
		
		return newCircle;
	}

	@Override
	public String getType() {
		return "circle";
	}
}
