package testControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.testfx.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import adraw4us.MainApp;
import controller.DetailPaletteController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.testfx.api.FxAssert;


public class testDetailPaletteController extends testApplicationUI{
	
	private VBox detailPalette;
	private VBox positionSection; 
	private VBox dimensionSection;
	private VBox angleSection;

	private HBox xPos;
	private HBox yPos;
	private HBox width;
	private HBox height;
	private HBox radius;
	private HBox length;
	private HBox angle;

	private TextField xPosText;
	private TextField yPosText;
	private TextField widthText;
	private TextField heightText;
	private TextField radiusText;
	private TextField lengthText;
	private TextField angleText;

	
	@Test
	@DisplayName("TO DO - test_DetailPalette_Layout")
	public void test_DetailPalette_Layout() {
		
		final String[][] nameArray = 
			{	{"Position", "X:", "Y:"},
				{"Dimensions", "Width:", "Height:", "Radius:", "Length"},
				{"Rotation", "Angle"}};
		
		detailPalette = find("#detailPalette");
		positionSection = find("#positionSection");
		dimensionSection = find("#dimensionSection");
		angleSection = find("#angleSection");
		 
		 for (int i = 0; i < nameArray.length; i++) {
			 for (int j = 0; j < nameArray[i].length; j++) {
			 }
		 }
	}
	
	@Test
	public void test_DetailPalette_Click() {
		detailPalette = find("#detailPalette");
		
		detailPalette.setDisable(false);
		clickOn((Node)find("#xPosText"));
		clickOn((Node)find("#yPosText"));
		clickOn((Node)find("#widthText"));
		clickOn((Node)find("#heightText"));
		clickOn((Node)find("#radiusText"));
		clickOn((Node)find("#lengthText"));
		clickOn((Node)find("#angleText"));
		
	}
	
	@Test
	public void test_setget_Functions() {
		detailPalette = find("#detailPalette");
		
		String testValue = "100";
		
		mainApp.paletteDetailController.setXPosText(testValue);
		assertEquals(mainApp.paletteDetailController.getXPosText().compareTo(testValue),0);
		mainApp.paletteDetailController.setYPosText(testValue);
		assertEquals(mainApp.paletteDetailController.getYPosText().compareTo(testValue),0);
		mainApp.paletteDetailController.setWidthText(testValue);
		assertEquals(mainApp.paletteDetailController.getWidthText().compareTo(testValue),0);
		mainApp.paletteDetailController.setHeightText(testValue);
		assertEquals(mainApp.paletteDetailController.getHeightText().compareTo(testValue),0);
		mainApp.paletteDetailController.setRadiusText(testValue);
		assertEquals(mainApp.paletteDetailController.getRadiusText().compareTo(testValue),0);
		mainApp.paletteDetailController.setLengthText(testValue);
		assertEquals(mainApp.paletteDetailController.getLengthText().compareTo(testValue),0);
		mainApp.paletteDetailController.setAngleText(testValue);
		assertEquals(mainApp.paletteDetailController.getAngleText().compareTo(testValue),0);

	}
	
	/*@Test
	public void paletteDisable_test() {
		detailPaletteController = new DetailPaletteController();
		
		detailPaletteController.paletteDisable(true);
		assertEquals(detailPaletteController.getPalette().isDisabled(), true);
		
		detailPaletteController.paletteDisable(false);
		assertEquals(detailPaletteController.getPalette().isDisabled(), false);
	}
	
	@Test
	public void select_test() {
		detailPaletteController.select(false, false, false, false, false);
		assertEquals(detailPaletteController.getXPosText(), false);
		assertEquals(detailPaletteController.getRadiusText(), false);
		assertEquals(detailPaletteController.getWidthText(), false);
		assertEquals(detailPaletteController.getHeightText(), false);
		assertEquals(detailPaletteController.getAngleText(), false);
		
		detailPaletteController.select(true, true, true, true, true);
		assertEquals(detailPaletteController.getXPosText(), true);
		assertEquals(detailPaletteController.getRadiusText(), true);
		assertEquals(detailPaletteController.getWidthText(), true);
		assertEquals(detailPaletteController.getHeightText(), true);
		assertEquals(detailPaletteController.getAngleText(), true);		
	}*/
}
