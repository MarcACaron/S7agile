package models;

import adraw4us.MainApp;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CustomTriangleHorizontal extends CustomShape {
	public CustomTriangleHorizontal() {
		this.shape = new Polygon();
		((Polygon) this.shape).getPoints().addAll(0.0, 0.0, 0.0, 0.0, 0.0 , 0.0);
		this.boundingBox = new Rectangle();
		this.scale=false;
		this.type = "triangleHorizontal";
	}

	
	@Override
	public CustomShape duplicate(int offsetX, int offsetY, MainApp mainApp) {
		CustomTriangleHorizontal newTriangle = new CustomTriangleHorizontal();
		applyValues(newTriangle, offsetX, offsetY, mainApp);
		return newTriangle;
	}

	public void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double startX;
		double startY;
		double width;
		double height;
		if(posYStart<posYEnd) {
			startY=posYStart;
			height = posYEnd-posYStart;
		}else {
			startY=posYEnd;
			height = posYStart-posYEnd;
		}
		if(posXStart<posXEnd) {
			startX=posXStart;
			width = posXEnd-posXStart;
		}else {
			startX=posXEnd;
			width = posXStart-posXEnd;
		}
		this.setWidth(width);
		this.setHeight(height);
		this.setYPosition(startY);
		this.setXPosition(startX);
	}
	
	public void ajustOnDragFromCenter(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double startX;
		double startY;
		double width;
		double height;

		if(posYStart<posYEnd) {
			height = posYEnd-posYStart;
			startY=posYStart-height;
		}else {
			startY=posYEnd;
			height = posYStart-posYEnd;
		}
		if(posXStart<posXEnd) {
			width = posXEnd-posXStart;
			startX=posXStart-width;
		}else {
			startX=posXEnd;
			width = posXStart-posXEnd;
		}
		this.setWidth(width*2);
		this.setHeight(height*2);
		this.setXPosition(startX);
		this.setYPosition(startY);
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
		setXPosition(this.boundingBox.getX());
	}

	@Override
	public void setHeight(double value) {
		this.boundingBox.setHeight(value);
		setYPosition(this.boundingBox.getY());
	}

}
