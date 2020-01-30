package adraw4US;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.DetailPaletteController;
import view.DrawingZoneController;
import view.MenuController;
import view.PaletteCouleurController;
import view.PaletteFormeController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    public MenuController menuController;
    public DrawingZoneController drawingZoneController;
    public PaletteCouleurController paletteCouleurController;
    public DetailPaletteController paletteDetailController;
    private Tool Tool;
    
    public MainApp() {
    	this.Tool = null;
	}
    public Tool getTool() {
		return Tool;
	}
	public void setTool(Tool tool) {
		Tool = tool;
	}
	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("adraw4US");

        initRootLayout();

        //showPersonOverview();
        showMainOverview();
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
            e.printStackTrace();
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
            
            AnchorPane MainOverview = (AnchorPane) loader1.load();
            AnchorPane ScrollPaneOverview = (AnchorPane) loader2.load();
            VBox FormPaletteOverview = (VBox) loader3.load();
            HBox ColorPaletteOverview = (HBox) loader4.load();   
            VBox detailPaletteOverview = (VBox) detailPaletteLoader.load();
            
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(MainOverview);
//            rootLayout.setTop(ColorPaletteOverview);
//            rootLayout.setLeft(FormPaletteOverview);
            MainOverview.getChildren().addAll(ScrollPaneOverview);
            MainOverview.getChildren().addAll(ColorPaletteOverview);
            MainOverview.getChildren().addAll(FormPaletteOverview);
            MainOverview.getChildren().addAll(detailPaletteOverview);
            
            AnchorPane.setTopAnchor(ColorPaletteOverview, 0.0);
            AnchorPane.setTopAnchor(FormPaletteOverview,ColorPaletteOverview.getPrefHeight());
            AnchorPane.setLeftAnchor(FormPaletteOverview, 0.0);
            AnchorPane.setTopAnchor(detailPaletteOverview,ColorPaletteOverview.getPrefHeight());
            AnchorPane.setRightAnchor(detailPaletteOverview, 0.0);
            
            
            AnchorPane.setBottomAnchor(ScrollPaneOverview, MainOverview.getHeight());
            AnchorPane.setRightAnchor(ScrollPaneOverview, detailPaletteOverview.getPrefWidth());
            AnchorPane.setTopAnchor(ScrollPaneOverview, ColorPaletteOverview.getPrefHeight());
            AnchorPane.setLeftAnchor(ScrollPaneOverview, FormPaletteOverview.getPrefWidth());
            
            
            
            drawingZoneController = loader2.getController();
            drawingZoneController.setMainApp(this);
            PaletteFormeController controller3 = loader3.getController();
            controller3.setMainApp(this);
            this.paletteCouleurController = loader4.getController();
            this.paletteCouleurController.setMainApp(this);
            
            paletteDetailController = detailPaletteLoader.getController();
            paletteDetailController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
/*    public void showPaletteOverview() {
    	try {
        FXMLLoader loader1 = new FXMLLoader();
        FXMLLoader loader2 = new FXMLLoader();
        FXMLLoader loader3 = new FXMLLoader();    	
        loader1.setLocation(MainApp.class.getResource("../view/LastOverview.fxml"));
        loader2.setLocation(MainApp.class.getResource("../view/ColorPaletteOverview.fxml"));
        loader3.setLocation(MainApp.class.getResource("../view/FormPaletteOverview.fxml"));
        AnchorPane LastOverview = (AnchorPane) loader1.load();
        HBox ColorPaletteOverview = (HBox) loader2.load();    
        VBox FormPaletteOverview = (VBox) loader3.load();        
        
        // Set person overview into the center of root layout.
        showMainOverview.setCenter(LastOverview);       
        AnchorPane.setLeftAnchor(FormPaletteOverview, 0.0);
        AnchorPane.setTopAnchor(ColorPaletteOverview, 0.0);
        
        rootLayout.setTop(ColorPaletteOverview);
        rootLayout.setLeft(FormPaletteOverview);
        
    } catch (IOException e) {
        e.printStackTrace();
    }        
    }*/
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}