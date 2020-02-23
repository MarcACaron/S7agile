
package testModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import javafx.scene.shape.Rectangle;
import models.CustomRectangle;
import models.RectangleTool;


public class TestRectangleTool {
	
	
	RectangleTool rectTool = new RectangleTool();
	
	@Test
	public void testToolReset() {
		rectTool.getShape().getDraw().setAccessibleHelp("test");
		
		rectTool.reset();
		
		assertNotEquals("test", rectTool.getShape().getDraw().getAccessibleHelp());
	}
	
	@Test
	public void testAdjustOnDrag1() {

		Tool.setStartFromCenter(false);
		rectTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double width = rectTool.getShape().getWidth();
		double height = rectTool.getShape().getHeight();
		double posX = rectTool.getShape().getXPos();
		double posY = rectTool.getShape().getYPos();
		assertTrue(1.999<width && width<2.01);
		assertTrue(1.999<height && height<2.01);
		assertTrue(1.999<posX && posX<2.01);
		assertTrue(1.999<posY && posY<2.01);
	}
	
	@Test
	public void testAdjustOnDrag2() {

		Tool.setStartFromCenter(true);
		rectTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double width = ((Rectangle)rectTool.getShape().getDraw()).getWidth();
		double height = ((Rectangle)rectTool.getShape().getDraw()).getHeight();
		double posX = ((Rectangle)rectTool.getShape().getDraw()).getX();
		double posY = ((Rectangle)rectTool.getShape().getDraw()).getY();
		

		
		assertTrue(3.999<width && width<4.001);
		assertTrue(3.999<height && height<4.001);
		assertTrue(-0.001<posX && posX<0.001);
		assertTrue(-0.001<posY && posY<0.001);
		assertEquals(4.0, width);
		assertEquals(4.0, height);
		assertEquals(0.0, posX);
		assertEquals(0.0, posY);
		
	}
	
	@Test
	public void testDuplicateMethod() {
		CustomRectangle rect = new CustomRectangle();
		CustomRectangle rectClone = (CustomRectangle) rect.duplicateAndOffset();

		assertEquals(true, rect.getStroke() == rectClone.getStroke());
		assertEquals(true, rect.getStrokeWidth() == rectClone.getStrokeWidth());
		assertEquals(true, rect.getFill() == rectClone.getFill());
		assertEquals(true, rect.getWidth() == rectClone.getWidth());
		assertEquals(true, rect.getHeight() == rectClone.getHeight());
		
	}
	/*
	@Test
	public void testOutline() {
		double PosX = 50, PosY = 50, size = 100;
		CustomRectangle rect = new CustomRectangle(PosX, PosY, size, size);
		double outline[] = rect.getOutlineCoords();
		
		assertEquals(true, outline[0] < PosX);
		assertEquals(true, outline[1] < PosY);
		assertEquals(true, outline[2] > PosX+size);
		assertEquals(true, outline[3] > PosX+size);
	}
	
	@Test
	public void testCenterCoord() {
		double PosX = 50, PosY = 50, size = 100;
		CustomRectangle rect = new CustomRectangle(PosX, PosY, size, size);
		Point2D center = rect.getCenterCoord();
		
		assertEquals(center.getX(), PosX + (size/2));
		assertEquals(center.getY(), PosY + (size/2));
		
	}*/

}
