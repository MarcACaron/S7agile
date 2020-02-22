package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;

import adraw4us.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.LayersGroup;


public class MenuController {
	
	@FXML
	private MenuBar menuBar;
	
	@FXML private MenuItem menuItemNew;
	
	@FXML private MenuItem menuItemSave;
	
	@FXML private MenuItem menuItemSaveAs;
	
	@FXML private MenuItem menuItemOpen;
	
	@FXML private MenuItem menuItemLayers;
	
	@FXML private MenuItem menuShowGridLines;
	
	@FXML private MenuItem menuItemMagnetism;
	
	@FXML private MenuItem menuItemUndo;
	
	@FXML private MenuItem menuItemRedo;
	
	private Logger loggerMenuController;
	
	@FXML
	private void clear() {
		this.mainApp.getDrawingZoneController().clearDrawing();
	}
	
	private MainApp mainApp;
	
	
	@FXML
    private void initialize() {	
		
		FileController fileController = FileController.getInstance();
		
		menuItemNew.setOnAction(e -> {
			
			LayersGroup layersGroup = this.mainApp.getDrawingZoneController().layersGroup;

			try {
				if (Boolean.TRUE.equals(fileController.askToSave(mainApp.getPrimaryStage(), layersGroup, mainApp.getDrawingZoneController().drawnShapes))) {
					this.mainApp.getDrawingZoneController().clearDrawing();
					fileController.clearFile();
				}
			} catch (FileNotFoundException | XMLStreamException e1) {
				loggerMenuController.log(Level.SEVERE, "Exeption:menuItemNew: "+e1.getMessage()+"; Fonction: initialize():MenuController:menuItemNew;");
			}
			
		});
		
		menuItemSaveAs.setOnAction(e -> {
			
			LayersGroup layersGroup = this.mainApp.getDrawingZoneController().layersGroup;
			
			if (Boolean.TRUE.equals(fileController.askForFile(mainApp.getPrimaryStage()))) {
				try {
					fileController.saveDrawing(layersGroup, mainApp.getDrawingZoneController().drawnShapes);
				} catch (FileNotFoundException | XMLStreamException e1) {
					loggerMenuController.log(Level.SEVERE, "Exeption:menuItemSaveAs: "+e1.getMessage()+"; Fonction: initialize():MenuController:menuItemSaveAs;");
				}
			}
				
			
		});
		
		menuItemSave.setOnAction(e -> {
			
			LayersGroup layersGroup = this.mainApp.getDrawingZoneController().layersGroup;
			
			if (fileController.getCurrentFile() == null && fileController.askForFile(mainApp.getPrimaryStage())) {
				
				try {
					fileController.saveDrawing(layersGroup, mainApp.getDrawingZoneController().drawnShapes);
				} catch (FileNotFoundException | XMLStreamException e1) {
					loggerMenuController.log(Level.SEVERE, "Exeption:menuItemSave: "+e1.getMessage()+"; Fonction: initialize():MenuController:menuItemSave;");
				}
					
			}
			
		});
		
		menuItemOpen.setOnAction(e -> {
			try {
				if (Boolean.TRUE.equals(fileController.askToSave(mainApp.getPrimaryStage(), LayersGroup.getLayersGroup(), mainApp.getDrawingZoneController().drawnShapes))) {
					fileController.openFile(mainApp.getPrimaryStage(), mainApp);
					this.mainApp.getDrawingZoneController().updateLayers(true);
						
				}
			} catch (FileNotFoundException | XMLStreamException e1) {
				loggerMenuController.log(Level.SEVERE, "Exeption:menuItemOpen: "+e1.getMessage()+"; Fonction: initialize():MenuController:MenuItemOpen;");
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
	            mainApp.getDrawingZoneController().updateLayers(true);
	            
	        }
	        catch (IOException ex) {
	        	loggerMenuController.log(Level.SEVERE, "Exeption: "+ex.getMessage()+"; Fonction: initialize():MenuController;");
	        }
			
		});
		
		menuShowGridLines.setOnAction(e -> 
			mainApp.getDrawingZoneController().inverseGridPaneVisibility()
		);
		
		menuItemMagnetism.setOnAction(e -> {
			mainApp.getDrawingZoneController().inverseMagnetism();
			}
		);
		
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
	
	@FXML private void undoClick() {
		mainApp.getDrawingZoneController().undo();
	}
	
	@FXML private void redoClick() {
		mainApp.getDrawingZoneController().redo();
	}
	
	@FXML private void resetDrawing() {
		mainApp.getDrawingZoneController().clearDrawing();
		mainApp.getDrawingZoneController().updateLayers(true);
	}
	
	
}
