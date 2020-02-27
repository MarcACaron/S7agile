package controller;

import adraw4us.MainApp;
import adraw4us.Tool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.CircleTool;
import models.CustomDrawTool;
import models.CustomShape;
import models.LineTool;
import models.MultiSelectionTool;
import models.RectangleTool;
import models.TriangleHorizontalTool;
import models.TriangleVerticalTool;
import models.SelectionTool;

public class PaletteFormeController {
	
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
	private void choosePointer() {
		if(pointeur.isSelected()) {
			this.mainApp.setTool(new SelectionTool(this.mainApp.getTool().getShape()));
		}else {
			pointeur.setSelected(true);
		}
		
	}
	@FXML
	private void chooseMultiSelection() {
		if(multiSelection.isSelected()) {
			this.mainApp.setTool(new MultiSelectionTool());
		}else {
			this.mainApp.setTool(new SelectionTool());
			pointeur.setSelected(true);
		}
		
	}
	@FXML
	private void chooseRectangle() {
		if(rectangle.isSelected()) {
			this.mainApp.setTool(new RectangleTool());
		}else {
			this.mainApp.setTool(new SelectionTool(this.mainApp.getTool().getShape()));
			pointeur.setSelected(true);
		}
		
	}
	@FXML
	private void chooseCircle() {
		if(circle.isSelected()) {
			this.mainApp.setTool(new CircleTool());
		}else {
			this.mainApp.setTool(new SelectionTool(this.mainApp.getTool().getShape()));
			pointeur.setSelected(true);
		}
		
	}
	
	@FXML
	private void chooseTriangleHorizontal() {
		if(triangleHorizontal.isSelected()) {
			this.mainApp.setTool(new TriangleHorizontalTool());
		}else {
			this.mainApp.setTool(new SelectionTool(this.mainApp.getTool().getShape()));
			pointeur.setSelected(true);
		}
		
	}
	
	@FXML
	private void chooseTriangleVertical() {
		if(triangleVertical.isSelected()) {
			this.mainApp.setTool(new TriangleVerticalTool());
		}else {
			this.mainApp.setTool(new SelectionTool(this.mainApp.getTool().getShape()));
			pointeur.setSelected(true);
		}
		
	}	
	@FXML
	private void chooseLine() {
		if(line.isSelected()) {
			this.mainApp.setTool(new LineTool());
		}else {
			this.mainApp.setTool(new SelectionTool(this.mainApp.getTool().getShape()));
			pointeur.setSelected(true);
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
		if(tg.getId().contentEquals("customShape1")) {
			if(tg.getUserData()==null) {
				if(mainApp.getTool().getShape()!=null) {
					customShape1Tooltip.setText(mainApp.getTool().getShape().getType());
					tg.setUserData(mainApp.getTool().getShape().duplicate(0, 0, mainApp));
					System.out.println("Enregistrement");
				}else {
					System.out.println("Pas de Shape");
				}
				tg.setSelected(false);
			}else {
				System.out.println("Préparation de la shape custom: +customShape1Tooltip.getText()");
				this.mainApp.setTool(new CustomDrawTool((CustomShape) tg.getUserData()));
				System.out.println(mainApp.getTool().getToolType());
				System.out.println(mainApp.getTool().getShape().getType());
			}
		}else if(tg.getId().contentEquals("customShape2")) {
			if(tg.getUserData()==null) {
				if(mainApp.getTool().getShape()!=null) {
					customShape2Tooltip.setText(mainApp.getTool().getShape().getType());
					tg.setUserData(mainApp.getTool().getShape().duplicate(0, 0, mainApp));
					System.out.println("Enregistrement");
				}else {
					System.out.println("Pas de Shape");
				}
				tg.setSelected(false);
			}else {
				System.out.println("Préparation de la shape custom: +customShape1Tooltip.getText()");
				this.mainApp.setTool(new CustomDrawTool((CustomShape) tg.getUserData()));
				System.out.println(mainApp.getTool().getToolType());
				System.out.println(mainApp.getTool().getShape().getType());
			}
		}else if(tg.getId().contentEquals("customShape3")) {
			if(tg.getUserData()==null) {
				if(mainApp.getTool().getShape()!=null) {
					customShape3Tooltip.setText(mainApp.getTool().getShape().getType());
					tg.setUserData(mainApp.getTool().getShape().duplicate(0, 0, mainApp));
					System.out.println("Enregistrement");
				}else {
					System.out.println("Pas de Shape");
				}
				tg.setSelected(false);
			}else {
				System.out.println("Préparation de la shape custom: +customShape1Tooltip.getText()");
				this.mainApp.setTool(new CustomDrawTool((CustomShape) tg.getUserData()));
				System.out.println(mainApp.getTool().getToolType());
				System.out.println(mainApp.getTool().getShape().getType());
			}
		}else if(tg.getId().contentEquals("customShape4")) {
			if(tg.getUserData()==null) {
				if(mainApp.getTool().getShape()!=null) {
					customShape4Tooltip.setText(mainApp.getTool().getShape().getType());
					tg.setUserData(mainApp.getTool().getShape().duplicate(0, 0, mainApp));
					System.out.println("Enregistrement");
				}else {
					System.out.println("Pas de Shape");
				}
				tg.setSelected(false);
			}else {
				System.out.println("Préparation de la shape custom: +customShape1Tooltip.getText()");
				this.mainApp.setTool(new CustomDrawTool((CustomShape) tg.getUserData()));
				System.out.println(mainApp.getTool().getToolType());
				System.out.println(mainApp.getTool().getShape().getType());
			}
		}
				
	}
	private MainApp mainApp;
	
	
	@FXML
    private void initialize() {
		// Put stuff to initialise here
    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
		this.mainApp.setTool(new SelectionTool());
		Tool.setStartFromCenter(true);
    }
}
