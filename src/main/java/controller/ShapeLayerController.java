package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.CustomShape;
import models.LayersGroup;

public class ShapeLayerController {

	@FXML private Button downButton;
	@FXML private Button upButton;

	@FXML private ListView<Label> shapeListView = new ListView<>();

	LayersGroup layerGroup = LayersGroup.getLayersGroup();

	@FXML
	private void initialize() {

		upButton.setOnAction(t -> {
			
			if ( shapeListView.getSelectionModel().getSelectedIndex() == -1 ) {
				return;
			}

			int index = shapeListView.getSelectionModel().getSelectedIndex();
			boolean hasPrevious = layerGroup.upShapeList(index);
			updateList();
			
			if(hasPrevious) {
				shapeListView.getSelectionModel().select(index-1);
				System.out.println("gneu");
			}else {
				shapeListView.getSelectionModel().select(index);
			}


		});

		downButton.setOnAction(t -> {
			
			if ( shapeListView.getSelectionModel().getSelectedIndex() == -1 ) {
				return;
			}
			int index = shapeListView.getSelectionModel().getSelectedIndex();
			boolean hasNext = layerGroup.downShapeList(index);
			updateList();
			
			if( hasNext ) {
				shapeListView.getSelectionModel().select(index+1);
			}else {
				shapeListView.getSelectionModel().select(index);
			}
			
		});


		updateList();

	}

	private void updateList() {
		shapeListView.getItems().clear();
		
		/*
		for (Iterator iter = layerGroup.getIterator(); iter.hasNext();) {
			Shape a = (Shape)iter.next();
			shapeListView.getItems().add(new Label(a.getId()));
		}*/
		for(int layerIndex = 0; layerIndex<LayersGroup.getLayersGroup().size(); layerIndex++) { //TODO: a faire
			ArrayList<CustomShape> drawnShapes = LayersGroup.getLayersGroup().getLayers().get(layerIndex).getDrawnShapes();
			for(int index = drawnShapes.size()-1; index>=0;index--) {
				
				shapeListView.getItems().add(new Label("L: "+drawnShapes.get(index).getLayer()+"; "+drawnShapes.get(index).getDraw().getId()+": "+drawnShapes.get(index).getType()));
			}
		}

	}

}
