package models;

import javafx.scene.shape.Rectangle;

public class CustomRectangle extends CustomShape {
	public CustomRectangle() {
		this.shape = new Rectangle();
		this.boundingBox = new Rectangle();
	}
	@Override
	public CustomShape duplicateAndOffset() {
		CustomRectangle newRectangle = new CustomRectangle();
		/*newRectangle.setStroke(this.getStroke());
		newRectangle.setStrokeWidth(this.getStrokeWidth());
		newRectangle.setFill(this.getFill());
		newRectangle.setXPos(this.getXPos() + XCOPYOFFSET);
		newRectangle.setYPos(this.getYPos() + YCOPYOFFSET);
		newRectangle.setWidth(this.getWidth());
		newRectangle.setHeight(this.getHeight());*/
		
		return newRectangle;
	}

	
	@Override
	public CustomShape duplicate() {
		CustomRectangle newRectangle = new CustomRectangle();
		/*newRectangle.setStroke(this.getStroke());
		newRectangle.setStrokeWidth(this.getStrokeWidth());
		newRectangle.setFill(this.getFill());
		newRectangle.setXPos(this.getXPos());
		newRectangle.setYPos(this.getYPos());
		newRectangle.setWidth(this.getWidth());
		newRectangle.setHeight(this.getHeight());*/
		
		return newRectangle;
	}
	
	@Override
	public String getType() {
		return "rectangle";
	}
	@Override
	public void setXPos(double value) {
		((Rectangle)this.shape).setX(value);
		this.boundingBox.setX(value);
	}

	@Override
	public void setYPos(double value) {
		((Rectangle)this.shape).setY(value);
		this.boundingBox.setY(value);
	}

	@Override
	public void setWidth(double value) {
		((Rectangle)this.shape).setWidth(value);
		this.boundingBox.setWidth(value);
	}

	@Override
	public void setHeight(double value) {
		((Rectangle)this.shape).setHeight(value);
		this.boundingBox.setHeight(value);
	}
}
