package models;

import adraw4us.MainApp;
import adraw4us.Tool;

public class CircleTool extends Tool{
	public CircleTool() {
		super(new CustomCircle());
	}

	@Override
	public void reset() {
		setShape(new CustomCircle());
		this.fillShape();
		shape.setStroke(stroke);
		shape.setStrokeWidth(lineWidth);
	}
	
	@Override
	protected void showSelectedShape(MainApp mainApp, CustomShape inputShape) {
		mainApp.getDrawingZoneController().clearSelectionLayer();
		mainApp.getDrawingZoneController().addSelectionShape(this.getShape().getOutlineCoords());
		
	}
	
	@Override
	public String getToolType() {
		return "circle";
	}
}
