package models;

import adraw4us.Tool;

public class LineTool extends Tool{

	public LineTool() {
		super(new CustomLine());
	}

	@Override
	public void reset() {
		setShape(new CustomLine());
		shape.setStroke(stroke);
		shape.setStrokeWidth(lineWidth);
	}
	
	@Override
	public String getToolType() {
		return "line";
	}
}
