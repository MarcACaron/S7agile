package models;

import java.util.function.Function;

import controller.DetailPaletteController;
import adraw4US.Tool;
import javafx.scene.shape.Shape;

public class SelectionTool extends Tool {

	public SelectionTool() {
		// TODO Auto-generated constructor stub
	}

	public SelectionTool(Shape tool) {
		super(tool);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		
	}

	@Override
	public void reset() {
		this.tool = null;
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
