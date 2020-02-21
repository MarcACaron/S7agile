package models;

import adraw4us.MainApp;
import adraw4us.Tool;

public class LineTool extends Tool{

	public LineTool() {
		super();
		setShape(new CustomLine());
	}

	@Override
	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		if(startFromCenter) {
			((CustomLine) this.shapes.get(0)).setStartX(posXEnd);
			((CustomLine) this.shapes.get(0)).setStartY(posYEnd);
			((CustomLine) this.shapes.get(0)).setEndX(2*posXStart-posXEnd);
			((CustomLine) this.shapes.get(0)).setEndY(2*posYStart-posYEnd);
		}else {
			((CustomLine) this.shapes.get(0)).setStartX(posXStart);
			((CustomLine) this.shapes.get(0)).setStartY(posYStart);
			((CustomLine) this.shapes.get(0)).setEndX(posXEnd);
			((CustomLine) this.shapes.get(0)).setEndY(posYEnd);
		}
		
	}

	@Override
	public void reset() {
		this.shapes.clear();
		setShape(new CustomLine());
		this.shapes.forEach(shape -> {
			shape.setStroke(stroke);
			shape.setStrokeWidth(lineWidth);
		});
	}
	
	@Override
	protected void showSelectedShape(MainApp mainApp, Transformable inputShape) {
		mainApp.getDrawingZoneController().clearSelectionLayer();
		//Not used in our case
	}
	
	@Override
	public String getToolType() {
		return "line";
	}
}
