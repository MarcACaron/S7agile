package adraw4us;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.DetailPaletteController;
import controller.DrawingZoneController;
import controller.MenuController;
import controller.PaletteCouleurController;
import controller.PaletteFormeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Persistance;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private MenuController menuController;
    private DrawingZoneController drawingZoneController;
    private PaletteCouleurController paletteCouleurController;
    private DetailPaletteController paletteDetailController;
    private Tool toolApp;
    private Logger logger = Logger.getLogger(MainApp.class.getName());
    
    public MainApp() {
    	this.toolApp = null;
	}
    public MenuController getMenuController() {
		return menuController;
	}
	public DrawingZoneController getDrawingZoneController() {
		return drawingZoneController;
	}
	public PaletteCouleurController getPaletteCouleurController() {
		return paletteCouleurController;
	}
	public DetailPaletteController getPaletteDetailController() {
		return paletteDetailController;
	}
	public Tool getTool() {
		return toolApp;
	}
	public void setTool(Tool tool) {
		toolApp = tool;
	}
	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("adraw4US");
        
        
        
        initRootLayout();

        showMainOverview();
        Persistance persi = new Persistance(toolApp);
        setTool(persi.readState());
        
        persi.setOnClosedEvent(primaryStage);
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
            //../
            rootLayout = (BorderPane) loader.load();

            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            menuController = loader.getController();
            menuController.setMainApp(this);
        } catch (IOException e) {
        	logger.log(Level.SEVERE, "Exeption: "+e.getMessage()+"; Fonction: initRootLayout();");
        }
    }
    
    public void showMainOverview() {
        try {
            // Load person overview.
            FXMLLoader loader1 = new FXMLLoader();
            FXMLLoader loader2 = new FXMLLoader();
            FXMLLoader loader3 = new FXMLLoader();
            FXMLLoader loader4 = new FXMLLoader();
            FXMLLoader detailPaletteLoader = new FXMLLoader();
            
            loader1.setLocation(MainApp.class.getResource("../view/MainOverview.fxml"));
            loader2.setLocation(MainApp.class.getResource("../view/ScrollPaneOverview.fxml"));
            loader3.setLocation(MainApp.class.getResource("../view/FormPaletteOverview.fxml"));
            loader4.setLocation(MainApp.class.getResource("../view/ColorPaletteOverview.fxml"));
            detailPaletteLoader.setLocation(MainApp.class.getResource("../view/DetailPalette.fxml"));
            
            AnchorPane mainOverview = (AnchorPane) loader1.load();
            AnchorPane scrollPaneOverview = (AnchorPane) loader2.load();
            VBox formPaletteOverview = (VBox) loader3.load();
            HBox colorPaletteOverview = (HBox) loader4.load();   
            VBox detailPaletteOverview = (VBox) detailPaletteLoader.load();
            
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainOverview);
            mainOverview.getChildren().addAll(scrollPaneOverview);
            mainOverview.getChildren().addAll(colorPaletteOverview);
            mainOverview.getChildren().addAll(formPaletteOverview);
            mainOverview.getChildren().addAll(detailPaletteOverview);
            
            AnchorPane.setTopAnchor(colorPaletteOverview, 0.0);
            AnchorPane.setTopAnchor(formPaletteOverview,colorPaletteOverview.getPrefHeight());
            AnchorPane.setLeftAnchor(formPaletteOverview, 0.0);
            AnchorPane.setTopAnchor(detailPaletteOverview,colorPaletteOverview.getPrefHeight());
            AnchorPane.setRightAnchor(detailPaletteOverview, 0.0);
            
            
            AnchorPane.setBottomAnchor(scrollPaneOverview, mainOverview.getHeight());
            AnchorPane.setRightAnchor(scrollPaneOverview, detailPaletteOverview.getPrefWidth());
            AnchorPane.setTopAnchor(scrollPaneOverview, colorPaletteOverview.getPrefHeight());
            AnchorPane.setLeftAnchor(scrollPaneOverview, formPaletteOverview.getPrefWidth());
            
            drawingZoneController = loader2.getController();
            drawingZoneController.setMainApp(this);
            PaletteFormeController controller3 = loader3.getController();
            controller3.setMainApp(this);
            this.paletteCouleurController = loader4.getController();
            this.paletteCouleurController.setMainApp(this);
            
            paletteDetailController = detailPaletteLoader.getController();
            paletteDetailController.setMainApp(this);

        } catch (IOException e) {
        	logger.log(Level.SEVERE, "Exeption: "+e.getMessage()+"; Fonction: showMainOverview();");
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}