package models;


import adraw4us.MainApp;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CustomCircle extends CustomShape {

	public CustomCircle() {
		this.shape = new Circle();
		this.boundingBox = new Rectangle();
		this.scale=true;
		this.type = "circle";
	}
	
	public CustomCircle(double posX, double posY, double radius) {
		this.shape = new Circle();
		this.boundingBox = new Rectangle();
		this.setXPos(posX);
		this.setYPos(posY);
		this.setWidth(2*radius);
		this.scale=true;
		this.type = "circle";
	}
	
	@Override
	public void setXPos(double value) {
		this.getDraw().getTransforms().clear();
		this.boundingBox.setX(value);
		((Circle)this.shape).setCenterX(value + this.boundingBox.getWidth()/2);
		if (this.getHFlip())
			this.flipShape(0, true);
    	if (this.getVFlip())
    		this.flipShape(1, true);
	}

	@Override
	public void setYPos(double value) {
		this.getDraw().getTransforms().clear();
		this.boundingBox.setY(value);
		((Circle)this.shape).setCenterY(value + this.boundingBox.getHeight()/2);
		if (this.getHFlip())
			this.flipShape(0, true);
    	if (this.getVFlip())
    		this.flipShape(1, true);
	}

	public void setWidth(double value) {
		this.boundingBox.setWidth(value);
		this.boundingBox.setHeight(value);
		((Circle)this.shape).setRadius(value/2);
		this.setXPos(this.boundingBox.getX());
		this.setYPos(this.boundingBox.getY());
	}

	public void setHeight(double value) {
		this.setWidth(value);
	}
	@Override
	public CustomShape duplicate(int offsetX, int offsetY, MainApp mainApp) {
		CustomCircle newCircle = new CustomCircle();
		applyValues(newCircle, offsetX, offsetY, mainApp);
		return newCircle;
	}
	
	@Override
	public void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double cote = Math.min(Math.abs(posXEnd-posXStart), Math.abs(posYEnd-posYStart));
		double startX;
		double startY;
		if(posXStart<posXEnd) {
			startX=posXStart;
		}else {
			startX=posXStart-cote;
		}
		if(posYStart<posYEnd) {
			startY=posYStart;
		}else {
			startY=posYStart-cote;
		}
		this.setWidth(cote);
		this.setHeight(cote);
		this.setXPos(startX);
		this.setYPos(startY);
	}
	
	@Override
	public void ajustOnDragFromCenter(double posXStart, double posYStart, double posXEnd, double posYEnd) {//Fonctionne
		double startX;
		double startY;
		double cote;
		cote = Math.min(Math.abs(posXEnd-posXStart), Math.abs(posYEnd-posYStart));
		startX=posXStart-cote;
		startY=posYStart-cote;
		this.setWidth(cote*2);
		this.setXPos(startX);
		this.setYPos(startY);
	}

	@Override
	protected String getContructorName() {
		return "CustomCircle";
	}
}
