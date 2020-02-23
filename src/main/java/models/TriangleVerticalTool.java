package models;

import adraw4us.Tool;

public class TriangleVerticalTool extends Tool{
	public TriangleVerticalTool() {
		super(new CustomTriangleVertical());
	}

	@Override
	public void reset() {
		setShape(new CustomTriangleVertical());
		this.fillShape();
		shape.setStroke(stroke);
		shape.setStrokeWidth(lineWidth);
	}
	
	@Override
	public String getToolType() {
		return "triangleVertical";
	}
}
