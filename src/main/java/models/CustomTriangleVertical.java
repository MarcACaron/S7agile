package models;

import adraw4us.MainApp;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CustomTriangleVertical extends CustomShape {
	public CustomTriangleVertical() {
		this.shape = new Polygon();
		((Polygon) this.shape).getPoints().addAll(0.0, 0.0, 0.0, 0.0, 0.0 , 0.0);
		this.boundingBox = new Rectangle();
		this.scale=false;
		this.type = "triangleHorizontal";
	}
	
	@Override
	public CustomShape duplicate(int offsetX, int offsetY, MainApp mainApp) {
		CustomTriangleVertical newTriangle = new CustomTriangleVertical();
		applyValues(newTriangle, offsetX, offsetY, mainApp);
		return newTriangle;
	}
	
	public void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double width;
		double height;
		double startX;
		double startY;
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
		double width;
		double height;
		double startX;
		double startY;
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
		this.setWidth(width*2);
		this.setHeight(height*2);
		this.setXPos(startX);
		this.setYPos(startY);
		
	}

	@Override
	public void setXPos(double value) {
    	this.getDraw().getTransforms().clear();
		this.boundingBox.setX(value);
		((Polygon)this.shape).getPoints().set(0, value);
		((Polygon)this.shape).getPoints().set(2, value);
		((Polygon)this.shape).getPoints().set(4, value+this.boundingBox.getWidth());
		if (this.getHFlip())
			this.flipShape(0, true);
    	if (this.getVFlip())
    		this.flipShape(1, true);
	}

	@Override
	public void setYPos(double value) {
    	this.getDraw().getTransforms().clear();
		this.boundingBox.setY(value);
		((Polygon)this.shape).getPoints().set(1, value);
		((Polygon)this.shape).getPoints().set(3, value+this.boundingBox.getHeight());
		((Polygon)this.shape).getPoints().set(5, value+this.boundingBox.getHeight()/2);
		if (this.getHFlip())
			this.flipShape(0, true);
    	if (this.getVFlip())
    		this.flipShape(1, true);
	}

	@Override
	public void setWidth(double value) {
		this.boundingBox.setWidth(value);
		setXPos(this.boundingBox.getX());
	}

	@Override
	public void setHeight(double value) {
		this.boundingBox.setHeight(value);
		setYPos(this.boundingBox.getY());
	}

	@Override
	protected String getContructorName() {
		return "CustomTriangleVertical";
	}

}
