package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import models.GridLayer;
import models.Layer;
import models.LayersGroup;

public class LayersController {
	
	@FXML private Button downButton;
	@FXML private Button upButton;
	
	@FXML private Button newButton;
	@FXML private Button deleteButton;
	
	@FXML private Button hideButton;
	
	@FXML private ListView<Label> layerListView = new ListView<Label>();
	
	LayersGroup layerGroup = LayersGroup.getLayersGroup();
	
	@FXML
    private void initialize() {
		
		newButton.setOnAction(t -> {
			Layer a = new GridLayer("Layer " + layerGroup.getLayers().size());
			layerGroup.createNewLayer(a);
			updateList();
			
		});
		
		deleteButton.setOnAction(t -> {
			for (int i = 0; i < layerGroup.getLayers().size(); ++i) {
				if (layerListView.getSelectionModel().getSelectedItem() != null && layerGroup.getLayers().get(i).getId().equals(layerListView.getSelectionModel().getSelectedItem().getText())) {
					layerGroup.deleteLayer(layerGroup.getLayers().get(i));
					
					Pane selectedPane = layerGroup.getLayers().get(i).getPane();
					selectedPane.getChildren().clear();
					updateList();
					break;
				}
			}
			
			layerListView.getItems().remove(layerListView.getSelectionModel().getSelectedItem());
		});
		
		upButton.setOnAction(t -> {
			String current = layerListView.getSelectionModel().getSelectedItem().getText();
			
			for (int i = 0; i < layerGroup.getLayers().size(); ++i) {
				if (layerGroup.getLayers().get(i).getId().equals(current)) {
					int newIndex = layerGroup.upList(layerGroup.getLayers().get(i));
					updateList();
					layerListView.getSelectionModel().select(newIndex);

					break;
				}
			}

		});
		
		downButton.setOnAction(t -> {
			Label currentLabel = layerListView.getSelectionModel().getSelectedItem();
			
			for (int i = 0; i < layerGroup.getLayers().size(); ++i) {
				if (currentLabel == null && layerGroup.getLayers().get(i).getId().equals(currentLabel.getText())) {
					int newIndex = layerGroup.downList(layerGroup.getLayers().get(i));
					updateList();
					layerListView.getSelectionModel().select(newIndex);

					break;
				}
			}

		});
		
		hideButton.setOnAction(t -> {
			Label currentLabel = layerListView.getSelectionModel().getSelectedItem();
			currentLabel.setDisable(!currentLabel.isDisabled());
			
			
			for (int i = 0; i < layerGroup.getLayers().size(); ++i) {
				
				if (layerGroup.getLayers().get(i).getId().equals(currentLabel.getText())) {
					Pane selectedPane = layerGroup.getLayers().get(i).getPane();
					selectedPane.setVisible(!selectedPane.isVisible());
					
					
					break;
				}
			}
			
		});
		
		updateList();
		
    }
	
	private void updateList() {
		layerListView.getItems().clear();
		for (int i = 0; i < layerGroup.getLayers().size(); ++i) {
			Layer a = layerGroup.getLayers().get(i);
			
			layerListView.getItems().add(new Label(a.getId()));
		}
	}

}
