package models;

import java.util.List;

import adraw4us.MainApp;
import adraw4us.Tool;
import controller.DetailPaletteController;
import controller.PaletteCouleurController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MultiSelectionTool extends Tool {
	protected Rectangle selection;

	public MultiSelectionTool(CustomShape shape) {
		super(shape);
		this.selection = new Rectangle();
	}
	public MultiSelectionTool() {
		super(null);
		this.selection = new Rectangle();
	}


	@Override
	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double posX;
		double posY;
		double width;
		double height;
		if(startFromCenter) {

			posX = Math.min(posXEnd, 2*posXStart-posXEnd);
			posY = Math.min(posYEnd, 2*posYStart-posYEnd);
			width = Math.abs(2*(posXEnd-posXStart));
			height = Math.abs(2*(posYEnd-posYStart));
		}else {
			posX = Math.min(posXStart, posXEnd);
			posY = Math.min(posYStart, posYEnd);
			width = Math.abs(posXEnd - posXStart);
			height = Math.abs(posYEnd - posYStart);
		}
		if(selection!=null) {
			this.selection.setX(posX);
			this.selection.setY(posY);
			this.selection.setWidth(width);
			this.selection.setHeight(height);
		}
	}

	@Override
	public void reset() {
		this.selection = new Rectangle();
		this.shape = new CustomUnionShape();
	}
	@Override
	public int mousePressed(DetailPaletteController detailPaletteController, Layer layer, MainApp mainApp) {
		this.reset();
		int index = layer.getPane().getChildren().size();
		this.selection = new Rectangle();
		selection.setStroke(Color.DARKGREY);
		selection.setStrokeWidth(5.0);
		selection.setFill(null);
		selection.getStrokeDashArray().addAll(25d, 20d, 5d, 20d);

		layer.getPane().getChildren().add(selection);
		
		return index;
	}
	
	@Override
	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		this.ajustOnDrag(posXStart, posYStart, posXEnd, posYEnd);
	}

	@Override
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController paletteCouleurController, DetailPaletteController pc) {
		double xStart = selection.getX();
		double yStart = selection.getY();
		double xEnd = selection.getX()+selection.getWidth();
		double yEnd = selection.getY()+selection.getHeight();
		pane.getChildren().remove(pane.getChildren().size()-1);
		for(int i=0; i<DrawnShapes.getDrawnShapes().size();i++) {
			if((DrawnShapes.getDrawnShapes().get(i)).isSelected(xStart, yStart, xEnd, yEnd)) {
				((CustomUnionShape)this.shape).add(DrawnShapes.getDrawnShapes().get(i));
			}	
		}
		if(((CustomUnionShape)this.shape).updateBoudingBox()) {
			fillDetails(mainApp.getPaletteDetailController(), this.shape, mainApp).apply(null);
		}
	}

	@Override
	public String getToolType() {
		return "multiSelection";
	}
}
