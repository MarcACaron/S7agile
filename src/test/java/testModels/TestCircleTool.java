package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import adraw4us.Tool;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import models.CircleTool;
import models.CustomCircle;



public class TestCircleTool {
	
	CircleTool circTool = new CircleTool();
	@Test
	public void testToolReset() {
		
		circTool.getShape().getDraw().setAccessibleHelp("test");
		
		circTool.reset();
		
		assertNotEquals("test", circTool.getShape().getDraw().getAccessibleHelp());
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
		
		double centerX = ((Circle)circTool.getShape().getDraw()).getCenterX();
		double centerY = ((Circle)circTool.getShape().getDraw()).getCenterY();
		double radius = ((Circle)circTool.getShape().getDraw()).getRadius();
		
		assertEquals(3.0, centerX);
		assertEquals(3.0, centerY);
		assertEquals(true, 0.99 < radius && radius < 1.001);
		
	}
	
	@Test
	public void testDuplicateMethod() {

		double PosX = 50, PosY = 50, radius = 100;
		CustomCircle circ = new CustomCircle(PosX, PosY, radius);
		CustomCircle circleClone = (CustomCircle) circ.duplicate(10, 10);
		
		assertEquals(circ.getStroke(), circleClone.getStroke());
		assertEquals(circ.getStrokeWidth(), circleClone.getStrokeWidth());
		assertEquals(circ.getFill(), circleClone.getFill());
		assertEquals(circ.getWidth(), circleClone.getWidth());
		assertEquals(circ.getRotate(), circleClone.getRotate());
		
	}
	@Test
	public void testOutline() {
		double PosX = 50, PosY = 50, radius = 10;
		CustomCircle circ = new CustomCircle(PosX, PosY, radius);
		double outline[] = circ.getOutlineCoords();
		
		assertEquals(true, outline[0] <= PosX - radius);
		assertEquals(true, outline[1] <= PosY - radius);
		assertEquals(true, outline[2] >= PosX + radius);
		assertEquals(true, outline[3] >= PosY + radius);
	}
	
	@Test
	public void testCenterCoord() {
		double PosX = 50, PosY = 50, radius = 100;
		CustomCircle circ = new CustomCircle(PosX, PosY, radius);
		Point2D center = circ.getCenterCoord();
		
		assertEquals(center.getX(), PosX+radius);
		assertEquals(center.getY(), PosY+radius);
		
	}

}
