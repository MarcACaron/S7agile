package models;

import java.util.ArrayList;

import adraw4us.MainApp;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CustomUnionShape extends CustomShape {
	ArrayList<CustomShape> listOfShape;
	public CustomUnionShape(ArrayList<CustomShape> listOfShape) {
		this.boundingBox = new Rectangle();
		this.listOfShape = listOfShape;
		scale = false;
		this.listOfShape.forEach(customShape->{
			if(customShape.isScale()) {
				this.scale=true;
			}
		});
		this.type = "CustomShape";
	}

	public CustomUnionShape() {
		this.boundingBox = new Rectangle();
		this.listOfShape = new ArrayList<>();
		this.listOfShape.forEach(customShape->{
			if(customShape.isScale()) {
				this.scale=true;
			}
		});
		this.type = "CustomUnionShape";
	}

	@Override
	public void setXPos(double value) {
		double deplacement= value-this.boundingBox.getX();
		listOfShape.forEach(sh -> {
			sh.setXPos(deplacement+sh.getXPos());
		});
		this.boundingBox.setX(value);
	}

	@Override
	public void setYPos(double value) {
		double deplacement= value-this.boundingBox.getY();
		listOfShape.forEach(sh -> {
			sh.setYPos(deplacement+sh.getYPos());
		});
		this.boundingBox.setY(value);
	}

	@Override
	public void setWidth(double value) {
		double rapportWidth = value/this.boundingBox.getWidth();
		listOfShape.forEach(sh -> {
			double rapportDeplacement = (sh.getXPos()-this.boundingBox.getX())/this.boundingBox.getWidth();
			sh.setXPos(this.boundingBox.getX() + rapportDeplacement*value);
			sh.setWidth(rapportWidth*sh.getWidth());
			
		});
		if(scale) {
			setHeight(value * getHeight()/getWidth(), true);
		}
		this.boundingBox.setWidth(value);
	}
	
	private void setWidth(double value, boolean stop) {
		double rapportWidth = value/this.boundingBox.getWidth();
		listOfShape.forEach(sh -> {
			double rapportDeplacement = (sh.getXPos()-this.boundingBox.getX())/this.boundingBox.getWidth();
			if(!sh.isScale()){
				sh.setWidth(rapportWidth*sh.getWidth());
			}
			sh.setXPos(this.boundingBox.getX() + rapportDeplacement*value);
		});
		this.boundingBox.setWidth(value);
	}

	@Override
	public void setHeight(double value) {
		double rapportWidth= value/this.boundingBox.getHeight();
		listOfShape.forEach(sh -> {
			double rapportDeplacement = (sh.getYPos()-this.boundingBox.getY())/this.boundingBox.getHeight();
			sh.setYPos(this.boundingBox.getY() + rapportDeplacement*value);
			sh.setHeight(rapportWidth*sh.getHeight());
		});
		if(scale) {
			setWidth(value * getWidth()/getHeight(), true);
		}
		this.boundingBox.setHeight(value);
	}
	
	public void setHeight(double value, boolean stop) {
		double rapportWidth= value/this.boundingBox.getHeight();
		listOfShape.forEach(sh -> {
			double rapportDeplacement = (sh.getYPos()-this.boundingBox.getY())/this.boundingBox.getHeight();
			if(!sh.isScale()){
				sh.setHeight(rapportWidth*sh.getHeight());
			}
			sh.setYPos(this.boundingBox.getY() + rapportDeplacement*value);
		});
		this.boundingBox.setHeight(value);
	}
	
	@Override
	public void setFill(Paint value, String fillName) {
		listOfShape.forEach(sh -> 
			sh.setFill(value, fillName)
		);
	}
	
	@Override
	public void setRotate(double value) {
		listOfShape.forEach(sh -> {
			double diff =  value- this.getRotate();
			double xCenter = this.getXPos()+this.getWidth()/2;
			double yCenter = this.getYPos()+this.getHeight()/2;
			double x = sh.getXPos() + sh.getWidth()/2 - xCenter;
			double y = sh.getYPos() + sh.getHeight()/2 - yCenter;
			double xTrans = x*Math.cos(Math.toRadians(diff)) + y*Math.sin(Math.toRadians(diff)) + xCenter;
			double yTrans = -x*Math.sin(Math.toRadians(diff)) + y*Math.cos(Math.toRadians(diff)) + yCenter;
			sh.setXPos(xTrans-sh.getWidth()/2);
			sh.setYPos(yTrans-sh.getHeight()/2);
			sh.setRotate(sh.getRotate()+diff);
			
		}
		);
		this.boundingBox.setRotate(value);
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
	public CustomShape duplicate(int offsetX, int offsetY, MainApp mainApp) {
		CustomUnionShape newUnion = new CustomUnionShape();
		for(int i=0; i<listOfShape.size(); i++) {
			CustomShape sh = listOfShape.get(i).duplicate(offsetX, offsetY, mainApp);
			sh.setOnMouseClicked(newUnion, mainApp);
			newUnion.add(sh);
		}
		newUnion.type=this.type;
		newUnion.updateBoudingBox();
		return newUnion;
	}

	public void add(CustomShape customShape) {
		this.listOfShape.add(customShape);
		if(customShape.isScale()) {
			scale=true;
		}
	}
	
	public boolean updateBoudingBox() {
		double xMin = 0;
		double yMin = 0;
		double xMax = 0;
		double yMax = 0;
		boolean ok = !listOfShape.isEmpty();
		if(ok) {
			xMin = this.listOfShape.get(0).getXPos();
			yMin = this.listOfShape.get(0).getYPos();
			xMax = this.listOfShape.get(0).getXPos()+this.listOfShape.get(0).getWidth();
			yMax = this.listOfShape.get(0).getYPos()+this.listOfShape.get(0).getHeight();
		}
		for(int i=0; i<listOfShape.size();i++) {
			xMin=Math.min(xMin, this.listOfShape.get(i).getXPos());	
			yMin=Math.min(yMin, this.listOfShape.get(i).getYPos());	
			xMax=Math.max(xMax, this.listOfShape.get(i).getXPos()+this.listOfShape.get(i).getWidth());	
			yMax=Math.max(yMax, this.listOfShape.get(i).getYPos()+this.listOfShape.get(i).getHeight());	
		}
		this.boundingBox.setX(xMin);
		this.boundingBox.setY(yMin);
		this.boundingBox.setWidth(xMax-xMin);
		this.boundingBox.setHeight(yMax-yMin);
		return ok;
	}
	
	public void group(String name, MainApp mainApp) {
		listOfShape.forEach(sh->{
			int index = LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().indexOf(sh);
			LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().remove(index);
			LayersGroup.getLayersGroup().getCurrentLayer().getPane().getChildren().remove(index);
			LayersGroup.getLayersGroup().getCurrentLayer().getPane().getChildren().add(sh.getDraw());
			CustomShape thisGroup = this;
			sh.setOnMouseClicked(thisGroup, mainApp);
			
			
		});
		LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().add(this);
		setLayer(LayersGroup.getLayersGroup().getCurrentLayer().getId());
	}
	@Override
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

	@Override
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
	public void setType(String type, MainApp mainApp) {
		super.setType(type, mainApp);
		group(type, mainApp);
	}
	
	@Override
	public int size() {
		return this.listOfShape.size();
	}
	@Override
	public void up(int collectionLength2, int index1, ObservableList<Node> a) {
		for(int i=0; i<size(); i++) {
			listOfShape.get(i).up(collectionLength2, index1+listOfShape.size()-1, a);
		}	
	}
	
	@Override
	public void down(int collectionLength2, int index1, ObservableList<Node> a) {
		for(int i=0; i<size(); i++) {
			listOfShape.get(i).down(collectionLength2, index1+i, a);
		}	
	}
	
	@Override
	public boolean isSelected(double xStart, double yStart, double xEnd, double yEnd) {
		boolean isSelected = listOfShape.size()>0;
		for(int i=0; i<listOfShape.size(); i++) {
			isSelected = listOfShape.get(i).isSelected(xStart, yStart, xEnd, yEnd) && isSelected;
		}
		return super.isSelected(xStart, yStart, xEnd, yEnd);
	}
	
	@Override
	public void draw(Layer layer) {
		listOfShape.forEach(sg->{
			sg.draw(layer);
		});
	}
	
	@Override
	public void setOnMouseClicked(CustomShape shapeToSelect, MainApp mainApp) {
		listOfShape.forEach(sh->{
			sh.setOnMouseClicked(shapeToSelect, mainApp);
		});
		
	}
}