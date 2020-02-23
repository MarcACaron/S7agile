package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import models.CustomCircle;
import models.PatternApplier;

public class TestPatternApplier {
	
	private PatternApplier patternApplier = new PatternApplier();
	
	@Test
	public void testFillRed() {
		CustomCircle shape = new CustomCircle();
		
		patternApplier.fillShape(shape.getDraw(), "rouge");
		
		assertEquals(Color.RED, shape.getFill());
	}
	

}
