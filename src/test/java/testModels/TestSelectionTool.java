package testModels;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import models.SelectionTool;


public class TestSelectionTool {
	
	SelectionTool selTool = new SelectionTool();
	
	@Test
	public void testToolReset() {
		
		selTool.reset();
		
		assertNotEquals(null, selTool);
	}
	
}
