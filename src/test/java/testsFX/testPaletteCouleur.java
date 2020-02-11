package testsFX;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import adraw4us.MainApp;
import adraw4us.Tool;
import javafx.scene.paint.Paint;
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
		Paint clickFill = Tool.getFill();
		mainApp.getPaletteCouleurController().fillShape("ananas");
		Paint fill = Tool.getFill();
		assertEquals(fill, clickFill);
	}

}
