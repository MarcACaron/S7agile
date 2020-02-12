package adraw4us;

import java.util.function.Function;

import controller.DetailPaletteController;
import controller.PaletteCouleurController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import models.Transformable;

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
	
	public Function<Object, Object> fillDetails(DetailPaletteController dp, Shape nd){
		return y -> {
			if(nd == null) 
				dp.paletteDisable(true);
			else {
				Transformable tShape = (Transformable) nd;
				dp.select(tShape);
				dp.setTextField(tShape);
			}
			return y;
			};
	}
	
	
	public int mousePressed(DetailPaletteController detailPaletteController, Pane pane) {
		int index = pane.getChildren().size();
		this.reset();
		pane.getChildren().add(this.getShape());
		return index;
	}
	
	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		this.ajustOnDrag(posXStart, posYStart, posXEnd, posYEnd);
	}
	
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController pc, DetailPaletteController dp) {
		Shape shape2 = (Shape) pane.getChildren().get(pane.getChildren().size()-1);
		pane.getChildren().remove(1);
		mainApp.getDrawingZoneController().applyToCurrentPane(shape2);
		
		shape2.setOnMouseClicked(t2 -> {
			mainApp.getTool().setShape(shape2);
			pc.setLineWidth(shape2.getStrokeWidth());
			pc.setStroke((Color) (shape2.getStroke()));
			//this.overlayForm();
			mainApp.getTool().fillDetails(dp, shape2).apply(null);
			
		});
		fillDetails(dp, shape2).apply(null);
	}
	
	public abstract void overlayForm();

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
