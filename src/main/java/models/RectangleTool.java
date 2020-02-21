package models;


import adraw4us.MainApp;
import adraw4us.Tool;

public class RectangleTool extends Tool {

	public RectangleTool() {
		super();
		setShape(new CustomRectangle());
	}

	@Override
	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double posX;
		double posY;
		double width;
		double height;
		if(startFromCenter) {

			posX = Math.min(posXEnd, 2*posXStart-posXEnd);
			posY = Math.min(posYEnd, 2*posYStart-posYEnd);
			width = Math.abs(2*(posXEnd-posXStart));
			height = Math.abs(2*(posYEnd-posYStart));
		}else {
			posX = Math.min(posXStart, posXEnd);
			posY = Math.min(posYStart, posYEnd);
			width = Math.abs(posXEnd - posXStart);
			height = Math.abs(posYEnd - posYStart);
		}
		((CustomRectangle) this.shapes.get(0)).setX(posX);
		((CustomRectangle) this.shapes.get(0)).setY(posY);
		((CustomRectangle) this.shapes.get(0)).setWidth(width);
		((CustomRectangle) this.shapes.get(0)).setHeight(height);
	}

	@Override
	public void reset() {
		this.shapes.clear();
		setShape(new CustomRectangle());
		this.fillShape();
		this.shapes.forEach(shape -> {
			shape.setStroke(stroke);
			shape.setStrokeWidth(lineWidth);
		});
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
