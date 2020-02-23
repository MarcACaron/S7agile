package testModels;

import java.io.File;

import org.junit.*;
import javafx.scene.paint.Color;
import models.CustomCircle;
import models.CustomLine;
import models.CustomRectangle;

public class TestXmlEncoderDecoder {
	
	
	CustomCircle circle = new CustomCircle();
	CustomRectangle rectangle = new CustomRectangle();
	CustomLine line = new CustomLine();
	
	File file;
	
	public TestXmlEncoderDecoder() {
		circle.getDraw().setAccessibleText("rouge");
		rectangle.getDraw().setAccessibleText("rouge");
		line.getDraw().setAccessibleText("rouge");
		
		circle.setStroke(Color.BLACK);
		rectangle.setStroke(Color.BLACK);
		line.setStroke(Color.BLACK);
	}
	//TODO: changer �a en une LayerGroup
	/*
	@Test
	public void testEncodeXML() {
		ObservableList<Node> shapeList = FXCollections.observableArrayList();
		
		file = new File("./Encodetest.xml");
		
		shapeList.add(circle);
		shapeList.add(line);
		shapeList.add(rectangle);
		
		//Boolean a = XmlEncoder.createXML(shapeList, file);
		
		assertEquals(true, a);
	}*/
	//TODO: refaire
	/*
	@Test
	public void testDecodeXML() {
		Pane pane = new Pane();
		
		file = new File("./Encodetest.xml");

		//XmlDecoder.readXML(file, pane);
		
		assertEquals(3, pane.getChildren().size());
		
	}*/
	
	@AfterClass
	public void clean() {
		file.delete();
	}

}
