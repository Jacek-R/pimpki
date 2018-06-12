import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Super title");
        HBox hBox = new HBox();
        ScrollPane scrollPane = new ScrollPane(hBox);

        primaryStage.setScene(new Scene(scrollPane, 300, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
