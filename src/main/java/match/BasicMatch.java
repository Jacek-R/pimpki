package match;

import world.Board;
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

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

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
    private final Board board;

    public BasicMatch(Configuration configuration, PimpekCloner pimpekCloner,
                      FoodGenerator foodGenerator, PimpekSpawner pimpekSpawner,
                      FoodSpawner foodSpawner, BoardCreator boardCreator,
                      MatchObserver observer, WorldManager worldManager,
                      Set<Pimpek> beings, StatisticToPoints statisticToPoints,
                      Board board) {
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
        this.board = board;
    }


    @Override
    public Map<Pimpek, PimpekStatistics> executeMatch() {

        int maxTurns = configuration.getMaxTurns();
        int interval = 1000;

        executeMatchRec(interval, maxTurns);
        return observer.getBeingsAndStats();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    private void executeMatchRec(int interval, int maxTurns) {

        new Timer().schedule(new TimerTask() {

            private int counter;

            @Override
            public void run() {
                for (Pimpek being : beings) {
                    try {
                        being.act();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    counter++;

                    if (counter < maxTurns && observer.getLiving() > 0) {
                        executeMatchRec(interval, maxTurns);
                    }
                }
            }
        }, interval);
    }
}
