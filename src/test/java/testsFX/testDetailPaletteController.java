package testControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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

	/*
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
	*/
	/*
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
		
	}*/
	/*
	@Test
	public void test_setget_Functions() {
		detailPalette = find("#detailPalette");
		
		String testValue = "100";
		
		mainApp.getPaletteDetailController().setXPosText(testValue);
		assertEquals(mainApp.getPaletteDetailController().getXPosText().compareTo(testValue),0);
		mainApp.getPaletteDetailController().setYPosText(testValue);
		assertEquals(mainApp.getPaletteDetailController().getYPosText().compareTo(testValue),0);
		mainApp.getPaletteDetailController().setWidthText(testValue);
		assertEquals(mainApp.getPaletteDetailController().getWidthText().compareTo(testValue),0);
		mainApp.getPaletteDetailController().setHeightText(testValue);
		assertEquals(mainApp.getPaletteDetailController().getHeightText().compareTo(testValue),0);
		mainApp.getPaletteDetailController().setRadiusText(testValue);
		assertEquals(mainApp.getPaletteDetailController().getRadiusText().compareTo(testValue),0);
		mainApp.getPaletteDetailController().setLengthText(testValue);
		assertEquals(mainApp.getPaletteDetailController().getLengthText().compareTo(testValue),0);
		mainApp.getPaletteDetailController().setAngleText(testValue);
		assertEquals(mainApp.getPaletteDetailController().getAngleText().compareTo(testValue),0);

	}*/
}
