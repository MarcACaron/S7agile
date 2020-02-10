package controller;



import java.util.ArrayList;

import adraw4us.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import models.ApplicationHistory;
import models.GridLayer;
import models.Layer;
import models.LayersGroup;

public class DrawingZoneController {
	
	//@FXML private Pane pane;
	
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private MainApp mainApp;
	
	ApplicationHistory history = ApplicationHistory.getInstance();
	
	LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	ArrayList<Pane> paneList = new ArrayList<Pane>();
	
	double orgX;
	double orgY;
	int childIndex;
	
	public void zoomIn(double zoom) {
        Scale scaleTransform = new Scale(zoom, zoom, 0, 0);
        anchorPane.getTransforms().add(scaleTransform);
    }
	
	public void zoomOut(double zoom) {
        Scale scaleTransform = new Scale(1/zoom, 1/zoom, 0, 0);
        anchorPane.getTransforms().add(scaleTransform);
    }

	@FXML
    private void initialize() {
		
		anchorPane.setOnMousePressed(t -> {
			System.out.println("allo");
			orgX = t.getX();
			orgY = t.getY();
			childIndex = anchorPane.getChildren().size();
			childIndex = this.mainApp.getTool().mousePressed(this.mainApp.getPaletteDetailController(), anchorPane);
		});
		anchorPane.setOnMouseDragged(t -> {
			this.mainApp.getTool().mouseDragged(orgX, orgY, t.getX(), t.getY());
		});
		anchorPane.setOnMouseReleased(t -> {
			this.mainApp.getTool().mouseReleased(mainApp, anchorPane, this.mainApp.getPaletteCouleurController(), this.mainApp.getPaletteDetailController());
		});
		
		GridLayer rootLayer = new GridLayer("Layer 0");
		layersGroup.createNewLayer(rootLayer);
		
		Pane pane2 = new Pane();
		anchorPane.getChildren().add(pane2);
		
		rootLayer.setPane(pane2);
    }
	
	public void updateLayers(){
		anchorPane.getChildren().clear();
		ArrayList<Layer> layers = layersGroup.getLayers();
		System.out.println("hkjhlmj"+layersGroup.size());
		
		for (int i = layers.size() - 1; i >= 0; --i) {
			Pane newPane = layers.get(i).getPane();
			
			if (newPane != null) {
				anchorPane.getChildren().add(newPane);
				
				AnchorPane.setBottomAnchor(newPane, 0.0);
				AnchorPane.setLeftAnchor(newPane, 0.0);
				AnchorPane.setRightAnchor(newPane, 0.0);
				AnchorPane.setTopAnchor(newPane, 0.0);
				
				layers.get(i).setPane(newPane);
			}
			
		}
		history.update();
	}
	
	public void clearDrawing() {
		
		ObservableList<Node> paneList = anchorPane.getChildren();
		
		for (int i = 0; i < paneList.size(); i++) {
			((Pane)(paneList.get(i))).getChildren().clear();
		}
	}
	
	public Pane getDrawingsAsOne() {
		ObservableList<Node> paneList = anchorPane.getChildren();
		Pane globalPane = new Pane();
		
		for (int i = 0; i < paneList.size(); i++) {
			globalPane.getChildren().addAll(((Pane)(paneList.get(i))).getChildren());
		}
		
		return globalPane;
	}
	
	public ObservableList<Node> getLayers(){
		return anchorPane.getChildren();
	}
	
	public void applyToCurrentPane(Shape shape) {
		Pane currentPane = layersGroup.getCurrentLayer().getPane();
		
		currentPane.getChildren().add(shape);
		layersGroup.getCurrentLayer().setPane(currentPane);

		updateLayers();
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
