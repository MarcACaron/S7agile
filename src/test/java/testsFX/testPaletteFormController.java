package testsFX;

import org.junit.jupiter.api.Test;

import javafx.scene.input.MouseButton;


public class testPaletteFormController extends testApplicationUI {
	@Test
	public void testPointeur() {
		clickOn("#pointeur");
	}
	@Test
	public void testMultiPointeur() {
		clickOn("#rectangle");
		moveBy(80, 80);
		drag();
		moveBy(2, 2);
		drop();
		clickOn("#line");
		moveBy(80, 80);
		drag();
		moveBy(2, 2);
		drop();
		clickOn("#circle");
		moveBy(80, 80);
		drag();
		moveBy(2, 2);
		drop();
		clickOn("#multiSelection");
		moveBy(80, 80);
		drag();
		moveBy(200, 200);
		drop();
	}
	@Test
	public void testRectangle() {
		clickOn("#rectangle");
		moveBy(150, 150);
		drag();
		moveBy(150, 150);
		drop();
	}
	@Test
	public void testCircle() {
		clickOn("#circle");
		moveBy(150, 150);
		drag();
		moveBy(150, 150);
		drop();
	}
	@Test
	public void testLine() {
		clickOn("#line");
		moveBy(150, 150);
		drag();
		moveBy(150, 150);
		drop();
	}
	@Test
	public void testStartPoint() {
		clickOn("#ptDepart");
		testLine();
		testCircle();
		testRectangle();
		clickOn("#pointeur");
		moveBy(250, 250);
		clickOn(MouseButton.PRIMARY);
	}
}
