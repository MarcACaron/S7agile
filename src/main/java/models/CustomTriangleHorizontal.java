package models;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CustomTriangleHorizontal extends CustomShape {
	public CustomTriangleHorizontal() {
		this.shape = new Polygon();
		((Polygon) this.shape).getPoints().addAll(0.0, 0.0, 0.0, 0.0, 0.0 , 0.0);
		this.boundingBox = new Rectangle();
	}
	
	@Override
	public CustomShape duplicateAndOffset() {
		CustomTriangleHorizontal newTriangleHorizontal = new CustomTriangleHorizontal();//TODO: reparer
		/*newRectangle.setStroke(this.getStroke());
		newRectangle.setStrokeWidth(this.getStrokeWidth());
		newRectangle.setFill(this.getFill());
		newRectangle.setXPos(this.getXPos() + XCOPYOFFSET);
		newRectangle.setYPos(this.getYPos() + YCOPYOFFSET);
		newRectangle.setWidth(this.getWidth());
		newRectangle.setHeight(this.getHeight());*/
		
		return newTriangleHorizontal;
	}

	
	@Override
	public CustomShape duplicate() {
		CustomTriangleHorizontal newTriangleHorizontal = new CustomTriangleHorizontal();//TODO: REPARER
		/*newRectangle.setStroke(this.getStroke());
		newRectangle.setStrokeWidth(this.getStrokeWidth());
		newRectangle.setFill(this.getFill());
		newRectangle.setXPos(this.getXPos());
		newRectangle.setYPos(this.getYPos());
		newRectangle.setWidth(this.getWidth());
		newRectangle.setHeight(this.getHeight());*/
		
		return newTriangleHorizontal;
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
		this.setWidth(width);
		this.setHeight(height);
		this.setXPos(startX);
		this.setYPos(startY);
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
		this.setWidth(width*2);
		this.setHeight(height*2);
		this.setXPos(startX);
		this.setYPos(startY);
	}
	
	@Override
	public String getType() {
		return "triangleHorizontal";
	}
	
	@Override
	public void setXPos(double value) {
		this.boundingBox.setX(value);
		((Polygon)this.shape).getPoints().set(0, value);
		((Polygon)this.shape).getPoints().set(2, value+this.boundingBox.getWidth());
		((Polygon)this.shape).getPoints().set(4, value+this.boundingBox.getWidth()/2);
	}

	@Override
	public void setYPos(double value) {
		this.boundingBox.setY(value);
		((Polygon)this.shape).getPoints().set(1, value+this.boundingBox.getHeight());
		((Polygon)this.shape).getPoints().set(3, value+this.boundingBox.getHeight());
		((Polygon)this.shape).getPoints().set(5, value);		
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

}
