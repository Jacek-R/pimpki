package match;

import worldManager.WorldManager;
import configuration.Configuration;
import food.foodGenerator.FoodGenerator;
import food.foodSpawner.FoodSpawner;
import observer.MatchObserver;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekCloner.PimpekCloner;
import pimpek.pimpekSpawner.PimpekSpawner;
import pimpek.pimpekStatistic.PimpekStatistics;
import parser.statisticsToPoints.StatisticToPoints;
import world.BoardCreator;

import java.util.Map;
import java.util.Set;

public class BasicMatch implements Match {

    private final Configuration configuration;
    private final PimpekCloner pimpekCloner;
    private final FoodGenerator foodGenerator;
    private final PimpekSpawner pimpekSpawner;
    private final FoodSpawner foodSpawner;
    private final BoardCreator boardCreator;
    private final MatchObserver observer;
    private final WorldManager worldManager;
    private final Set<Pimpek> beings;
    private final StatisticToPoints statisticToPoints;  // parser

    public BasicMatch(Configuration configuration, PimpekCloner pimpekCloner,
                      FoodGenerator foodGenerator, PimpekSpawner pimpekSpawner,
                      FoodSpawner foodSpawner, BoardCreator boardCreator,
                      MatchObserver observer, WorldManager worldManager,
                      Set<Pimpek> beings, StatisticToPoints statisticToPoints) {
        this.configuration = configuration;
        this.pimpekCloner = pimpekCloner;
        this.foodGenerator = foodGenerator;
        this.pimpekSpawner = pimpekSpawner;
        this.foodSpawner = foodSpawner;
        this.boardCreator = boardCreator;
        this.observer = observer;
        this.worldManager = worldManager;
        this.beings = beings;
        this.statisticToPoints = statisticToPoints;
    }


    @Override
    public Map<Pimpek, PimpekStatistics> executeMatch() {


        // przygotowanie do meczu
        // tutaj kolejka :)
        // proponuję tu zwracać mapę z wynikami od observera (żeby wyżej budować całościowy wynik):

        return observer.getBeingsAndStats();
    }

    private void prepareMatch() {


    }
}
