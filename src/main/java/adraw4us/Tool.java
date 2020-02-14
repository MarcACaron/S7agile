package adraw4us;

import java.util.ArrayList;
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
	protected ArrayList<Shape> shapes;
	
	public Tool() {
		this.shapes=new ArrayList<>();
	}
	
	public Tool(Shape shape) {
		this.shapes=new ArrayList<>();
		this.shapes.add(shape);
	}
	public Tool(ArrayList<Shape> shapes) {
		this.shapes=shapes;
	}
	
	public abstract void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd);
	
	public void fillShape() {
		if(this.shapes!=null) {
			this.shapes.forEach(shape -> {
				shape.setFill(fill);
				shape.setAccessibleText(fillName);
			});
		}
	}
	public abstract void reset();
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public void setShape(Shape shape) {
		this.shapes.clear();
		this.shapes.add(shape);
	}
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
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
		int index = pane.getChildren().size()-1 + shapes.size();
		this.reset();
		this.shapes.forEach(shape -> {
			pane.getChildren().add(shape);
		});
		return index;
	}
	
	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		this.ajustOnDrag(posXStart, posYStart, posXEnd, posYEnd);
	}
	
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController pc, DetailPaletteController dp) {
		Shape shape2 = (Shape) pane.getChildren().get(pane.getChildren().size()-1);
		pane.getChildren().remove(pane.getChildren().size()-1);
		mainApp.getDrawingZoneController().applyToCurrentPane(shape2);
		
		shape2.setOnMouseClicked(t2 -> {
			mainApp.getTool().setShape(shape2);
			pc.setLineWidth(shape2.getStrokeWidth());
			pc.setStroke((Color) (shape2.getStroke()));
			
			mainApp.getTool().fillDetails(dp, shape2).apply(null);
			
		});
		fillDetails(dp, shape2).apply(null);
	}

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
	
	public abstract String getToolType();
}
