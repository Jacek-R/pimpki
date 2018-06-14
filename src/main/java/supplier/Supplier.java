package supplier;

import configuration.Configuration;
import food.foodGenerator.FoodGenerator;
import food.foodModel.Food;
import food.foodSpawner.FoodSpawner;
import observer.MatchObserver;
import obstacle.obstacleSpawner.ObstacleSpawner;
import parser.statisticsToPoints.StatisticToPoints;
import pimpek.pimpekCloner.PimpekCloner;
import pimpek.pimpekFactory.PimpekFactory;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekSpawner.PimpekSpawner;
import world.BoardCreator;
import worldManager.WorldManager;

import java.util.Set;

public interface Supplier {

    Configuration getConfiguration();

    WorldManager getWorldManager();

    PimpekFactory getPimpekFactory();

    BoardCreator getBoardCreator();

    MatchObserver getMatchObserver();

    FoodGenerator getFoodGenerator();

    Set<Pimpek> getBeings();

    Set<Food> getSuplies();

    FoodSpawner getFoodSpawner();

    PimpekSpawner getPimpekSpawner();

    ObstacleSpawner getObstacleSpawner();

    PimpekCloner getPimpekCloner();

    StatisticToPoints getPointsParser();
}
