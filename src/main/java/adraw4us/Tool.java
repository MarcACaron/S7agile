package adraw4us;

import java.util.function.Function;

import controller.DetailPaletteController;
import controller.PaletteCouleurController;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import models.CustomContextMenu;
import models.CustomShape;
import models.Layer;
import models.LayersGroup;
import models.ShapeName;

public abstract class Tool {

	protected static Paint fill;
	protected static String fillName;
	protected static Paint stroke;
	protected static double lineWidth;
	protected static double lineStyle;
	protected static boolean startFromCenter;
	protected CustomShape shape;

	protected LayersGroup layerGroup = LayersGroup.getLayersGroup();

	public Tool(CustomShape shape) {
		this.shape = shape;
	}

	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		if(startFromCenter)
			this.shape.ajustOnDragFromCenter(posXStart, posYStart, posXEnd, posYEnd);
		else
			this.shape.ajustOnDragFromCorner(posXStart, posYStart, posXEnd, posYEnd);
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

	public Function<Object, Object> fillDetails(DetailPaletteController dp, CustomShape nd, MainApp mainApp){
		return y -> {
			if(nd == null) {
				dp.paletteDisable(true);
				ShapeName.getShapeName().name = null;
			}
			else {
				CustomShape tShape = (CustomShape) nd;
				dp.select(tShape);
				dp.setTextField(tShape);
				showSelectedShape(mainApp);
				ShapeName.getShapeName().name = nd.getType();
			}
			return y;
		};
	}
	
	
	public int mousePressed(DetailPaletteController detailPaletteController, Layer layer, MainApp mainApp) {
		int index = layer.getPane().getChildren().size();
		this.reset();
		shape.setLayer(layer.getId());
		layer.getDrawnShapes().add(shape);
		shape.draw(layer);
		return index;
	}

	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		this.ajustOnDrag(posXStart, posYStart, posXEnd, posYEnd);
	}
	
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController pc, DetailPaletteController dp) {
		CustomShape shape2 = this.shape;
		pane.getChildren().remove(pane.getChildren().size()-1);

		Pane currentPane = layerGroup.getCurrentLayer().getPane();
		int sizePane = currentPane.getChildren().size();

		shape2.getDraw().setId(shape2.getType() + sizePane + " " + shape2.getLayer() );

		mainApp.getDrawingZoneController().applyToCurrentPane(shape2.getDraw());

		shape2.getDraw().setOnMouseClicked(t2 -> {
			MouseButton button = t2.getButton();
			if ( button == MouseButton.PRIMARY ) {
				mainApp.getTool().setShape(shape2);
				pc.setLineWidth(shape2.getStrokeWidth());
				pc.setStroke((Color) (shape2.getStroke()));

				mainApp.getTool().fillDetails(dp, shape2, mainApp).apply(null);
			}
			else if ( button == MouseButton.SECONDARY ) {
				CustomContextMenu contextMenu = new CustomContextMenu(mainApp, shape2);

				contextMenu.setItems();	
				contextMenu.setY(t2.getScreenY()); contextMenu.setX(t2.getScreenX()); contextMenu.show(shape2.getDraw().getScene().getWindow());
			}

		});

		fillDetails(dp, shape2, mainApp).apply(null);
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

	protected final void showSelectedShape(MainApp mainApp) {
		mainApp.getDrawingZoneController().clearSelectionLayer();
		mainApp.getDrawingZoneController().addSelectionShape(this.getShape().getOutlineCoords());
	}
	public abstract String getToolType();
}
