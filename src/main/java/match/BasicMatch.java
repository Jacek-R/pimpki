package match;

import world.Board;
import worldManager.WorldManager;
import configuration.Configuration;
import observer.MatchObserver;
import pimpek.pimpekModel.Pimpek;
import parser.statisticsToPoints.StatisticToPoints;

import java.io.FileNotFoundException;
import java.util.Set;

public class BasicMatch implements Match {

    private final Configuration configuration;
    private final MatchObserver observer;
    private final Set<Pimpek> beings;
    private final StatisticToPoints statisticToPoints;  // parser
    private final Board board;
    private int turnCounter;

    public BasicMatch(Configuration configuration,
                      MatchObserver observer,
                      Set<Pimpek> beings, StatisticToPoints statisticToPoints,
                      Board board) {
        this.configuration = configuration;
        this.observer = observer;
        this.beings = beings;
        this.statisticToPoints = statisticToPoints;
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public synchronized void run() {
        int maxTurns = configuration.getMaxTurns();

        while(turnCounter < maxTurns && observer.getLiving() > 0) {
            for (Pimpek being : beings) {
                System.out.println("Jetsem w for, pimpek + " + being + " with location: " + being.getLocation());
                try {
                    being.act();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            try {
                wait(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            turnCounter++;
        }
    }
}
