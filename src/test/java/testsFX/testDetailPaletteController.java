package testsFX;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import javafx.scene.input.KeyCode;

public class testDetailPaletteController extends testApplicationUI{
	@BeforeEach
	public void supprime() {
		this.delPersistance();
	}
	//@Test
	public void testDetailWithCircle() {
		clickOn("#circle");
		moveBy(150, 150);
		drag();
		moveBy(150, 150);
		drop();
		doubleClickOn("#xPosText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#yPosText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#widthText");
		doubleClickOn("#heightText");
		doubleClickOn("#radiusText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#lengthText");
		doubleClickOn("#angleText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		double x = this.mainApp.getTool().getShape().getXPos();
		double y = this.mainApp.getTool().getShape().getYPos();
		double height = this.mainApp.getTool().getShape().getHeight();
		double width = this.mainApp.getTool().getShape().getWidth();
		double rotation = this.mainApp.getTool().getShape().getRotate();
		String type = this.mainApp.getTool().getShape().getType();
		assertEquals(111, x);
		assertEquals(111, y);
		assertEquals(111, rotation);
		assertEquals(111, height);
		assertEquals(111, width);
		assertEquals("circle", type);
	}
	//@Test
	public void testDetailWithRectangle() {
		clickOn("#rectangle");
		moveBy(150, 150);
		drag();
		moveBy(150, 150);
		drop();
		doubleClickOn("#xPosText");
		doubleClickOn("#xPosText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#yPosText");
		doubleClickOn("#yPosText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#widthText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#heightText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#radiusText");
		doubleClickOn("#lengthText");
		doubleClickOn("#angleText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		double x = this.mainApp.getTool().getShape().getXPos();
		double y = this.mainApp.getTool().getShape().getYPos();
		double height = this.mainApp.getTool().getShape().getHeight();
		double width = this.mainApp.getTool().getShape().getWidth();
		double rotation = this.mainApp.getTool().getShape().getRotate();
		String type = this.mainApp.getTool().getShape().getType();
		assertEquals(111, x);
		assertEquals(111, y);
		assertEquals(111, rotation);
		assertEquals(111, height);
		assertEquals(111, width);
		assertEquals("rectangle",type);
	}
	//@Test
	public void testDetailWithLine() {
		clickOn("#line");
		moveBy(80, 80);
		drag();
		moveBy(150, 150);
		drop();
		doubleClickOn("#xPosText");
		doubleClickOn("#xPosText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#yPosText");
		doubleClickOn("#yPosText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#widthText");
		doubleClickOn("#heightText");
		doubleClickOn("#radiusText");
		doubleClickOn("#lengthText");
		doubleClickOn("#lengthText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		doubleClickOn("#angleText");
		doubleClickOn("#angleText");
		type(KeyCode.NUMPAD1,3);
		type(KeyCode.ENTER);
		double x = this.mainApp.getTool().getShape().getXPos();
		double y = this.mainApp.getTool().getShape().getYPos();
		double height = this.mainApp.getTool().getShape().getHeight();
		double width = this.mainApp.getTool().getShape().getWidth();
		double rotation = this.mainApp.getTool().getShape().getRotate();
		String type = this.mainApp.getTool().getShape().getType();
		assertEquals(111, x);
		assertEquals(111, y);
		assertEquals(111, height);
		assertEquals(111, width);
		assertEquals(111, rotation);
		assertEquals("line", type);
	}
}
