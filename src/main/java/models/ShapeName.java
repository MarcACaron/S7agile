package models;

import adraw4us.MainApp;

public class ShapeName {
	
	private static ShapeName shapeName;
	public String name;
	public MainApp mainApp;
	private ShapeName(MainApp mainApp) {
		this.mainApp=mainApp;
	}
	public static ShapeName getShapeName() {
		return shapeName;
	}
	public static void init(MainApp mainApp) {
		shapeName = new ShapeName(mainApp);
	}
	public void submitName() {
		mainApp.getTool().getShape().setType(name, mainApp);
	}
	

}
