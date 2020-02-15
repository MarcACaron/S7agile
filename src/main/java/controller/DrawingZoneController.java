package controller;



import java.util.ArrayList;
import java.util.List;

import adraw4us.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
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
	
	private Point2D finalPoint = new Point2D(0.0,0.0);
	
	LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	ArrayList<Pane> paneList = new ArrayList<>();
	List<Point2D> listPoints = new ArrayList<>();
	
	double orgX;
	double orgY;
	double pointGridX;
	double pointGridY;
	int childIndex;
	boolean gridPaneBoolean;
	boolean magnetismState = false;
	
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
		boolean visState = getLineGridPaneVisibility(); 

		gridPane.setGridLinesVisible(!visState);
	}
	
	public boolean getLineGridPaneVisibility() {
		gridPaneBoolean = gridPane.isGridLinesVisible();
		return gridPaneBoolean;
	}
	
	
	public void inverseMagnetism() {
		magnetismState = !magnetismState;
	}
    
	public void createGridPoints() {
		for (int i=0; i<100; i++) {
			pointGridX = i * 50;
			for (int j=0; j<100; j++) {
				pointGridY = j * 50;	
				listPoints.add(new Point2D(pointGridX, pointGridY));
			}
		}
	}
	
    private void getNearestGridPoint(double pointX, double pointY){
    	
    	List<Double> listDistancePoints = new ArrayList<>();
    	Point2D comparedPoint = new Point2D(pointX,pointY); 
    	
    	double distanceMin = 200.0;
    	
    	createGridPoints();
    	
    	for(int i=0; i<10000; i++) {
    		listDistancePoints.add(comparedPoint.distance(listPoints.get(i)));
        	        	
        	if (listDistancePoints.get(i) < distanceMin) {
        		distanceMin = listDistancePoints.get(i);
        		finalPoint = listPoints.get(i);
        	}
    	}
    	System.out.print("\n"+finalPoint);
    }
    
    public void setNearestGridPoint() {
    	if (magnetismState == true) {
    		orgX = finalPoint.getX();
    		orgY = finalPoint.getY();
    	}
    }
    
	@FXML
    private void initialize() {
		anchorPane.setOnMousePressed(t -> {
			orgX = t.getX();
			orgY = t.getY();
			childIndex = anchorPane.getChildren().size();
			childIndex = this.mainApp.getTool().mousePressed(this.mainApp.getPaletteDetailController(), anchorPane);
			getNearestGridPoint(orgX, orgY);
			setNearestGridPoint();
		});
		
		anchorPane.setOnMouseDragged(t -> 
			this.mainApp.getTool().mouseDragged(orgX, orgY, t.getX(), t.getY())
		);
		
		anchorPane.setOnMouseReleased(t -> {
			orgX = t.getX();
			orgY = t.getY();
			this.mainApp.getTool().mouseReleased(mainApp, anchorPane, this.mainApp.getPaletteCouleurController(), this.mainApp.getPaletteDetailController());
			getNearestGridPoint(orgX, orgY);
			setNearestGridPoint();
		});
		
		clearDrawing();
		updateLayers();
    }
	
	public void updateLayers() {
		List<Layer> layers = layersGroup.getLayers();
		
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
		
		ObservableList<Node> clearPaneList = anchorPane.getChildren();
		
		for (int i = 1; i < clearPaneList.size() - 1; ++i) {
			if ( gridPane.getId().equals(clearPaneList.get(i).getId())  ) {
				((Pane)(clearPaneList.get(i))).getChildren().clear();
			}
		}
		
		anchorPane.toFront();
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
