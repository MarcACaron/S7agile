package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import javafx.geometry.Point2D;
import models.CustomCircle;
import models.CustomLine;
import models.LineTool;;

public class TestLineTool {
	
	LineTool lineTool = new LineTool();
	
	@Test
	public void testToolReset() {
		lineTool.getShapes().get(0).setAccessibleHelp("test");
		
		lineTool.reset();
		
		assertNotEquals("test", lineTool.getShapes().get(0).getAccessibleHelp());
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
		
		double startX = ((CustomLine)lineTool.getShapes().get(0)).getStartX();
		double startY = ((CustomLine)lineTool.getShapes().get(0)).getStartY();
		double endX = ((CustomLine)lineTool.getShapes().get(0)).getEndX();
		double endY = ((CustomLine)lineTool.getShapes().get(0)).getEndY();
		
		assertEquals(4.0, startX);
		assertEquals(4.0, startY);
		assertEquals(0.0, endX);
		assertEquals(0.0, endY);
		
	}
	
	@Test
	public void testDuplicateMethod() {
		CustomLine line = new CustomLine();
		
		line.setStartX(0);
		line.setStartY(0);
		line.setEndX(40);
		line.setEndY(45);
		
		CustomLine lineClone = (CustomLine) line.duplicateAndOffset();

		assertEquals(true, line.getStroke() == lineClone.getStroke());
		assertEquals(true, line.getStrokeWidth() == lineClone.getStrokeWidth());
		assertEquals(true, line.getFill() == lineClone.getFill());
		assertEquals(true, line.getLength() == lineClone.getLength());
		assertEquals(true, line.getRotation() == lineClone.getRotation());
		
	}
	
	@Test
	public void testOutline() {
		double startX = 50, startY = 50, endX = 100, endY = 100;
		CustomLine line = new CustomLine(startX, startY, endX, endY);
		double outline[] = line.getOutlineCoords();
		
		assertEquals(true, outline[0] < startX);
		assertEquals(true, outline[1] < startY);
		assertEquals(true, outline[2] > endX);
		assertEquals(true, outline[3] > endY);
	}
	
	@Test
	public void testCenterCoord() {
		double startX = 50, startY = 50, endX = 100, endY = 100;
		CustomLine line = new CustomLine(startX, startY, endX, endY);
		Point2D center = line.getCenterCoord();
		
		assertEquals(center.getX(), startX + (endX - startX)/2);
		assertEquals(center.getY(), startY + (endY - startY)/2);
		
	}

}
