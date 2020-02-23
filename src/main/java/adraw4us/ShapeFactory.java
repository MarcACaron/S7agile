package adraw4us; 
 
import models.CustomCircle; 
import models.CustomLine; 
import models.CustomRectangle;
import models.CustomShape; 
 
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
		default: 
			return null; 
		} 
	} 
 
} 
