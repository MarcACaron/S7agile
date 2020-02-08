package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class LayersGroup {
	
	private static LayersGroup instance = new LayersGroup();
	
	public static LayersGroup getLayersGroup( ) {
		return instance;
	}
	
	private ArrayList<Layer> layers = new ArrayList<Layer>();
	
	public Layer getCurrentLayer() {
		return layers.get(0);
	}
	
	public LayersGroup() {
		//constructor
	}
	
	public ArrayList<Layer> getLayers() {
		return layers;
	}
	
	public void createNewLayer(Layer layer) {
		layers.add(layer);
	}
	
	public void deleteLayer(Layer layer) {
		layers.remove(layer);
	}
	
	public int downList(Layer layer) {
		
		if (layers.indexOf(layer) != layers.size() - 1) {
			Collections.swap(layers, layers.indexOf(layer), layers.indexOf(layer) + 1);
		}
		
		return layers.indexOf(layer);
	}
	
	public int upList(Layer layer) {
		
		if (layers.indexOf(layer) != 0) {
			Collections.swap(layers, layers.indexOf(layer), layers.indexOf(layer) - 1);
		}
		
		return layers.indexOf(layer);
	}

}
