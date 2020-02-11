package controller;



import java.util.ArrayList;

import adraw4us.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
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
	private GridPane gridPane;
	private MainApp mainApp;
	
	//ApplicationHistory history = ApplicationHistory.getInstance();
	
	LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	ArrayList<Pane> paneList = new ArrayList<Pane>();
	
	double orgX;
	double orgY;
	int childIndex;
	boolean gridPaneBoolean;
	
	public void redo() {
		
	}
	
	public void undo() {
		
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
		boolean visState = getLineGridPane(); //Boolean qui set la visibilit� des lines
		if (visState == true) {
			gridPane.setGridLinesVisible(false);
		}
		else {
			gridPane.setGridLinesVisible(true);
		}
	}
	
	public boolean getLineGridPane() {
		gridPaneBoolean = gridPane.isGridLinesVisible();
		return gridPaneBoolean;
	}
	
	public void printMagnetismeGridPane() {
		System.out.println(getMagnetismeGridPane());
	}
	
	public Pos getMagnetismeGridPane() {
		return gridPane.getAlignment();
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
		
		updateLayers();
    }
	
	public void updateLayers() {
		ArrayList<Layer> layers = layersGroup.getLayers();
		
		System.out.println(layersGroup.size() + " size in drawing board");
		
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
			
		}
	}
	
	public void clearDrawing() {
		layersGroup.reset();
		
		ObservableList<Node> paneList = anchorPane.getChildren();
		
		for (int i = 0; i < paneList.size(); ++i) {
			((Pane)(paneList.get(i))).getChildren().clear();
		}
		
		anchorPane.getChildren().clear();
		
		updateLayers();
	}
	
	public ObservableList<Node> getLayers(){
		return anchorPane.getChildren();
	}
	
	public void applyToCurrentPane(Shape shape) {
		Pane currentPane = layersGroup.getCurrentLayer().getPane();
		
		currentPane.getChildren().add(shape);
		layersGroup.getCurrentLayer().setPane(currentPane);
		
		System.out.println(layersGroup.getCurrentLayer().getPane().getChildren().size());

		updateLayers();
	}
	
//	public void undo() {
//		ArrayList<Layer> olderLayers = history.undoHistory();
//		
//		if (olderLayers != null) {
//			layersGroup.replaceLayers(olderLayers);
//			updateLayers();
//		}
//	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
