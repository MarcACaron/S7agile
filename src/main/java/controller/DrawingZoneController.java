package controller;



import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import models.CustomRectangle;
import models.ApplicationHistory;
import models.GridLayer;
import models.Layer;
import models.LayersGroup;
import models.Transformable;

public class DrawingZoneController {
		
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private GridPane gridPane;
	private MainApp mainApp;
	
	private Transformable shapeCopy;
	ApplicationHistory history = ApplicationHistory.getInstance();
	
	LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	double orgX;
	double orgY;
	int childIndex;
	boolean gridPaneBoolean;
	
	public void redo() {
		clearDrawing();
		
		layersGroup.clear();
		ArrayList<Layer> redoLayers = history.redoHistory();
		
		layersGroup.replaceLayers(redoLayers);
		updateLayers(true);
	}
	
	public void undo() {
		clearDrawing();
		layersGroup.clear();
		ArrayList<Layer> undoLayers = history.undoHistory();
		
		layersGroup.replaceLayers(undoLayers);
		updateLayers(true);
	}
	
	public void zoomIn(double zoom) {
        Scale scaleTransform = new Scale(zoom, zoom, 0, 0);
        anchorPane.getTransforms().add(scaleTransform);
    }
	
	public void zoomOut(double zoom) {
        Scale scaleTransform = new Scale(1/zoom, 1/zoom, 0, 0);
        anchorPane.getTransforms().add(scaleTransform);
    }

	public void inverseGridPaneVisibility() {
		boolean visState = getLineGridPaneVisibility(); //Boolean qui set la visibilit� des lines

		gridPane.setGridLinesVisible(!visState);
	}
	
	public boolean getLineGridPaneVisibility() {
		gridPaneBoolean = gridPane.isGridLinesVisible();
		return gridPaneBoolean;
	}
	
	@FXML
    private void initialize() {
		
		anchorPane.setOnMousePressed(t -> {
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
		updateLayers(true);
    }
	
	public void updateLayers(Boolean saveHistory) {
		ArrayList<Layer> layers = layersGroup.getLayers();
		
		for (int i = layers.size() - 1; i >= 0; --i) {
			Pane newPane = layers.get(i).getPane();
			
			if (newPane != null && !anchorPane.getChildrenUnmodifiable().contains(newPane)) {
				anchorPane.getChildren().add(newPane);
				
				AnchorPane.setBottomAnchor(newPane, 0.0);
				AnchorPane.setLeftAnchor(newPane, 0.0);
				AnchorPane.setRightAnchor(newPane, 0.0);
				AnchorPane.setTopAnchor(newPane, 0.0);
				
				layers.get(i).setPane(newPane);
			}
			else if (anchorPane.getChildrenUnmodifiable().contains(newPane)) {
				anchorPane.getChildren().remove(newPane);
				
				anchorPane.getChildren().add(newPane);
				
				AnchorPane.setBottomAnchor(newPane, 0.0);
				AnchorPane.setLeftAnchor(newPane, 0.0);
				AnchorPane.setRightAnchor(newPane, 0.0);
				AnchorPane.setTopAnchor(newPane, 0.0);
				
				layers.get(i).setPane(newPane);
			}
			
		}
		
		if ( saveHistory ) {
			history.update();
		}
		
	}
	
	public void clearDrawing() {
		layersGroup.reset();
		
		ObservableList<Node> clearPaneList = anchorPane.getChildren();
		
		for (int i = 0; i < paneList.size(); ++i) {
			
			if ( !gridPane.getId().equals(paneList.get(i).getId()) ) {
				((Pane)(paneList.get(i))).getChildren().clear();
				anchorPane.getChildren().remove(i);
			}
		}
		
		//updateLayers(true);
	}
	
	public void applyToCurrentPane(Shape shape) {
		Pane currentPane = layersGroup.getCurrentLayer().getPane();
		
		currentPane.getChildren().add(shape);
		layersGroup.getCurrentLayer().setPane(currentPane);

		updateLayers(true);
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
			this.applyToCurrentPane(shapeCopy.duplicateAndOffset());
		}
	}
}
