package controller;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.LayersGroup;
import models.ShapeName;

public class GroupingController {

	@FXML private TextField name;
	@FXML private Button submit;
	private ShapeName shapeName;

	LayersGroup layerGroup = LayersGroup.getLayersGroup();

	@FXML private void submitGroup() {
		shapeName.name = name.getText();
		shapeName.submitName();
		Stage stage = (Stage) submit.getScene().getWindow();
	    stage.close();
	}
	@FXML
	private void initialize() {
		shapeName = ShapeName.getShapeName();
		name.setText(shapeName.name);
	}

}
