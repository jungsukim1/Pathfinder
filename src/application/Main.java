package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Main extends Application {
	private int WINDOWHEIGHT = 800; 
	private int WINDOWWIDTH = 900;
	private int NUM_BUTTON_LINES = 32;
	private int BUTTONS_PER_LINE = 32; 
	private GridPane main = new GridPane();
	private int columns = 40;
	private int rows = 40;
	private ArrayList[][] table = new ArrayList[columns][rows];
	private RadioButton ENDNODE = new RadioButton("End Node");
	private RadioButton STARTNODE = new RadioButton("Start Node");
	private RadioButton WALLS = new RadioButton("Walls");
	
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		root.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
		VBox toggle = new VBox();
		ToggleGroup group = new ToggleGroup();
		ENDNODE.setToggleGroup(group);
		STARTNODE.setToggleGroup(group);
		WALLS.setToggleGroup(group);
		toggle.getChildren().addAll(ENDNODE,STARTNODE,WALLS);
		toggle.setSpacing(50);
		toggle.setAlignment(Pos.CENTER_LEFT);
		toggle.relocate(820, 350);
		Parent map = createMap();
		root.getChildren().addAll(map, toggle);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Parent createMap() {

        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(20);            
            main.getColumnConstraints().add(column);
        }
        
        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(20);
            main.getRowConstraints().add(row);
        }
        main.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
        for (int x = 0 ; x < columns ; x++) {
            for (int y = 0 ; y < rows ; y++) {
                main.add(createCell(table[x][y]), x, y);
            }
        }
        return main;
    }
	
    private StackPane createCell(ArrayList table) {

        StackPane cell = new StackPane();
        cell.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				cell.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
				System.out.println("ID: " + main.getColumnIndex(cell) + main.getRowIndex(cell));
			}
        	
        });
        	

        return cell;
    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
