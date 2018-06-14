package match;

import world.Board;
import configuration.Configuration;
import observer.MatchObserver;
import pimpek.pimpekModel.Pimpek;
import parser.statisticsToPoints.StatisticToPoints;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class BasicMatch implements Match {

    private final Configuration configuration;
    private final MatchObserver observer;
    private final Set<Pimpek> beings;
    private final Set<Pimpek> clonedBeings;
    private final StatisticToPoints statisticToPoints;  // parser
    private final Board board;
    private int turnCounter;
    private final int TURN_INTERVAL = 100;

    public BasicMatch(Configuration configuration,
                      MatchObserver observer,
                      Set<Pimpek> beings, StatisticToPoints statisticToPoints,
                      Board board) {
        this.configuration = configuration;
        this.observer = observer;
        this.beings = beings;
        this.clonedBeings = new HashSet<>();
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
                wait(TURN_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            turnCounter++;
        }
    }

    @Override
    public MatchObserver getObserver() {
        return observer;
    }


}
