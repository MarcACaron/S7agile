package testsFX;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import javafx.scene.input.KeyCode;


public class TestMenuController extends testApplicationUI{
	
	@Test
	public void testFileNew() {
		clickOn("#MenuBarFile");
		clickOn("#menuItemNew");
		clickOn("No");
		//TODO: Tester si on a bien un truc neuf
	}	
	@Test
	@Order(1)
	public void testFileSave() {
		//TODO: Supprimer le fichier avant de faire ce test
		clickOn("#MenuBarFile");
		clickOn("#menuItemSave");
		type(KeyCode.T);
		type(KeyCode.E);
		type(KeyCode.S);
		type(KeyCode.T);
		type(KeyCode.ENTER);
		type(KeyCode.LEFT);
		type(KeyCode.ENTER);
		//TODO: Tester si on a bien le fichier voulu
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
		//TODO: Tester si on a bien la même chose qu'avant la sauvegarde
	}
	@Test
	public void testEdit() {
		clickOn("#MenuBarEdit");
		
	}
	@Test
	public void testLayoutGrid() {
		clickOn("#MenuBarLayout");
		clickOn("#menuShowGridLines");
		pause(3);
		clickOn("#MenuBarLayout");
		clickOn("#menuShowGridLines");
		pause(2);//TODO: Verifier qu'on a bien les grilles
	}
	@Test
	public void testLayoutLayers() {
		clickOn("#MenuBarLayout");
		clickOn("#menuItemLayers");
		clickOn("#newButton");
		clickOn("Layer 0");
		clickOn("Up");
		clickOn("Layer 0");
		clickOn("Down");
		clickOn("Layer 0");
		clickOn("#hideButton");
		clickOn("Layer 0");
		clickOn("#hideButton");
		clickOn("Layer 0");
		//clickOn("Delete"); NE MARCHE PAS
	}
	
	private void pause(int times) {
		for(int i=0; i<times; i++) {
			moveBy(150, 150);
			moveBy(-150, -150);
		}
	}
	/*private MenuBar menuBar;
	
	final String[][] nameArray =
		{		{"File", "New", "Open", "Open as", "Close", "Save", "Save as", "Revert", "Page Setup", "Print", "Quit"},
				{"Edit", "Undo ctl z", "Cut", "Copy","Paste","Clear","Duplicate","Select All ctl","Round Corners","Reshape","Smooth","Unsmooth","Show Clipboard"},
				{"Layout", "Turn Autogrid off","Show rules","Show gridlines","Show size","Show page breaks","Save as","Revert","Page Setup","Print","Quit"},
				{"Pen","1 points","2 points","4 points","6 points","8 points","10 points","Plain line","Dashed line","Autosize line","Arrow at start","Arrow at end","Pens","Dashes","Arrows aa"}, 
				{"Font","Times","Helvetica","Fonts","SetStyle aa"},
				{"Size","9points","10points","12points","14points","18points","24points","36points","48points","Single space","Double space","Font sizes","Line spacing aa"}, 
				{"Help", "New", "Open", "Open as", "Close", "Save", "Save as", "Revert", "Page Setup", "Print", "Quit"}};
	
	/*
	@Test
	public void test_MenuBar_Layout() {
		
		menuBar = find("#menuBar");
		
		clickOn(menuBar);
		WaitForAsyncUtils.waitForFxEvents();
		
		for (int i = 0; i < nameArray.length; i++) {;
			System.out.println("Comparing: " + menuBar.getMenus().get(i).getText() + " to: " + nameArray[i][0]);
			assertEquals(menuBar.getMenus().get(i).getText().compareTo(nameArray[i][0]), 0);
			for (int j = 0; j < nameArray[i].length - 1; j++) {
				System.out.println("Comparing: " + menuBar.getMenus().get(i).getItems().get(j).getText() + " to: " + nameArray[i][j+1]);
				assertEquals(menuBar.getMenus().get(i).getItems().get(j).getText().compareTo(nameArray[i][j+1]), 0);
			}
		}
	}
	
	@Test
	public void test_MenuBar_Click() {
		menuBar = find("#menuBar");
		for (int i = 0; i < nameArray.length; i++) {;
			clickOn(menuBar);
		}
	}
	*/
}
