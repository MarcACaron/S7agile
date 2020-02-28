package adraw4us; 
 
import models.CustomCircle; 
import models.CustomLine; 
import models.CustomRectangle;
import models.CustomShape;
import models.CustomTriangleHorizontal;
import models.CustomTriangleVertical;
import models.CustomUnionShape; 
 
public class ShapeFactory { 
	private ShapeFactory() {
	}
	
	public static CustomShape build(String shapeName) { 
		switch (shapeName) { 
		case "CustomRectangle": 
			return new CustomRectangle(); 
		case "CustomLine": 
			return new CustomLine(); 
		case "CustomCircle": 
			System.out.println("cercle");
			return new CustomCircle(); 
		case "CustomTriangleHorizontal" :
			return new CustomTriangleHorizontal();
		case "CustomTriangleVertical" :
			return new CustomTriangleVertical();
		case "CustomUnionShape" :
			return new CustomUnionShape();
		default: 
			return null; 
		} 
	} 
 
} 
