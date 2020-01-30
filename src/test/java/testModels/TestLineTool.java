package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import ddraw4US.LineTool;
import models.CustomLine;;

public class TestLineTool {
	
	LineTool lineTool = new LineTool();
	
	@Test
	public void testToolReset() {
		lineTool.tool.setAccessibleHelp("test");
		
		lineTool.reset();
		
		assertNotEquals("test", lineTool.tool.getAccessibleHelp());
	}
	
	@Test
	public void testAdjustOnDrag1() {
		
		lineTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double startX = ((CustomLine)lineTool.tool).getStartX();
		double startY = ((CustomLine)lineTool.tool).getStartY();
		double endX = ((CustomLine)lineTool.tool).getEndX();
		double endY = ((CustomLine)lineTool.tool).getEndY();
		
		assertEquals(2.0, startX);
		assertEquals(2.0, startY);
		assertEquals(4.0, endX);
		assertEquals(4.0, endY);
		
	}
	
	@Test
	public void testAdjustOnDrag2() {
		
		lineTool.startFromCenter = true;
		lineTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double startX = ((CustomLine)lineTool.tool).getStartX();
		double startY = ((CustomLine)lineTool.tool).getStartY();
		double endX = ((CustomLine)lineTool.tool).getEndX();
		double endY = ((CustomLine)lineTool.tool).getEndY();
		
		assertEquals(4.0, startX);
		assertEquals(4.0, startY);
		assertEquals(0.0, endX);
		assertEquals(0.0, endY);
		
	}

}
