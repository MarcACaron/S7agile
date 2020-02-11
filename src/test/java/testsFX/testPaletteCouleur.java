package testsFX;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import adraw4us.MainApp;
import adraw4us.Tool;
import javafx.stage.Stage;

public class testPaletteCouleur extends ApplicationTest{
	MainApp mainApp;
	@Override public void start(Stage stage) {
        mainApp = new MainApp();
        mainApp.start(stage);
    }
	
	@Test
	public void selectColor() {
		clickOn("#fillAnanas");
		String clickFill = Tool.getFillName();
		mainApp.getPaletteCouleurController().fillShape("ananas");
		String fill = Tool.getFillName();
		assertEquals(fill, clickFill);
		clickOn("#fillDirt");
		clickFill = Tool.getFillName();
		mainApp.getPaletteCouleurController().fillShape("dirt");
		fill = Tool.getFillName();
		assertEquals(fill, clickFill);
		clickOn("#fillRed");
		clickFill = Tool.getFillName();
		mainApp.getPaletteCouleurController().fillShape("rouge");
		fill = Tool.getFillName();
		assertEquals(fill, clickFill);
	}

}