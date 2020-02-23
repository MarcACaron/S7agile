package models;


import adraw4us.Tool;

public class RectangleTool extends Tool {

	public RectangleTool() {
		super(new CustomRectangle());
	}

	@Override
	public void reset() {
		setShape(new CustomRectangle());
		this.fillShape();
		shape.setStroke(stroke);
		shape.setStrokeWidth(lineWidth);
	}
	
	@Override
	public String getToolType() {
		return "rectangle";
	}
}
