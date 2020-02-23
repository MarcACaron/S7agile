package adraw4us;

import java.util.ArrayList;
import java.util.function.Function;

import controller.DetailPaletteController;
import controller.PaletteCouleurController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import models.CustomShape;
import models.Layer;

public abstract class Tool {
	protected static Paint fill;
	protected static String fillName;
	protected static Paint stroke;
	protected static double lineWidth;
	protected static double lineStyle;
	protected static boolean startFromCenter;
	protected CustomShape shape;
	
	public Tool(CustomShape shape) {
		this.shape=shape;
	}
	
	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		if(startFromCenter)
			this.shape.ajustOnDragFromCenter(posXStart, posYStart, posXEnd, posYEnd);
		else
			this.shape.ajustOnDragFromCorner(posXStart, posYStart, posXEnd, posYEnd);
		/*double startX;
		double startY;
		double width;
		double height;
		if(posXStart<posXEnd) {
			startX=posXStart;
			width = posXEnd-posXStart;
		}else {
			startX=posXEnd;
			width = posXStart-posXEnd;
		}
		if(posYStart<posYEnd) {
			startY=posYStart;
			height = posYEnd-posYStart;
		}else {
			startY=posYEnd;
			height = posYStart-posYEnd;
		}
		this.shape.setXPos(startX);
		this.shape.setYPos(startY);
		this.shape.setWidth(width);
		this.shape.setHeight(height);*/
	}
	
	public void fillShape() {
		if(this.shape!=null) {
			this.shape.setFill(fill, fillName);
		}
	}
	public abstract void reset();
	public CustomShape getShape() {
		return shape;
	}
	
	public void setShape(CustomShape shape) {
		this.shape = shape;
	}
	
	public Function<Object, Object> fillDetails(DetailPaletteController dp, CustomShape nd){
		return y -> {
			if(nd == null) 
				dp.paletteDisable(true);
			else {
				CustomShape tShape = (CustomShape) nd;
				dp.select(tShape);
				dp.setTextField(tShape);
			}
			return y;
			};
	}
	
	
	public int mousePressed(DetailPaletteController detailPaletteController, Layer layer, ArrayList<CustomShape> drawnShapes) {
		int index = layer.getPane().getChildren().size();
		this.reset();
		shape.setLayer(layer.getId());
		drawnShapes.add(shape);
		layer.getPane().getChildren().add(shape.getDraw());
		return index;
	}
	
	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		this.ajustOnDrag(posXStart, posYStart, posXEnd, posYEnd);
	}
	
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController pc, DetailPaletteController dp, ArrayList<CustomShape> drawnShapes) {
		CustomShape shape2 = this.shape;
		pane.getChildren().remove(pane.getChildren().size()-1);
		mainApp.getDrawingZoneController().applyToCurrentPane(shape2.getDraw());
		
		shape2.getDraw().setOnMouseClicked(t2 -> {
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
