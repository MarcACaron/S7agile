package controller;



import adraw4us.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;

public class DrawingZoneController {
	
	@FXML
	private Pane pane;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private GridPane gridPane;
	private MainApp mainApp;
	double orgX;
	double orgY;
	int childIndex;
	
	public void zoomIn(double zoom) {
        Scale scaleTransform = new Scale(zoom, zoom, 0, 0);
        gridPane.getTransforms().add(scaleTransform);
        pane.getTransforms().add(scaleTransform);        
    }
	
	public void zoomOut(double zoom) {
        Scale scaleTransform = new Scale(1/zoom, 1/zoom, 0, 0);
        gridPane.getTransforms().add(scaleTransform);
        pane.getTransforms().add(scaleTransform);

    }

	public void showGridPane() {
		gridPane.setVisible(true);
	}
	
	public void hideGridPane() {
		gridPane.setVisible(false);
	}
	
	
	@FXML
    private void initialize() {
		pane.setOnMousePressed(t -> {
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
    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.mainApp.getMenuController().setPane(pane);
    }
}
