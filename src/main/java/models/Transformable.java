package models;

import javafx.scene.shape.Shape;

public interface Transformable {
	public final int xCopyOffset = 50;
	public final int yCopyOffset = 50;
	
	public void setXPosTool(double value);
	public void setYPosTool(double value);
	public void setWidthTool(double value);
	public void setHeightTool(double value);
	public void setRadiusTool(double value);
	public void setLengthTool(double value);
	public void setRotationTool(double value);
	public double getXPos();
	public double getYPos();
	public double getWidth();
	public double getHeight();
	public double getRadius();
	public double getLength();
	public double getRotation();
	public boolean widthToolisNeeded();
	public boolean heightToolisNeeded();
	public boolean radiusToolisNeeded();
	public boolean lengthToolisNeeded();
	public Shape duplicate();
}
