package testControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.testfx.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import controller.DetailPaletteController;
import adraw4US.MainApp;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.testfx.api.FxAssert;


public class TestMenuController extends testApplicationUI{

	private MenuBar menuBar;
	
	final String[][] nameArray =
		{		{"File", "New", "Open", "Open as", "Close", "Save", "Save as", "Revert", "Page Setup", "Print", "Quit"},
				{"Edit", "Undo ctl z", "Cut", "Copy","Paste","Clear","Duplicate","Select All ctl","Round Corners","Reshape","Smooth","Unsmooth","Show Clipboard"},
				{"Layout", "Turn Autogrid off","Show rules","Show gridlines","Show size","Show page breaks","Save as","Revert","Page Setup","Print","Quit"},
				{"Pen","1 points","2 points","4 points","6 points","8 points","10 points","Plain line","Dashed line","Autosize line","Arrow at start","Arrow at end","Pens","Dashes","Arrows aa"}, 
				{"Font","Times","Helvetica","Fonts","SetStyle aa"},
				{"Size","9points","10points","12points","14points","18points","24points","36points","48points","Single space","Double space","Font sizes","Line spacing aa"}, 
				{"Help", "New", "Open", "Open as", "Close", "Save", "Save as", "Revert", "Page Setup", "Print", "Quit"}};
	
	
	@Test
	public void test_MenuBar_Layout() {
		
		menuBar = find("#menuBar");
		
		clickOn(menuBar);
		WaitForAsyncUtils.waitForFxEvents();
		
		for (int i = 0; i < nameArray.length; i++) {;
			System.out.println("Comparing: " + menuBar.getMenus().get(i).getText() + " to: " + nameArray[i][0]);
			assertEquals(menuBar.getMenus().get(i).getText().compareTo(nameArray[i][0]), 0);
			for (int j = 0; j < nameArray[i].length - 1; j++) {
				System.out.println("Comparing: " + menuBar.getMenus().get(i).getItems().get(j).getText() + " to: " + nameArray[i][j+1]);
				assertEquals(menuBar.getMenus().get(i).getItems().get(j).getText().compareTo(nameArray[i][j+1]), 0);
			}
		}
	}
	
	@Test
	public void test_MenuBar_Click() {
		menuBar = find("#menuBar");
		for (int i = 0; i < nameArray.length; i++) {;
			clickOn(menuBar);
		}
	}
	
}
