package controller;

import java.awt.Transparency;
import java.util.ArrayList;
import java.util.List;

import adraw4us.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import models.ApplicationHistory;
import models.Layer;
import models.LayersGroup;
import models.Transformable;

public class DrawingZoneController {
	
	private final Color outlineColor = Color.DARKBLUE, 
						inlineColor = Color.LIGHTBLUE;
		
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private GridPane gridPane;
	@FXML
	private AnchorPane selectionLayoutPane;
	
	private MainApp mainApp;
	
	private ArrayList<Shape> shapesCopy;
	ApplicationHistory history = ApplicationHistory.getInstance();
	
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
		clearDrawing();
		
		layersGroup.clear();
		ArrayList<Layer> redoLayers = (ArrayList<Layer>)history.redoHistory();
		
		layersGroup.replaceLayers(redoLayers);
		updateLayers(true);
	}
	
	public void undo() {
		clearDrawing();
		layersGroup.clear();
		ArrayList<Layer> undoLayers = (ArrayList<Layer>)history.undoHistory();
		
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
			childIndex = this.mainApp.getTool().mousePressed(this.mainApp.getPaletteDetailController(), layersGroup.getCurrentLayer().getPane());
			getNearestGridPoint(orgX, orgY);
			setNearestGridPoint();
		});
		anchorPane.setOnMouseDragged(t -> this.mainApp.getTool().mouseDragged(orgX, orgY, t.getX(), t.getY()));
		
		anchorPane.setOnMouseReleased(t -> this.mainApp.getTool().mouseReleased(mainApp, layersGroup.getCurrentLayer().getPane(), this.mainApp.getPaletteCouleurController(), this.mainApp.getPaletteDetailController()));
		updateLayers(true);
    }
	
	public void updateLayers(Boolean saveHistory) {
		ArrayList<Layer> layers = (ArrayList<Layer>)layersGroup.getLayers();
		
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
		
		if ( Boolean.TRUE.equals(saveHistory)) {
			history.update();
		}
		
	}
	
	public void clearDrawing() {
		layersGroup.reset();
		
		ObservableList<Node> paneList = anchorPane.getChildren();
		
		for (int i = 0; i < paneList.size(); ++i) {
			
			if ( !gridPane.getId().equals(paneList.get(i).getId()) ) {
				((Pane)(paneList.get(i))).getChildren().clear();
				anchorPane.getChildren().remove(i);
			}
		}
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
		
		shapesCopy = (ArrayList<Shape>) mainApp.getTool().getShapes().clone();

	}
	
	public void pasteShape() {
		if (shapesCopy != null) {
			shapesCopy.forEach(shape -> {
				this.applyToCurrentPane(shape); 
			});
			
		}
	}
	
	public void addSelectionShape(double coords[]) {
		Shape shapeToAdd = new Rectangle(coords[0], coords[1], coords[2]-coords[0], coords[3]-coords[1]); //startX, startY, EndX, EndY
		shapeToAdd.setStroke(outlineColor);
		shapeToAdd.getStrokeDashArray().addAll(2d, 5d);
		shapeToAdd.setStrokeWidth(2);
		shapeToAdd.setFill(Color.TRANSPARENT);
		selectionLayoutPane.getChildren().add(shapeToAdd);
		
		Shape dots[] = {new Circle(coords[0], coords[1], 5),
						new Circle(coords[2], coords[1], 5),
						new Circle(coords[0], coords[3], 5),
						new Circle(coords[2], coords[3], 5)};
		for (int i = 0; i < dots.length; i++) {
			dots[i].setStroke(outlineColor);
			dots[i].setFill(inlineColor);
			selectionLayoutPane.getChildren().add(dots[i]);
		}
	}
	
	public void clearSelectionLayer() {
		selectionLayoutPane.getChildren().clear();
	}
	
	public void FlipCurrentShape(int flipVorH) {
		//flipVorH = 1 is VFlip, 0 is HFlip
		Transformable shape = (Transformable)mainApp.getTool().getShapes().get(0);
		mainApp.getTool().getShapes().get(0).getTransforms().add(transformIntoReflection(shape.getCenterCoord(), flipVorH));
	}
	
	private Transform transformIntoReflection(Point2D p1, int flipXorY) {
		//flipXorY = 1 is Y-Flip, 0 is X-Flip
	    Translate translation = new Translate(-p1.getX(), -p1.getY());
	    Scale scale;
	    if (flipXorY == 1) {
	    	scale = new Scale(1, -1);
	    }else{
	    	scale = new Scale(-1, 1);
	    }
	    Affine reflection = new Affine();
	    reflection.append(translation.createInverse());
	    reflection.append(scale);
	    reflection.append(translation);
	    return reflection ;
	}
}
