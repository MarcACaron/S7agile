package controller;

import adraw4us.MainApp;
import javafx.fxml.FXML;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;


public class MenuController {
	
	private Pane pane;
	
	@FXML
	private MenuBar menuBar;
	
	@FXML private MenuItem menuItemNew;
	
	@FXML private MenuItem menuItemSave;
	
	@FXML private MenuItem menuItemSaveAs;
	
	@FXML private MenuItem menuItemOpen;
	
	@FXML private MenuItem menuShowGridLines;
	
	@FXML
	private void clear() {
		pane.getChildren().clear();
	}
	
	private MainApp mainApp;
	
	
	@FXML
    private void initialize() {	
		
		FileController fileController = FileController.getInstance();
		
		menuItemNew.setOnAction(e -> {

			if (fileController.askToSave(mainApp.getPrimaryStage(), pane)) {
				pane.getChildren().clear();
				fileController.clearFile();
			}
			
		});
		
		menuItemSaveAs.setOnAction(e -> {
			
			if (fileController.askForFile(mainApp.getPrimaryStage())) {
				fileController.saveDrawing(pane);
			}
				
			
		});
		
		menuItemSave.setOnAction(e -> {
			
			if (fileController.getCurrentFile() == null && fileController.askForFile(mainApp.getPrimaryStage())) {
				
				fileController.saveDrawing(pane);
					
			}
			
		});
		
		menuItemOpen.setOnAction(e -> {
			
			if (fileController.askToSave(mainApp.getPrimaryStage(), pane)) {
				fileController.openFile(mainApp.getPrimaryStage(), pane);
					
			}
			
		});
		
		menuShowGridLines.setOnAction(e ->{
			this.mainApp.getDrawingZoneController().inverseGridPaneVisibility();
		});
    }

	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
	
	
}
