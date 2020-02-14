package controller;



import java.util.ArrayList;
import java.util.Collections;
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
	
    public void setNearestGridPoint() {
    	if (magnetismState == true) {
		orgX = finalPoint.getX();
		orgY = finalPoint.getY();
    	}
    }
    
	/*public void createGridPoints() {
		for (int i=0; i<100; i++) {
			pointGridX = i * 50;
			for (int j=0;j<100; j++) {
				pointGridY = i * 50;	
			}
		}
	}*/
	
	//Tableau de point à initialiser avec un Tableau
    Point2D point1 = new Point2D(0.0,0.0);
    Point2D point2 = new Point2D(0.0,50.0);
    Point2D point3 = new Point2D(0.0,100.0);
    Point2D point4 = new Point2D(50.0,0.0);
    Point2D point5 = new Point2D(50.0,50.0);
    Point2D point6 = new Point2D(50.0,100.0);
    Point2D point7 = new Point2D(100.0,0.0);
    Point2D point8 = new Point2D(100.0,50.0);
    Point2D point9 = new Point2D(100.0,100.0);
	
    private void getNearestGridPoint(double pointX, double pointY){
    	
    	double distanceMin = 50.0;
    	int indexPointsTab;
    	Point2D comparedPoint = new Point2D(pointX,pointY);    	
    	ArrayList<Point2D> pointsTab = new ArrayList<>(); 
   	
    	pointsTab.add(point1);
    	pointsTab.add(point2);
    	pointsTab.add(point3);
    	pointsTab.add(point4);
    	pointsTab.add(point5);
    	pointsTab.add(point6);
    	pointsTab.add(point7);
    	pointsTab.add(point8);
    	pointsTab.add(point9);
    	
    	double distancesTab[] = {comparedPoint.distance(point1),
    							 comparedPoint.distance(point2),
    							 comparedPoint.distance(point3),
    							 comparedPoint.distance(point4),
    							 comparedPoint.distance(point5),
    							 comparedPoint.distance(point6),
    							 comparedPoint.distance(point7),
    							 comparedPoint.distance(point8),
    							 comparedPoint.distance(point9)};
    	
    	for (int i=0;i<distancesTab.length;i++)
    	{
    		if(distancesTab[i] < distanceMin) {
    			distanceMin = distancesTab[i];
    			indexPointsTab = i;
    	    	finalPoint = pointsTab.get(indexPointsTab);
    		}
    	}   	    	
    	System.out.println("Point le plus proche :" + finalPoint);
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
		
		anchorPane.setOnMouseDragged(t -> {
			this.mainApp.getTool().mouseDragged(orgX, orgY, t.getX(), t.getY());
		});
		
		anchorPane.setOnMouseReleased(t -> 
			this.mainApp.getTool().mouseReleased(mainApp, anchorPane, this.mainApp.getPaletteCouleurController(), this.mainApp.getPaletteDetailController())
		);
		
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
