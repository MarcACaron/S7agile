package testsFX;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import adraw4us.Tool;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class testPaletteCouleur extends testApplicationUI{
	@FXML
	private AnchorPane anchorPane;
	
	@BeforeEach
	public void supprime() {
		this.delPersistance();
	}
	@Test
	public void testSelectFillColor() {
		clickOn("#fillAnanas");
		String clickFill = Tool.getFillName();
		assertNotNull(mainApp);
		assertNotNull(mainApp.getPaletteCouleurController());
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
		Paint click = Tool.getStroke();
		
		clickOn("#stroke");
		type(KeyCode.UP);
		type(KeyCode.ENTER);
		click = Tool.getStroke();
		assertEquals(Color.LIME, click);
	}
	
	@Test
	public void testSelectStrokeWidth() {
		double strokeW = Tool.getLineWidth();
		
		clickOn("#lineWidth");
		type(KeyCode.DOWN);
		type(KeyCode.ENTER);
		strokeW = Tool.getLineWidth();
		assertEquals(3.0, strokeW);
	}
	
	@Test
	public void testZoom() {
		Pane pane = this.mainApp.getDrawingZoneController().getAnchorPane();
		clickOn("#zoomOut");
		clickOn("#zoomIn");
		
		assertEquals(true, pane.getTransforms().size() > 1);
	}
}
