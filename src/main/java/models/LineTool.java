package models;

import adraw4us.Tool;

public class LineTool extends Tool{

	public LineTool() {
		this.shape = new CustomLine();
	}

	@Override
	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		if(startFromCenter) {
			((CustomLine) this.shape).setStartX(posXEnd);
			((CustomLine) this.shape).setStartY(posYEnd);
			((CustomLine) this.shape).setEndX(2*posXStart-posXEnd);
			((CustomLine) this.shape).setEndY(2*posYStart-posYEnd);
		}else {
			((CustomLine) this.shape).setStartX(posXStart);
			((CustomLine) this.shape).setStartY(posYStart);
			((CustomLine) this.shape).setEndX(posXEnd);
			((CustomLine) this.shape).setEndY(posYEnd);
		}
		
	}

	@Override
	public void reset() {
		this.shape = new CustomLine();
		this.shape.setStroke(stroke);
		this.shape.setStrokeWidth(lineWidth);
	}
	
	@Override
	public void overlayForm() {}
}
