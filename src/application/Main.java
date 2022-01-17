package application;

import java.util.Calendar;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	private int WINDOWHEIGHT = 800; 
	private int WINDOWWIDTH = 900; 
	private GridPane main = new GridPane();
	private int columns = 40;
	private int rows = 40;
	private int rowSelected = 0;
	private int colSelected = 0;
	private Rectangle[][] map = new Rectangle[columns][rows];
	private Points[][] nodes = new Points[columns][rows];
	private RadioButton ENDNODE = new RadioButton("End Node");
	private RadioButton STARTNODE = new RadioButton("Start Node");
	private RadioButton WALLS = new RadioButton("Walls");
	private Button START = new Button("Start search");
	private Label LABEL = new Label("Welcome to Pathfinding");
	private Timeline tl = new Timeline();
	private Algorithm aStar;
		
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		root.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
		VBox toggle = new VBox();
		ToggleGroup group = new ToggleGroup();
		ENDNODE.setToggleGroup(group);
		STARTNODE.setToggleGroup(group);
		WALLS.setToggleGroup(group);
		toggle.getChildren().addAll(STARTNODE, ENDNODE, WALLS, START, LABEL);
		toggle.setSpacing(50);
		toggle.setAlignment(Pos.CENTER_LEFT);
		toggle.relocate(810, 350);
		Parent map = createMap();
		startSearch();	
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
            	map[x][y] = draw(x, y);
                main.add(map[x][y], x, y);
                Points n = new Points(x, y);
                nodes[x][y] = n;

            }
        }
        return main;
    }
	
    private Rectangle draw(int x, int y) {

        Rectangle cell2 = new Rectangle(x, y, 20, 20);
        cell2.setFill(Color.WHITE);

            cell2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            	
    			@Override
    			public void handle(MouseEvent arg0) {
    				colSelected = main.getColumnIndex(cell2);
    				rowSelected = main.getRowIndex(cell2);
    				settingNode(arg0);
    			}
            	
            });

        return cell2;
    }
	public void settingNode (MouseEvent arg) {
    	if (ENDNODE.isSelected()) {
    		Points current = nodes[colSelected][rowSelected];
    		current.setType(2);
    		Rectangle red = map[colSelected][rowSelected];
    		red.setFill(Color.RED);
    		for (int i = 0; i < columns; i ++) {
    			for (int j = 0; j < rows; j ++) {
    				Points n = nodes[i][j];
    				int check = n.getType();
    				if (check == 2 && n != current) {   					
    		    		n.setType(0);
    		    		Rectangle last = map[i][j];
    		    		last.setFill(Color.WHITE);
    				}
    			}
    		}

    	}
    	else if(STARTNODE.isSelected()) {
    		Points current = nodes[colSelected][rowSelected];
    		current.setType(1);
    		Rectangle green = map[colSelected][rowSelected];
    		green.setFill(Color.GREEN);
    		for (int i = 0; i < columns; i ++) {
    			for (int j = 0; j < rows; j ++) {
    				Points n = nodes[i][j];
    				int check = n.getType();
    				if (check == 1 && n != current) {   					
    		    		n.setType(0);
    		    		Rectangle last = map[i][j];
    		    		last.setFill(Color.WHITE);
    				}
    			}
    		}

    	}
    	else if(WALLS.isSelected()){
    		Points n = nodes[colSelected][rowSelected];
    		n.setType(3);
    		Rectangle cell = map[colSelected][rowSelected];
    		cell.setFill(Color.BLACK);
    	}
    }
	
	public void startSearch() {
		START.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				aStar = new Algorithm(nodes);
				aStar.aStar();
				if(aStar.findingStart(nodes) == null) {
					LABEL.setText("NO START NODE FOUND! PLEASE SELECT A START NODE");
				}else if(aStar.findingTarget(nodes) == null) {
					LABEL.setText("NO END NODE FOUND! PLEASE SELECT AN END NODE");
				}
				if(aStar.getPath() == null) {
					LABEL.setText("NO PATH!");
				}else {
					for (Points path : aStar.getPath()) {
						Rectangle cell = map[path.getX()][path.getY()];
						cell.setFill(Color.BLUE);

					}
				}					
			}
						
		});
		
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
