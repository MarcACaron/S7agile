package controller;

import java.io.IOException;

import adraw4us.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MenuController {
	
	@FXML
	private MenuBar menuBar;
	
	@FXML private MenuItem menuItemNew;
	
	@FXML private MenuItem menuItemSave;
	
	@FXML private MenuItem menuItemSaveAs;
	
	@FXML private MenuItem menuItemOpen;
	
	@FXML private MenuItem menuItemLayers;
	
	@FXML
	private void clear() {
		this.mainApp.getDrawingZoneController().clearDrawing();
	}
	
	private MainApp mainApp;
	
	
	@FXML
    private void initialize() {	
		
		FileController fileController = FileController.getInstance();
		
		menuItemNew.setOnAction(e -> {
			
			Pane drawing = this.mainApp.getDrawingZoneController().getDrawingsAsOne();

			if (fileController.askToSave(mainApp.getPrimaryStage(), drawing)) {
				this.mainApp.getDrawingZoneController().clearDrawing();
				fileController.clearFile();
			}
			
		});
		
		menuItemSaveAs.setOnAction(e -> {
			
			Pane drawing = this.mainApp.getDrawingZoneController().getDrawingsAsOne();
			
			if (fileController.askForFile(mainApp.getPrimaryStage())) {
				fileController.saveDrawing(drawing);
			}
				
			
		});
		
		menuItemSave.setOnAction(e -> {
			
			Pane drawing = this.mainApp.getDrawingZoneController().getDrawingsAsOne();
			
			if (fileController.getCurrentFile() == null && fileController.askForFile(mainApp.getPrimaryStage())) {
				
				fileController.saveDrawing(drawing);
					
			}
			
		});
		
		menuItemOpen.setOnAction(e -> {
			
			Pane drawing = this.mainApp.getDrawingZoneController().getDrawingsAsOne();
			
			if (fileController.askToSave(mainApp.getPrimaryStage(), drawing)) {
				fileController.openFile(mainApp.getPrimaryStage(), drawing);
					
			}
			
		});
		
		menuItemLayers.setOnAction(e -> {

			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/LayerWindow.fxml"));
	            root = loader.load();
	            Stage stage = new Stage();
	            stage.initModality(Modality.WINDOW_MODAL);
	            stage.initOwner(mainApp.getPrimaryStage().getScene().getWindow());
	            stage.setScene(new Scene(root));
	            stage.showAndWait();
	            
	            mainApp.getDrawingZoneController().updateLayers();
	            
	        }
	        catch (IOException ex) {
	        }
			
		});
		
    }

	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
	@FXML private void saveButtonOnClick() {
		mainApp.getDrawingZoneController().saveShape();
	}
	
	@FXML private void pasteButtonOnClick() {
		mainApp.getDrawingZoneController().pasteShape();
	}
	
	
}
