package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import models.CircleTool;
import models.CustomCircle;



public class TestCircleTool {
	
	CircleTool circTool = new CircleTool();
	
	@Test
	public void testToolReset() {
		circTool.getTool().setAccessibleHelp("test");
		
		circTool.reset();
		
		assertNotEquals("test", circTool.getTool().getAccessibleHelp());
	}
	
	@Test
	public void testAdjustOnDrag1() {
		
		circTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double centerX = ((CustomCircle)circTool.getTool()).getCenterX();
		double centerY = ((CustomCircle)circTool.getTool()).getCenterY();
		double radius = ((CustomCircle)circTool.getTool()).getRadius();
		
		assertEquals(2.0, centerX);
		assertEquals(2.0, centerY);
		assertEquals(true, 2.50 < radius && radius < 2.83);
		
	}
	
	@Test
	public void testAdjustOnDrag2() {
		
		Tool.setStartFromCenter(false);
		circTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double centerX = ((CustomCircle)circTool.getTool()).getCenterX();
		double centerY = ((CustomCircle)circTool.getTool()).getCenterY();
		double radius = ((CustomCircle)circTool.getTool()).getRadius();
		
		assertEquals(3.0, centerX);
		assertEquals(3.0, centerY);
		assertEquals(true, 1.40 < radius && radius < 1.42);
		
	}

}
