package models;

import java.util.function.Function;

import adraw4us.Tool;
import controller.DetailPaletteController;
import javafx.scene.shape.Shape;

public class SelectionTool extends Tool {

	public SelectionTool() {
	}

	public SelectionTool(Shape tool) {
		super(tool);
	}

	@Override
	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		//No need as he might not draw for now
	}

	@Override
	public void reset() {
		this.shape = null;
	}

	@Override
	public Function<Object, Object> fillDetails(DetailPaletteController pc, Shape nd) {
		return (y) -> {
			pc.paletteDisable(true);
			pc.setTextField(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
			
			return y;
			};
	}
}
