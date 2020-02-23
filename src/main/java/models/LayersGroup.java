package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		
		if (shapeIndex != layers.get(0).getPane().getChildren().size() - 1) {
			Collections.swap(layers.get(0).getPane().getChildren(), shapeIndex, shapeIndex + 1);
		}
		
		return shapeIndex + 1;
	}
	
	public int upShapeList(int shapeIndex) {
		
		if (shapeIndex != layers.get(0).getPane().getChildren().size()) {
			Node shape = layers.get(0).getPane().getChildren().get(shapeIndex);
			//(Shape)shape.setId
			layers.get(0).getPane().getChildren().remove(shape);
			layers.get(0).getPane().getChildren().add(shapeIndex - 1, shape);
			//Collections.swap(layers.get(0).getPane().getChildren(), shapeIndex, shapeIndex - 1);
		}
		
		return shapeIndex - 1;
	}
	
	public void replaceLayers(List<Layer> newLayers) {
		layers = newLayers;
	}
	
	public Iterator getIterator() {
		return new ShapeIterator();
	}
	
	private class ShapeIterator implements Iterator {

	      private int index;
	      
	      public int getIndex() {
	    	  return this.index;
	      }

	      @Override
	      public boolean hasNext() {
	      
	         if(index < layers.get(0).getPane().getChildren().size() ){
	            return true;
	         }
	         return false;
	      }

	      @Override
	      public Object next() {
	      
	         if(this.hasNext()){
	            return layers.get(0).getPane().getChildren().get(index++);
	         }
	         return null;
	      }		
	   }

}
