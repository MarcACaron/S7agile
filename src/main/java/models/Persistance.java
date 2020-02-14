package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import adraw4us.Tool;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Persistance {
	
	Tool appTool;
	
	public Persistance(Tool applicationTool) {
		this.appTool = applicationTool;
	}
	
	public void setOnClosedEvent(Stage primaryStage) {
		primaryStage.setOnCloseRequest( e -> {
			
			try {
				File persistanceFile = new File("persistance.ini");
				persistanceFile.createNewFile(); // if file already exists will do nothing 
				FileWriter oFile = new FileWriter(persistanceFile, false); 
				
				writeLine(oFile, "FillName", Tool.getFillName());
				writeLine(oFile, "Stroke", Tool.getStroke().toString());
				writeLine(oFile, "LineWidth", String.valueOf(Tool.getLineWidth()));
				writeLine(oFile, "FormType", appTool.getToolType());
				writeLine(oFile, "DrawMode", String.valueOf(Tool.isStartFromCenter()));
				
				oFile.close();
			} catch (Exception ex) {
				
			}
		});
	}
	
	private void writeLine(FileWriter file, String property, String propertyValue ) {
		try {
			file.write(property + "=" + propertyValue + System.lineSeparator());
		}
		catch (IOException ex) {
			
		}
	}
	
	public Tool readState() {
		
		BufferedReader reader;
		Tool returnTool = new RectangleTool();
		
		try {
			reader = new BufferedReader(new FileReader("persistance.ini"));
			
			String line = reader.readLine();
			
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, "=");
				
				if (st.countTokens() != 2) {
					continue;
				}
				String initialToken = st.nextToken();
				if (initialToken.equalsIgnoreCase("FillName")) {
					Tool.setFillName(st.nextToken());
					
				} else if (initialToken.equalsIgnoreCase("Stroke")) {
					Tool.setStroke(Paint.valueOf(st.nextToken()));
					
				} else if (initialToken.equalsIgnoreCase("LineWidth")) {
					
					Tool.setLineWidth(Double.valueOf(st.nextToken()));
					
				} else if (initialToken.equalsIgnoreCase("FormType")) {
					
					String token = st.nextToken();
					
					getCorrectTool(token);
					
				} else if (initialToken.equalsIgnoreCase("DrawMode")) {
					Tool.setStartFromCenter(Boolean.valueOf(st.nextToken()));
				}
				line = reader.readLine();
			}
			reader.close();
			return returnTool;
		} catch (Exception ex) {
			
		}
		return null;
	}

	private Tool getCorrectTool(String token) {
		
		if (token.equalsIgnoreCase("rectangle")) {
			return new RectangleTool();
		} else if (token.equalsIgnoreCase("circle")) {
			return new CircleTool();
		} else if (token.equalsIgnoreCase("selection")) {
			return new SelectionTool();
		} else if (token.equalsIgnoreCase("multiSelection")) {
			return new MultiSelectionTool();
		}
		return new SelectionTool();
	}
}
