package tournament;

import explorer.MapManager;
import explorer.WorldManager;
import match.BasicMatch;
import match.Match;
import model.configuration.Configuration;
import model.food.Food;
import model.foodGenerator.BasicFoodGenerator;
import model.foodGenerator.FoodGenerator;
import model.foodSpawner.BasicFoodSpawner;
import model.foodSpawner.FoodSpawner;
import model.helpers.BasicNameGenerator;
import model.helpers.NameGenerator;
import model.observer.BasicObserver;
import model.observer.MatchObserver;
import model.pimpek.Pimpek;
import model.pimpekCloner.BasicPimpekCloner;
import model.pimpekFactory.BasicPimpekFactory;
import model.pimpekCloner.PimpekCloner;
import model.pimpekGenerator.BasicPimpekGenerator;
import model.pimpekFactory.PimpekFactory;
import model.pimpekGenerator.PimpekGenerator;
import model.pimpekSpawner.BasicPimpekSpawner;
import model.pimpekSpawner.PimpekSpawner;
import model.pimpekStatistic.PimpekStatistics;
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
