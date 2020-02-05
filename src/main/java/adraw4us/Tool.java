package adraw4us;

import java.util.function.Function;

import controller.DetailPaletteController;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class Tool {
	protected static Paint fill;
	protected static String fillName;
	protected static Paint stroke;
	protected static double lineWidth;
	protected static double lineStyle;
	protected static boolean startFromCenter;
	protected Shape shape;
	
	public Tool() {
	}
	
	public Tool(Shape shape) {
		this.shape = shape;
	}
	
	public abstract void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd);
	
	public void fillShape() {
		if(this.shape!=null) {
			this.shape.setFill(fill);
			this.shape.setAccessibleText(fillName);
		}
	}
	public abstract void reset();
	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public abstract Function<Object, Object> fillDetails(DetailPaletteController paletteDetailController, Shape nd);

	public static Paint getFill() {
		return fill;
	}

	public static String getFillName() {
		return fillName;
	}

	public static Paint getStroke() {
		return stroke;
	}

	public static double getLineWidth() {
		return lineWidth;
	}

	public static double getLineStyle() {
		return lineStyle;
	}

	public static boolean isStartFromCenter() {
		return startFromCenter;
	}

	public static void setFill(Paint fill) {
		Tool.fill = fill;
	}

	public static void setFillName(String fillName) {
		Tool.fillName = fillName;
	}

	public static void setStroke(Paint stroke) {
		Tool.stroke = stroke;
	}

	public static void setLineWidth(double lineWidth) {
		Tool.lineWidth = lineWidth;
	}

	public static void setLineStyle(double lineStyle) {
		Tool.lineStyle = lineStyle;
	}

	public static void setStartFromCenter(boolean startFromCenter) {
		Tool.startFromCenter = startFromCenter;
	}
}
