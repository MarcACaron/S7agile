package controller;



import adraw4us.MainApp;
import adraw4us.Tool;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import models.SelectionTool;

public class DrawingZoneController {
	
	@FXML
	private Pane pane;
	@FXML
	private ScrollPane scrollPane;
	private MainApp mainApp;
	double orgX;
	double orgY;
	int childIndex;
	
	public void zoomIn(double zoom) {
        Scale scaleTransform = new Scale(zoom, zoom, 0, 0);
        pane.getTransforms().add(scaleTransform);
    }
	
	public void zoomOut(double zoom) {
        Scale scaleTransform = new Scale(1/zoom, 1/zoom, 0, 0);
        pane.getTransforms().add(scaleTransform);
    }

	@FXML
    private void initialize() {
		pane.setOnMousePressed(t -> {
			if(this.mainApp.getTool().getClass()==SelectionTool.class){// Mode selection	
				this.mainApp.getTool().fillDetails(this.mainApp.getPaletteDetailController(), null).apply(null);
				this.mainApp.getTool().reset();
			}else {
				this.mainApp.getTool().reset();
				orgX = t.getX();
				orgY = t.getY();
				childIndex = pane.getChildren().size();
				pane.getChildren().add(this.mainApp.getTool().getTool());
			}
		});
		pane.setOnMouseDragged(t -> {
			if(this.mainApp.getTool()==null) {// Mode selection
				
			}else {
				this.mainApp.getTool().ajustOnDrag(orgX, orgY, t.getX(), t.getY());
			}
		});
		pane.setOnMouseReleased(t -> {
			if(this.mainApp.getTool().getClass()==SelectionTool.class) {// Mode selection
				
			}else {
				Shape sh = (Shape) pane.getChildren().get(childIndex);
				Tool tool = this.mainApp.getTool();
				sh.setOnMouseClicked(t2 -> {
					if(this.mainApp.getTool().getClass()==SelectionTool.class) {
						this.mainApp.getTool().setTool(sh);
						this.mainApp.getPaletteCouleurController().setLineWidth(sh.getStrokeWidth());
						this.mainApp.getPaletteCouleurController().setStroke((Color) (sh.getStroke()));
						
						tool.fillDetails(this.mainApp.getPaletteDetailController(), sh).apply(null);
					}
				});
			}
		});
		
    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.mainApp.getMenuController().setPane(pane);
    }
}
