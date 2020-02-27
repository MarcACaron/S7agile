package models;

import adraw4us.MainApp;
import javafx.scene.shape.Rectangle;

public class CustomRectangle extends CustomShape {
	public CustomRectangle() {
		this.shape = new Rectangle();
		this.boundingBox = new Rectangle();
		this.scale=false;
		this.type = "rectangle";
	}
	
	public CustomRectangle(double posX, double posY, double width, double height) {
		this.shape = new Rectangle();
		this.boundingBox = new Rectangle();
		
		this.setXPosition(posX);
		this.setYPosition(posY);
		this.setWidth(width);
		this.setHeight(height);
		this.scale=false;
		this.type = "rectangle";
	}
	
	@Override
	public CustomShape duplicate(int offsetX, int offsetY, MainApp mainApp) {
		CustomRectangle newRectangle = new CustomRectangle();
		applyValues(newRectangle, offsetX, offsetY, mainApp);
		
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
		this.setXPosition(startX);
		this.setYPosition(startY);
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
		this.setXPosition(startX);
		this.setYPosition(startY);
		this.setWidth(width*2);
		this.setHeight(height*2);
		
	}

	@Override
	public void setXPos(double value) {
		this.boundingBox.setX(value);
		((Rectangle)this.shape).setX(value);
	}

	@Override
	public void setYPos(double value) {
		this.boundingBox.setY(value);
		((Rectangle)this.shape).setY(value);
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
