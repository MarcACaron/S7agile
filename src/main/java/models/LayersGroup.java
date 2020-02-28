package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


public class LayersGroup implements Container {

	private List<Layer> layers;

	private static LayersGroup instance = new LayersGroup();
	
	public static LayersGroup getLayersGroup( ) {
		return instance;
	}


	public Layer getCurrentLayer() {
		return layers.get(0);
	}

	public int size() {
		return layers.size();
	}

	public void reset() {
		layers = new ArrayList<>();

		GridLayer rootLayer = new GridLayer("Layer 0");
		createNewLayer(rootLayer);

		Pane pane2 = new Pane();

		rootLayer.setPane(pane2);
	}

	public void clear() {
		layers = new ArrayList<>();
	}

	public LayersGroup() {
		layers = new ArrayList<>();

		GridLayer rootLayer = new GridLayer("Layer 0");
		createNewLayer(rootLayer);

		Pane pane2 = new Pane();

		rootLayer.setPane(pane2);
	}

	public List<Layer> getLayers() {
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

	public boolean downShapeList(int shapeIndex) {
		int shapeIndexTemp = shapeIndex;
		int layerIndex=0;
		while(shapeIndexTemp-LayersGroup.getLayersGroup().getLayers().get(layerIndex).getDrawnShapes().size()>=0) {
			shapeIndexTemp = shapeIndexTemp-LayersGroup.getLayersGroup().getLayers().get(layerIndex).getDrawnShapes().size();
			layerIndex++;
		}

		ObservableList<Node> a = layers.get(layerIndex).getPane().getChildren();
		if (shapeIndexTemp < a.size() - 1) {

			int index1 = layers.get(layerIndex).getDrawnShapes().size()-shapeIndexTemp-1;
			int index2 = layers.get(layerIndex).getDrawnShapes().size()-shapeIndexTemp-2;
			int collectionLength2 = layers.get(layerIndex).getDrawnShapes().get(index2).size();
			
			layers.get(layerIndex).getDrawnShapes().get(index1).down(collectionLength2, index1, a);
			Collections.swap(LayersGroup.getLayersGroup().getLayers().get(layerIndex).getDrawnShapes(), index1, index2);

			return shapeIndexTemp + 1<layers.get(layerIndex).getDrawnShapes().size();
		}

		return false;
	}

	public boolean upShapeList(int shapeIndex) {
		
		int shapeIndexTemp = shapeIndex;
		int layerIndex=0;
		while(shapeIndexTemp-layers.get(layerIndex).getDrawnShapes().size()>=0) {
			shapeIndexTemp = shapeIndexTemp-layers.get(layerIndex).getDrawnShapes().size();
			layerIndex++;
		}

		ObservableList<Node> a = layers.get(layerIndex).getPane().getChildren();
		if (shapeIndexTemp != 0) {

			int index1 = layers.get(layerIndex).getDrawnShapes().size()-shapeIndexTemp-1;
			int index2 = layers.get(layerIndex).getDrawnShapes().size()-shapeIndexTemp;
			int collectionLength2 = layers.get(layerIndex).getDrawnShapes().get(index2).size();
			
			layers.get(layerIndex).getDrawnShapes().get(index1).up(collectionLength2, index1, a);
			Collections.swap(layers.get(layerIndex).getDrawnShapes(), index1, index2);
			
			return shapeIndexTemp - 1>=0;
		}

		return false;
	}

	public void replaceLayers(List<Layer> newLayers) {
		layers = newLayers;
	}

	public Iterator getIterator() {
		return new ShapeIterator();
	}

	private class ShapeIterator implements Iterator {

		private int index = layers.get(0).getPane().getChildren().size()-1;

		public int getIndex() {
			return this.index;
		}

		@Override
		public boolean hasNext() {

			Boolean a = false;

			if(index >= 0 ){
				a = true;
			}
			return a;
		}

		@Override
		public Object next() {

			if(this.hasNext()){
				return layers.get(0).getPane().getChildren().get(index--);
			}
			return null;
		}		
	}

}
