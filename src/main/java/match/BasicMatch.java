package match;

import world.Board;
import configuration.Configuration;
import observer.MatchObserver;
import pimpek.pimpekModel.Pimpek;
import parser.statisticsToPoints.StatisticToPoints;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BasicMatch implements Match {

    private final Configuration configuration;
    private final MatchObserver observer;
    private final Set<Pimpek> beings;
    private final Set<Pimpek> clonedBeings;
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
        this.clonedBeings = Collections.newSetFromMap(new ConcurrentHashMap<>());
        this.statisticToPoints = statisticToPoints;
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean registerClonedPlayer(Pimpek pimpek) {
        return clonedBeings.add(pimpek);
    }

    @Override
    public synchronized void run() {
        int maxTurns = configuration.getMaxTurns();

        while(turnCounter < maxTurns && observer.getLiving() > 0) {

            for (Pimpek being : beings) {
                try {
                    being.act();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            for (Pimpek cloned : clonedBeings) {
                try {
                    cloned.act();
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
