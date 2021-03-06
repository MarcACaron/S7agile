package testsFX;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class testPaletteFormController extends testApplicationUI {
	@BeforeEach
	public void supprime() {
		this.delPersistance();
	}
	@Test
	public void testPointeur() {
		clickOn("#pointeur");
		assertEquals("selection", this.mainApp.getTool().getToolType());
	}
	@Test
	public void testMultiPointeur() {
		clickOn("#rectangle");
		assertEquals("rectangle", this.mainApp.getTool().getToolType());
		moveBy(100, 0);
		drag();
		moveBy(2, 2);
		drop();
		clickOn("#line");
		moveBy(100, 0);
		drag();
		moveBy(2, 2);
		drop();
		clickOn("#circle");
		moveBy(100, 0);
		drag();
		moveBy(2, 2);
		drop();
		clickOn("#multiSelection");
		moveBy(100, 0);
		drag();
		moveBy(200, 200);
		drop();
	}
	@Test
	public void testRectangle() {
		clickOn("#rectangle");
		moveBy(150, 50);
		drag();
		moveBy(150, 150);
		drop();
		assertEquals("rectangle", this.mainApp.getTool().getToolType());

	}
	@Test
	public void testCircle() {
		clickOn("#circle");
		moveBy(150, 50);
		drag();
		moveBy(150, 150);
		drop();
		assertEquals("circle", this.mainApp.getTool().getToolType());
	}
	@Test
	public void testLine() {
		clickOn("#line");
		moveBy(150, -50);
		drag();
		moveBy(150, 150);
		drop();
		assertEquals("line", this.mainApp.getTool().getToolType());
	}
	@Test
	public void testSelection() {
		clickOn("#pointeur");
		moveBy(150, 100);
		clickOn();
		drop();
		
		assertEquals("selection", this.mainApp.getTool().getToolType());
	}
	
	@Test
	public void testTriangleHorizontal() {
		clickOn("#triangleHorizontal");
		moveBy(150, 0);
		drag();
		moveBy(100, 100);
		drop();
		assertEquals("triangleHorizontal", this.mainApp.getTool().getToolType());
	

	}
	@Test
	public void testTriangleVertical() {
		clickOn("#triangleVertical");
		moveBy(200, 0);
		drag();
		moveBy(100, 100);
		drop();
		assertEquals("triangleVertical", this.mainApp.getTool().getToolType());
	}
	@Test
	public void testStartPoint() {
		clickOn("#ptDepart");
		testLine();
		testCircle();
		testRectangle();
		testTriangleHorizontal();
		testTriangleVertical();
	}
	
	@Test
	public void testFlip() {
		clickOn("#triangleVertical");
		moveBy(200, 0);
		drag();
		moveBy(100, 100);
		drop();
		
		clickOn("#hFlipButton");
		clickOn("#vFlipButton");
		assertTrue(this.mainApp.getTool().getShape().getHFlip());
		assertTrue(this.mainApp.getTool().getShape().getVFlip());
		
	}
	
	@Test
	public void testRightClick() {
		clickOn("#rectangle");
		moveBy(200, 0);
		drag();
		moveBy(100, 100);
		drop();
		
		moveBy(-50,-50);
		rightClickOn();
		clickOn("Flip Vertical");
		drop();
		
		assertTrue(this.mainApp.getTool().getShape().getVFlip());
	}
}
