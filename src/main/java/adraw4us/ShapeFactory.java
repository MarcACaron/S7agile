package adraw4us; 
 
import models.CustomCircle; 
import models.CustomLine; 
import models.CustomRectangle;
import models.CustomShape;
import models.CustomTriangleHorizontal; 
 
public class ShapeFactory { 
	private ShapeFactory() {
	}
	
	public static CustomShape build(String shapeName) { 
		switch (shapeName) { 
		case "rectangle": 
			return new CustomRectangle(); 
		case "line": 
			return new CustomLine(); 
		case "circle": 
			return new CustomCircle(); 
		case "triangleHorizontal" :
			return new CustomTriangleHorizontal();
		default: 
			return null; 
		} 
	} 
 
} 
