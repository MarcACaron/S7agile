package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import models.GridLayer;
import models.Iterator;
import models.Layer;
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

		for (Iterator iter = layerGroup.getIterator(); iter.hasNext();) {
			Shape a = (Shape)iter.next();
			shapeListView.getItems().add(new Label(a.getId()));
		}

	}

}
