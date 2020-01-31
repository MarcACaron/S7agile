package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import models.CircleTool;
import models.CustomCircle;



public class TestCircleTool {
	
	CircleTool circTool = new CircleTool();
	
	@Test
	public void testToolReset() {
		circTool.tool.setAccessibleHelp("test");
		
		circTool.reset();
		
		assertNotEquals("test", circTool.tool.getAccessibleHelp());
	}
	
	@Test
	public void testAdjustOnDrag1() {
		
		circTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double centerX = ((CustomCircle)circTool.tool).getCenterX();
		double centerY = ((CustomCircle)circTool.tool).getCenterY();
		double radius = ((CustomCircle)circTool.tool).getRadius();
		
		assertEquals(2.0, centerX);
		assertEquals(2.0, centerY);
		assertEquals(true, 2.50 < radius && radius < 2.83);
		
	}
	
	@Test
	public void testAdjustOnDrag2() {
		
		circTool.startFromCenter = false;
		circTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double centerX = ((CustomCircle)circTool.tool).getCenterX();
		double centerY = ((CustomCircle)circTool.tool).getCenterY();
		double radius = ((CustomCircle)circTool.tool).getRadius();
		
		assertEquals(3.0, centerX);
		assertEquals(3.0, centerY);
		assertEquals(true, 1.40 < radius && radius < 1.42);
		
	}

}
