package models;

import java.util.ArrayList;

import adraw4us.MainApp;
import adraw4us.Tool;
import controller.DetailPaletteController;
import controller.PaletteCouleurController;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class SelectionTool extends Tool {

	public SelectionTool() {
	}

	public SelectionTool(Shape tool) {
		super(tool);
	}
	
	public SelectionTool(ArrayList<Shape> shapes) {
		super(shapes);
	}

	@Override
	public void ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		//No need as he might not draw for now
	}

	@Override
	public void reset() {
		this.shapes.clear();
	}
	
	@Override
	public int mousePressed(DetailPaletteController dp, Pane pane) {
		int index = pane.getChildren().size()-1;
		if(index<0)
			index=0;
		this.fillDetails(dp, null).apply(null);
		this.reset();
		return index;
	}

	@Override
	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		//
	}

	@Override
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController paletteCouleurController, DetailPaletteController pc) {
		//
	}
}
