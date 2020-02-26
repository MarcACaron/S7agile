package models;

import java.util.ArrayList;

public class DrawnShapes {
	
	private static ArrayList<CustomShape> drawnShapes;
	private DrawnShapes() {
	}
	public static ArrayList<CustomShape> getDrawnShapes() {
		if(drawnShapes==null)
			drawnShapes = new ArrayList<>();
		return drawnShapes;
	}

}
