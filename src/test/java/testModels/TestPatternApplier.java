package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import models.CustomCircle;
import models.PatternApplier;

public class TestPatternApplier {
	
	private PatternApplier patternApplier = new PatternApplier();
	
	@Test
	public void testFillRed() {
		CustomCircle shape = new CustomCircle();
		
		shape.setAccessibleText("rouge");
		
		shape = (CustomCircle)patternApplier.setFill(shape);
		
		assertEquals(Color.RED, shape.getFill());
	}
	

}
