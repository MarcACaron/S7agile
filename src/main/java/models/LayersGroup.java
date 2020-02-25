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

	public int downShapeList(int shapeIndex) {

		ObservableList<Node> a = layers.get(0).getPane().getChildren();

		if (shapeIndex < a.size() - 1) {

			int realIndex = a.size() - 1 - shapeIndex;
			Node shape = a.get(realIndex);
			a.remove(shape);
			a.add(realIndex - 1, shape);

			return shapeIndex + 1;
		}

		return -1;
	}

	public int upShapeList(int shapeIndex) {

		ObservableList<Node> a = layers.get(0).getPane().getChildren();

		if (shapeIndex != 0) {

			int realIndex = a.size() - 1 - shapeIndex;
			Node shape = a.get(realIndex);
			a.remove(shape);
			a.add(realIndex + 1, shape);

			return shapeIndex - 1;
		}

		return -1;
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
