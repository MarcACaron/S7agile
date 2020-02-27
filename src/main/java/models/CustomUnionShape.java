package models;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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
		this.listOfShape = new ArrayList<CustomShape>();
		this.listOfShape.forEach(customShape->{
			if(customShape.isScale()) {
				this.scale=true;
			}
		});
		this.type = "CustomShape";
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
			System.out.println("hhhh");
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
	public CustomShape duplicate(int offsetX, int offsetY) {
		return null;
	}

	public void add(CustomShape customShape) {
		this.listOfShape.add(customShape);
		if(customShape.isScale()) {
			scale=true;
		}
	}
	
	public boolean updateBoudingBox() {//TODO: Conserver pour la suite
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
	
	public void group(String name) {
		listOfShape.forEach(sh->{
			int index = LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().indexOf(sh);
			LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().remove(index);
			LayersGroup.getLayersGroup().getCurrentLayer().getPane().getChildren().remove(index);
			LayersGroup.getLayersGroup().getCurrentLayer().getPane().getChildren().add(sh.getDraw());
		});
		LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().add(this);
		setLayer(LayersGroup.getLayersGroup().getCurrentLayer().getId());
	}
	@Override
	public void ajustOnDragFromCorner(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajustOnDragFromCenter(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setType(String type) {
		super.setType(type);
		group(type);
	}
	
	@Override
	public int size() {
		return this.listOfShape.size();
	}
	@Override
	public void up(int collectionLength2, int index1, ObservableList<Node> a) {
		for(int i=0; i<size(); i++) {
			listOfShape.get(i).up(collectionLength2, index1+i+listOfShape.size()-1, a);
		}	
	}
	
	@Override
	public void down(int collectionLength2, int index1, ObservableList<Node> a) {
		for(int i=0; i<size(); i++) {
			listOfShape.get(i).down(collectionLength2, index1+i, a);
		}	
	}
	
	@Override
	public String toString() {
		return "L: "+this.getLayer()+"; ShapeGrp: "+this.getType();
	}
}
