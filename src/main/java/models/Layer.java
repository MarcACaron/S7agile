package models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

public abstract class Layer {
	
	protected String layerId;
	
	protected Pane pane = new Pane();
	
	private ArrayList<CustomShape> drawnShapes = new ArrayList<>();
	
	public List<CustomShape> getDrawnShapes() {
		if(drawnShapes==null)
			drawnShapes = new ArrayList<>();
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
