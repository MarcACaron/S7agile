package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import models.CustomLine;
import models.LineTool;;

public class TestLineTool {
	
	LineTool lineTool = new LineTool();
	
	@Test
	public void testToolReset() {
		lineTool.getShape().setAccessibleHelp("test");
		
		lineTool.reset();
		
		assertNotEquals("test", lineTool.getShape().getAccessibleHelp());
	}
	
	@Test
	public void testAdjustOnDrag1() {
		
		lineTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double startX = ((CustomLine)lineTool.getShape()).getStartX();
		double startY = ((CustomLine)lineTool.getShape()).getStartY();
		double endX = ((CustomLine)lineTool.getShape()).getEndX();
		double endY = ((CustomLine)lineTool.getShape()).getEndY();
		
		assertEquals(2.0, startX);
		assertEquals(2.0, startY);
		assertEquals(4.0, endX);
		assertEquals(4.0, endY);
		
	}
	
	@Test
	public void testAdjustOnDrag2() {
		
		Tool.setStartFromCenter(true);
		lineTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double startX = ((CustomLine)lineTool.getShape()).getStartX();
		double startY = ((CustomLine)lineTool.getShape()).getStartY();
		double endX = ((CustomLine)lineTool.getShape()).getEndX();
		double endY = ((CustomLine)lineTool.getShape()).getEndY();
		
		assertEquals(4.0, startX);
		assertEquals(4.0, startY);
		assertEquals(0.0, endX);
		assertEquals(0.0, endY);
		
	}

}
