package testModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.Test;

import models.CustomRectangle;
import models.RectangleTool;


public class TestRectangleTool {
	
	
	RectangleTool rectTool = new RectangleTool();
	
	@Test
	public void testToolReset() {
		rectTool.tool.setAccessibleHelp("test");
		
		rectTool.reset();
		
		assertNotEquals("test", rectTool.tool.getAccessibleHelp());
	}
	
	@Test
	public void testAdjustOnDrag1() {
		
		rectTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double width = ((CustomRectangle)rectTool.tool).getWidth();
		double height = ((CustomRectangle)rectTool.tool).getHeight();
		double posX = ((CustomRectangle)rectTool.tool).getX();
		double posY = ((CustomRectangle)rectTool.tool).getY();
		
		assertEquals(2.0, width);
		assertEquals(2.0, height);
		assertEquals(2.0, posX);
		assertEquals(2.0, posY);
		
	}
	
	@Test
	public void testAdjustOnDrag2() {
		
		rectTool.startFromCenter = true;
		rectTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double width = ((CustomRectangle)rectTool.tool).getWidth();
		double height = ((CustomRectangle)rectTool.tool).getHeight();
		double posX = ((CustomRectangle)rectTool.tool).getX();
		double posY = ((CustomRectangle)rectTool.tool).getY();
		
		assertEquals(4.0, width);
		assertEquals(4.0, height);
		assertEquals(0.0, posX);
		assertEquals(0.0, posY);
		
	}
}
