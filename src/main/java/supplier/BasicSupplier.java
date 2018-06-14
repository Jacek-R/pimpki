package supplier;

import configuration.Configuration;
import food.foodGenerator.BasicFoodGenerator;
import food.foodGenerator.FoodGenerator;
import food.foodModel.Food;
import food.foodSpawner.BasicFoodSpawner;
import food.foodSpawner.FoodSpawner;
import helpers.nameGenerator.BasicNameGenerator;
import helpers.nameGenerator.NameGenerator;
import match.BasicMatch;
import match.Match;
import observer.BasicObserver;
import observer.MatchObserver;
import obstacle.obstacleSpawner.BasicObstacleSpawner;
import obstacle.obstacleSpawner.ObstacleSpawner;
import parser.statisticsToPoints.PimpekPoints;
import parser.statisticsToPoints.StatisticToPoints;
import pimpek.pimpekCloner.BasicPimpekCloner;
import pimpek.pimpekCloner.PimpekCloner;
import pimpek.pimpekFactory.BasicPimpekFactory;
import pimpek.pimpekFactory.PimpekFactory;
import pimpek.pimpekGenerator.BasicPimpekGenerator;
import pimpek.pimpekGenerator.PimpekGenerator;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekSpawner.BasicPimpekSpawner;
import pimpek.pimpekSpawner.PimpekSpawner;

import world.Board;
import world.BoardCreator;
import world.WorldCreator;
import worldManager.MapManager;
import worldManager.WorldManager;

import java.io.FileNotFoundException;
import java.util.Set;

public class BasicSupplier implements Supplier {

    private final Configuration configuration;
    private final WorldManager worldManager;
    private final PimpekFactory pimpekFactory;


    private final BoardCreator boardCreator;
    private final MatchObserver matchObserver;
    private final PimpekGenerator pimpekGenerator;
    private final FoodGenerator foodGenerator;

    private final Set<Pimpek> beings;
    private final Set<Food> supplies;

    private final FoodSpawner foodSpawner;
    private final PimpekSpawner pimpekSpawner;
    private final ObstacleSpawner obstacleSpawner;

    private final PimpekCloner pimpekCloner;

    private final StatisticToPoints pointsParser;

    public static Supplier getInstance(Configuration configuration) {
        return new BasicSupplier(configuration);
    }

    private BasicSupplier(Configuration configuration) {
        this.configuration = configuration;
        this.worldManager = createWorldManager();
        this.foodSpawner = createFoodSpawner();
        this.pimpekSpawner = createPimpekSpawner();
        this.obstacleSpawner = createObstacleSpawner();

        this.pimpekFactory = createPimpekFactory();

        this.pimpekGenerator = createPimpekGenerator();
        this.foodGenerator = createFoodGenerator();

        this.pimpekCloner = createCloner();

        this.beings = pimpekGenerator.generate();
        this.supplies = foodGenerator.generate();

        this.matchObserver = createObserver();

        this.pointsParser = createPointsParser();

        this.boardCreator = createWorldCreator();
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public WorldManager getWorldManager() {
        return worldManager;
    }

    @Override
    public PimpekFactory getPimpekFactory() {
        return pimpekFactory;
    }

    @Override
    public BoardCreator getBoardCreator() {
        return boardCreator;
    }

    @Override
    public MatchObserver getMatchObserver() {
        return matchObserver;
    }

    @Override
    public FoodGenerator getFoodGenerator() {
        return foodGenerator;
    }

    @Override
    public Set<Pimpek> getBeings() {
        return beings;
    }

    @Override
    public Set<Food> getSupplies() {
        return supplies;
    }

    @Override
    public FoodSpawner getFoodSpawner() {
        return foodSpawner;
    }

    @Override
    public PimpekSpawner getPimpekSpawner() {
        return pimpekSpawner;
    }

    @Override
    public ObstacleSpawner getObstacleSpawner() {
        return obstacleSpawner;
    }

    @Override
    public PimpekCloner getPimpekCloner() {
        return pimpekCloner;
    }

    @Override
    public StatisticToPoints getPointsParser() {
        return pointsParser;
    }

    @Override
    public Match getNewMatch() throws FileNotFoundException {
        return createMatch();
    }

    private Match createMatch() throws FileNotFoundException {

        Board board = boardCreator.create();
        worldManager.setBoard(board);
        matchObserver.reset();

        return new BasicMatch(configuration, pimpekCloner, foodGenerator, pimpekSpawner, foodSpawner, boardCreator,
                matchObserver, worldManager, beings, pointsParser, board);
    }

    private StatisticToPoints createPointsParser() {
        return new PimpekPoints();
    }

    private MatchObserver createObserver() {

        return new BasicObserver(pimpekCloner, pimpekSpawner, beings);
    }

    private PimpekFactory createPimpekFactory() {

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

    private ObstacleSpawner createObstacleSpawner() {

        return new BasicObstacleSpawner(worldManager);
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

    private BoardCreator createWorldCreator() {

        return new WorldCreator(worldManager, configuration, foodSpawner, pimpekSpawner,
                obstacleSpawner, beings, supplies);
    }


}
