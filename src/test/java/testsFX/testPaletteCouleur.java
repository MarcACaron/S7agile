package testsFX;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import adraw4us.Tool;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class testPaletteCouleur extends testApplicationUI{
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
		clickOn("#zoomOut");
		clickOn("#zoomIn");
	}
}
