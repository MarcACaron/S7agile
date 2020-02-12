package models;

import java.util.ArrayList;


import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class ApplicationHistory {
	
	private static ApplicationHistory instance = new ApplicationHistory();
	
	ArrayList<ArrayList<Layer>> history = new ArrayList<ArrayList<Layer>>();
	
	ArrayList<Layer> reDoHistory = new ArrayList<Layer>();
	
	LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	public static ApplicationHistory getInstance() {
		return instance;
	}
	
	public ApplicationHistory() {
		//Constructor
	}
	
	public ArrayList<Layer> undoHistory() {
		if (history.size() == 0 || history.size() == 1) {
			return null;
		}
		
		int historyIndex = history.size() - 2;
		
		ArrayList<Layer> returnLayers = history.get(historyIndex);
		
		reDoHistory = history.get(historyIndex + 1);
		
		history.remove(historyIndex + 1);
		
		return returnLayers;
	}
	
	public ArrayList<Layer> redoHistory() {
		
		if (reDoHistory.size() > 0) {
			ArrayList<Layer> returnLayers = reDoHistory;
			
			//history.add(returnLayers);
			reDoHistory = new ArrayList<Layer>();
			
			return returnLayers;
		}
		
		return null;
	}
	
	private void addHistory(ArrayList<Layer> newHistory) {
		history.add(new ArrayList<Layer>(newHistory));
		reDoHistory = new ArrayList<Layer>();
	}
	
	public void update() {
		
		ArrayList<Layer> newLayers = layersGroup.getLayers();
		ArrayList<Layer> newHistoryLayers = new ArrayList<Layer>();
		
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
