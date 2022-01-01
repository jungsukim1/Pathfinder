package application;
	
import javafx.application.Application;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private int WINDOWHEIGHT = 800; 
	private int WINDOWWIDTH = 900;
	private int NUM_BUTTON_LINES = 32;
	private int BUTTONS_PER_LINE = 32;
	
	private GridPane MAP = new GridPane();
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
		GridPane main = new GridPane();
        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                //int number = NUM_BUTTON_LINES * r + c;
                Button button = new Button(); 
                button.setPrefSize(25, 20);
                main.add(button, c, r);
                button.setId(r+","+c);
                button.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        System.out.println("id: " + button.getId());
                        button.setStyle("-fx-background-color: RED");
                    }
                });
            }
        }
        return main;
    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
