package testModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import models.CustomLine;
import models.CustomRectangle;
import models.RectangleTool;


public class TestRectangleTool {
	
	
	RectangleTool rectTool = new RectangleTool();
	
	@Test
	public void testToolReset() {
		rectTool.getShape().setAccessibleHelp("test");
		
		rectTool.reset();
		
		assertNotEquals("test", rectTool.getShape().getAccessibleHelp());
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
		
		double width = ((CustomRectangle)rectTool.getShape()).getWidth();
		double height = ((CustomRectangle)rectTool.getShape()).getHeight();
		double posX = ((CustomRectangle)rectTool.getShape()).getX();
		double posY = ((CustomRectangle)rectTool.getShape()).getY();
		
		assertEquals(4.0, width);
		assertEquals(4.0, height);
		assertEquals(0.0, posX);
		assertEquals(0.0, posY);
		
	}
	
	@Test
	public void testDuplicateMethod() {
		CustomRectangle rect = new CustomRectangle();
		CustomRectangle rectClone = (CustomRectangle) rect.duplicate();

		assertEquals(true, rect.getStroke() == rectClone.getStroke());
		assertEquals(true, rect.getStrokeWidth() == rectClone.getStrokeWidth());
		assertEquals(true, rect.getFill() == rectClone.getFill());
		assertEquals(true, rect.getWidth() == rectClone.getWidth());
		assertEquals(true, rect.getHeight() == rectClone.getHeight());
		
	}
}
