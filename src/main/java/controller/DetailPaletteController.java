package controller;

import adraw4us.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Transformable;

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
	@FXML	private HBox radius;
	@FXML	private HBox length;
	@FXML	private HBox angle;
	
	@FXML	private TextField xPosText;
	@FXML	private TextField yPosText;
	@FXML	private TextField widthText;
	@FXML	private TextField heightText;
	@FXML	private TextField radiusText;
	@FXML	private TextField lengthText;
	@FXML	private TextField angleText;
	
	public void paletteDisable(boolean pState) {
		if(detailPalette.isDisabled()!=pState) {
			detailPalette.setDisable(pState);
		}
	}
	public void select(Transformable tShape) {
		paletteDisable(false);
		
		this.width.setDisable(!tShape.widthToolisNeeded());
		this.height.setDisable(!tShape.heightToolisNeeded());
		this.radius.setDisable(!tShape.radiusToolisNeeded());
		this.length.setDisable(!tShape.lengthToolisNeeded());
		this.angle.setDisable(false);
	}
	@FXML private void initialize() {
		detailPalette.setDisable(true);
	}
	@FXML 
	private void onXPosChange(){
		((Transformable)this.mainApp.getTool().getShapes().get(0)).setXPosTool(Double.valueOf(xPosText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShapes().get(0));
	}

	@FXML 
	private void onYPosChange(){
		((Transformable)this.mainApp.getTool().getShapes().get(0)).setYPosTool(Double.valueOf(yPosText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShapes().get(0));
	}

	@FXML 
	private void onWidthChange(){
		((Transformable)this.mainApp.getTool().getShapes().get(0)).setWidthTool(Double.valueOf(widthText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShapes().get(0));
	}

	@FXML 
	private void onHeightChange(){
		((Transformable)this.mainApp.getTool().getShapes().get(0)).setHeightTool(Double.valueOf(heightText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShapes().get(0));
	}

	@FXML 
	private void onRadiusChange(){
		((Transformable)this.mainApp.getTool().getShapes().get(0)).setRadiusTool(Double.valueOf(radiusText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShapes().get(0));
	}

	@FXML 
	private void onLengthChange(){
		((Transformable)this.mainApp.getTool().getShapes().get(0)).setLengthTool(Double.valueOf(lengthText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShapes().get(0));
	}

	@FXML 
	private void onRotateChange(){
		((Transformable)this.mainApp.getTool().getShapes().get(0)).setRotationTool(Double.valueOf(angleText.getText()));
		this.mainApp.getTool().fillDetails(this, this.mainApp.getTool().getShapes().get(0));
	}
	
	public void setMainApp(MainApp inputMain){mainApp = inputMain;}

	public void setTextField(Transformable tShape) {
		
		setXPosText(String.valueOf(Math.floor(tShape.getXPos() * 100) / 100));
		setYPosText(String.valueOf(Math.floor(tShape.getYPos() * 100) / 100));
		setWidthText(String.valueOf(Math.floor(tShape.getWidth() * 100) / 100));
		setHeightText(String.valueOf(Math.floor(tShape.getHeight() * 100) / 100));
		setRadiusText(String.valueOf(Math.floor(tShape.getRadius() * 100) / 100));
		setLengthText(String.valueOf(Math.floor(tShape.getLength() * 100) / 100));
		setAngleText(String.valueOf(Math.floor(tShape.getRotation() * 100) / 100));
		
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
	public void setRadiusText(String inputText) {radiusText.setText(inputText);}
	public String getRadiusText() {return radiusText.getText();}
	public void setLengthText(String inputText) {lengthText.setText(inputText);}
	public String getLengthText() {return lengthText.getText();}
	public void setAngleText(String inputText) {angleText.setText(inputText);}
	public String getAngleText() {return angleText.getText();}
	
	
}
