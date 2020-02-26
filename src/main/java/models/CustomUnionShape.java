package models;

import java.util.ArrayList;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CustomUnionShape extends CustomShape {
	ArrayList<CustomShape> listOfShape;
	public CustomUnionShape(ArrayList<CustomShape> listOfShape) {
		this.boundingBox = new Rectangle();
		this.listOfShape = listOfShape;
	}

	public CustomUnionShape() {
		this.boundingBox = new Rectangle();
		this.listOfShape = new ArrayList<>();
	}

	@Override
	public void setShapeXPos(double value) {
		// On ne fait rien, on ne sait pas à laquelle il faut le faire
	}

	@Override
	public void setShapeYPos(double value) {
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
		listOfShape.forEach(sh -> 
			sh.setFill(value, fillName)
		);
	}
	
	@Override
	public void setRotate(double value) {
		listOfShape.forEach(sh -> 
			sh.setRotate(value)
		);
	}
	
	@Override
	public void setStroke(Paint value) {
		listOfShape.forEach(sh -> 
			sh.setStroke(value)
		);
	}
	
	@Override
	public void setStrokeWidth(double strokeWidth) {
		listOfShape.forEach(sh -> 
			sh.setStrokeWidth(strokeWidth)
		);
	} 
	
	@Override
	public String getType() {
		return null;
	}

	@Override
	public CustomShape duplicate(int offsetX, int offsetY) {
		return null;
	}

	public void add(CustomShape customShape) {
		this.listOfShape.add(customShape);
		updateBoudingBox();
	}
	
	private void updateBoudingBox() {//TODO: Conserver pour la suite
		double xMin = this.listOfShape.get(0).getXPos();
		double yMin = this.listOfShape.get(0).getXPos();
		double xMax = this.listOfShape.get(0).getXPos();
		double yMax = this.listOfShape.get(0).getXPos();
		
	}

	@Override
	public void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajustOnDragFromCenter(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		// TODO Auto-generated method stub
		
	}
}
