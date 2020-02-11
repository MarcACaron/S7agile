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
		boolean visState = getLineGridPaneVisibility(); //Boolean qui set la visibilit� des lines

		gridPane.setGridLinesVisible(!visState);
	}
	
	public boolean getLineGridPaneVisibility() {
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
		clearDrawing();
		updateLayers();
    }
	
	public void updateLayers() {
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
	}
	
	public void clearDrawing() {
		layersGroup.reset();
		
		ObservableList<Node> paneList = anchorPane.getChildren();
		
		for (int i = 1; i < paneList.size() - 1; ++i) {
			if ( gridPane.getId().equals(paneList.get(i).getId())  ) {
				((Pane)(paneList.get(i))).getChildren().clear();
			}
			else {
				System.out.println("plz detect grid");
			}
			
		}
		
		//anchorPane.getChildren().clear();
		
		updateLayers();
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
