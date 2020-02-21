package models;

import adraw4us.MainApp;
import adraw4us.Tool;

public class CircleTool extends Tool{

	public CircleTool() {
		super();
		setShape(new CustomCircle());
	}

	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double radius;
		CustomCircle circle = (CustomCircle)this.shapes.get(0);
		if(startFromCenter){
			radius = Math.sqrt(Math.pow(posXEnd-posXStart, 2)+Math.pow(posYEnd-posYStart, 2));
			circle.setCenterX(posXStart);
			circle.setCenterY(posYStart);
			circle.setRadius(radius);
		}else {
			radius = Math.sqrt(Math.pow(posXEnd-posXStart,2)+Math.pow(posYStart-posYEnd,2))/2;
			double cX = (posXEnd+posXStart)/2;
			double cY = (posYEnd+posYStart)/2;
			circle.setCenterX(cX);
			circle.setCenterY(cY);
			circle.setRadius(radius);
		}
		
	}
	
	@Override
	public void reset() {
		this.shapes.clear();
		setShape(new CustomCircle());
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
		return "circle";
	}
}
