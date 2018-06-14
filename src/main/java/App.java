import pimpek.pimpekModel.Angry;
import worldManager.MapManager;
import worldManager.WorldManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import configuration.Configuration;
import configuration.WorldConfiguration;
import food.foodModel.Apple;
import food.foodModel.Food;
import food.foodModel.Strawberry;
import food.foodSpawner.BasicFoodSpawner;
import food.foodSpawner.FoodSpawner;
import obstacle.obstacleSpawner.BasicObstacleSpawner;
import obstacle.obstacleSpawner.ObstacleSpawner;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekModel.SimplePimpek;
import pimpek.pimpekSpawner.BasicPimpekSpawner;
import pimpek.pimpekSpawner.PimpekSpawner;
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
        beings.add(new SimplePimpek("Tomek", 100, 120, worldManager));
        beings.add(new Angry("Henio", 100, 120, worldManager));
        Set<Food> foods = new HashSet<>();
        foods.add(new Apple());
        foods.add(new Strawberry());
        foods.add(new Apple());
        return new WorldCreator(worldManager, configuration, foodSpawner, pimpekSpawner, obstacleSpawner, beings, foods).create();
    }
}
