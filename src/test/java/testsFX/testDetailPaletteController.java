package testsFX;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.scene.input.KeyCode;
import models.Transformable;

public class testDetailPaletteController extends testApplicationUI{
	@Test
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
		double x = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getXPos();
		double y = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getYPos();
		double rotation = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getRotation();
		double radius = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getRadius();
		String type = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getType();
		assertEquals(x, 111);
		assertEquals(y, 111);
		assertEquals(rotation, 111);
		assertEquals(radius, 111);
		assertEquals(type, "circle");
	}
	@Test
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
		double x = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getXPos();
		double y = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getYPos();
		double height = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getHeight();
		double width = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getWidth();
		double rotation = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getRotation();
		String type = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getType();
		assertEquals(x, 111);
		assertEquals(y, 111);
		assertEquals(rotation, 111);
		assertEquals(height, 111);
		assertEquals(width, 111);
		assertEquals(type, "rectangle");
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
		double x = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getXPos();
		double y = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getYPos();
		double length = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getLength();
		double rotation = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getRotation();
		String type = ((Transformable)this.mainApp.getTool().getShapes().get(0)).getType();
		assertEquals(x, 111);
		assertEquals(y, 111);
		assertEquals(rotation, 111);
		assertEquals(110.9<length&& length<111.1, true);
		assertEquals(type, "line");
	}
}
