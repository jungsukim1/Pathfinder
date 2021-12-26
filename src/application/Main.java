package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	int WINDOWHEIGHT = 400; 
	int WINDOWWIDTH = 600;
	int NUM_BUTTON_LINES = 10;
	int BUTTONS_PER_LINE = 10;
	
	private GridPane MAP = new GridPane();
	
	
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
                    }
                });
            }
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
