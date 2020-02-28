package models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;

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
		
		if (!reDoHistory.isEmpty()) {
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
			
			Pane newPane = new Pane();
			
			Layer newLayer = new GridLayer("Layer " + i);
			newLayer.setPane(newPane);
			
			newHistoryLayers.add(newLayer);
		}
		
		history.add(newHistoryLayers);
	}

}
