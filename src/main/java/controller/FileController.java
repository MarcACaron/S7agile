package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import javax.xml.stream.XMLStreamException;

import adraw4us.MainApp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.LayersGroup;
import models.XmlDecoder;
import models.XmlEncoder;

public class FileController {

	private static FileController instance = new FileController();

	public static FileController getInstance( ) {
		return instance;
	}


	private FileChooser fileChooser;
	private File currentFile;

	public Boolean askForFile(Stage primaryStage) {
		
		fileChooser = new FileChooser();
		if (currentFile != null) {
			fileChooser.setInitialDirectory(currentFile.getParentFile());
		}
		
		fileChooser.setTitle("Save Drawing");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		File fileCandidate = fileChooser.showSaveDialog(primaryStage);
		
		if (fileCandidate != null) {
			currentFile = fileCandidate;
			return true;
		}

		return false;

	}

	public void saveDrawing(LayersGroup layersGroupe) throws FileNotFoundException, XMLStreamException {
		XmlEncoder.createXML(layersGroupe, currentFile);
	}
	
	public File getCurrentFile() {
		return currentFile;
	}
	
	public void clearFile() {
		currentFile = null;
	}
	
	public Boolean askToSave(Stage stage, LayersGroup layersGroup) throws FileNotFoundException, XMLStreamException {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Do you want to save before creating a new draw?");
		
		ButtonType buttonTypeYes = new ButtonType("Yes");
		ButtonType buttonTypeNo = new ButtonType("No");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == buttonTypeYes){
			if (getCurrentFile() == null ) {
				askForFile(stage);
			}
			
			if (getCurrentFile() != null) {
				saveDrawing(layersGroup);
			} else {
				return false;
			}
		    return true;
		} else if (result.isPresent() && result.get() == buttonTypeNo) {
			return true;
		} else if (result.isPresent() && result.get() == buttonTypeCancel) {
			return false;
		}
		return false;
	}

	public void openFile(Stage stage, MainApp mainApp) throws FileNotFoundException, XMLStreamException {
		
		fileChooser = new FileChooser();
		if (currentFile != null) {
			fileChooser.setInitialDirectory(currentFile.getParentFile());
		}
		
		fileChooser.setTitle("Open Drawing");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		File fileCandidate = fileChooser.showOpenDialog(stage);
		
		if (fileCandidate != null && fileCandidate.isFile()) {
			currentFile = fileCandidate;
			XmlDecoder.readXML(currentFile, mainApp);

		}
	}

}
