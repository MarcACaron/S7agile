package models;


import adraw4us.Tool;

public class RectangleTool extends Tool {

	public RectangleTool() {
		this.shape = new CustomRectangle();
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
		((CustomRectangle) this.shape).setX(posX);
		((CustomRectangle) this.shape).setY(posY);
		((CustomRectangle) this.shape).setWidth(width);
		((CustomRectangle) this.shape).setHeight(height);
	}

	@Override
	public void reset() {
		this.shape = new CustomRectangle();
		this.fillShape();
		this.shape.setStroke(stroke);
		this.shape.setStrokeWidth(lineWidth);
	}
}
