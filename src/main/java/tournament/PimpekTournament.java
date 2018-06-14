package tournament;

import worldManager.MapManager;
import worldManager.WorldManager;
import match.BasicMatch;
import match.Match;
import configuration.Configuration;
import food.foodModel.Food;
import food.foodGenerator.BasicFoodGenerator;
import food.foodGenerator.FoodGenerator;
import food.foodSpawner.BasicFoodSpawner;
import food.foodSpawner.FoodSpawner;
import helpers.nameGenerator.BasicNameGenerator;
import helpers.nameGenerator.NameGenerator;
import observer.BasicObserver;
import observer.MatchObserver;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekCloner.BasicPimpekCloner;
import pimpek.pimpekFactory.BasicPimpekFactory;
import pimpek.pimpekCloner.PimpekCloner;
import pimpek.pimpekGenerator.BasicPimpekGenerator;
import pimpek.pimpekFactory.PimpekFactory;
import pimpek.pimpekGenerator.PimpekGenerator;
import pimpek.pimpekSpawner.BasicPimpekSpawner;
import pimpek.pimpekSpawner.PimpekSpawner;
import pimpek.pimpekStatistic.PimpekStatistics;
import parser.statisticsToPoints.PimpekPoints;
import parser.statisticsToPoints.StatisticToPoints;
import world.BoardCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PimpekTournament implements Tournament {

    private final Configuration configuration;
    private final WorldManager worldManager;
    private final PimpekFactory pimpekFactory;
    private final StatisticToPoints pointsParser;
    private final Map<Pimpek, PimpekStatistics> results;


    public PimpekTournament(Configuration configuration) {
        this.configuration = configuration;
        this.worldManager = createWorldManager();
        this.pimpekFactory = createFactory();
        this.pointsParser = createPointsParser();
        this.results = new HashMap<>();
    }


    @Override
    public void begin() {


        // Initiate dependencies:

        PimpekCloner cloner = createCloner();
        PimpekSpawner pimpekSpawner = createPimpekSpawner();
        PimpekGenerator pimpekGenerator = createPimpekGenerator();
        Set<Pimpek> contestants = pimpekGenerator.generate();
        MatchObserver observer = createObserver(cloner, pimpekSpawner, contestants);
        FoodGenerator foodGenerator = createFoodGenerator();
        Set<Food> food = foodGenerator.generate();
        FoodSpawner foodSpawner = createFoodSpawner();


        // tutaj produkcja:
//        BoardCreator boardCreator = new WorldCreator(................);

        BoardCreator boardCreator = null;  // temporary! :D


        // mamy gotowe i start:



        int matchQuantity = configuration.getMatchQuantity();
        Match match;
        Map<Pimpek, PimpekStatistics> matchResult;
        for (int i=0; i<matchQuantity; i++) {

            // initiate single match:
            match = new BasicMatch(configuration, cloner, foodGenerator, pimpekSpawner, foodSpawner, boardCreator,
                    observer, worldManager, contestants, pointsParser);

            // execute match, build result
            matchResult = match.executeMatch();

            // tu metoda, która by budowała globalny result z rezultatu danego meczu

            System.out.println(results);

        }


    }

    private StatisticToPoints createPointsParser() {
        return new PimpekPoints();
    }

    private MatchObserver createObserver(PimpekCloner pimpekCloner, PimpekSpawner pimpekSpawner, Set<Pimpek> pimpki) {

        return new BasicObserver(pimpekCloner, pimpekSpawner, pimpki);
    }

    private PimpekFactory createFactory() {

        NameGenerator nameGenerator = new BasicNameGenerator();
        return new BasicPimpekFactory(nameGenerator, configuration, worldManager);
    }

    private WorldManager createWorldManager() {

        return new MapManager();
    }

    private PimpekSpawner createPimpekSpawner() {

        return new BasicPimpekSpawner(worldManager);
    }

    private FoodSpawner createFoodSpawner() {

        return new BasicFoodSpawner(worldManager);
    }

    private PimpekCloner createCloner() {

        return new BasicPimpekCloner(pimpekFactory);
    }


    private PimpekGenerator createPimpekGenerator() {

        return new BasicPimpekGenerator(configuration, pimpekFactory);
    }

    private FoodGenerator createFoodGenerator() {

        return new BasicFoodGenerator(configuration);
    }





}
