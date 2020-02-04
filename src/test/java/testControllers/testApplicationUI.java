package testControllers;

import org.testfx.framework.junit5.ApplicationTest;
import adraw4us.MainApp;
import javafx.scene.Node;
import javafx.stage.Stage;

public class testApplicationUI extends ApplicationTest{
	protected MainApp mainApp;
	
	@Override
	public void start(Stage stage) {
		mainApp = new MainApp();
		mainApp.start(stage);
		mainApp.getPrimaryStage().toFront();
	}
	
	public <T extends Node> T find(final String query) {
		return lookup(query).query();
	}
	
}
