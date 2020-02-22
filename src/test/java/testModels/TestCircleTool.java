package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import javafx.geometry.Point2D;
import models.CircleTool;
import models.Transformable;
import models.CustomCircle;
import models.CustomRectangle;



public class TestCircleTool {
	
	CircleTool circTool = new CircleTool();
	@Test
	public void testToolReset() {
		
		circTool.getShapes().get(0).setAccessibleHelp("test");
		
		circTool.reset();
		
		assertNotEquals("test", circTool.getShapes().get(0).getAccessibleHelp());
	}
	/*
	@Test
	public void testAdjustOnDrag1() {
		
		circTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double centerX = ((CustomCircle)circTool.getShape()).getCenterX();
		double centerY = ((CustomCircle)circTool.getShape()).getCenterY();
		double radius = ((CustomCircle)circTool.getShape()).getRadius();
		
		assertEquals(2.0, centerX);
		assertEquals(2.0, centerY);
		assertEquals(true, 2.50 < radius && radius < 2.83);
		
	}
	*/
	@Test
	public void testAdjustOnDrag2() {
		
		Tool.setStartFromCenter(false);
		circTool.ajustOnDrag(2.0, 2.0, 4.0, 4.0);
		
		double centerX = ((CustomCircle)circTool.getShapes().get(0)).getCenterX();
		double centerY = ((CustomCircle)circTool.getShapes().get(0)).getCenterY();
		double radius = ((CustomCircle)circTool.getShapes().get(0)).getRadius();
		
		assertEquals(3.0, centerX);
		assertEquals(3.0, centerY);
		assertEquals(true, 1.40 < radius && radius < 1.42);
		
	}
	
	@Test
	public void testDuplicateMethod() {
		CustomCircle circle = new CustomCircle();
		CustomCircle circleClone = (CustomCircle) circle.duplicateAndOffset();
		
		assertEquals(circle.getStroke() == circleClone.getStroke(), true);
		assertEquals(circle.getStrokeWidth() == circleClone.getStrokeWidth(), true);
		assertEquals(circle.getFill() == circleClone.getFill(), true);
		assertEquals(circle.getRadius() == circleClone.getRadius(), true);
		assertEquals(circle.getRotation() == circleClone.getRotation(), true);
		
	}
	
	@Test
	public void testOutline() {
		double PosX = 50, PosY = 50, radius = 100;
		CustomCircle circ = new CustomCircle(PosX, PosY, radius);
		double outline[] = circ.getOutlineCoords();
		
		assertEquals(true, outline[0] < PosX - radius);
		assertEquals(true, outline[1] < PosY - radius);
		assertEquals(true, outline[2] > PosX + radius);
		assertEquals(true, outline[3] > PosY + radius);
	}
	
	@Test
	public void testCenterCoord() {
		double PosX = 50, PosY = 50, radius = 100;
		CustomCircle circ = new CustomCircle(PosX, PosY, radius);
		Point2D center = circ.getCenterCoord();
		
		assertEquals(center.getX(), PosX);
		assertEquals(center.getY(), PosY);
		
	}

}
