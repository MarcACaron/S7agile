package models;

import java.util.ArrayList;

public class ApplicationHistory {
	
	private static ApplicationHistory instance = new ApplicationHistory();
	
	ArrayList<ArrayList<Layer>> history = new ArrayList<ArrayList<Layer>>();
	
	ArrayList<Layer> reDoHistory = new ArrayList<Layer>();
	
	public static ApplicationHistory getInstance() {
		return instance;
	}
	
	public ApplicationHistory() {
		//Constructor
	}
	
	public ArrayList<Layer> undoHistory() {
		if (history.size() == 0) {
			return null;
		}
		
		int historyIndex = history.size() - 2;
		
		ArrayList<Layer> returnLayers = history.get(historyIndex);
		
		reDoHistory = history.get(history.size() - 1);
		
		history.remove(historyIndex + 1);
		
		return returnLayers;
	}
	
	public ArrayList<Layer> redoHistory() {
		
		if (reDoHistory.size() != 0) {
			ArrayList<Layer> returnLayers = reDoHistory;
			
			history.add(returnLayers);
			reDoHistory.clear();
			
			return returnLayers;
		}
		
		return null;
	}
	
	public void addHistory(ArrayList<Layer> newHistory) {
		history.add(newHistory);
		reDoHistory.clear();
	}
	
	public void update() {
		LayersGroup layersGroup = LayersGroup.getLayersGroup();
		
		ArrayList<Layer> newLayers = layersGroup.getLayers();
		
		addHistory(newLayers);
		
	}

}
