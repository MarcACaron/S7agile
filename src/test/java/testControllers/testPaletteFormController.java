package testControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.testfx.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import adraw4US.MainApp;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.testfx.api.FxAssert;


public class testPaletteFormController extends testApplicationUI {
	
	
    private ToggleButton pointeur;
    private ToggleButton rectangle;
    private ToggleButton circle;
    private ToggleButton line;
    private ToggleButton ptDepart;
	private ImageView ptDepartImage;
	
	@Test
	public void test_PaletteFormLayout() {
		pointeur = find("#pointeur");
		rectangle = find("#rectangle");
		circle = find("#circle");
		line = find("#line");
		ptDepart = find("#ptDepart");
		
		clickOn(pointeur);
		clickOn(rectangle);
		clickOn(circle);
		clickOn(line);
		clickOn(ptDepart);
	}
}
