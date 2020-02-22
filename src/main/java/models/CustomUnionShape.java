package models;

import java.util.ArrayList;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CustomUnionShape extends CustomShape {
	ArrayList<CustomShape> listOfShape;
	public CustomUnionShape(ArrayList<CustomShape> listOfShape) {
		this.boundingBox = new Rectangle();
		this.listOfShape = listOfShape;
	}

	public CustomUnionShape() {
		this.boundingBox = new Rectangle();
		this.listOfShape = new ArrayList<CustomShape>();
	}

	@Override
	public void setXPos(double value) {
		// On ne fait rien, on ne sait pas à laquelle il faut le faire
	}

	@Override
	public void setYPos(double value) {
		// On ne fait rien, on ne sait pas à laquelle il faut le faire
	}

	@Override
	public void setWidth(double value) {
		// On ne fait rien, on ne sait pas à laquelle il faut le faire
	}

	@Override
	public void setHeight(double value) {
		// On ne fait rien, on ne sait pas à laquelle il faut le faire
	}
	
	@Override
	public void setFill(Paint value, String fillName) {
		listOfShape.forEach(sh -> {
			sh.setFill(value, fillName);
		});
	}
	
	@Override
	public void setRotate(double value) {
		listOfShape.forEach(sh -> {
			sh.setRotate(value);
		});
	}
	
	@Override
	public void setStroke(Paint value) {
		listOfShape.forEach(sh -> {
			sh.setStroke(value);
		});
	}
	
	@Override
	public void setStrokeWidth(double strokeWidth) {
		listOfShape.forEach(sh -> {
			sh.setStrokeWidth(strokeWidth);
		});
	} 
	
	@Override
	public String getType() {
		return null;
	}

	@Override
	public CustomShape duplicateAndOffset() {
		return null;
	}

	@Override
	public CustomShape duplicate() {
		return null;
	}

	public void add(CustomShape customShape) {
		this.listOfShape.add(customShape);
		updateBoudingBox();
	}
	
	private void updateBoudingBox() {
		double xMin = this.listOfShape.get(0).getXPos();
		double yMin = this.listOfShape.get(0).getXPos();
		double xMax = this.listOfShape.get(0).getXPos();
		double yMax = this.listOfShape.get(0).getXPos();
		
	}
}
