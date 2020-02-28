package testsFX;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import controller.FileController;
import javafx.scene.input.KeyCode;
import models.LayersGroup;


public class TestMenuController extends testApplicationUI{
	
	LayersGroup layersGroup = LayersGroup.getLayersGroup();
	
	FileController fileCont = FileController.getInstance();
	
	@Test
	public void testFileNew() {
		clickOn("#MenuBarFile");
		clickOn("#menuItemNew");
		clickOn("No");
		assertEquals(1, layersGroup.getLayers().size());
	}	
	@Test
	@Order(1)
	public void testFileSave() {
		clickOn("#MenuBarFile");
		clickOn("#menuItemSave");
		type(KeyCode.T);
		type(KeyCode.E);
		type(KeyCode.S);
		type(KeyCode.T);
		type(KeyCode.ENTER);
		type(KeyCode.LEFT);
		type(KeyCode.ENTER);
		
		File f = new File("test.xml");
		
		assertEquals(false, f.exists());
	}
	@Test
	@Order(2)
	public void testFileOpen() {
		clickOn("#rectangle");
		moveBy(150, 150);
		drag();
		moveBy(150, 150);
		drop();
		clickOn("#MenuBarFile");
		clickOn("#menuItemOpen");
		type(KeyCode.ENTER);//sauvegarde
		type(KeyCode.T);
		type(KeyCode.E);
		type(KeyCode.S);
		type(KeyCode.T);
		type(KeyCode.ENTER);
		type(KeyCode.LEFT);
		type(KeyCode.ENTER);
		type(KeyCode.T);
		type(KeyCode.E);
		type(KeyCode.S);
		type(KeyCode.T);
		type(KeyCode.ENTER);
		
		assertEquals(true, fileCont.getCurrentFile().length() > 0);
		
	}
	@Test
	public void testLayoutGrid() {
		clickOn("#MenuBarLayout");
		clickOn("#menuShowGridLines");
		clickOn("#MenuBarLayout");
		clickOn("#menuShowGridLines");
		assertEquals(true,this.mainApp.getDrawingZoneController().getLineGridPaneVisibility());
	}
	@Test
	public void testLayoutLayers() {
		clickOn("#MenuBarLayout");
		clickOn("#menuItemLayers");
		clickOn("#newButton");
		assertEquals(2, layersGroup.getLayers().size());
		clickOn("Layer 0");
		clickOn("Up");
		assertEquals("Layer 0", layersGroup.getLayers().get(0).getId());
		clickOn("Layer 0");
		clickOn("Down");
		clickOn("Layer 0");
		clickOn("#hideButton");
		clickOn("Layer 0");
		clickOn("#hideButton");
		clickOn("Layer 1");
		clickOn("Delete");
		
		assertEquals(1, layersGroup.getLayers().size());
	}
	
	@Test
	public void testShapeHistory() {
		clickOn("#rectangle");
		moveBy(150, 50);
		drag();
		moveBy(150, 150);
		drop();
		
		moveBy(-150, -50);
		drag();
		moveBy(150, 150);
		drop();
		
		clickOn("#MenuBarLayout");
		clickOn("#menuItemShapeOrder");
		
		String a = layersGroup.getCurrentLayer().getDrawnShapes().get(0).toString();
		
		clickOn(layersGroup.getCurrentLayer().getDrawnShapes().get(0).toString());
		
		clickOn("Up");
		
		clickOn(a);
		
		clickOn("Down");
		
		assertEquals(a, layersGroup.getCurrentLayer().getDrawnShapes().get(0).toString());
		
	}
	
	private void pause(int times) {
		for(int i=0; i<times; i++) {
			moveBy(150, 150);
			moveBy(-150, -150);
		}
	}
}
