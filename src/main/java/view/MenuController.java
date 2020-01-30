package view;

import java.util.Optional;

import adraw4US.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

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
	
	private FileController fileController;
	
	@FXML
	private void clear() {
		pane.getChildren().clear();
	}
	
	private MainApp mainApp;
	
	public MenuController() {
		// TODO Auto-generated constructor stub
	
	}
	
	@FXML
    private void initialize() {	
		
		fileController = FileController.getInstance();
		
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
			
			if (fileController.getCurrentFile() == null) {
				if (fileController.askForFile(mainApp.getPrimaryStage())) {
					fileController.saveDrawing(pane);
				}
					
			}
			
		});
		
		menuItemOpen.setOnAction(e -> {
			
			if (fileController.askToSave(mainApp.getPrimaryStage(), pane)) {
				fileController.openFile(mainApp.getPrimaryStage(), pane);
					
			}
			
		});
		
    }

	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
	public void setPane(Pane pane) {
		this.pane = pane;
	}
	
	
	
}
