package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.CustomShape;
import models.DrawnShapes;
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
			
			int newIndex = layerGroup.upShapeList(shapeListView.getSelectionModel().getSelectedIndex());
			updateList();
			
			if( newIndex != -1 ) {
				shapeListView.getSelectionModel().select(newIndex);
			}


		});

		downButton.setOnAction(t -> {
			
			if ( shapeListView.getSelectionModel().getSelectedIndex() == -1 ) {
				return;
			}
			
			int newIndex = layerGroup.downShapeList(shapeListView.getSelectionModel().getSelectedIndex());
			updateList();
			
			if( newIndex != -1 ) {
				shapeListView.getSelectionModel().select(newIndex);
			}
			
		});


		updateList();

	}

	private void updateList() {
		shapeListView.getItems().clear();
		ArrayList<CustomShape> drawnShapes = DrawnShapes.getDrawnShapes();
		/*
		for (Iterator iter = layerGroup.getIterator(); iter.hasNext();) {
			Shape a = (Shape)iter.next();
			shapeListView.getItems().add(new Label(a.getId()));
		}*/
		for(int index = drawnShapes.size()-1; index>=0;index--) {
			
			shapeListView.getItems().add(new Label("L: "+drawnShapes.get(index).getLayer()+"; "+drawnShapes.get(index).getDraw().getId()+": "+drawnShapes.get(index).getType()));
		}
		/*for (java.util.Iterator<CustomShape> iter = drawnShapes.iterator(); iter.hasNext();) {
			CustomShape a = (CustomShape)iter.next();
			shapeListView.getItems().add(new Label("L: "+a.getLayer()+"; "+a.getDraw().getId()+": "+a.getType()));
		}*/

	}

}
