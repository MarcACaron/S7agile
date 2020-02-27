package models;

import adraw4us.MainApp;
import adraw4us.Tool;
import controller.DetailPaletteController;
import controller.PaletteCouleurController;
import javafx.scene.layout.Pane;

public class CustomDrawTool extends Tool {

	public CustomDrawTool(CustomShape shape) {
		super(shape);
	}

	@Override
	public void reset() {
		shape = null;//?
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
		/*
		this.ajustOnDrag(posXStart, posYStart, posXEnd, posYEnd);
		*/
	}
	
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController pc, DetailPaletteController dp) {
		/*
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

		fillDetails(dp, shape2, mainApp).apply(null);*/
	}
	
	@Override
	public String getToolType() {
		return "customDraw";
	}

}
