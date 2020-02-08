package models;

import javafx.scene.layout.Pane;

public abstract class Layer {
	
	protected String layerId;
	
	protected Pane pane = new Pane();

	public abstract void setVisibility(Boolean visibility);
	
	public Pane getPane() {
		return pane;
	}
	
	public abstract void setPane(Pane pane);
	
	public String getId() {
		return layerId;
	}
	
}
