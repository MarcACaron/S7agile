package models;

import java.util.ArrayList;

public class ApplicationHistory {
	
	ArrayList<ArrayList<Layer>> history = new ArrayList<ArrayList<Layer>>();
	
	ArrayList<Layer> reDoHistory = new ArrayList<Layer>();
	
	public ApplicationHistory(Layer initialLayer) {
		
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
		ArrayList<Layer> returnLayers = reDoHistory;
		
		history.add(returnLayers);
		reDoHistory.clear();
		
		return returnLayers;
	}
	
	public void addHistory(ArrayList<Layer> newHistory) {
		history.add(newHistory);
		reDoHistory.clear();
	}

}
