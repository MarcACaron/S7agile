package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import javafx.scene.shape.Line;
import models.CustomLine;
import models.LineTool;;

public class TestLineTool {
	
	LineTool lineTool = new LineTool();
	
	@Test
	public void testToolReset() {
		lineTool.getShape().getDraw().setAccessibleHelp("test");
		
		lineTool.reset();
		
		assertNotEquals("test", lineTool.getShape().getDraw().getAccessibleHelp());
	}
	/*
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
	*/
	@Test
	public void testAdjustOnDrag2() {
		
		Tool.setStartFromCenter(true);
		lineTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double startX = ((Line)lineTool.getShape().getDraw()).getStartX();
		double startY = ((Line)lineTool.getShape().getDraw()).getStartY();
		double endX = ((Line)lineTool.getShape().getDraw()).getEndX();
		double endY = ((Line)lineTool.getShape().getDraw()).getEndY();
		
		assertEquals(4.0, startX);
		assertEquals(4.0, startY);
		assertEquals(0.0, endX);
		assertEquals(0.0, endY);
		
	}
	
	@Test
	public void testDuplicateMethod() {
		CustomLine line = new CustomLine();
		
		((Line) line.getDraw()).setStartX(0);
		((Line) line.getDraw()).setStartY(0);
		((Line) line.getDraw()).setEndX(40);
		((Line) line.getDraw()).setEndY(45);
		
		CustomLine lineClone = (CustomLine) line.duplicateAndOffset();

		assertEquals(true, line.getStroke() == lineClone.getStroke());
		assertEquals(true, line.getStrokeWidth() == lineClone.getStrokeWidth());
		assertEquals(true, line.getFill() == lineClone.getFill());
		assertEquals(true, line.getWidth() == lineClone.getWidth());
		assertEquals(true, line.getHeight() == lineClone.getHeight());
		assertEquals(true, line.getRotate() == lineClone.getRotate());
		
	}

}
