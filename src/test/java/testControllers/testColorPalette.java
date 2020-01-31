package testControllers;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.testfx.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import adraw4US.MainApp;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.testfx.api.FxAssert;


public class testColorPalette extends testApplicationUI {
	private Button fillAnanas;
	private Button fillDirt;
	private Button fillRed;
	private ColorPicker stroke;
	private ChoiceBox lineWidth;

	@Test
	public void test_FillButtons() {
		fillAnanas = find("#fillAnanas");
		fillDirt = find("#fillDirt");
		fillRed = find("#fillRed");
		stroke = find("#stroke");
		lineWidth = find("#lineWidth");
		
		clickOn(fillAnanas);
		clickOn(fillDirt);
		clickOn(fillRed);
		clickOn(stroke);
		clickOn(lineWidth);
		
		//assertEquals(mainApp.getTool().getTool().getAccessibleText().compareTo("Ananas"), 0);
	}
}
