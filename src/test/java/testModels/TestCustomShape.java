package testModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import models.CustomRectangle;
import models.CustomShape;

class TestCustomShape {
	private static CustomShape shape;
	double PosX = 50, PosY = 50, size = 100;
	@Test
	void test() {
		/*shape.duplicate()
		shape.duplicateAndOffset()
		shape.getDraw()
		shape.getType()*/
		
	}
	
	@BeforeAll
	public static void setShape() {
		double PosX = 50, PosY = 50, size = 100;
		shape = new CustomRectangle(PosX, PosY, size, size);
	}
	
	@Test
	public void testHFlip() {
		shape.flipShape(0, false);
		assertEquals(true, shape.getHFlip());
	}
	
	@Test
	public void testAssertHFlipTransform() {
		shape.flipShape(0, false);
		assertEquals(true, shape.getDraw().getTransforms().size() > 0);
	}
	
	@Test
	public void testVFlip() {
		shape.flipShape(1, false);
		assertEquals(true, shape.getVFlip());
	}
	
	@Test
	public void testAssertVFlipTransform() {
		shape.flipShape(1, false);
		assertEquals(true, shape.getDraw().getTransforms().size() > 0);
	}
	
	@Test
    public void testCenterCoord() {
	    
		Point2D center = shape.getCenterCoord();
		assertEquals(center.getX(), PosX + (size/2));
		assertEquals(center.getY(), PosY + (size/2));
    }
	
	@Test
	public void testGetDraw() {
		
	}
	
	@Test
	public void testFill() {
		shape.setFill(Color.BLACK, "nothing");
		assertEquals(true, shape.getFill() == Color.BLACK);
	}
	
	@Test
	public void setHeight() {
		shape.setHeight(10);
		assertEquals(10, shape.getHeight());
		shape.setHeight(100);
		assertEquals(100, shape.getHeight());
	}
	
	@Test
	public void testLayer() {
		shape.setLayer("Layer 2");
		assertEquals("Layer 2", shape.getLayer());
	}
	
	@Test
	public void testPosition() {
		shape.setXPosition(20);
		shape.setYPosition(30);
		
		assertEquals(20, shape.getXPos());
		assertEquals(30, shape.getYPos());
	}
	
	@Test
	public void testDuplicate() {
		CustomShape cloneShape = shape.duplicate(10, 10);
		
		assertEquals(shape.getStroke(), cloneShape.getStroke());
		assertEquals(shape.getStrokeWidth(), cloneShape.getStrokeWidth());
		assertEquals(shape.getFill(), cloneShape.getFill());
		assertEquals(shape.getWidth(), cloneShape.getWidth());
		assertEquals(shape.getHeight(), cloneShape.getHeight());
	}
}

