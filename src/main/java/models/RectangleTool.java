package models;


import adraw4us.MainApp;
import adraw4us.Tool;

public class RectangleTool extends Tool {

	public RectangleTool() {
		super(new CustomRectangle());
		System.out.println("ok");
	}

	@Override
	public void reset() {
		setShape(new CustomRectangle());
		this.fillShape();
		shape.setStroke(stroke);
		shape.setStrokeWidth(lineWidth);
	}
	
	@Override
	protected void showSelectedShape(MainApp mainApp, Transformable inputShape) {
		mainApp.getDrawingZoneController().clearSelectionLayer();
		mainApp.getDrawingZoneController().addSelectionShape(((Transformable)this.getShapes().get(0)).getOutlineCoords());
		
	}
	
	@Override
	public String getToolType() {
		return "rectangle";
	}
}
