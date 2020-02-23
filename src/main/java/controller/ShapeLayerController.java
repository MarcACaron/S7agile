package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
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
			String current = shapeListView.getSelectionModel().getSelectedItem().getText();
			
			for (Iterator iter = layerGroup.getIterator(); iter.hasNext(); iter.next()) {
				
				if (current != null && current.contains(String.valueOf(iter.getIndex())) ) {
					int newIndex = layerGroup.upShapeList(iter.getIndex());
					updateList();
					shapeListView.getSelectionModel().select(newIndex);

					break;
				}
			}

		});
		
		downButton.setOnAction(t -> {
			String current = shapeListView.getSelectionModel().getSelectedItem().getText();
			
			for (Iterator iter = layerGroup.getIterator(); iter.hasNext(); iter.next()) {
				
				if (current != null && current.contains(String.valueOf(iter.getIndex())) ) {
					int newIndex = layerGroup.downShapeList(iter.getIndex());
					updateList();
					shapeListView.getSelectionModel().select(newIndex);

					break;
				}
			}

		});
		

		updateList();
		
    }
	
	private void updateList() {
		shapeListView.getItems().clear();
		
		for (Iterator iter = layerGroup.getIterator(); iter.hasNext();) {
			shapeListView.getItems().add(new Label("shape" + iter.getIndex()));
			iter.next();
		}

	}

}
