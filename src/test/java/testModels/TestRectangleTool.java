
package testModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import javafx.geometry.Point2D;
import models.CustomLine;
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
		/*
		rectTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double width = ((CustomRectangle)rectTool.getShape()).getWidth();
		double height = ((CustomRectangle)rectTool.getShape()).getHeight();
		double posX = ((CustomRectangle)rectTool.getShape()).getX();
		double posY = ((CustomRectangle)rectTool.getShape()).getY();
		
		assertEquals(2.0, width);
		assertEquals(2.0, height);
		assertEquals(2.0, posX);
		assertEquals(2.0, posY);
		*/
	}
	
	@Test
	public void testAdjustOnDrag2() {
		
		Tool.setStartFromCenter(true);
		rectTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double width = ((Rectangle)rectTool.getShape().getDraw()).getWidth();
		double height = ((Rectangle)rectTool.getShape().getDraw()).getHeight();
		double posX = ((Rectangle)rectTool.getShape().getDraw()).getX();
		double posY = ((Rectangle)rectTool.getShape().getDraw()).getY();
		
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
