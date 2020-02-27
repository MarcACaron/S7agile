package models;

import adraw4us.Tool;

public class CustomDrawTool extends Tool {
	CustomShape base;
	public CustomDrawTool(CustomShape shape) {
		super(shape);
		base=shape;
	}

	@Override
	public void reset() {
		setShape(base.duplicate(0, 0, mainApp));
	}
	
	@Override
	public String getToolType() {
		return "customDraw";
	}

}
