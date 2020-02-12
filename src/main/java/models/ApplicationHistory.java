package models;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class ApplicationHistory {
	
	private static ApplicationHistory instance = new ApplicationHistory();
	
	ArrayList<ArrayList<Layer>> history = new ArrayList<>();
	
	ArrayList<Layer> reDoHistory = new ArrayList<>();
	
	LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	public static ApplicationHistory getInstance() {
		return instance;
	}
	
	public ApplicationHistory() {
		//Constructor
	}
	
	public List<Layer> undoHistory() {
		if (history.isEmpty() || history.size() == 1) {
			return new ArrayList<>();
		}
		
		int historyIndex = history.size() - 2;
		
		ArrayList<Layer> returnLayers = history.get(historyIndex);
		
		reDoHistory = history.get(historyIndex + 1);
		
		history.remove(historyIndex + 1);
		
		return returnLayers;
	}
	
	public List<Layer> redoHistory() {
		
		if (reDoHistory.size() > 0) {
			ArrayList<Layer> returnLayers = reDoHistory;
		
			reDoHistory = new ArrayList<>();
			
			return returnLayers;
		}
		
		return new ArrayList<>();
	}
	
	public void update() {
		
		ArrayList<Layer> newLayers = (ArrayList<Layer>)layersGroup.getLayers();
		ArrayList<Layer> newHistoryLayers = new ArrayList<>();
		
		for (int i = 0; i < newLayers.size(); ++i) {
			Layer layer = newLayers.get(i);
			
			ObservableList<Node> paneChilds = layer.getPane().getChildrenUnmodifiable();
			Pane newPane = new Pane();
			
			for (int j = 0; j < paneChilds.size(); ++j) {
				Transformable shape = (Transformable)paneChilds.get(j);
				Shape newShape = shape.duplicate();
				newPane.getChildren().add(newShape);
			}
			
			Layer newLayer = new GridLayer("Layer " + i);
			newLayer.setPane(newPane);
			
			newHistoryLayers.add(newLayer);
		}
		
		history.add(newHistoryLayers);
	}

}
