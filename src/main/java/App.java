import explorer.MapManager;
import explorer.WorldManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.configuration.Configuration;
import model.configuration.WorldConfiguration;
import model.food.Food;
import model.foodSpawner.BasicFoodSpawner;
import model.foodSpawner.FoodSpawner;
import model.obstacleSpawner.BasicObstacleSpawner;
import model.obstacleSpawner.ObstacleSpawner;
import model.pimpek.Pimpek;
import model.pimpekSpawner.BasicPimpekSpawner;
import model.pimpekSpawner.PimpekSpawner;
import world.Board;
import world.WorldCreator;

import java.util.HashSet;
import java.util.Set;

public class App extends Application {

    void run(String... args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Super title");
        Board world =setConfigurationForWorldCreator();
        ScrollPane scrollPane = new ScrollPane(world.getGridPane());
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(scrollPane, 400, 400));
        primaryStage.show();
    }

    public Board setConfigurationForWorldCreator() throws Exception{
        Configuration configuration = new WorldConfiguration(10, 10, 3, 10, 120, 100);
        WorldManager worldManager = new MapManager();
        FoodSpawner foodSpawner = new BasicFoodSpawner(worldManager);
        PimpekSpawner pimpekSpawner = new BasicPimpekSpawner(worldManager);
        ObstacleSpawner obstacleSpawner = new BasicObstacleSpawner(worldManager);
        Set<Pimpek> beings = new HashSet<>();
        Set<Food> foods = new HashSet<>();
        return new WorldCreator(worldManager, configuration, foodSpawner, pimpekSpawner, obstacleSpawner, beings, foods).create();
    }
}
