package testModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import models.CustomRectangle;
import models.RectangleTool;


public class TestRectangleTool {
	
	
	RectangleTool rectTool = new RectangleTool();
	
	@Test
	public void testToolReset() {
		rectTool.getTool().setAccessibleHelp("test");
		
		rectTool.reset();
		
		assertNotEquals("test", rectTool.getTool().getAccessibleHelp());
	}
	
	@Test
	public void testAdjustOnDrag1() {
		
		rectTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double width = ((CustomRectangle)rectTool.getTool()).getWidth();
		double height = ((CustomRectangle)rectTool.getTool()).getHeight();
		double posX = ((CustomRectangle)rectTool.getTool()).getX();
		double posY = ((CustomRectangle)rectTool.getTool()).getY();
		
		assertEquals(2.0, width);
		assertEquals(2.0, height);
		assertEquals(2.0, posX);
		assertEquals(2.0, posY);
		
	}
	
	@Test
	public void testAdjustOnDrag2() {
		
		Tool.setStartFromCenter(true);
		rectTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double width = ((CustomRectangle)rectTool.getTool()).getWidth();
		double height = ((CustomRectangle)rectTool.getTool()).getHeight();
		double posX = ((CustomRectangle)rectTool.getTool()).getX();
		double posY = ((CustomRectangle)rectTool.getTool()).getY();
		
		assertEquals(4.0, width);
		assertEquals(4.0, height);
		assertEquals(0.0, posX);
		assertEquals(0.0, posY);
		
	}
}
