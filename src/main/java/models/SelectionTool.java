package models;

import adraw4us.Tool;
import controller.DetailPaletteController;
import controller.PaletteCouleurController;
import javafx.scene.layout.Pane;

public class SelectionTool extends Tool {

	public SelectionTool(CustomShape shape) {
		super(shape);
	}
	
	public SelectionTool() {
		super(null);
	}

	@Override
	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		
		//Intended to be empty
	}

	@Override
	public void reset() {
		
		//Intended to be empty
	}
	@Override
	public int mousePressed(DetailPaletteController detailPaletteController, Layer layer) {
		int index = layer.getPane().getChildren().size()-1;
		if(index<0)
			index=0;
		this.fillDetails(detailPaletteController, null).apply(null);
		this.reset();
		return index;
	}

	@Override
	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		//
	}

	@Override
	public void mouseReleased(Pane pane, PaletteCouleurController pc, DetailPaletteController dp) {
		mainApp.getDrawingZoneController().clearSelectionLayer();
		//showSelectedShape(mainApp, inputShape);
	}
	
	@Override
	public String getToolType() {
		return "selection";
	}
}
