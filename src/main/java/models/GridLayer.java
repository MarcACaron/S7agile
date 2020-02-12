package models;

import javafx.scene.layout.Pane;

public class GridLayer extends Layer {
	
	public GridLayer(String layerId) {
		this.layerId = layerId;
	}
	
	@Override
	public Pane getPane() {
		return pane;
	}
	
	@Override
	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
	@Override
	public void setVisibility(Boolean visibility) {
		pane.setVisible(visibility);
	}
}
