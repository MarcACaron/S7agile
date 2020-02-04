package controller;

import adraw4us.MainApp;
import adraw4us.Tool;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.CircleTool;
import models.LineTool;
import models.RectangleTool;
import models.SelectionTool;

public class PaletteFormeController {
	
	@FXML
    private ToggleButton pointeur;
	@FXML
    private ToggleButton rectangle;
	@FXML
    private ToggleButton circle;
	@FXML
    private ToggleButton line;
	@FXML
    private ToggleButton ptDepart;
	@FXML
	private ImageView ptDepartImage;
	
	@FXML
	private void choosePointer() {
		if(pointeur.isSelected()) {
			this.mainApp.setTool(new SelectionTool());
		}else {
			pointeur.setSelected(true);
			this.mainApp.setTool(new SelectionTool());
		}
		
	}
	@FXML
	private void chooseRectangle() {
		if(rectangle.isSelected()) {
			this.mainApp.setTool(new RectangleTool());
		}else {
			this.mainApp.setTool(new SelectionTool());
			pointeur.setSelected(true);
		}
		
	}
	@FXML
	private void chooseCircle() {
		if(circle.isSelected()) {
			this.mainApp.setTool(new CircleTool());
		}else {
			this.mainApp.setTool(new SelectionTool());
			pointeur.setSelected(true);
		}
		
	}
	@FXML
	private void chooseLine() {
		if(line.isSelected()) {
			this.mainApp.setTool(new LineTool());
		}else {
			this.mainApp.setTool(new SelectionTool());
			pointeur.setSelected(true);
		}
		
	}
	@FXML
	private void choosePtDepart() {
		if(ptDepart.isSelected()) {
			ptDepartImage.setImage(new Image("images/corner.png"));
			Tool.startFromCenter=false;
		}else {
			ptDepartImage.setImage(new Image("images/center.png"));
			Tool.startFromCenter=true;
		}
	}
	
	private MainApp mainApp;
	
	
	@FXML
    private void initialize() {
		// Put stuff to initialise here
    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
		this.mainApp.setTool(new SelectionTool());
		Tool.startFromCenter = true;
    }
}
