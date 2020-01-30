package view;

import java.io.*;

import com.sun.javafx.geom.Shape;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.CustomCircle;
import models.CustomLine;
import models.CustomRectangle;
import models.Identifiable;
import models.ShapeType;
import models.XmlDecoder;
import models.XmlEncoder;

import java.io.File;
import java.util.Optional;

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

	public void saveDrawing(Pane pane) {
		ObservableList<Node> shapeList = pane.getChildren();

		XmlEncoder.createXML(shapeList, currentFile);

	}
	
	public File getCurrentFile() {
		return currentFile;
	}
	
	public void clearFile() {
		currentFile = null;
	}
	
	public Boolean askToSave(Stage stage, Pane pane) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText("Do you want to save before creating a new draw?");
		
		ButtonType buttonTypeYes = new ButtonType("Yes");
		ButtonType buttonTypeNo = new ButtonType("No");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeYes){
			if (getCurrentFile() == null ) {
				askForFile(stage);
			}
			
			if (getCurrentFile() != null) {
				saveDrawing(pane);
			} else {
				return false;
			}
		    return true;
		} else if (result.get() == buttonTypeNo) {
			return true;
		} else if (result.get() == buttonTypeCancel) {
			return false;
		}
		return false;
	}

	public void openFile(Stage stage, Pane pane) {
		
		fileChooser = new FileChooser();
		if (currentFile != null) {
			fileChooser.setInitialDirectory(currentFile.getParentFile());
		}
		
		fileChooser.setTitle("Open Drawing");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		File fileCandidate = fileChooser.showOpenDialog(stage);
		
		if (fileCandidate != null && fileCandidate.isFile()) {
			pane.getChildren().clear();
			System.out.println(pane.getChildren().size());
			currentFile = fileCandidate;
			XmlDecoder.readXML(currentFile, pane);

		}
	}

}
