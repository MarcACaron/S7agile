package models;

import java.util.function.Function;

import adraw4us.Tool;
import controller.DetailPaletteController;
import javafx.scene.shape.Shape;

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
	public Function<Object, Object> fillDetails(DetailPaletteController pc, Shape nd) {
		return (y) -> {
			pc.select(true, true, true, false, false);
			CustomLine line = (CustomLine)nd;
			pc.setTextField(line.getStartX(), line.getStartY(), 0.0, 0.0, 0.0, Math.sqrt(Math.pow((line.getStartX()-line.getStartY()), 2) + Math.pow((line.getEndX()-line.getEndY()), 2)), line.getRotate());
			return y;
			};
	}
}
