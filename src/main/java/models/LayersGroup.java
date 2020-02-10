package models;

import java.util.ArrayList;
import java.util.Collections;


public class LayersGroup {
	
	public static LayersGroup instance = new LayersGroup();
	
	public static LayersGroup getLayersGroup( ) {
		return instance;
	}
	
	private ArrayList<Layer> layers = new ArrayList<Layer>();
	
	public Layer getCurrentLayer() {
		return layers.get(0);
	}
	
	public int size() {
		return layers.size();
	}
	
	public void clear() {
		instance = new LayersGroup();
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
		if ( layers.size() != 1 ) {
			layers.remove(layer);
		}
		
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
	
	public void replaceLayers(ArrayList<Layer> newLayers) {
		layers = newLayers;
	}

}
