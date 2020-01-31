package models;

import java.util.function.Function;

import controller.DetailPaletteController;
import adraw4US.Tool;
import javafx.scene.shape.Shape;

public class CircleTool extends Tool{

	public CircleTool() {
		this.tool = new CustomCircle();
	}

	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double radius;
		CustomCircle circle = (CustomCircle)this.tool;
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
		this.tool = new CustomCircle();
		this.fillShape();
		this.tool.setStroke(stroke);
		this.tool.setStrokeWidth(lineWidth);
	}

	@Override
	public Function<Object, Object> fillDetails(DetailPaletteController pc, Shape nd) {
		return (y) -> {
			pc.select(true, true, false, true, false);
			CustomCircle circle = (CustomCircle)nd;
			pc.setTextField(circle.getCenterX(), circle.getCenterY(), 0.0, 0.0, circle.getRadius(), 0.0, circle.getRotate());
			return y;
			};
	}
}
