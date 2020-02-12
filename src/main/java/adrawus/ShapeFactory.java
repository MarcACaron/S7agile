package adraw4us; 
 
import javafx.scene.shape.Shape; 
import models.CustomCircle; 
import models.CustomLine; 
import models.CustomRectangle; 
 
public class ShapeFactory { 
	private ShapeFactory() {
	}
	
	public static Shape build(String shapeName) { 
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
