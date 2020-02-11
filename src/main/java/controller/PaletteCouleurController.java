package controller;

import adraw4us.MainApp;
import adraw4us.Tool;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class PaletteCouleurController {
	
	@FXML
	private Button testBut;
	@FXML
    private Button fillAnanas;
	@FXML
    private Button fillDirt;
	@FXML
    private Button fillRed;	
	@FXML 
	private Button gridButton;
	@FXML
    private ToggleButton gridBut;
	@FXML
    private ColorPicker stroke;
	@FXML
    private ChoiceBox<Double> lineWidth;
	@FXML
    private ChoiceBox<double[]> lineStyle;
	@FXML
    private ChoiceBox<int[]> policeText;
    @FXML
    private ImageView detailsImage;
	
	private MainApp mainApp;
		
	
	@FXML
	private void zoomIn() {
		this.mainApp.getDrawingZoneController().zoomIn(1.1);
	}
	@FXML
	private void zoomOut() {
		this.mainApp.getDrawingZoneController().zoomOut(1.1);
	}
	@FXML
	private void ananas() {
		fillShape("ananas");
	}
	
	@FXML
	private void dirt() {
		fillShape("dirt");
	}
	
	@FXML
	private void red() {
		fillShape("rouge");
	}
	
	private void fillShape(String value) {
		if(!value.equals("")) {
			if(value.equals("rouge")) {
			    Tool.setFill(Color.RED);
			}else {
				String imagePath = "images/" + value + ".png";
				Image image = new Image(imagePath); 
			    ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
			    Tool.setFill(radialGradient);
			}
		    Tool.setFillName(value);
			if(this.mainApp.getTool().getShape()!=null) {
				
			    this.mainApp.getTool().fillShape();
			}
		}
	}
	
	public void setStroke(Color stroke) {
		this.stroke.setValue(stroke);
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth.setValue(lineWidth);
	}

	@FXML
	private void changeStrokeColor() {
		if(this.mainApp.getTool().getShape()!=null) {
			this.mainApp.getTool().getShape().setStroke(stroke.getValue());
		}
		Tool.setStroke(stroke.getValue());
	}
	
	@FXML
	private void changeLineWidth() {
		if(this.mainApp.getTool().getShape()!=null) {
			this.mainApp.getTool().getShape().setStrokeWidth(lineWidth.getValue().doubleValue());
		}
		Tool.setLineWidth(lineWidth.getValue().doubleValue());
	}
	
	@FXML
    private void initialize() {
		lineWidth.setItems(FXCollections.observableArrayList(1.0,3.0, 5.0, 7.0, 9.0));
		lineWidth.setOnAction(t-> changeLineWidth());
    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
		stroke.setValue(Color.BLACK);
		Tool.setStroke(Color.BLACK);
		lineWidth.setValue(1.0);
		this.fillShape("ananas");
		this.fillAnanas.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image("images/ananas.png"), 45, 55, 100, 100, false), null, null)));
		this.fillDirt.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image("images/dirt.png"), 0, 0, 200, 200, false), null, null)));
		this.fillRed.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
    }
}
