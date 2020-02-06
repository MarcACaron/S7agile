package models;

import java.util.function.Function;

import adraw4us.Tool;
import controller.DetailPaletteController;
import controller.PaletteCouleurController;
import javafx.scene.shape.Shape;

public class CircleTool extends Tool{

	public CircleTool() {
		this.shape = new CustomCircle();
	}

	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double radius;
		CustomCircle circle = (CustomCircle)this.shape;
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
		this.shape = new CustomCircle();
		this.fillShape();
		this.shape.setStroke(stroke);
		this.shape.setStrokeWidth(lineWidth);
	}
}
