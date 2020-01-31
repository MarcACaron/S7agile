package models;

import javafx.scene.shape.Shape;

public interface Drawable {
	
	default public Shape ajustOnDrag(double posXStart, double posYStart, double posXEnd, double posYEnd) {
		double posX = Math.min(posXStart, posXEnd);
		double posY = Math.min(posYStart, posYEnd);
		double width = Math.abs(posXEnd - posXStart);
		double height = Math.abs(posYEnd - posYStart);
		return new CustomRectangle(posX, posY, width, height);
	}
	
	public void endAjust(double posXStart, double posYStart, double posXEnd, double posYEnd);
}
