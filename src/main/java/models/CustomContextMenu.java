package models;

import adraw4us.MainApp;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class CustomContextMenu extends ContextMenu {

	private MainApp mainApp;
	private CustomShape shape;

	public CustomContextMenu(MainApp mainApp, CustomShape shape) {
		this.mainApp = mainApp;
		this.shape = shape;
	}

	public void setItems() {
		if(this.mainApp != null) {
			MenuItem itemFlipH = new MenuItem("Flip Horizontal");

			itemFlipH.setOnAction( e -> this.shape.flipShape(0, false));

			MenuItem itemFlipV = new MenuItem("Flip Vertical");

			itemFlipV.setOnAction( e -> this.shape.flipShape(1, false));
			this.getItems().addAll(itemFlipH,itemFlipV);
		}


	}

}
