import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import world.Board;
import world.WorldCreator;

public class App extends Application {

    void run(String... args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Super title");
        Board world = new WorldCreator().create();
        ScrollPane scrollPane = new ScrollPane(world.getGridPane());
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(scrollPane, 400, 400));
        primaryStage.show();
    }
}
