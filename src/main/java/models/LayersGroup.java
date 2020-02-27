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

	public boolean downShapeList(int shapeIndex_) {

		
		int shapeIndex = shapeIndex_;
		int layerIndex=0;
		while(shapeIndex-LayersGroup.getLayersGroup().getLayers().get(layerIndex).getDrawnShapes().size()>=0) {
			shapeIndex = shapeIndex-LayersGroup.getLayersGroup().getLayers().get(layerIndex).getDrawnShapes().size();
			layerIndex++;
		}

		ObservableList<Node> a = layers.get(layerIndex).getPane().getChildren();
		if (shapeIndex < a.size() - 1) {

			int realIndex = a.size() - 1 - shapeIndex;
			Node shape = a.get(realIndex);
			a.remove(shape);
			a.add(realIndex - 1, shape);
			
			int index = LayersGroup.getLayersGroup().getLayers().get(layerIndex).getDrawnShapes().size()-shapeIndex-1;
			Collections.swap(LayersGroup.getLayersGroup().getLayers().get(layerIndex).getDrawnShapes(), index, index-1);

			return shapeIndex + 1<layers.get(layerIndex).getDrawnShapes().size();
		}

		return false;
	}

	public boolean upShapeList(int shapeIndex_) {
		
		int shapeIndex = shapeIndex_;
		int layerIndex=0;
		while(shapeIndex-layers.get(layerIndex).getDrawnShapes().size()>=0) {
			shapeIndex = shapeIndex-layers.get(layerIndex).getDrawnShapes().size();
			layerIndex++;
		}

		ObservableList<Node> a = layers.get(layerIndex).getPane().getChildren();
		if (shapeIndex != 0) {

			int realIndex = a.size() - 1 - shapeIndex;
			Node shape = a.get(realIndex);
			a.remove(shape);
			a.add(realIndex + 1, shape);
			int index1 = layers.get(layerIndex).getDrawnShapes().size()-shapeIndex-1;
			int index2 = layers.get(layerIndex).getDrawnShapes().size()-shapeIndex;
			int collectionLength2 = layers.get(layerIndex).getDrawnShapes().get(index2).size();
			//LayersGroup.getLayersGroup().getDrawnShapes().get(index1).up(collectionLength2, index1);
			ArrayList<CustomShape> buffer = layers.get(layerIndex).getDrawnShapes();
			Collections.swap(buffer, index1, index2);
			
			return shapeIndex - 1>=0;
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
