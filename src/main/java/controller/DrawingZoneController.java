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
import models.GridLayer;
import models.Layer;
import models.LayersGroup;

public class DrawingZoneController {
	
	@FXML private Pane pane;
	
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private MainApp mainApp;
	
	private Clipboard clipboard;
	private Shape shapeCopy;
	
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
		
		pane.setOnMousePressed(t -> {
			System.out.println("allo");
			orgX = t.getX();
			orgY = t.getY();
			childIndex = pane.getChildren().size();
			childIndex = this.mainApp.getTool().mousePressed(this.mainApp.getPaletteDetailController(), pane);
		});
		pane.setOnMouseDragged(t -> {
			this.mainApp.getTool().mouseDragged(orgX, orgY, t.getX(), t.getY());
		});
		pane.setOnMouseReleased(t -> {
			this.mainApp.getTool().mouseReleased(mainApp, pane, this.mainApp.getPaletteCouleurController(), this.mainApp.getPaletteDetailController());
		});
		
		GridLayer rootLayer = new GridLayer("Layer 0");
		layersGroup.createNewLayer(rootLayer);
		
		Pane pane2 = new Pane();
		pane.getChildren().add(pane2);
		
		rootLayer.setPane(pane2);
		
    }
	
	public void updateLayers(){
		pane.getChildren().clear();
		ArrayList<Layer> layers = layersGroup.getLayers();
		
		for (int i = 0; i < layers.size(); ++i) {
			Pane newPane = layers.get(i).getPane();
			if (newPane != null) {
				pane.getChildren().add(newPane);
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
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
	public void saveShape() {
		
		Shape shape = mainApp.getTool().getShape();
		
		//Node shape = mainApp.getTool().getShape();
		
		//Clipboard.getSystemClipboard();
		//ClipboardContent cc = new ClipboardContent();
		//SnapshotParameters snapshotParam = new SnapshotParameters();
		//WritableImage image = shape.snapshot(snapshotParam, null);
		
		//cc.putImage(image);
		//clipboard.setContent(cc);
		
		
	}
	
	public void pasteShape() {
		pane.getChildren().add(shapeCopy);
		
	}
}
