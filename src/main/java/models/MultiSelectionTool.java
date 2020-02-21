package models;

import java.util.ArrayList;

import adraw4us.MainApp;
import adraw4us.Tool;
import controller.DetailPaletteController;
import controller.PaletteCouleurController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class MultiSelectionTool extends Tool {
	protected CustomRectangle selection;
	public MultiSelectionTool() {
		super();
	}

	public MultiSelectionTool(Shape shape) {
		super(shape);
	}
	
	public MultiSelectionTool(ArrayList<Shape> shapes) {
		super(shapes);
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
		this.shapes.clear();
		this.selection = null;
	}
	
	public int mousePressed(DetailPaletteController dp, Pane pane) {
		this.reset();
		int index = pane.getChildren().size();
		this.selection = new CustomRectangle();
		selection.setStroke(Color.DARKGREY);
		selection.setStrokeWidth(5.0);
		selection.setFill(null);
		selection.getStrokeDashArray().addAll(25d, 20d, 5d, 20d);
		System.out.println("....");
		if(selection!=null)
			pane.getChildren().add(selection);
		
		return index;
	}
	
	@Override
	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		this.ajustOnDrag(posXStart, posYStart, posXEnd, posYEnd);
	}

	@Override
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController paletteCouleurController, DetailPaletteController pc) {double xStart = selection.getX();
		double yStart = selection.getY();
		double xEnd = selection.getX()+selection.getWidth();
		double yEnd = selection.getY()+selection.getHeight();
		pane.getChildren().remove(pane.getChildren().size()-1);
		for(int i=0; i<pane.getChildren().size();i++) {
			if(((Transformable)pane.getChildren().get(i)).isSelected(xStart, yStart, xEnd, yEnd)) {
				this.shapes.add((Shape)pane.getChildren().get(i));
			}
				
		}
	}
	
	@Override
	protected void showSelectedShape(MainApp mainApp, Transformable inputShape) {
		mainApp.getDrawingZoneController().clearSelectionLayer();
	}

	@Override
	public String getToolType() {
		return "multiSelection";
	}
}
