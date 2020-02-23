package testsFX;

import java.io.File;

import org.testfx.framework.junit5.ApplicationTest;
import adraw4us.MainApp;
import javafx.scene.Node;
import javafx.stage.Stage;

public class testApplicationUI extends ApplicationTest{
	protected MainApp mainApp;
	protected Stage stage;
	@Override
	public void start(Stage stage) {
		mainApp = new MainApp();
		this.stage = stage;
		this.mainApp.start(stage);
		mainApp.getPrimaryStage().toFront();
	}
	
	public <T extends Node> T find(final String query) {
		return lookup(query).query();
	}
	
	public void delPersistance() {
		File persistanceFile = new File("persistance.ini");
		persistanceFile.delete();
	}
}
