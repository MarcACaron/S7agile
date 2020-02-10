package models;

import adraw4us.Tool;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class PatternApplier {
	public void fillShape(Shape shape, String value) {
		Paint fill;
		if(!value.equals("")) {
			if(value.equals("rouge")) {
			    fill = (Color.RED);
			}else {
				String imagePath = "images/" + value + ".png";
				Image image = new Image(imagePath); 
			    fill = new ImagePattern(image, 50, 50, 200, 200, false);
			}
		    Tool.setFillName(value);
			if(shape!=null) {
				
			    shape.setFill(fill);
			}
		}
	}

}
