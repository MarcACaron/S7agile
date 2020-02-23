package models;

import adraw4us.MainApp;
import adraw4us.Tool;

public class LineTool extends Tool{

	public LineTool() {
		super(new CustomLine());
	}

	@Override
	public void reset() {
		setShape(new CustomLine());
		shape.setStroke(stroke);
		shape.setStrokeWidth(lineWidth);
	}
	
	@Override
	protected void showSelectedShape(MainApp mainApp, CustomShape inputShape) {
		mainApp.getDrawingZoneController().clearSelectionLayer();
		//Not used in our case
	}
	
	@Override
	public String getToolType() {
		return "line";
	}
}
