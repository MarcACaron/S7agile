package models;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CustomTriangleVertical extends CustomShape {
	public CustomTriangleVertical() {
		this.shape = new Polygon();
		((Polygon) this.shape).getPoints().addAll(0.0, 0.0, 0.0, 0.0, 0.0 , 0.0);
		this.boundingBox = new Rectangle();
	}
	
	@Override
	public CustomShape duplicateAndOffset() {
		CustomTriangleVertical newTriangleVertical = new CustomTriangleVertical();//TODO: reparer
		
		return newTriangleVertical;
	}

	
	@Override
	public CustomShape duplicate() {
		CustomTriangleVertical newTriangleVertical = new CustomTriangleVertical();//TODO: REPARER
		
		return newTriangleVertical;
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
		this.setXPosition(startX);
		this.setYPosition(startY);
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
		this.setXPosition(startX);
		this.setYPosition(startY);
		
	}
	
	@Override
	public String getType() {
		return "triangleHorizontal";
		
	}
	@Override
	public void setShapeXPos(double value) {
		((Polygon)this.shape).getPoints().set(0, value+this.boundingBox.getWidth());
		((Polygon)this.shape).getPoints().set(2, value+this.boundingBox.getWidth());
		((Polygon)this.shape).getPoints().set(4, value);
	}

	@Override
	public void setShapeYPos(double value) {
		((Polygon)this.shape).getPoints().set(1, value);
		((Polygon)this.shape).getPoints().set(3, value+this.boundingBox.getHeight());
		((Polygon)this.shape).getPoints().set(5, value+this.boundingBox.getHeight()/2);
	}

	@Override
	public void setWidth(double value) {
		this.boundingBox.setWidth(value);
		setXPosition(this.boundingBox.getX());
	}

	@Override
	public void setHeight(double value) {
		this.boundingBox.setHeight(value);
		setYPosition(this.boundingBox.getY());
	}

}
