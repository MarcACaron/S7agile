package controller;

import adraw4us.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.CustomShape;

public class DetailPaletteController {
	private MainApp mainApp;
	
	@FXML	private VBox detailPalette;
	@FXML	private VBox positionSection; 
	@FXML	private VBox dimensionSection;
	@FXML	private VBox angleSection;
	
	@FXML	private HBox xPos;
	@FXML	private HBox yPos;
	@FXML	private HBox width;
	@FXML	private HBox height;
	@FXML	private HBox angle;
	
	@FXML	private TextField xPosText;
	@FXML	private TextField yPosText;
	@FXML	private TextField widthText;
	@FXML	private TextField heightText;
	@FXML	private TextField angleText;
	
	public void paletteDisable(boolean pState) {
		if(detailPalette.isDisabled()!=pState) {
			detailPalette.setDisable(pState);
		}
	}
	public void select(CustomShape tShape) {
		paletteDisable(false);
	}
	@FXML private void initialize() {
		detailPalette.setDisable(true);
	}
	@FXML 
	private void onXPosChange(){
		this.mainApp.getTool().getShape().setXPos(Double.valueOf(xPosText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShape());
	}

	@FXML 
	private void onYPosChange(){
		this.mainApp.getTool().getShape().setYPos(Double.valueOf(yPosText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShape());
	}

	@FXML 
	private void onWidthChange(){
		this.mainApp.getTool().getShape().setWidth(Double.valueOf(widthText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShape());
	}

	@FXML 
	private void onHeightChange(){
		((CustomShape)this.mainApp.getTool().getShape()).setHeight(Double.valueOf(heightText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShape());
	}

	@FXML 
	private void onRotateChange(){
		((CustomShape)this.mainApp.getTool().getShape()).setRotate(Double.valueOf(angleText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShape());
	}
	
	public void setMainApp(MainApp inputMain){mainApp = inputMain;}

	public void setTextField(CustomShape tShape) {
		
		setXPosText(String.valueOf(Math.floor(tShape.getXPos() * 100) / 100));
		setYPosText(String.valueOf(Math.floor(tShape.getYPos() * 100) / 100));
		setWidthText(String.valueOf(Math.floor(tShape.getWidth() * 100) / 100));
		setHeightText(String.valueOf(Math.floor(tShape.getHeight() * 100) / 100));
		setAngleText(String.valueOf(Math.floor(tShape.getRotate() * 100) / 100));
		
	}
	
	public VBox getPalette() {return detailPalette;}
	
	public void setXPosText(String inputText) {xPosText.setText(inputText);}
	public String getXPosText() {return xPosText.getText();}
	public void setYPosText(String inputText) {yPosText.setText(inputText);}
	public String getYPosText() {return yPosText.getText();}
	public void setWidthText(String inputText) {widthText.setText(inputText);}
	public String getWidthText() {return widthText.getText();}
	public void setHeightText(String inputText) {heightText.setText(inputText);}
	public String getHeightText() {return heightText.getText();}
	public void setAngleText(String inputText) {angleText.setText(inputText);}
	public String getAngleText() {return angleText.getText();}
	
	
}
