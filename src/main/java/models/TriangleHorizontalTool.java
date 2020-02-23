package models;

import adraw4us.Tool;

public class TriangleHorizontalTool extends Tool{
	public TriangleHorizontalTool() {
		super(new CustomTriangleHorizontal());
	}

	@Override
	public void reset() {
		setShape(new CustomTriangleHorizontal());
		this.fillShape();
		shape.setStroke(stroke);
		shape.setStrokeWidth(lineWidth);
	}
	
	@Override
	public String getToolType() {
		return "triangleHorizontal";
	}
}
