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
	public void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double startX;
		double startY;
		double width;
		double height;
		if(posXStart<posXEnd) {
			startX=posXStart;
			width = posXEnd-posXStart;
		}else {
			startX=posXEnd;
			width = posXStart-posXEnd;
		}
		if(posYStart<posYEnd) {
			startY=posYStart;
			height = posYEnd-posYStart;
		}else {
			startY=posYEnd;
			height = posYStart-posYEnd;
		}
		this.setXPos(startX);
		this.setYPos(startY);
		this.setWidth(width);
		this.setHeight(height);
	}
	public void ajustOnDragFromCenter(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double startX;
		double startY;
		double width;
		double height;
		if(posXStart<posXEnd) {
			width = posXEnd-posXStart;
			startX=posXStart-width;
		}else {
			startX=posXEnd;
			width = posXStart-posXEnd;
		}
		if(posYStart<posYEnd) {
			height = posYEnd-posYStart;
			startY=posYStart-height;
		}else {
			startY=posYEnd;
			height = posYStart-posYEnd;
		}
		this.setXPos(startX);
		this.setYPos(startY);
		this.setWidth(width*2);
		this.setHeight(height*2);
		
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
