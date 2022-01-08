package application;
	
import java.util.ArrayList;
import java.util.Collection;


import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
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
	private GridPane main = new GridPane();
	private int columns = 40;
	private int rows = 40;
	private int rowSelected = 0;
	private int colSelected = 0;
	//private ArrayList[][] table = new ArrayList[columns][rows];
	private StackPane[][] map = new StackPane[columns][rows];
	private Points[][] nodes = new Points[columns][rows];
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
		Algorithm start = new Algorithm(nodes);
		
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
            	map[x][y] = createCell();
                main.add(map[x][y], x, y);
                Points n = new Points(x, y);
                nodes[x][y] = n;

            }
        }
        return main;
    }
	
    private StackPane createCell() {

        StackPane cell = new StackPane();

            cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            	
    			@Override
    			public void handle(MouseEvent arg0) {
    				colSelected = main.getColumnIndex(cell);
    				rowSelected = main.getRowIndex(cell);
    				settingNode(arg0);
    			}
            	
            });

        return cell;
    }
	public void settingNode (MouseEvent arg) {
    	if (ENDNODE.isSelected()) {
    		Points current = nodes[colSelected][rowSelected];
    		current.setColor("RED");
    		current.setType(2);
    		StackPane red = map[colSelected][rowSelected];
    		red.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
    		for (int i = 0; i < columns; i ++) {
    			for (int j = 0; j < rows; j ++) {
    				Points n = nodes[i][j];
    				int check = n.getType();
    				if (check == 2 && n != current) {   					
    		    		n.setColor("WHITE");
    		    		n.setType(0);
    		    		StackPane last = map[i][j];
    		    		last.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    				}
    			}
    		}

    	}
    	else if(STARTNODE.isSelected()) {
    		Points current = nodes[colSelected][rowSelected];
    		current.setColor("GREEN");
    		current.setType(1);
    		StackPane green = map[colSelected][rowSelected];
    		green.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
    		for (int i = 0; i < columns; i ++) {
    			for (int j = 0; j < rows; j ++) {
    				Points n = nodes[i][j];
    				int check = n.getType();
    				System.out.println("Node: " + n.getX() + "," + n.getY() + "," + n.getType() + "," + n.getColor());
    				if (check == 1 && n != current) {   					
    		    		n.setColor("WHITE");
    		    		n.setType(0);
    		    		StackPane last = map[i][j];
    		    		last.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        				System.out.println("Node: " + n.getX() + "," + n.getY() + "," + n.getType() + "," + n.getColor());
    		    		System.out.println("Node: " + current.getX() + "," + current.getY() + "," + current.getType() + "," + current.getColor());
    				}
    			}
    		}

    	}
    	else if(WALLS.isSelected()){
    		Points n = nodes[colSelected][rowSelected];
    		n.setColor("BLACK");
    		n.setType(3);
    		StackPane cell = map[colSelected][rowSelected];
    		cell.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    	}
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
