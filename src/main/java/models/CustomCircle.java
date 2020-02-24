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
		this.setWidth(value);
	}
	@Override
	public CustomShape duplicateAndOffset() {
		CustomCircle newCircle = new CustomCircle();
		
		return newCircle;
	}
	
	@Override
	public CustomShape duplicate() {
		CustomCircle newCircle = new CustomCircle();
		
		return newCircle;
	}
	@Override
	public void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd) {//TODO: refaire
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
		if(posXStart<posXEnd) {
			startX=posXStart-cote;
		}else {
			startX=posXEnd;
		}
		if(posYStart<posYEnd) {
			startY=posYStart-cote;
		}else {
			startY=posYEnd;
		}
		this.setWidth(cote*2);
		this.setXPos(startX);
		this.setYPos(startY);
	}

	@Override
	public String getType() {
		return "circle";
	}
}
