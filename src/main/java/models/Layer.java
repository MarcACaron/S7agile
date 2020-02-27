package models;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

public abstract class Layer {
	
	protected String layerId;
	
	protected Pane pane = new Pane();
	
	private ArrayList<CustomShape> drawnShapes = new ArrayList<CustomShape>();
	
	public ArrayList<CustomShape> getDrawnShapes() {
		if(drawnShapes==null)
			drawnShapes = new ArrayList<CustomShape>();
		return drawnShapes;
	}
	public abstract void setVisibility(Boolean visibility);
	
	public Pane getPane() {
		return pane;
	}
	
	public abstract void setPane(Pane pane);
	
	public String getId() {
		return layerId;
	}
}
