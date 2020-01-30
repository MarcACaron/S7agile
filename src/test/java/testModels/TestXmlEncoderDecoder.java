package testModels;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.*;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.CustomCircle;
import models.CustomLine;
import models.CustomRectangle;
import models.XmlDecoder;
import models.XmlEncoder;

public class TestXmlEncoderDecoder {
	
	
	CustomCircle circle = new CustomCircle();
	CustomRectangle rectangle = new CustomRectangle();
	CustomLine line = new CustomLine();
	
	File file;
	
	public TestXmlEncoderDecoder() {
		circle.setAccessibleText("rouge");
		rectangle.setAccessibleText("rouge");
		line.setAccessibleText("rouge");
		
		circle.setStroke(Color.BLACK);
		rectangle.setStroke(Color.BLACK);
		line.setStroke(Color.BLACK);
	}
	
	@Test
	public void testEncodeXML() {
		ObservableList<Node> shapeList = FXCollections.observableArrayList();
		
		file = new File("./Encodetest.xml");
		
		shapeList.add(circle);
		shapeList.add(line);
		shapeList.add(rectangle);
		
		Boolean a = XmlEncoder.createXML(shapeList, file);
		
		assertEquals(true, a);
	}
	
	@Test
	public void testDecodeXML() {
		Pane pane = new Pane();
		
		file = new File("./Encodetest.xml");

		XmlDecoder.readXML(file, pane);
		
		assertEquals(3, pane.getChildren().size());
		
	}
	
	@AfterClass
	public void clean() {
		file.delete();
	}

}
