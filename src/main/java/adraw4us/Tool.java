package adraw4us;

import java.util.function.Function;

import controller.DetailPaletteController;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class Tool {
	public static Paint fill;
	public static String fillName;
	public static Paint stroke;
	public static double lineWidth;
	public static double lineStyle;
	public static boolean startFromCenter;
	public Shape tool;
	
	public Tool() {
	}
	
	public Tool(Shape tool) {
		this.tool = tool;
	}
	
	public abstract void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd);
	
	public void fillShape() {
		if(this.tool!=null) {
			this.tool.setFill(fill);
			this.tool.setAccessibleText(fillName);
		}
	}
	public abstract void reset();
	public Shape getTool() {
		return tool;
	}

	public void setTool(Shape tool) {
		this.tool = tool;
	}
	
	public abstract Function<Object, Object> fillDetails(DetailPaletteController paletteDetailController, Shape nd);
}
