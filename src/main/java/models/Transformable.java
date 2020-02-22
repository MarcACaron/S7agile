package models;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public interface Transformable {
	public final static int XCOPYOFFSET = 50;
	public final static int YCOPYOFFSET = 50;
	public final static int selectionShapeOffset = 10;
	
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
	public String getType();
	public boolean widthToolisNeeded();
	public boolean heightToolisNeeded();
	public boolean radiusToolisNeeded();
	public boolean lengthToolisNeeded();
	public Shape duplicateAndOffset();
	public Shape duplicate();
	public boolean isSelected(double xStart, double yStart, double xEnd, double yEnd);
	
	public double[] getOutlineCoords();
	public Point2D getCenterCoord();
	
}
