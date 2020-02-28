package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;

import adraw4us.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.LayersGroup;


public class MenuController {
	
	@FXML
	private MenuBar menuBar;
	
	@FXML private Menu MenuBarGroup;
	@FXML private MenuItem menuItemNew;
	
	@FXML private MenuItem menuItemSave;
	
	@FXML private MenuItem menuItemSaveAs;
	
	@FXML private MenuItem menuItemOpen;
	
	@FXML private MenuItem menuItemLayers;
	
	@FXML private MenuItem menuShowGridLines;
	
	@FXML private MenuItem menuItemMagnetism;
	
	@FXML private MenuItem menuItemUndo;
	
	@FXML private MenuItem menuItemRedo;
	
	@FXML private MenuItem menuItemShapeOrder;
	
	@FXML private MenuItem menuItemGroupShapes;
	
	@FXML private Menu DeleteOne;
	
	private Logger loggerMenuController = Logger.getLogger("MenuController");
	
	@FXML
	private void clear() {
		this.mainApp.getDrawingZoneController().clearDrawing();
		LayersGroup.getLayersGroup().getCurrentLayer().getDrawnShapes().clear();
	}
	
	private MainApp mainApp;
	
	@FXML
	private void loadDeletable() {
		DeleteOne.getItems().clear();
		for(int i=1; i<=4; i++) {
			if(mainApp.getPaletteFormeController().isDeletable(i)) {
				MenuItem mn = new MenuItem();
				mn.setText(mainApp.getPaletteFormeController().getDescription(i));
				DeleteOne.getItems().add(mn);
				if(i==1)
					mn.setOnAction(t->{
						mainApp.getPaletteFormeController().remove(1);
					});
				if(i==2)
					mn.setOnAction(t->{
						mainApp.getPaletteFormeController().remove(2);
					});
				if(i==3)
					mn.setOnAction(t->{
						mainApp.getPaletteFormeController().remove(3);
					});
				if(i==4)
					mn.setOnAction(t->{
						mainApp.getPaletteFormeController().remove(4);
					});
			}
		}
	}
	
	@FXML
    private void initialize() {	
		
		FileController fileController = FileController.getInstance();
		
		menuItemNew.setOnAction(e -> {
			
			LayersGroup layersGroup = this.mainApp.getDrawingZoneController().layersGroup;

			try {
				if (Boolean.TRUE.equals(fileController.askToSave(mainApp.getPrimaryStage(), layersGroup))) {
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
					fileController.saveDrawing(layersGroup);
				} catch (FileNotFoundException | XMLStreamException e1) {
					loggerMenuController.log(Level.SEVERE, "Exeption:menuItemSaveAs: "+e1.getMessage()+"; Fonction: initialize():MenuController:menuItemSaveAs;");
				}
			}
				
			
		});
		
		menuItemSave.setOnAction(e -> {
			
			LayersGroup layersGroup = this.mainApp.getDrawingZoneController().layersGroup;
			
			if (fileController.getCurrentFile() == null && fileController.askForFile(mainApp.getPrimaryStage())) {
				
				try {
					fileController.saveDrawing(layersGroup);
				} catch (FileNotFoundException | XMLStreamException e1) {
					loggerMenuController.log(Level.SEVERE, "Exeption:menuItemSave: "+e1.getMessage()+"; Fonction: initialize():MenuController:menuItemSave;");
				}
					
			}
			
		});
		
		menuItemOpen.setOnAction(e -> fileOpenAction()
			);
		
		menuItemLayers.setOnAction(e -> actionLayers());
		
		menuItemShapeOrder.setOnAction(e -> actionShapeOrder());
		
		menuShowGridLines.setOnAction(e -> 
			mainApp.getDrawingZoneController().inverseGridPaneVisibility()
		);
		
		menuItemMagnetism.setOnAction(e -> 
			mainApp.getDrawingZoneController().inverseMagnetism()
		);
		
		menuItemGroupShapes.setOnAction(e -> 
			group()
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
	@FXML private void group() {

		openModal("../view/GroupingWindow.fxml");
	}
	private void actionShapeOrder() {
		
		openModal("../view/LayerShapeWindow.fxml");
	}
	
	private void actionLayers() {
		
		openModal("../view/LayerWindow.fxml");

	}
	
	private void openModal(String path) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(path));
            root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainApp.getPrimaryStage().getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.showAndWait();
            mainApp.getDrawingZoneController().updateLayers(true);
            
        }
        catch (IOException ex) {
        	loggerMenuController.log(Level.SEVERE, "Exeption: "+ex.getMessage()+"; Fonction: openModal("+path+"):MenuController;");
        }
	}
	
	private void fileOpenAction( ) {
		FileController fileController = FileController.getInstance();
		
		try {
			if (Boolean.TRUE.equals(fileController.askToSave(mainApp.getPrimaryStage(), LayersGroup.getLayersGroup()))) {
				fileController.openFile(mainApp.getPrimaryStage(), mainApp);
				this.mainApp.getDrawingZoneController().updateLayers(true);
					
			}
		} catch (FileNotFoundException | XMLStreamException e1) {
			loggerMenuController.log(Level.SEVERE, "Exeption:menuItemOpen: "+e1.getMessage()+"; Fonction: initialize():MenuController:MenuItemOpen;");
		}
		
	}
	
}
