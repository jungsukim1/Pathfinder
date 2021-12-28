package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private int WINDOWHEIGHT = 800; 
	private int WINDOWWIDTH = 800;
	private int NUM_BUTTON_LINES = 25;
	private int BUTTONS_PER_LINE = 20;
	
	private GridPane MAP = new GridPane();
	private Label LBLWALLS = new Label("Walls");
	private Label LBLSTARTNODE = new Label("Start Node");
	private Label LBLENDNODE = new Label("End Node");
	private RadioButton ENDNODE = new RadioButton();
	private RadioButton STARTNODE = new RadioButton();
	private RadioButton WALLS = new RadioButton();
	
	
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		root.getChildren().add(MAP);
		addButton();
		Scene scene = new Scene(root, WINDOWWIDTH, WINDOWHEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void addButton() {
//        Button b = new Button("b");
//        b.setTranslateX(-300 + 25);
//		b.setTranslateY(-200 + 25);
//		b.setPrefSize(25, 25);
//        MAP.add(b);
		
		
        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                //int number = NUM_BUTTON_LINES * r + c;
                Button button = new Button();            	
                button.setPrefSize(30, 30);
                MAP.add(button, c, r);
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
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
