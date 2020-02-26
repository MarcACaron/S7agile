package testsFX;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.input.MouseButton;


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
		assertTrue(299.999<this.mainApp.getTool().getShape().getWidth() && 301>this.mainApp.getTool().getShape().getWidth());
		assertTrue(299.999<this.mainApp.getTool().getShape().getWidth() && 301>this.mainApp.getTool().getShape().getHeight());

	}
	@Test
	public void testCircle() {
		clickOn("#circle");
		moveBy(150, 50);
		drag();
		moveBy(150, 150);
		drop();
		assertEquals("circle", this.mainApp.getTool().getToolType());
		assertTrue(299.999<this.mainApp.getTool().getShape().getWidth() && 301>this.mainApp.getTool().getShape().getWidth());
		assertTrue(299.999<this.mainApp.getTool().getShape().getWidth() && 301>this.mainApp.getTool().getShape().getHeight());
	}
	@Test
	public void testLine() {
		clickOn("#line");
		moveBy(150, -50);
		drag();
		moveBy(150, 150);
		drop();
		assertEquals("line", this.mainApp.getTool().getToolType());
		assertTrue(299.999<this.mainApp.getTool().getShape().getWidth() && 301>this.mainApp.getTool().getShape().getWidth());
		assertTrue(299.999<this.mainApp.getTool().getShape().getWidth() && 301>this.mainApp.getTool().getShape().getHeight());
	}
	@Test
	public void testTriangleHorizontal() {
		clickOn("#triangleHorizontal");
		moveBy(150, 0);
		drag();
		moveBy(100, 100);
		drop();
		assertEquals("triangleHorizontal", this.mainApp.getTool().getToolType());
		assertTrue(199.999<this.mainApp.getTool().getShape().getWidth() && 201>this.mainApp.getTool().getShape().getWidth());
		assertTrue(199.999<this.mainApp.getTool().getShape().getWidth() && 201>this.mainApp.getTool().getShape().getHeight());
	}
	@Test
	public void testTriangleVertical() {
		clickOn("#triangleVertical");
		moveBy(150, 0);
		drag();
		moveBy(100, 100);
		drop();
		assertEquals("triangleVertical", this.mainApp.getTool().getToolType());
		assertTrue(199.999<this.mainApp.getTool().getShape().getWidth() && 201>this.mainApp.getTool().getShape().getWidth());
		assertTrue(199.999<this.mainApp.getTool().getShape().getWidth() && 201>this.mainApp.getTool().getShape().getHeight());
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
}
