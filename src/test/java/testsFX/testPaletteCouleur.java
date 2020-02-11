package testsFX;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;

import adraw4us.MainApp;
import adraw4us.Tool;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
//import tools.LibraryForTest;
//import tools.Memory

@ExtendWith(ApplicationExtension.class)
public class testPaletteCouleur extends FxRobot {
	//Attributes
	private MainApp mainApp = new MainApp();
	
	//Methods are create for reduce redundancy
	public MainApp getMainApp() {
		return this.mainApp;
	}

	@Start
	public void onStart(Stage stage) throws ClassNotFoundException, IOException, URISyntaxException {
		this.getMainApp().start(stage);
	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	/* After each test, reboot the window and actions */
	public void tearDown(FxRobot robot) throws Exception {
		/* Robot click on edit menu */
		robot.clickOn("#editMenu");
		/* Robot click on the item menu clear to erase all figures */
		robot.clickOn("#clear");
		release(new KeyCode[] {});
		release(new MouseButton[] {});
		//Memory.getMemoryInfo();
	}
	
	@Stop
	public void stop() {
		//this.getMainApp().freeMemory();
	}
	
	@BeforeAll
	public static void end() {
		System.gc();
	}

	
	@Test
	public void testSelectFillColor() {
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
	
	@Test
	public void testSelectStrokeColor() {
		clickOn("#stroke");
	}
	
	@Test
	public void selectStrokeWidth() {
		clickOn("#lineWidth");
	}

}
