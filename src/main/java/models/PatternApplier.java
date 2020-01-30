package models;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

public class PatternApplier {
	
	
	public PatternApplier() {
		this.imagePackagePath = new String("images/");
	}
	
	private String imagePackagePath;
	
	public Shape setFill(Shape shape) {
		if (shape.getAccessibleText().compareToIgnoreCase("rouge") == 0) {
			shape.setFill(Color.RED);
			
		} else {
			String imagePath = imagePackagePath + shape.getAccessibleText() + ".png";
			System.out.println(imagePath);
			Image image = new Image(imagePath); 
		    ImagePattern radialGradient = new ImagePattern(image, 50, 50, 200, 200, false);
		    shape.setFill(radialGradient);
		}
		
		return shape;
	}
	
	public void setImagePath(String path) {
		imagePackagePath = path;
	}

}
