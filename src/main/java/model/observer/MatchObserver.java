package model.observer;

import model.pimpek.Pimpek;
import model.pimpekStatistic.PimpekStatistics;

import java.util.Map;

public interface MatchObserver {

    boolean clone(Pimpek pimpek);
    boolean registerEnergyPoints(Pimpek pimpek, int pointsToAdd);
    boolean registerDeath(Pimpek pimpek);
    Map<Pimpek,PimpekStatistics> getStatistics();
}
