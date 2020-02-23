package models;

import java.util.ArrayList;

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
	public int mousePressed(DetailPaletteController detailPaletteController, Layer layer, ArrayList<CustomShape> drawnShapes) {
		this.reset();
		int index = layer.getPane().getChildren().size();
		this.selection = new Rectangle();
		selection.setStroke(Color.DARKGREY);
		selection.setStrokeWidth(5.0);
		selection.setFill(null);
		selection.getStrokeDashArray().addAll(25d, 20d, 5d, 20d);
		System.out.println("....");
		if(selection!=null)
			layer.getPane().getChildren().add(selection);
		
		return index;
	}
	
	@Override
	public void mouseDragged(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		this.ajustOnDrag(posXStart, posYStart, posXEnd, posYEnd);
	}

	@Override
	public void mouseReleased(MainApp mainApp, Pane pane, PaletteCouleurController paletteCouleurController, DetailPaletteController pc, ArrayList<CustomShape> drawnShapes) {
		System.out.println("dfsmlsldlsghlk");
		double xStart = selection.getX();
		double yStart = selection.getY();
		double xEnd = selection.getX()+selection.getWidth();
		double yEnd = selection.getY()+selection.getHeight();
		pane.getChildren().remove(pane.getChildren().size()-1);
		for(int i=0; i<drawnShapes.size();i++) {
			if((drawnShapes.get(i)).isSelected(xStart, yStart, xEnd, yEnd)) {
				System.out.println("jkh");
				System.out.println((CustomUnionShape)this.shape==null);
				System.out.println(drawnShapes==null);
				System.out.println(drawnShapes.get(i)==null);
				((CustomUnionShape)this.shape).add(drawnShapes.get(i));//NULL
			}
				
		}
	}
	
	@Override
	protected void showSelectedShape(MainApp mainApp, CustomShape inputShape) {
		mainApp.getDrawingZoneController().clearSelectionLayer();
	}

	@Override
	public String getToolType() {
		return "multiSelection";
	}
}
