package controller;



import java.util.ArrayList;

import adraw4us.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import models.CustomRectangle;
import models.ApplicationHistory;
import models.GridLayer;
import models.Layer;
import models.LayersGroup;
import models.Transformable;

public class DrawingZoneController {
	
	//@FXML private Pane pane;
	
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private MainApp mainApp;
	
	private Clipboard clipboard;
	private Transformable shapeCopy;
	ApplicationHistory history;
	
	LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	ArrayList<Pane> paneList = new ArrayList<Pane>();
	
	double orgX;
	double orgY;
	int childIndex;
	
	public void zoomIn(double zoom) {
        Scale scaleTransform = new Scale(zoom, zoom, 0, 0);
        //pane.getTransforms().add(scaleTransform);
    }
	
	public void zoomOut(double zoom) {
        Scale scaleTransform = new Scale(1/zoom, 1/zoom, 0, 0);
        //pane.getTransforms().add(scaleTransform);
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
		
		history = new ApplicationHistory(rootLayer);
    }
	
	public void updateLayers(){
		anchorPane.getChildren().clear();
		ArrayList<Layer> layers = layersGroup.getLayers();
		System.out.println("hkjhlmj"+layersGroup.size());
		
		for (int i = layers.size() - 1; i >= 0; --i) {
			Pane newPane = layers.get(i).getPane();
			if (newPane != null) {
				System.out.println("NewPane added to anchorPane size : " + newPane.getChildren().size());
				anchorPane.getChildren().add(newPane);
				
				AnchorPane.setBottomAnchor(newPane, 0.0);
				AnchorPane.setLeftAnchor(newPane, 0.0);
				AnchorPane.setRightAnchor(newPane, 0.0);
				AnchorPane.setTopAnchor(newPane, 0.0);
				
				layers.get(i).setPane(newPane);
			}
			else {
				System.out.println("Pane was Null");
			}
			
		}
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
	
	public void saveShape() {
		
		System.out.println("Copying");	
		shapeCopy = (Transformable)mainApp.getTool().getShape();

	}
	
	public void pasteShape() {
		if (shapeCopy != null) {
			System.out.println("Pasting");
			anchorPane.getChildren().add(shapeCopy.duplicate());
		}
		
	}
}
