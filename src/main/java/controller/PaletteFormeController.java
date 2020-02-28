package controller;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLStreamException;

import adraw4us.MainApp;
import adraw4us.Tool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import models.CircleTool;
import models.CustomDrawTool;
import models.CustomShape;
import models.LineTool;
import models.MultiSelectionTool;
import models.RectangleTool;
import models.TriangleHorizontalTool;
import models.TriangleVerticalTool;
import models.XmlDecoder;
import models.XmlEncoder;
import models.SelectionTool;

public class PaletteFormeController {

	private MainApp mainApp;
	@FXML
    private ToggleButton pointeur;
	@FXML
    private ToggleButton multiSelection;
	@FXML
    private ToggleButton rectangle;
	@FXML
    private ToggleButton circle;
	@FXML
    private ToggleButton triangleHorizontal;
	@FXML
    private ToggleButton triangleVertical;
	@FXML
    private ToggleButton line;
	@FXML
    private ToggleButton ptDepart;
	@FXML
	private ImageView ptDepartImage;
	@FXML
	private Tooltip ptDepartToolTip;
	@FXML
    private ToggleButton customShape1;
	@FXML
	private Tooltip customShape1Tooltip;
	@FXML
    private ToggleButton customShape2;
	@FXML
	private Tooltip customShape2Tooltip;
	@FXML
    private ToggleButton customShape3;
	@FXML
	private Tooltip customShape3Tooltip;
	@FXML
    private ToggleButton customShape4;
	@FXML
	private Tooltip customShape4Tooltip;
	@FXML
    private Pane paneCS1;
	@FXML
    private Pane paneCS2;
	@FXML
    private Pane paneCS3;
	@FXML
    private Pane paneCS4;
	
	@FXML
	private void choosePointer() {
		if(pointeur.isSelected()) {
			if(this.mainApp.getTool()==null)
				this.mainApp.setTool(new SelectionTool());
			else
				this.mainApp.setTool(new SelectionTool(this.mainApp.getTool().getShape()));
		}else {
			pointeur.setSelected(true);
		}
		
	}
	@FXML
	private void chooseMultiSelection() {
		if(multiSelection.isSelected()) {
			this.mainApp.setTool(new MultiSelectionTool());
		}
	}
	@FXML
	private void chooseRectangle() {
		if(rectangle.isSelected()) {
			this.mainApp.setTool(new RectangleTool());
		}
	}
	@FXML
	private void chooseCircle() {
		if(circle.isSelected()) {
			this.mainApp.setTool(new CircleTool());
		}
	}
	
	@FXML
	private void chooseTriangleHorizontal() {
		if(triangleHorizontal.isSelected()) {
			this.mainApp.setTool(new TriangleHorizontalTool());
		}
	}
	
	@FXML
	private void chooseTriangleVertical() {
		if(triangleVertical.isSelected()) {
			this.mainApp.setTool(new TriangleVerticalTool());
		}
	}	
	@FXML
	private void chooseLine() {
		if(line.isSelected()) {
			this.mainApp.setTool(new LineTool());
		}
	}
	@FXML
	private void choosePtDepart() {
		if(ptDepart.isSelected()) {
			ptDepartImage.setImage(new Image("images/corner.png"));
			ptDepartToolTip.setText("Click to Draw from Center");
			Tool.setStartFromCenter(false);
		}else {
			ptDepartImage.setImage(new Image("images/center.png"));
			ptDepartToolTip.setText("Click to Draw from Corner");
			Tool.setStartFromCenter(true);
		}
	}
	
	@FXML private void drawCustom(ActionEvent event) {
		ToggleButton tg = (ToggleButton) event.getSource();
		String fileName=null;
		Tooltip toolTip = null;
		int nb = 0;
		if(tg.getId().contentEquals("customShape1")) {
			 nb=1;
			 toolTip= customShape1Tooltip;
			 fileName = "shape1.xml";
		}else if(tg.getId().contentEquals("customShape2")) {
			 nb=2;
			 toolTip= customShape2Tooltip;
			 fileName = "shape2.xml";
		}else if(tg.getId().contentEquals("customShape3")) {
			 nb=3;
			 toolTip= customShape3Tooltip;
			 fileName = "shape3.xml";
		}else if(tg.getId().contentEquals("customShape4")) {
			 nb=4;
			 toolTip= customShape4Tooltip;
			 fileName = "shape4.xml";
		}
		if(tg.getUserData()==null) {
			if(mainApp.getTool().getShape()!=null) {
				toolTip.setText(mainApp.getTool().getShape().getType());
				tg.setUserData(mainApp.getTool().getShape().duplicate(0, 0, mainApp));
				fillCustomShape(nb, mainApp.getTool().getShape().duplicate(0, 0, mainApp));
				register(fileName, mainApp.getTool().getShape().duplicate(0, 0, mainApp));
			}
			tg.setSelected(false);
		}else {
			this.mainApp.setTool(new CustomDrawTool((CustomShape) tg.getUserData()));
		}
				
	}
	
	
	private void register(String string, CustomShape duplicate) {
		try {
			XmlEncoder.customShapeEncoder(duplicate, string);
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void fillCustomShape(int nb, CustomShape sh) {
		Tooltip toolTip=null;
		ToggleButton tg = null;;
		Pane p=null;
		if(nb==1) {
			p = paneCS1;
			toolTip = customShape1Tooltip;
			tg = customShape1;
		}
		if(nb==2) {
			p=paneCS2;
			toolTip = customShape2Tooltip;
			tg = customShape2;
		}
		if(nb==3) {
			p=paneCS3;
			toolTip = customShape3Tooltip;
			tg = customShape3;
		}
		if(nb==4) {
			p=paneCS4;
			toolTip = customShape4Tooltip;
			tg = customShape4;
		}
		toolTip.setText(sh.getType());
		tg.setUserData(sh.duplicate(0, 0, mainApp));
		resize(sh);
		sh.draw(p);
	}
	
	private CustomShape resize(CustomShape sh) {
		sh.setXPos(0);
		sh.setYPos(0);
		if(sh.getWidth()>34)
			sh.setWidth(34);
		if(sh.getHeight()>32)
			sh.setHeight(32);
		return sh;
	}
	private void loadCustomShapes() {
		for(int i=1; i<=4; i++) {
			File file = new File("shape"+i+".xml");
			if(file.exists()) {
				try {
					fillCustomShape(i, XmlDecoder.customShapeDecoder(file, mainApp));
				} catch (FileNotFoundException | XMLStreamException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@FXML
    private void initialize() {
		// Put stuff to initialise here
    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        pointeur.fire();
		//this.mainApp.setTool(new SelectionTool());
		
		loadCustomShapes();
		Tool.setStartFromCenter(true);
    }
	public boolean isDeletable(int nb) {
		ToggleButton tg = null;
		if(nb==1)
			tg=customShape1;
		if(nb==2)
			tg=customShape2;
		if(nb==3)
			tg=customShape3;
		if(nb==4)
			tg=customShape4;
		return tg.getUserData()!=null;
	}
	
	public String getDescription(int nb) {
		Tooltip toolTip = null;
		if(nb==1)
			toolTip= customShape1Tooltip;
		if(nb==2)
			toolTip= customShape2Tooltip;
		if(nb==3)
			toolTip= customShape3Tooltip;
		if(nb==4)
			toolTip= customShape4Tooltip;
		return toolTip.getText();
	}
	public void remove(int nb) {
		Tooltip toolTip=null;
		ToggleButton tg = null;;
		Pane p=null;
		File file = new File("shape"+nb+".xml");
		if(nb==1) {
			System.out.println("suppr1");
			p = paneCS1;
			toolTip = customShape1Tooltip;
			tg = customShape1;
		}
		if(nb==2) {
			p=paneCS2;
			toolTip = customShape2Tooltip;
			tg = customShape2;
		}
		if(nb==3) {
			p=paneCS3;
			toolTip = customShape3Tooltip;
			tg = customShape3;
		}
		if(nb==4) {
			p=paneCS4;
			toolTip = customShape4Tooltip;
			tg = customShape4;
		}
		if(file.exists()) {
			file.delete();
		}
		toolTip.setText("No shape");
		p.getChildren().clear();
		tg.setUserData(null);
		System.out.println(tg.getUserData()==null);
	}
}
