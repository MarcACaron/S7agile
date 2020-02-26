package models;

import java.util.List;

import adraw4us.MainApp;
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
	public int mousePressed(DetailPaletteController detailPaletteController, Layer layer, List<CustomShape> drawnShapes, MainApp mainApp) {
		int index = layer.getPane().getChildren().size()-1;
		if(index<0)
			index=0;
		this.fillDetails(detailPaletteController, null, mainApp).apply(null);
		this.reset();
		return index;
	}

	@Override
	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		//
	}

	@Override
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController pc, DetailPaletteController dp, List<CustomShape> drawnShapes) {
		mainApp.getDrawingZoneController().clearSelectionLayer();
		//showSelectedShape(mainApp, inputShape);
	}
	
	@Override
	public String getToolType() {
		return "selection";
	}
}
